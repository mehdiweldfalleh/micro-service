# micro-service

Projet de Voyage Microservices est une application qui permet d'aider les entreprises à trouver des partenaires de voyage dans le monde professionnel, facilitant ainsi le développement de leurs activités en trouvant des partenaires actifs dans les domaines qui intéressent l'entreprise.


Ce projet a été réalisé par un groupe de 3 personnes, chacune étant responsable d'un microservice spécifique :

- Yasmine : Entreprise Server + Eureka + Docker + H2 + Zuul Gateway
- Mehdi : Client Server + Eureka + Docker + H2  + Zuul Gateway
- Senda : Voyage Server + Eureka + Docker + H2 + Zuul Gateway

## Avis des clients

L'application comprend également une fonctionnalité d'avis des clients développée avec Node.js et MongoDB.


![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/cbd3f094-59c5-4dcc-82d8-8ffc82c2f30a)

![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/c95c8acb-cc77-4769-9394-0a4012c64e77)

````
node ./server.js

````

## Technologies Utilisées
- Visual Studio Code
- Java 8
- Node.js
- MongoDB
- H2 Database
- Docker
## Prérequis

Avant de pouvoir exécuter le projet, assurez-vous d'avoir installé les éléments suivants :

- Java Development Kit (JDK) 8
- Visual Studio Code
- Docker
- MongoDB (pour le service d'avis des clients)

## Installation et exécution

1. Clonez le dépôt GitHub :

   ```shell
   git clone [https://github.com/votre-nom/projet-voyage-microservices.git](https://github.com/mehdiweldfalleh/micro-service.git)


2. Ouvrez chaque microservice dans Visual Studio Code et installez les dépendances nécessaires.

3. Pour chaque microservice, exécutez la commande suivante dans le terminal :

mvn spring-boot:run

4. Accédez à l'URL http://localhost:8761 pour accéder au tableau de bord Eureka et vérifier que tous les microservices sont enregistrés et en cours d'exécution.



5. Accédez à l'URL http://localhost:8762/nom-service/path  ( exemple : http://localhost:8762/entreprise-service/entreprise/retreiveAllEntreprises )  pour accéder a Zuul Gateway.



6. Pour exécuter le service d'avis des clients, assurez-vous d'avoir MongoDB installé et exécutez la commande suivante dans le terminal :

cd avis
npm install
node server.js

# Description du pattern

![image](https://github.com/sendanemissi/VoyageAffaire/assets/86804472/f2c9acc3-a347-4b2e-baac-86cbb60d6f81)

Spring Cloud Config gère les données de configuration des applications via un service centralisé, de sorte que les données de configuration de nos environnements soient clairement séparées de notre microservice déployé. Cela garantit que, peu importe le nombre d'instances de microservices que nous avons lancées, elles auront toujours la même configuration. 

# Configuration de Spring Cloud Config

````shell
<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-config-server</artifactId>
</dependency>
````
````
#eureka registration
spring.application.name=config-server
server.port=8889
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
eureka.instance.prefer-ip-address=true
#eureka.server.wait-time-in-ms-when-sync-empty=5
#eureka.client.register-with-eureka=true
spring.profiles.active=native
#spring.cloud.config.server.native.searchLocations=file://${user.home}/centralRepo
spring.cloud.config.server.native.searchLocations=./src/main/resources/centralRepo
````
````java
@SpringBootApplication
@EnableConfigServer
public class ConfigserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigserverApplication.class, args);
    }
}
````

# Spring Cloud/Netflix Eureka

- La découverte de services est essentielle pour les microservices pour deux raisons principales. Elle offre à l'application la possibilité d'augmenter rapidement le nombre d'instances de service exécutées dans un environnement. Les consommateurs de services sont abstraits de l'emplacement physique du service via la découverte de services. Étant donné que les consommateurs de services ne connaissent pas l'emplacement physique des instances de service réelles, de nouvelles instances de service peuvent être ajoutées ou supprimées du pool de services disponibles.

- Le deuxième avantage de la découverte de services est qu'elle contribue à augmenter la résilience des applications. Lorsqu'une instance de microservice devient instable ou indisponible, la plupart des moteurs de découverte de services la suppriment de la liste interne des services disponibles. Les dommages causés par un service indisponible seront minimisés, car le moteur de découverte de services acheminera les appels aux services disponibles

````shell
<!-- Les dépendances de Eureka client (message service) -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
````

````
spring.application.name = eureka
server.port = 8761
#ne pas enregistrer les microservices qui ne sont pas métier
eureka.client.register-with-eureka=false
#ne pas garder les microservices qui sont arréter
eureka.client.fetch-registry=false
````

````java
@SpringBootApplication
@EnableEurekaServer
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
````
## Configuration de Eureka client

````shell
<!-- Les dépendances de Eureka client (message service) -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
````
````
spring.application.name=entreprise-service
server.port=8099
#Eeureka server url
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true

eureka.client.service-url.defaultZone=http://localhost:8761/eureka

````

````java
@SpringBootApplication
@EnableEurekaClient
public class EntrepriseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrepriseServiceApplication.class, args);
	}

}
````
## Exemple d'interaction

En cas d'appel à http://localhost:8761, on voit que Entreorise service a été enregistré auprès d'Eureka.

![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/007c3886-235a-4b61-b385-539a31edfe49)

# Passerelle de services – Spring Cloud/Netflix Zuul

Dans une architecture distribuée comme celle des microservices une passerelle de services sert d'intermédiaire entre un service et le client qui l'appelle. Le client ne parle qu'à une seule URL gérée par la passerelle de services. La passerelle de services distingue le chemin provenant de l'appel du client et détermine le service que le client tente d'invoquer.

![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/a20faf3f-fcae-45e8-8193-20a819680785)


## Configuration de Zuul

````shell
<!-- Les dépendances de Zuul -->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-zuul</artifactId>
</dependency>
````
````
spring.application.name=zuul-gateway
server.port=8762
eureka.instance.prefer-ip-address=true 
eureka.client.register-with-eureka=true 
eureka.client.fetchRegistry=true
eureka.client.serviceUrl.defaultZone=${EUREKA_URI:http://localhost:8761/eureka}
zuul.host.socket-timeout-millis: 30000 
zuul.routes.client-service.path=/client-service/*
zuul.routes.client.serviceId=client-service
zuul.routes.entreprise-service.path=/entreprise-service
zuul.routes.entreprise.serviceId=entreprise-service/*
#zuul.routes.reclamation-service.path=/users
#zuul.routes.reclamation.serviceId=/users/*
spring.security.user.name=user
spring.security.user.password=user  
````

````java
@Configuration
public class FeignConfig {
@Bean
public BasicAuthRequestInterceptor mbBasicAuthRequestInterceptor(){
    return new BasicAuthRequestInterceptor("user","user");
}    
}
````

- Spring Cloud facilite la création d'une passerelle de services.
- La passerelle de services Zuul s'intègre au serveur Eureka de Netflix et peut automatiquement mapper les services enregistrés auprès d'Eureka sur une route Zuul.
- Zuul peut préfixer tous les itinéraires gérés, afin que vous puissiez facilement préfixer vos itinéraires avec quelque chose comme /api.
- En utilisant le serveur Spring Cloud Config, vous pouvez recharger dynamiquement les mappings de route sans avoir à redémarrer le serveur Zuul.



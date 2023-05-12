# micro-service

Projet de Voyage Microservices est une application qui permet d'aider les entreprises à trouver des partenaires de voyage dans le monde professionnel, facilitant ainsi le développement de leurs activités en trouvant des partenaires actifs dans les domaines qui intéressent l'entreprise.


Ce projet a été réalisé par un groupe de 3 personnes, chacune étant responsable d'un microservice spécifique :

- Yasmine : Entreprise Server + Eureka + Docker + Mysql + Zuul Gateway
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

![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/64befd5a-df16-4ca7-b566-b71f019d030a)


5. Accédez à l'URL http://localhost:8762/nom-service/path  ( exemple : http://localhost:8762/entreprise-service/entreprise/retreiveAllEntreprises )  pour accéder a Zuul Gateway.
http://localhost:8762/login
username:user
password:user
![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/fcb40f3e-de9b-48d5-b1ed-c04b9064a2b1)
![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/c5e65fe8-df80-4167-9e1e-99732f740f4f)



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

# Docker Compose 
![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/3b542eab-a27d-4e0f-93af-6c7e94254060)

- Docker Compose est un outil permettant de définir et de gérer des applications multi-conteneurs. Il utilise un fichier YAML pour décrire les services, les réseaux et les volumes nécessaires à votre application. Chaque service représente un conteneur Docker, qui peut être une partie de votre application ou un service tiers.

- Dans le cadre de notre projet de microservices avec Eureka, Docker Compose est utilisé pour orchestrer les différents services. Eureka est un service de découverte de services développé par Netflix, qui permet aux microservices de s'enregistrer et de découvrir les autres services disponibles dans l'environnement.

- Le fichier docker-compose.yml fourni dans ce référentiel définit 5 services :

````yml
version: "3.9"

services:
 
 eurekaserver:
   build: C:\Users\yariahi\Desktop\micro-service\eureuka
   container_name: eureka
   image: eureka-images
   networks:
      - eureka-server
   ports:
    - "8761:8761"

 zuul-gateway:
   build: C:\Users\yariahi\Desktop\micro-service\Zuul-Gateway\Zuul-Gateway
  #  network_mode: host 
   image: zuul-images
   ports:
   - "8762:8762"
   networks:
      - eureka-server
   environment:
    - eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka
   depends_on:
     - eurekaserver

 microservice-voyage:
   build: C:\Users\yariahi\Desktop\micro-service\Voyage
  #  network_mode: host 
   image: voyage-images
   ports:
   - "8085:8085"
   networks:
      - eureka-server
   environment:
    - eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka
   depends_on:
     - eurekaserver

 microservice-client:
   build: C:\Users\yariahi\Desktop\micro-service\Client
  #  network_mode: host 
   image: client-images
   ports:
   - "8090:8090"
   networks:
      - eureka-server
   environment:
    - eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka
   depends_on:
     - eurekaserver
  
 microservice-entreprise:
   build: C:\Users\yariahi\Desktop\micro-service\Entreprise
  #  network_mode: host 
   image: entreprise-images
   ports:
   - "8099:8099"
   networks:
      - eureka-server
   environment:
    - eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka
   depends_on:
     - eurekaserver

networks:
  eureka-server:
    name: eureka-server
    driver: bridge

````


Pour lancer l'application, exécutez la commande docker-compose up à partir du répertoire racine du projet. Docker Compose se chargera de créer les conteneurs Docker pour chaque service, de les connecter au réseau approprié et de gérer les volumes et les dépendances définis dans le fichier.

Assurez-vous d'avoir Docker et Docker Compose installés sur votre machine avant d'exécuter l'application.

![image](https://github.com/mehdiweldfalleh/micro-service/assets/86804472/3f53cb61-6648-4b3c-9108-8b0ff9209564)

# MySQL et Configuration dans Docker Compose
Ce projet utilise Docker Compose pour gérer le déploiement d'une architecture de microservices avec une base de données MySQL. Docker Compose facilite la conteneurisation et l'orchestration des différents services nécessaires à l'application.

## Prérequis
Avant d'exécuter l'application, assurez-vous d'avoir Docker et Docker Compose installés sur votre machine. Vous pouvez trouver les instructions d'installation pour Docker ici et pour Docker Compose ici.

## Configuration de MySQL
Le fichier docker-compose.yml du projet contient la configuration du service MySQL (mysql-db) utilisé par le service entreprise-service. Par défaut, le service MySQL est configuré avec les paramètres suivants :

Version MySQL : latest
Nom de la base de données : entreprise_db
Nom d'utilisateur : root
Mot de passe : MYSQL_ROOT_PASSWORD (défini comme une variable d'environnement)
Vous pouvez personnaliser ces paramètres selon vos besoins. Assurez-vous de conserver les variables d'environnement et les volumes nécessaires si vous modifiez la configuration.

## Exécution de l'application
Pour exécuter l'application, suivez les étapes suivantes :

Ouvrez un terminal ou une invite de commande.

Accédez au répertoire du projet.

Exécutez la commande suivante pour démarrer l'application à l'aide de Docker Compose :

````shell
docker-compose up
````

Docker Compose construira et démarrera les conteneurs définis dans le fichier docker-compose.yml. Vous verrez des journaux indiquant l'avancement de chaque service.

Une fois que tous les services sont en cours d'exécution, vous pouvez accéder au service entreprise-service via le port spécifié. Référez-vous à la documentation spécifique du service pour plus d'informations sur l'accès et l'interaction avec les microservices.



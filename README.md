# micro-service

Projet de Voyage Microservices est une application qui permet d'aider les entreprises à trouver des partenaires de voyage dans le monde professionnel, facilitant ainsi le développement de leurs activités en trouvant des partenaires actifs dans les domaines qui intéressent l'entreprise.

# Microservices

Ce projet a été réalisé par un groupe de 3 personnes, chacune étant responsable d'un microservice spécifique :

- Yasmine : Entreprise Server + Eureka + Docker + MySQL + Zuul Gateway
- Mehdi : Client Server + Eureka + Docker + H2 DB + Zuul Gateway
- Senda : Voyage Server + Eureka + Docker + SQL + Zuul Gateway

# Avis des clients

L'application comprend également une fonctionnalité d'avis des clients développée avec Node.js et MongoDB.


De plus, nous avons développé un service permettant aux clients de donner leur avis sur les partenaires de voyage, utilisant Node.js et MongoDB.

## Technologies Utilisées
- Visual Studio Code
- Java 8
- Node.js
- MongoDB
- MySQL
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
   git clone https://github.com/votre-nom/projet-voyage-microservices.git


2. Ouvrez chaque microservice dans Visual Studio Code et installez les dépendances nécessaires.

3. Pour chaque microservice, exécutez la commande suivante dans le terminal :

mvn spring-boot:run

4. Accédez à l'URL http://localhost:8761 pour accéder au tableau de bord Eureka et vérifier que tous les microservices sont enregistrés et en cours d'exécution.

5. Pour exécuter le service d'avis des clients, assurez-vous d'avoir MongoDB installé et exécutez la commande suivante dans le terminal :

cd avis-des-clients
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

# Exemple de Postman

# Spring Cloud/Netflix Eureka


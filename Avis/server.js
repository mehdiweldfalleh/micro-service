const express = require("express");
const mongoose = require("mongoose");
const MongoClient = require('mongodb').MongoClient;

const Avis = require("./models/avis")
const bodyParser = require("body-parser")
const Eureka = require('eureka-js-client').Eureka
const eurekaHost = (process.env.EUREKA_CLIENT_SERVICEURL_DEFAULTZONE || '127.0.0.1');
const hostName = (process.env.HOSTNAME || 'localhost')
const ipAddr = '127.0.0.1';
const app = express();
// const mongodbUrl = 'mongodb://localhost:27017/avis';
const port_local = 5505
const  port_eureuka = 8761;

// Configuration du serveur Express
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// Configuration de la connexion à MongoDB
const mongoURL = 'mongodb://127.0.0.1:27017/avis'; // Remplace avec l'URL de connexion de ta base de données MongoDB
//const dbName = 'avis'; // Remplace avec le nom de ta base de données

mongoose.connect(mongoURL, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => {
    console.log('Connexion à MongoDB réussie !');
    // Ici, tu peux démarrer ton serveur ou effectuer d'autres opérations après la connexion réussie
  })
  .catch((error) => {
    console.error('Erreur de connexion à MongoDB :', error);
  });
//listener port config
app.listen(port_local, () => { console.log("server starting") })

const client = new Eureka({
    instance: {
      app: 'avis-service',
      instanceId: 'avis-service',
      hostName: hostName,
      ipAddr: ipAddr,
      port: {
        '$': port_local,
        '@enabled': 'true',
      },
      vipAddress: "avis-service",
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
    },
    //retry 10 time for 3 minute 20 seconds.
    eureka: {
      host: eurekaHost,
      port: port_eureuka,
      servicePath: '/eureka/apps/',
      maxRetries: 10,
      requestRetryDelay: 2000,
    },
  })

//start register
client.logger.level('debug');
client.start((error) => {
    console.log(error || 'complete');
});

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))

// parse application/json
app.use(bodyParser.json())


//get all avis
app.get('/allAvis', async (req, res) => {
    try {
        await Avis.find({})
            .then(result => {
                res.send(result)
            })
    } catch (error) {
        console.log(error)
    }
})


//add avis
app.post("/addAvis", async (req, res) => {
    try {
        let newAvis = new Avis({
            id: req.body.id,
            commentaire: req.body.commentaire,
            rate: req.body.rate
        })
        await newAvis.save().then(result => {
            res.send(result)
        })
    } catch (error) {
        console.log(error)

    }
})

//delete avis
app.delete("/deleteAvis/:id", async (req, res) => {
    try {
        if (await Avis.findOneAndDelete({ id: req.params.id })){
            res.send("Avis deleted")
        } else {
            res.send("Avis does not exist")
        }
        
    } catch (error) {
        res.send("error");
    }
})

//update avis
app.put("/updateAvis/:id", async (req, res) => {
    try {
        await Avis.findByIdAndUpdate({_id: req.params.id }, {
            id: req.body.id,
            commentaire: req.body.commentaire,
            rate: req.body.rate
        }).then(result => {
            res.send(result)
        })
    } catch (error) {
        res.send(error)
    }
})


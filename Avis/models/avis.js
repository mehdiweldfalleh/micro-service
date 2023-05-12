const mongoose = require("mongoose");

const avisSchema = mongoose.Schema({
    id: {
        type: String,
        require: false,
    },
    commentaire: {
        type: String,
        require: false,
    },
    rate: {
        type: Number,
        require: false,
    }
})

module.exports = Avis = mongoose.model("avis", avisSchema)
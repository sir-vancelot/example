package com.batman.example.domain.datastore

interface Datastore <Model> {
    /*
       Setup a cache to prevent constant queries against the db
    */
    var cache: ArrayList<Model>

    // <editor-fold desc="Initialize the cache">
    fun initialize()
    // </editor-fold>

    // <editor-fold desc="Create a row in the database and return the model">
    fun create(model: Model)
    // </editor-fold>

    // <editor-fold desc="Read all of the rows from the database and return the models"
    fun read(): ArrayList<Model>
    // </editor-fold>

    // <editor-fold desc="Update the database row corresponding to the provided model">
    fun update(model: Model)
    // </editor-fold>

    // <editor-fold desc="Delete the database row corresponding to the provided model">
    fun delete(model: Model)
    // </editor-fold>
}
package com.batman.example.infrastructure.datastore

interface Datastore <Model> {
    fun create(): Model
    fun read(): ArrayList<Model>
    fun update(model: Model)
    fun delete(model: Model)
}
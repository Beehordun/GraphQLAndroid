package com.example.androidgraphql

interface Bus {
    fun isRegistered(o: Any): Boolean

    fun register(o: Any)

    fun unregister(o: Any)

    fun post(event: Any)
}
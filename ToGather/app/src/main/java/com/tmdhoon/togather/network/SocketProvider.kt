package com.tmdhoon.togather.network

import io.socket.client.IO
import io.socket.client.Socket

object SocketProvider {
    private lateinit var socket : Socket
    fun getSocket() : Socket{
        socket = IO.socket("http://52.55.240.35:8081")
        return socket
    }
}
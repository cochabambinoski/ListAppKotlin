package com.example.genremusicians

import android.content.Context
import android.os.Looper
import android.os.Message
import android.util.Log
import java.util.logging.Handler

class PseudoMessagingManager (private val context:Context){
    private val tag = this::class.simpleName

    private val connectionCallbackMiliseconds = 5000L
    private val postHandler = android.os.Handler(Looper.getMainLooper())

    fun connect(connectionCallBack: (PseudoMessagingConnection) -> Unit) {
        Log.d(tag,"Initiating connection")
        postHandler.postDelayed(
            {
                Log.d(tag,"Connection established")
                connectionCallBack(PseudoMessagingConnection())
            },
            connectionCallbackMiliseconds
        )
    }
}

class PseudoMessagingConnection{
    private val tag = this::class.simpleName

    fun send(message: String){
        Log.d(tag, message)
    }

    fun disconnect() {
        Log.d(tag,"Disconnected")
    }
}
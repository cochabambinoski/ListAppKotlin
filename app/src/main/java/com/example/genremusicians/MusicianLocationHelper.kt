package com.example.genremusicians

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.genremusicians.Models.MusicianInfo
import java.sql.Connection

class MusicianLocationHelper(val context: Context, val lifecycle:Lifecycle):LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }
    val tag = this::class.simpleName
    var currentLat = 0.0
    var currentLon = 0.0

    val locManager = PseudoLocationManager(context) { lat, lon ->
        currentLat = lat
        currentLon = lon
        Log.d(tag,"Location Callback Lat:$currentLat lon: $currentLon")
    }

    fun sendMessage(musician: MusicianInfo){
        val getTogetherMessage = "$currentLat|$currentLon|${musician.name}"
        msgConnection?.send(getTogetherMessage)
    }

    val msgManager = PseudoMessagingManager(context)
    var msgConnection : PseudoMessagingConnection? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumeHandler(){
        Log.d(tag, "resumeHandler")
        locManager.start()
        msgManager.connect { connection ->
            Log.d(tag, "CallBack Connection - Lifecycle state: ${lifecycle.currentState}")
            if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
                msgConnection = connection
            } else {
                connection.disconnect()
            }

        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseHandler(){
        Log.d(tag, "pausedHandler")
        locManager.stop()
        msgConnection?.disconnect()
    }
}
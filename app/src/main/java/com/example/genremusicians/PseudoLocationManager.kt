package com.example.genremusicians

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log

class PseudoLocationManager(private val content:Context, private val callback:(Double,Double)->Unit):Runnable {
    private val tag = this::class.simpleName
    private val lats = doubleArrayOf(
        40.9,
        168.9,
        59.741
    )
    private  val longs :DoubleArray = doubleArrayOf(
        40.9,
        168.9,
        59.741
    )
    private var locationIndex = 0
    private val callbackMiliseconds = 300L

    private var enable = false
    private val postHandler = Handler(Looper.getMainLooper())

    fun start(){
        enable = true
        Log.d(tag, "Location manager started")
        triggerCallbackAndScheduleNext()
    }

    fun stop(){
        enable = false
        postHandler.removeCallbacks(this)
        Log.d(tag, "Location manager stopped")
    }

    private fun triggerCallbackAndScheduleNext(){
        callback(lats[locationIndex], longs[locationIndex])
        locationIndex = (locationIndex + 1) % lats.size
        if (enable)
            postHandler.postDelayed(this, callbackMiliseconds)
    }

    override fun run() {
        triggerCallbackAndScheduleNext()
    }
}
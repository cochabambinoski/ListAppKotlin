package com.example.genremusicians.services

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.os.ResultReceiver
import android.util.Log
import com.example.genremusicians.MyServicesActivity
import java.lang.Exception

class MyIntentService() : IntentService("MyWorkerThread") {
    val TAG = MyIntentService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"onCreate, Thread name" + Thread.currentThread().name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy, Thread name" + Thread.currentThread().name)
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG,"onHandleIntent, Thread name" + Thread.currentThread().name)
        val sleepTime = intent?.getIntExtra("sleepTime",1)!!
        val resultReceiver = intent?.getParcelableExtra<ResultReceiver>("receiver")
        var ctr = 1
        while (ctr <= sleepTime){
            Log.i(TAG,"Counter is now, $ctr")
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {

            }
            ctr++
        }
        var myBundle = Bundle()
        myBundle.putString("resultIntentService","Counter stopped at $ctr seconds")
        resultReceiver?.send(18, myBundle)
    }
}
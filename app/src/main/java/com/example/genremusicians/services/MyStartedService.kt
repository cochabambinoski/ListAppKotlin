package com.example.genremusicians.services

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class MyStartedService: Service(){

    val TAG = MyStartedService::class.java.simpleName

    override fun onBind(p0: Intent?): IBinder? {
        Log.i(TAG,"on bind, Thread name" + Thread.currentThread().name)
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"onCreate, Thread name" + Thread.currentThread().name)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG,"onStartCommand, Thread name" + Thread.currentThread().name)
        val sleepTime = intent?.getIntExtra("sleepTime",1)
        MyAsyncTask().execute(sleepTime)
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy, Thread name" + Thread.currentThread().name)
    }

    inner class MyAsyncTask : AsyncTask<Int,String,String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            Log.i(TAG,"onDestroy, Thread name" + Thread.currentThread().name)
        }

        override fun doInBackground(vararg params: Int?): String {
            Log.i(TAG,"onDestroy, Thread name" + Thread.currentThread().name)
            val sleepTime = params[0]!!
            var ctr = 1
            while (ctr <= sleepTime){
                publishProgress("Counter is now $ctr")
                try {
                    Thread.sleep(1000)
                } catch (e:Exception) {

                }
                ctr++
            }
            return "Counter stopped at $ctr seconds"
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
        //    Toast.makeText(MyStartedService@this, values[0],Toast.LENGTH_SHORT)
            Log.i(TAG,"Counter value ${values[0]}" + Thread.currentThread().name)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            stopSelf()
            Log.i(TAG,"onDestroy, Thread name" + Thread.currentThread().name)
            val intent = Intent("action.service.to.activity")
            intent.putExtra("startServiceResult",result)
            sendBroadcast(intent)
        }
    }

}
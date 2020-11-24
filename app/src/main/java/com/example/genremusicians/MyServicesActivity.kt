package com.example.genremusicians

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.genremusicians.databinding.ActivityMyServicesBinding
import com.example.genremusicians.services.MyIntentService
import com.example.genremusicians.services.MyStartedService

class MyServicesActivity : AppCompatActivity() {

    lateinit var binding:ActivityMyServicesBinding
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMyServicesBinding>(this, R.layout.activity_my_services)

        binding.buttonStartService.setOnClickListener {
            Intent(this,MyStartedService::class.java).also {intent ->
                intent.putExtra("sleepTime", 10)
                startService(intent)
            }
        }

        binding.buttonStopService.setOnClickListener {
            Intent(this,MyStartedService::class.java).also {intent ->
                stopService(intent)
            }
        }

        binding.buttonStartIntentService.setOnClickListener() {
            Intent(this,MyIntentService::class.java).also { intent ->
                val myReceiver = MyResultReceiver(null)
                intent.putExtra("sleepTime", 10)
                intent.putExtra("receiver",myReceiver)
                startService(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("action.service.to.activity")
        registerReceiver(myStartedServiceReceiver,intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(myStartedServiceReceiver)
    }

    private val myStartedServiceReceiver= object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i("MyResultReceiver",Thread.currentThread().name)
            binding.textViewStartService.setText(intent?.getStringExtra("startServiceResult"))
        }

    }

    inner class MyResultReceiver(handler: Handler?) : ResultReceiver(handler){
        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)

            Log.i("MyResultReceiver",Thread.currentThread().name)

            if(resultCode == 18 && resultData != null){
                val result = resultData.getString("resultIntentService")
                handler.post(Runnable {
                    Log.i("MyResultReceiver",Thread.currentThread().name)
                    binding.textViewIntentService.setText(result)
                })
            }
        }
    }
}
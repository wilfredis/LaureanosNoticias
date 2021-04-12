package com.wilfredis.laureanosnoticias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import network.NotiApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var textData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textData = findViewById(R.id.txtData)

        getnoti()
    }

    private fun getnoti(){

        NotiApi.restrofitServices.getProperties().enqueue(
                object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                       textData.text = response.body()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        textData.text = "Err: " + t.message
                    }
                }
        )
      textData.text = "Respuesta"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
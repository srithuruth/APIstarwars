package com.example.kathishan.apistarwars

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = retrofitBuilder
            .client(okHttpClient)
            .build()

        val starwarClient = retrofit.create(StarwarServices::class.java)

        btnSubmit.setOnClickListener {
            starwarClient.getUserDetails(etInput.text.toString()).enqueue(object : Callback<StarwarCharacter> {
                override fun onFailure(call: Call<StarwarCharacter>, throwable: Throwable) {
                    throwable.printStackTrace()
                }

                override fun onResponse(call: Call<StarwarCharacter>, response: Response<StarwarCharacter>) {
                    if (response.isSuccessful) {
                        val starwarCharacter = response.body()
                        tvStatus.text = starwarCharacter?.name
                    }
                }
            })
        }



    }
}

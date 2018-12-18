package com.example.kathishan.apistarwars

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path



interface StarwarServices {
    @GET("{user}")
    fun getUserDetails(@Path("user") userName: String): Call<StarwarCharacter>
}
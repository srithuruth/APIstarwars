package com.example.kathishan.apistarwars

import com.google.gson.annotations.SerializedName


data class StarwarCharacter(val name: String,
                      val height: String,
                      val gender: String,
                      @SerializedName("node_id")val nodeId: String)
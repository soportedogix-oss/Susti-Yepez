package com.idat.appyepezjorge.api

import com.idat.appyepezjorge.model.ProductoResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun obtenerProductos(): Call<ProductoResponse>
}
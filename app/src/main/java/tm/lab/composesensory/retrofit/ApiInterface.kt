package tm.lab.composesensory.retrofit

import android.graphics.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("static/json/table.json")
    fun getTable() : Call<List<String>>

    @GET("db")
    fun getDB() : Call<Array<List<String>>>

    companion object {

        var BASE_URL = "https://10.7.38.161:5000/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}
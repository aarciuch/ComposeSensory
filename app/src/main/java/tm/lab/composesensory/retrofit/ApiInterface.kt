package tm.lab.composesensory.retrofit

import android.content.Context
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://developer.android.com/privacy-and-security/security-config#TrustingAdditionalCas


interface ApiInterface {

    @GET("table.json")
    fun getTable() : Call<List<String>>

    companion object {

        //var BASE_URL = "http://10.7.38.161:5000/static/json/"
        var BASE_URL = "https://10.7.38.161:5000/static/json/"

        fun create(context: Context): ApiInterface {


            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}
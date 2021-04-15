package network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val Base_url = "https://newsapi.org/v2/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Base_url)
    .build()


interface NoticiaApiService{
   @GET("top-headlines?sources=bbc-news&apiKey=8512cd4ad18b4466a107bdceda66fce6")
    fun getProperties(): Call<Notiproperties>
}

object  NotiApi{
    val restrofitServices: NoticiaApiService by lazy { retrofit.create(NoticiaApiService::class.java) }
}

package network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val Base_url = "https://newsapi.org"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Base_url)
    .build()


interface NoticiaApiService{
   @GET("/v2/top-headlines?sources=bbc-news&apiKey=8512cd4ad18b4466a107bdceda66fce6")
   // @GET("v2/top-headlines?country=" + @contry + "jp&apiKey=8512cd4ad18b4466a107bdceda66fce6")
    fun getProperties(): Call<String>
}

object  NotiApi{
    val restrofitServices: NoticiaApiService by lazy { retrofit.create(NoticiaApiService::class.java) }
}

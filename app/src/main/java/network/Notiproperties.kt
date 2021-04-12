package network

import com.squareup.moshi.Json

data class Notiproperties(
val id : String,
val type: String,
val cuerpo2: Notiproperties2,
@Json(name = "img_src")
val imgSrcUrl: String
)

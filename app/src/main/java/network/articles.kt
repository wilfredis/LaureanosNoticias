package network

import com.squareup.moshi.Json

data class articles(
        val source: Source,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        @Json(name = "urlToImagen")
        val urlToImagen: String,
        val publishedAt: String,
        val content: String
)

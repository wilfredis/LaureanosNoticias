package com.wilfredis.laureanosnoticias

import android.app.VoiceInteractor
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import network.NotiApi
import network.Notiproperties
import network.articles
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var noti_Data: RecyclerView
    lateinit var txtData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtData = findViewById(R.id.txtData)
        noti_Data = findViewById(R.id.noti_Data)
        noti_Data.layoutManager = LinearLayoutManager(this)
       // txtData.text = "lol" 
        getnoti()
    }

    private fun getnoti(){

        NotiApi.restrofitServices.getProperties().enqueue(
                object : Callback<Notiproperties>{

                    override fun onResponse(call: Call<Notiproperties>, response: Response<Notiproperties>) {
                        val noti =response.body()?.articles
                        txtData.text = noti?.size.toString();
                        if (noti != null) {
                                noti_Data.adapter = NotiAdapter(noti)
                        }

                    }

                    override fun onFailure(call: Call<Notiproperties>, t: Throwable) {
                    }
                }
        )
    }

    class NotiAdapter(var items: List<articles>) : RecyclerView.Adapter<NotiAdapter.ViewHolder>(){
        class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            var image : ImageView = itemView.findViewById(R.id.imageView)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var itemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false)
            return  ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val properties : articles = items[position]
            val imgUrl = properties.urlToImagen.toUri().buildUpon().scheme("https").build()
            Glide.with(holder.image.context)
                    .load(imgUrl)
                   .apply(RequestOptions().placeholder(R.drawable.cargando).error(R.drawable.nodisponible))
                    .into(holder.image)
        }

        override fun getItemCount(): Int {
           return items.size;
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
package com.wilfredis.laureanosnoticias

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import network.NotiApi
import network.Notiproperties
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var noti_Data: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noti_Data = findViewById(R.id.noti_Data)
        noti_Data.layoutManager = LinearLayoutManager(this)

        getnoti()
    }

    private fun getnoti(){

        NotiApi.restrofitServices.getProperties().enqueue(
                object : Callback<List<Notiproperties>>{

                    override fun onResponse(call: Call<List<Notiproperties>>, response: Response<List<Notiproperties>>) {
                     noti_Data.adapter = NotiAdapter(response.body()!!)
                      // textData.text = "Logrado ${response.body()?.size} propiedades"
                    }

                    override fun onFailure(call: Call<List<Notiproperties>>, t: Throwable) {
                      //  textData.text = "Err: " + t.message
                    }
                }
        )
      //textData.text = "Respuesta"
    }

    class NotiAdapter(var items: List<Notiproperties>) : RecyclerView.Adapter<NotiAdapter.ViewHolder>(){
        class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            var image : ImageButton = itemView.findViewById(R.id.imageView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var itemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false)
            return  ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val properties : Notiproperties = items[position]
            Glide.with(holder.image.context)
                    .load(properties.imgSrcUrl)
                    .into(holder.image)

            TODO("Not yet implemented")
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
package com.example.recipe.presentation.addrecipe.chooseimage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.net.Uri
import com.example.recipe.R
import com.example.recipe.utils.GlideApp

/**
 * Адаптер для отображения элементов списка
 */
class ImagesAdapter(private val list: List<Uri>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.choose_image_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        GlideApp.with(holder.itemView.context)
            .asBitmap()
            .load(list[position])
            .into(holder.icon)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val icon: ImageView = itemView.findViewById(R.id.icon)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(list[absoluteAdapterPosition])
        }
    }
}

/**
 * Интерфейс слушателя клика на элемент списка
 */
interface OnItemClickListener {
    /**
     * Обработка нажатия на элемент списка
     *
     * @param uri uri изображения
     */
    fun onClick(uri: Uri)
}

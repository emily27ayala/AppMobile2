package com.example.app.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.R


class PhotoAdapter(private var dataSet: List<Photo>, private var listener: ((Int)->Unit)? = null) :

    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.photo_name)
            imageView= view.findViewById(R.id.photo_img)
        }
    }

    fun updateList(list: List<Photo>){
        dataSet = list
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.photo_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val photo = dataSet[position]
        viewHolder.textView.text = photo.author
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
        Glide
            .with(viewHolder.itemView.context)
            .load(photo.download_url)
            .centerCrop()
            .into(viewHolder.imageView)

    }
    override fun getItemCount() = dataSet.size

}
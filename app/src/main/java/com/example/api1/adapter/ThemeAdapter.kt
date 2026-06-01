package com.example.api1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.api1.R
import com.example.api1.model.ThemeItem
import com.example.api1.ui.DetailsActivity
import android.util.Log

class ThemeAdapter(
    private val context: Context,
    private val themes: List<ThemeItem>
) : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {

    companion object {
        private const val IMAGE_BASE_URL =
            "https://pkmaster-cdn.qtonzapps.in/fullscreen/image/"
    }

    inner class ThemeViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val imgTheme: ImageView =
            itemView.findViewById(R.id.imgTheme)

        val txtSongName: TextView =
            itemView.findViewById(R.id.txtSongName)

        val btnDownload: ImageButton =
            itemView.findViewById(R.id.btnDownload)

        val btnPlay: ImageButton =
            itemView.findViewById(R.id.btnPlay)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThemeViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_theme, parent, false)

        return ThemeViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ThemeViewHolder,
        position: Int
    ) {

        val theme = themes[position]

        // Set the song/theme name
        holder.txtSongName.text = theme.SoundName ?: theme.Theme_Name

        // Build the full image URL
        val imageUrl = if (theme.Thumnail_Small.isNullOrEmpty()) {
            IMAGE_BASE_URL + theme.Thumnail_Big
        } else {
            IMAGE_BASE_URL + theme.Thumnail_Small
        }

        Log.d("ThemeAdapter", "Position: $position, Theme: ${theme.Theme_Name}, Image: $imageUrl")

        // Load image with Glide
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgTheme)

        // Image click - go to details
        holder.imgTheme.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("image", imageUrl)
            intent.putExtra("name", theme.Theme_Name)
            intent.putExtra("location", "Not Downloaded")
            context.startActivity(intent)
        }

        // Download button
        holder.btnDownload.setOnClickListener {
            Log.d("ThemeAdapter", "Download: ${theme.SoundName}")
        }

        // Play button
        holder.btnPlay.setOnClickListener {
            Log.d("ThemeAdapter", "Play: ${theme.SoundName}")
        }
    }

    override fun getItemCount(): Int {
        return themes.size
    }
}

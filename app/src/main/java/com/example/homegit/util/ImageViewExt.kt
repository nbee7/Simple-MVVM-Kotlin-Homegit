package com.example.homegit.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.setImageUrl(context: Context, imageUrl: String, progressBar: ProgressBar) {
    if (!isValidContext(context)) return

    progressBar.visible()
    Glide.with(context)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                ): Boolean {
                    progressBar.gone()
                    return false
                }

                override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                ): Boolean {
                    progressBar.gone()
                    return false
                }
            }).into(this)
}

//Method yang tidak digunakan sebaiknya dihilangkan
fun ImageView.setImageUrl(c: Context, imageUrl: String, progressBar: ProgressBar, errorResourceId: Int) {
    if (!isValidContext(c)) return

    val options = RequestOptions()
            .error(errorResourceId)

    progressBar.visible()
    Glide.with(c)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                ): Boolean {
                    progressBar.gone()
                    return false
                }

                override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                ): Boolean {
                    progressBar.gone()
                    return false
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .apply(options)
            .into(this)
}

fun isValidContext(context: Context): Boolean {
    val activity = context as? Activity
    return if (activity != null) {
        !(activity.isDestroyed || activity.isFinishing)
    } else {
        true
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
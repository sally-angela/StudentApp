package com.sally.studentapp.view

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.sally.studentapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

@BindingAdapter("android:imageUrl")
fun loadPhoto(imageView:ImageView, url:String) {
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
    picasso.build().load(url).into(imageView, object: Callback {
        override fun onSuccess() {
            var v = imageView.parent as View
            var pb = v.findViewById<ProgressBar>(R.id.progressBar)
            pb.visibility = View.INVISIBLE
            imageView.visibility = View.VISIBLE
        }

        override fun onError(e: Exception?) {
            Log.e("picasso", e?.message.toString())
        }
    })
}
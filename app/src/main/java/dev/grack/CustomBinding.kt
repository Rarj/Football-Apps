package dev.grack

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import dev.grack.zmatchschedulefootbal.R

object CustomBinding {

  @JvmStatic
  @BindingAdapter("app:imageUrl")
  fun setImage(view: AppCompatImageView, url: String?) {
    if (url != null) {
      Glide.with(view.context)
            .load(url)
            .into(view)
    } else {
      Glide.with(view.context)
            .load(R.drawable.dummy)
            .into(view)
    }
  }

}
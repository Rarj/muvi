package rio.arj.nsbtechnicaltest.helper

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import rio.arj.nsbtechnicaltest.BuildConfig
import rio.arj.nsbtechnicaltest.R

fun AppCompatImageView.loadImage(url: String?) {
  if (url.isNullOrBlank()) {
    Glide.with(this.context)
      .load(R.drawable.ic_guest)
      .into(this)
    return
  }

  Glide.with(this.context)
    .load(BuildConfig.BASE_URL_IMAGE + url)
    .into(this)
}

fun <T> Context.goToDetailActivity(it: Class<T>, movieId: String) {
  val intent = Intent(this, it)
  intent.putExtra("MOVIE_ID_KEY", movieId)
  startActivity(intent)
}
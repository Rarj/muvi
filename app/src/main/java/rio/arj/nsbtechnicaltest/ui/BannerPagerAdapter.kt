package rio.arj.nsbtechnicaltest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import kotlinx.coroutines.FlowPreview
import rio.arj.nsbtechnicaltest.R
import rio.arj.nsbtechnicaltest.data.repository.main.model.BannerResponse
import rio.arj.nsbtechnicaltest.helper.goToDetailActivity
import rio.arj.nsbtechnicaltest.helper.loadImage
import rio.arj.nsbtechnicaltest.ui.detail.DetailActivity

@FlowPreview
class BannerPagerAdapter(
  private val ctx: Context,
  private val images: List<BannerResponse>
) : PagerAdapter() {

  lateinit var layoutInflater: LayoutInflater

  override fun getCount(): Int {
    return images.size
  }

  override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view == `object`
  }

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    layoutInflater = LayoutInflater.from(ctx)

    val view = layoutInflater.inflate(R.layout.item_banner, container, false)

    val imageBanner = view.findViewById<AppCompatImageView>(R.id.image_banner)
    imageBanner.loadImage(images[position].backdropUrl)

    imageBanner.setOnClickListener {
      ctx.goToDetailActivity(DetailActivity::class.java, images[position].id)
    }

    container.addView(view, 0)

    return view
  }


  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    container.removeView(`object` as View)
  }
}
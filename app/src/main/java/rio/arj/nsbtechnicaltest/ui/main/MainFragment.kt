package rio.arj.nsbtechnicaltest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel
import rio.arj.nsbtechnicaltest.R
import rio.arj.nsbtechnicaltest.data.repository.main.model.BannerResponse
import rio.arj.nsbtechnicaltest.databinding.FragmentMainBinding
import rio.arj.nsbtechnicaltest.helper.goToDetailActivity
import rio.arj.nsbtechnicaltest.ui.BannerPagerAdapter
import rio.arj.nsbtechnicaltest.ui.detail.DetailActivity
import rio.arj.nsbtechnicaltest.ui.main.adapter.MainComingAdapter
import rio.arj.nsbtechnicaltest.ui.main.adapter.MainPopularAdapter


@FlowPreview
class MainFragment : Fragment() {

  private lateinit var binding: FragmentMainBinding
  private val mainViewModel by viewModel<MainViewModel>()

  private var dotscount = 0
  private var dots: MutableList<ImageView> = mutableListOf()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_main, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    observer()
  }

  private fun observer() {
    with(mainViewModel) {
      bannerResponse.observe(viewLifecycleOwner) { bannerList ->
        setBannerMovie(bannerList)
      }

      popularMovieResponse.observe(viewLifecycleOwner) {
        if (it.isNotEmpty()) {
          val popularAdapter = MainPopularAdapter(it) { movieId ->
            requireContext().goToDetailActivity(DetailActivity::class.java, movieId)
          }

          binding.recyclerPopular.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = popularAdapter
          }
          val snapHelper = LinearSnapHelper()
          snapHelper.attachToRecyclerView(binding.recyclerPopular)
        }
      }

      comingMovieResponse.observe(viewLifecycleOwner) {
        if (it.isNotEmpty()) {
          val comingAdapter = MainComingAdapter(it) { movieId ->
            requireContext().goToDetailActivity(DetailActivity::class.java, movieId)
          }

          binding.recyclerComing.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = comingAdapter
          }
          val snapHelper = LinearSnapHelper()
          snapHelper.attachToRecyclerView(binding.recyclerComing)
        }
      }
    }
  }

  private fun setBannerMovie(bannerList: List<BannerResponse>) {
    if (bannerList.isNotEmpty()) {
      val backdropList = mutableListOf<BannerResponse>()
      bannerList.forEachIndexed { index, bannerResponse ->
        if (index <= 3) {
          backdropList.add(bannerResponse)
        }
      }
      val adapter = BannerPagerAdapter(requireContext(), backdropList)

      binding.viewPagerBanner.adapter = adapter

      dotscount = adapter.count - 1
      for (i in 0..dotscount) {
        dots.add(ImageView(requireContext()))
      }

      for (i in 0..dotscount) {
        dots[i] = ImageView(requireContext())
        dots[i].setImageDrawable(
          ContextCompat.getDrawable(
            requireContext(),
            R.drawable.ic_banner_not_selected
          )
        )
        binding.sliderIndicator.addView(dots[i])
      }

      dots[0].setImageDrawable(
        ContextCompat.getDrawable(
          requireContext(),
          R.drawable.ic_banner_selected
        )
      )

      binding.viewPagerBanner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
          position: Int,
          positionOffset: Float,
          positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
          for (i in 0..dotscount) {
            dots[i].setImageDrawable(
              ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_banner_not_selected
              )
            )
          }

          dots[position].setImageDrawable(
            ContextCompat.getDrawable(
              requireContext(),
              R.drawable.ic_banner_selected
            )
          )

        }

        override fun onPageScrollStateChanged(state: Int) {}
      })
    }
  }

}
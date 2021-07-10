package rio.arj.nsbtechnicaltest.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rio.arj.nsbtechnicaltest.R
import rio.arj.nsbtechnicaltest.data.repository.detail.model.DetailResponse
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteEntity
import rio.arj.nsbtechnicaltest.data.repository.popular.model.CreditResponse
import rio.arj.nsbtechnicaltest.databinding.ActivityDetailBinding
import rio.arj.nsbtechnicaltest.helper.loadImage

@FlowPreview
class DetailActivity : AppCompatActivity() {

  private val detailViewModel by viewModel<DetailViewModel>()
  private lateinit var binding: ActivityDetailBinding

  private val movieId: String by lazy {
    intent.getStringExtra("MOVIE_ID_KEY") ?: throw RuntimeException("movie should not null")
  }

  private var isFavorite = false
  private var detailResponse: DetailResponse? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
    binding.viewModel = detailViewModel
    binding.lifecycleOwner = this

    loadDetailMovie()
    listener()
  }

  private fun loadDetailMovie() {
    lifecycleScope.launch {
      detailViewModel.findMovie(movieId).map { it }
        .collect {
          isFavorite = it.isNotEmpty()
          setButtonFavorite()
        }

      detailViewModel.getDetailMovie(movieId.toInt())
        .flatMapMerge {
          detailResponse = it
          setupUi(it)
          detailViewModel.creditsMovie(it.id ?: throw RuntimeException("id should be not null"))
        }
        .collect {
          setupCredits(it)
        }
    }
  }

  private fun setButtonFavorite() {
    if (isFavorite) {
      setupButtonFavoriteRemove()
    } else {
      setupButtonFavoriteAdd()
    }
  }

  private fun setupButtonFavoriteAdd() {
    binding.buttonAddRemoveFavorite.text = getString(R.string.label_add_favorite)
    binding.buttonAddRemoveFavorite.icon =
      ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_add)
  }

  private fun setupButtonFavoriteRemove() {
    binding.buttonAddRemoveFavorite.text = getString(R.string.label_remove_favorite)
    binding.buttonAddRemoveFavorite.icon =
      ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_remove)
  }

  private fun setupUi(detail: DetailResponse) {
    binding.imagePoster.loadImage(detail.posterPath.toString())
    binding.textTitle.text = detail.title
    val runtime = "${detail.runtime?.div(60)}h ${detail.runtime?.mod(60)}m"
    binding.textRuntime.text = runtime
    binding.textGenre.text = detail.getGenreFormatted()
    binding.textOverview.text = detail.overview
  }

  private fun setupCredits(credit: CreditResponse) {
    val detailCasterAdapter = DetailCasterAdapter(credit)
    binding.recyclerCaster.apply {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      adapter = detailCasterAdapter
    }
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(binding.recyclerCaster)
  }

  private fun listener() {
    binding.buttonBack.setOnClickListener { finish() }

    binding.buttonAddRemoveFavorite.setOnClickListener {
      if (isFavorite) {
        removeFromFavorite()
      } else {
        addToFavorite()
      }
      isFavorite = !isFavorite
    }
  }

  private fun removeFromFavorite() {
    lifecycleScope.launch {
      detailViewModel.deleteMovie(movieId)
        .collect {
          setupButtonFavoriteAdd()
        }
    }
  }

  private fun addToFavorite() {
    val movie = FavoriteEntity(
      movieId,
      detailResponse?.title.toString(),
      detailResponse?.getReleaseDateFormatted().toString(),
      detailResponse?.getGenreFormatted().toString(),
      detailResponse?.backdropPath.toString()
    )

    lifecycleScope.launch {
      detailViewModel.addFavorite(movie)
        .collect { setupButtonFavoriteRemove() }
    }
  }
}
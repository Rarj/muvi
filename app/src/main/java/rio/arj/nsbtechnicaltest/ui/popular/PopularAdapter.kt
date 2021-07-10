package rio.arj.nsbtechnicaltest.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.FlowPreview
import rio.arj.nsbtechnicaltest.data.repository.popular.model.GenreResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.PopularMoviePagingResponse
import rio.arj.nsbtechnicaltest.databinding.ItemPopularPagingBinding
import rio.arj.nsbtechnicaltest.helper.loadImage

class PopularAdapter(
  private val genres: GenreResponse?,
  private val listener: (movieId: String) -> Unit
) : PagingDataAdapter<PopularMoviePagingResponse, PopularAdapter.ViewHolder>(diffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemPopularPagingBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  @FlowPreview
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(
      getItem(position),
      popularMovieModelMapper(position),
      listener
    )
  }

  private fun popularMovieModelMapper(position: Int): List<String> {
    val genreMapping = mutableListOf<String>()
    snapshot()[position].let {
      it?.genreIds?.forEach { id ->
        genres?.genres?.forEach { genreResponse ->
          if (id == genreResponse.id) {
            genreMapping.add(genreResponse.name)
          }
        }
      }
    }
    return genreMapping
  }

  class ViewHolder(private val binding: ItemPopularPagingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
      model: PopularMoviePagingResponse?,
      genre: List<String>,
      listener: (movieId: String) -> Unit
    ) {
      binding.imagePoster.loadImage(model?.posterPath)
      binding.textTitle.text = model?.originalTitle
      binding.textGenre.text = genre.toString().replace("[", "").replace("]", "")

      binding.root.setOnClickListener { listener.invoke(model?.id.toString()) }
    }
  }

  companion object {
    val diffCallback = object : DiffUtil.ItemCallback<PopularMoviePagingResponse>() {
      override fun areItemsTheSame(
        oldItem: PopularMoviePagingResponse,
        newItem: PopularMoviePagingResponse
      ): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(
        oldItem: PopularMoviePagingResponse,
        newItem: PopularMoviePagingResponse
      ): Boolean {
        return oldItem == newItem
      }
    }
  }
}

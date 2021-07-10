package rio.arj.nsbtechnicaltest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rio.arj.nsbtechnicaltest.data.repository.main.model.ComingMovieResponse
import rio.arj.nsbtechnicaltest.databinding.ItemPopularMainBinding
import rio.arj.nsbtechnicaltest.helper.loadImage

class MainComingAdapter(
  private val list: List<ComingMovieResponse>,
  private val listener: (movieId: String) -> Unit
) : RecyclerView.Adapter<MainComingAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemPopularMainBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position], listener)
  }

  class ViewHolder(private val viewDataBinding: ItemPopularMainBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(
      model: ComingMovieResponse,
      listener: (movieId: String) -> Unit
    ) {
      viewDataBinding.imagePopular.loadImage(model.posterPath)
      viewDataBinding.root.setOnClickListener { listener.invoke(model.id) }
    }
  }

}
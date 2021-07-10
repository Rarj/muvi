package rio.arj.nsbtechnicaltest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rio.arj.nsbtechnicaltest.data.repository.main.model.PopularMovieResponse
import rio.arj.nsbtechnicaltest.databinding.ItemComingMainBinding
import rio.arj.nsbtechnicaltest.helper.loadImage

class MainPopularAdapter(
  private val list: List<PopularMovieResponse>,
  private val listener: (movieId: String) -> Unit
) : RecyclerView.Adapter<MainPopularAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemComingMainBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position], listener)
  }

  class ViewHolder(private val viewDataBinding: ItemComingMainBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(
      model: PopularMovieResponse,
      listener: (movieId: String) -> Unit
    ) {
      viewDataBinding.imagePopular.loadImage(model.posterPath)

      viewDataBinding.root.setOnClickListener { listener.invoke(model.id) }
    }
  }

}
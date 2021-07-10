package rio.arj.nsbtechnicaltest.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteEntity
import rio.arj.nsbtechnicaltest.databinding.ItemFavoriteBinding
import rio.arj.nsbtechnicaltest.helper.loadImage

class FavoriteAdapter(
  private val favoriteList: MutableList<FavoriteEntity>,
  private val listener: FavoriteListener
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val viewDataBinding = ItemFavoriteBinding.inflate(inflater, parent, false)
    return ViewHolder(viewDataBinding)
  }

  override fun getItemCount(): Int {
    return favoriteList.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(favoriteList[position], listener)
  }

  class ViewHolder(private val binding: ItemFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
      model: FavoriteEntity,
      listener: FavoriteListener
    ) {
      binding.imagePoster.loadImage(model.imageUrl)
      binding.textName.text = model.name
      binding.textReleaseDate.text = model.releaseDate
      binding.textGenre.text = model.getGenreFormatted()

      binding.buttonRemoveFavorite.setOnClickListener { listener.onItemRemove(model.movieId) }
      binding.root.setOnClickListener { listener.onItemClicked(model.movieId) }
    }
  }

}
package rio.arj.nsbtechnicaltest.ui.favorite

interface FavoriteListener {
  fun onItemRemove(movieId: String)
  fun onItemClicked(movieId: String)
}
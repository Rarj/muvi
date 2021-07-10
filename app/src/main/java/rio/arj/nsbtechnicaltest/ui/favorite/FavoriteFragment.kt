package rio.arj.nsbtechnicaltest.ui.favorite

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rio.arj.nsbtechnicaltest.R
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteEntity
import rio.arj.nsbtechnicaltest.databinding.FragmentFavoriteBinding
import rio.arj.nsbtechnicaltest.helper.goToDetailActivity
import rio.arj.nsbtechnicaltest.ui.detail.DetailActivity

@ExperimentalCoroutinesApi
@FlowPreview
class FavoriteFragment : Fragment() {

  private val favoriteViewModel by viewModel<FavoriteViewModel>()
  private lateinit var binding: FragmentFavoriteBinding
  private lateinit var favoriteAdapter: FavoriteAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_favorite, container, false)
    binding.viewModel = favoriteViewModel
    binding.lifecycleOwner = this

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.inputSearch.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

      }

      override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        setSearchLabel(text)
        filterFavoriteMovie(text)
      }

      override fun afterTextChanged(p0: Editable?) {

      }
    })
  }

  private fun loadFavoriteList() {
    lifecycleScope.launch {
      favoriteViewModel.findAll().collect {
        setupUi(it)
      }
    }
  }

  private fun setupUi(list: List<FavoriteEntity>) {
    favoriteAdapter = FavoriteAdapter(list.toMutableList(), object : FavoriteListener {
      override fun onItemRemove(movieId: String) {
        lifecycleScope.launch {
          favoriteViewModel.deleteMovie(movieId).collect {
            loadFavoriteList()
          }
        }
      }

      override fun onItemClicked(movieId: String) {
        requireContext().goToDetailActivity(DetailActivity::class.java, movieId)
      }
    })

    binding.recyclerFavorite.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = favoriteAdapter
    }
  }

  private fun setSearchLabel(text: CharSequence?) {
    if (text.toString().isNotBlank()) {
      binding.textSearchLabel.text = HtmlCompat.fromHtml(
        getString(R.string.result_search_label, text.toString()),
        HtmlCompat.FROM_HTML_MODE_COMPACT
      )
      binding.textSearchLabel.visibility = View.VISIBLE
    } else {
      binding.textSearchLabel.visibility = View.GONE
    }
  }

  private fun filterFavoriteMovie(text: CharSequence?) {
    lifecycleScope.launch {
      favoriteViewModel.findMovies(text.toString())
        .flatMapLatest { it }
        .collect { setupUi(it) }
    }
  }

  override fun onResume() {
    super.onResume()
    loadFavoriteList()
  }

}
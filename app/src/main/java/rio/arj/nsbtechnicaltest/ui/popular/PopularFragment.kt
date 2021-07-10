package rio.arj.nsbtechnicaltest.ui.popular

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
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rio.arj.nsbtechnicaltest.R
import rio.arj.nsbtechnicaltest.data.repository.popular.model.GenreResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.PopularMoviePagingResponse
import rio.arj.nsbtechnicaltest.databinding.FragmentPopularBinding
import rio.arj.nsbtechnicaltest.helper.goToDetailActivity
import rio.arj.nsbtechnicaltest.ui.detail.DetailActivity

@FlowPreview
class PopularFragment : Fragment() {

  private val popularViewModel by viewModel<PopularViewModel>()
  private lateinit var binding: FragmentPopularBinding

  private val popularAdapter by lazy {
    PopularAdapter(genreResponse) { movieId ->
      requireContext().goToDetailActivity(
        DetailActivity::class.java,
        movieId
      )
    }
  }

  private var genreResponse: GenreResponse? = null
  private lateinit var pagingDataResponse: PagingData<PopularMoviePagingResponse>

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_popular, container, false)
    binding.viewModel = popularViewModel
    binding.lifecycleOwner = this

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.inputSearch.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

      override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        setSearchLabel(text)
        filterListMovie(text)
      }

      override fun afterTextChanged(p0: Editable?) {}
    })

    popularViewModel.viewModelScope.launch {
      popularViewModel.genresMovie
        .flatMapMerge {
          it
        }.flatMapMerge { genreModel ->
          genreResponse = genreModel
          popularViewModel.popularMovies
        }
        .collect {
          pagingDataResponse = it
          binding.recyclerPopularPaging.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = popularAdapter
          }

          popularAdapter.submitData(pagingDataResponse)
        }
    }
  }

  private fun filterListMovie(text: CharSequence?) {
    lifecycleScope.launch {
      popularViewModel.popularMovies
        .map { popularPaging ->
          popularPaging.filter { it.originalTitle.contains(text.toString(), ignoreCase = true) }
        }.debounce(300)
        .collect { popularAdapter.submitData(it) }
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

}
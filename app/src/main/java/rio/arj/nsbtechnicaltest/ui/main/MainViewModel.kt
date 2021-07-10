package rio.arj.nsbtechnicaltest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rio.arj.nsbtechnicaltest.data.repository.main.MainRepositoryImpl
import rio.arj.nsbtechnicaltest.data.repository.main.model.BannerResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.ComingMovieResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.PopularMovieResponse

class MainViewModel(private val repository: MainRepositoryImpl) : ViewModel() {

  private var _bannerResponse = MutableLiveData<List<BannerResponse>>()
  val bannerResponse get() = _bannerResponse

  private var _popularMovieResponse = MutableLiveData<List<PopularMovieResponse>>()
  val popularMovieResponse get() = _popularMovieResponse

  private var _comingMovieResponse = MutableLiveData<List<ComingMovieResponse>>()
  val comingMovieResponse get() = _comingMovieResponse

  init {
    getBanner()
    getPopularMovie()
    getComingMovie()
  }

  private fun getBanner() {
    viewModelScope.launch {
      val result = repository.getBanner()
      _bannerResponse.value = result.results
    }
  }

  private fun getPopularMovie() {
    viewModelScope.launch {
      val result = repository.getPopularMovie()
      _popularMovieResponse.value = result.results.subList(0, 10)
    }
  }

  private fun getComingMovie() {
    viewModelScope.launch {
      val result = repository.getComingMovie()
      _comingMovieResponse.value = result.results.subList(0, 10)
    }
  }
}
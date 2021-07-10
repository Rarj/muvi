package rio.arj.nsbtechnicaltest.data.repository.main

import rio.arj.nsbtechnicaltest.BuildConfig
import rio.arj.nsbtechnicaltest.data.BaseResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.BannerResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.ComingMovieResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.PopularMovieResponse
import rio.arj.nsbtechnicaltest.network.ApiService
import java.util.*

class MainRepositoryImpl(private val apiService: ApiService) : MainRepository {
  override suspend fun getBanner(): BaseResponse<BannerResponse> {
    return apiService.getBanner(BuildConfig.API_KEY)
  }

  override suspend fun getPopularMovie(): BaseResponse<PopularMovieResponse> {
    return apiService.getPopularMovie(BuildConfig.API_KEY)
  }

  override suspend fun getComingMovie(): BaseResponse<ComingMovieResponse> {
    val nextYear = Calendar.getInstance().get(Calendar.YEAR) + 1
    return apiService.getComingMovieResponse(BuildConfig.API_KEY, year = nextYear)
  }
}
package rio.arj.nsbtechnicaltest.data.repository.main

import rio.arj.nsbtechnicaltest.data.BaseResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.BannerResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.ComingMovieResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.PopularMovieResponse

interface MainRepository {
  suspend fun getBanner(): BaseResponse<BannerResponse>
  suspend fun getPopularMovie(): BaseResponse<PopularMovieResponse>
  suspend fun getComingMovie(): BaseResponse<ComingMovieResponse>
}
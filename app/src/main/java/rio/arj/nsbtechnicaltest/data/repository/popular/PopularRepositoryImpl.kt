package rio.arj.nsbtechnicaltest.data.repository.popular

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import rio.arj.nsbtechnicaltest.BuildConfig
import rio.arj.nsbtechnicaltest.data.BaseResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.CreditResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.GenreResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.PopularMoviePagingResponse
import rio.arj.nsbtechnicaltest.network.ApiService

class PopularRepositoryImpl(private val apiService: ApiService) : PopularRepository {
  override suspend fun getPopularMovie(page: Int): BaseResponse<PopularMoviePagingResponse> {
    return apiService.getPopularMoviePaging(BuildConfig.API_KEY, page = page)
  }

  override suspend fun getGenres(): Flow<GenreResponse> {
    return flow {
      emit(apiService.getGenres(BuildConfig.API_KEY))
    }.flowOn(Dispatchers.IO)
  }

  override suspend fun getCredits(movieId: Int): Flow<CreditResponse> {
    return flow {
      emit(apiService.getCredits(movieId, BuildConfig.API_KEY))
    }.flowOn(Dispatchers.IO)
  }
}
package rio.arj.nsbtechnicaltest.data.repository.detail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import rio.arj.nsbtechnicaltest.BuildConfig
import rio.arj.nsbtechnicaltest.data.repository.detail.model.DetailResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.CreditResponse
import rio.arj.nsbtechnicaltest.network.ApiService

class DetailRepositoryImpl(private val apiService: ApiService) : DetailRepository {
  override suspend fun getDetailMovie(id: Int): Flow<DetailResponse> {
    return flow {
      emit(apiService.getDetailMovie(id, BuildConfig.API_KEY))
    }.flowOn(Dispatchers.IO)
  }

  override suspend fun getCredits(movieId: Int): Flow<CreditResponse> {
    return flow {
      emit(apiService.getCredits(movieId, BuildConfig.API_KEY))
    }.flowOn(Dispatchers.IO)
  }
}
package rio.arj.nsbtechnicaltest.data.repository.detail

import kotlinx.coroutines.flow.Flow
import rio.arj.nsbtechnicaltest.data.repository.detail.model.DetailResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.CreditResponse

interface DetailRepository {
  suspend fun getDetailMovie(id: Int): Flow<DetailResponse>
  suspend fun getCredits(movieId: Int): Flow<CreditResponse>
}
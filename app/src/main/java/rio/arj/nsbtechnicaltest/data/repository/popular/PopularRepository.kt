package rio.arj.nsbtechnicaltest.data.repository.popular

import kotlinx.coroutines.flow.Flow
import rio.arj.nsbtechnicaltest.data.BaseResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.CreditResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.GenreResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.PopularMoviePagingResponse

interface PopularRepository {
  suspend fun getPopularMovie(page: Int): BaseResponse<PopularMoviePagingResponse>
  suspend fun getGenres(): Flow<GenreResponse>
  suspend fun getCredits(movieId: Int): Flow<CreditResponse>
}
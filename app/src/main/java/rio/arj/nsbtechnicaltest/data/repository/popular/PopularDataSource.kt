package rio.arj.nsbtechnicaltest.data.repository.popular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import rio.arj.nsbtechnicaltest.data.repository.popular.model.PopularMoviePagingResponse

class PopularDataSource(
  private val repository: PopularRepositoryImpl
) : PagingSource<Int, PopularMoviePagingResponse>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMoviePagingResponse> {
    return try {
      val currentLoadingPageKey = params.key ?: 1
      val response = repository.getPopularMovie(currentLoadingPageKey)

      val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

      LoadResult.Page(
        data = response.results as MutableList<PopularMoviePagingResponse>,
        prevKey = prevKey,
        nextKey = currentLoadingPageKey.plus(1)
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, PopularMoviePagingResponse>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }
}
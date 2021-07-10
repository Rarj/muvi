package rio.arj.nsbtechnicaltest.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rio.arj.nsbtechnicaltest.data.BaseResponse
import rio.arj.nsbtechnicaltest.data.repository.detail.model.DetailResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.BannerResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.ComingMovieResponse
import rio.arj.nsbtechnicaltest.data.repository.main.model.PopularMovieResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.CreditResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.GenreResponse
import rio.arj.nsbtechnicaltest.data.repository.popular.model.PopularMoviePagingResponse

interface ApiService {
  @GET("discover/movie")
  suspend fun getBanner(
    @Query("api_key") apiKey: String,
    @Query("language") language: String = "en-US",
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("include_adult") includeAdult: Boolean = false,
    @Query("include_video") includeVideo: Boolean = false,
    @Query("page") page: Int = 1
  ): BaseResponse<BannerResponse>

  @GET("discover/movie")
  suspend fun getPopularMovie(
    @Query("api_key") apiKey: String,
    @Query("language") language: String = "en-US",
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("include_adult") includeAdult: Boolean = false,
    @Query("include_video") includeVideo: Boolean = false,
    @Query("page") page: Int = 1
  ): BaseResponse<PopularMovieResponse>

  @GET("discover/movie")
  suspend fun getComingMovieResponse(
    @Query("api_key") apiKey: String,
    @Query("language") language: String = "en-US",
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("include_adult") includeAdult: Boolean = false,
    @Query("include_video") includeVideo: Boolean = false,
    @Query("page") page: Int = 1,
    @Query("year") year: Int
  ): BaseResponse<ComingMovieResponse>

  @GET("discover/movie")
  suspend fun getPopularMoviePaging(
    @Query("api_key") apiKey: String,
    @Query("language") language: String = "en-US",
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("include_adult") includeAdult: Boolean = false,
    @Query("include_video") includeVideo: Boolean = false,
    @Query("page") page: Int
  ): BaseResponse<PopularMoviePagingResponse>

  @GET("genre/movie/list")
  suspend fun getGenres(
    @Query("api_key") apiKey: String
  ): GenreResponse

  @GET("movie/{movie_id}/credits")
  suspend fun getCredits(
    @Path("movie_id") movieId: Int,
    @Query("api_key") apiKey: String
  ): CreditResponse

  @GET("movie/{movie_id}")
  suspend fun getDetailMovie(
    @Path("movie_id") movieId: Int,
    @Query("api_key") apiKey: String,
    @Query("language") language: String = "en-US"
  ): DetailResponse

}
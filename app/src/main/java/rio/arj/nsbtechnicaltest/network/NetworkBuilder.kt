package rio.arj.nsbtechnicaltest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rio.arj.nsbtechnicaltest.BuildConfig

class NetworkBuilder {
  fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
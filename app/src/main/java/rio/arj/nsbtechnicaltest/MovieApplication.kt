package rio.arj.nsbtechnicaltest

import android.app.Application
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import rio.arj.nsbtechnicaltest.di.appModules
import rio.arj.nsbtechnicaltest.di.databaseModules
import rio.arj.nsbtechnicaltest.di.repositoryModules
import rio.arj.nsbtechnicaltest.di.viewModelModules

@FlowPreview
class MovieApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    if (GlobalContext.getOrNull() == null) {
      startKoin {
        androidContext(this@MovieApplication)
        modules(appModules, viewModelModules, repositoryModules, databaseModules)
      }
    }
  }
}
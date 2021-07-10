package rio.arj.nsbtechnicaltest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteDao
import rio.arj.nsbtechnicaltest.data.repository.favorite.FavoriteEntity

@Database(
  entities = [FavoriteEntity::class],
  version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {
  abstract fun favoriteDao(): FavoriteDao

  companion object {
    private var INSTANCE: FavoriteDatabase? = null

    fun getAppDataBase(context: Context): FavoriteDatabase? {
      if (INSTANCE == null) {
        synchronized(FavoriteDatabase::class) {
          INSTANCE =
            Room.databaseBuilder(
              context,
              FavoriteDatabase::class.java, "favorite.db"
            ).build()
        }
      }
      return INSTANCE
    }
  }
}
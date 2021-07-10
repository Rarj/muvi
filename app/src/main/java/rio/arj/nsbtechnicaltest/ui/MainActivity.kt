package rio.arj.nsbtechnicaltest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import rio.arj.nsbtechnicaltest.R
import rio.arj.nsbtechnicaltest.databinding.ActivityMainBinding
import rio.arj.nsbtechnicaltest.ui.favorite.FavoriteFragment
import rio.arj.nsbtechnicaltest.ui.main.MainFragment
import rio.arj.nsbtechnicaltest.ui.popular.PopularFragment

@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    binding.bottomNavigation.selectedItemId = R.id.menu_home
    loadFragment(MainFragment())

    binding.bottomNavigation.setOnItemSelectedListener {
      when (it.itemId) {
        R.id.menu_home -> {
          loadFragment(MainFragment())
          return@setOnItemSelectedListener true
        }
        R.id.menu_popular -> {
          loadFragment(PopularFragment())
          return@setOnItemSelectedListener true
        }
        R.id.menu_favorite -> {
          loadFragment(FavoriteFragment())
          return@setOnItemSelectedListener true
        }
        R.id.menu_grid -> {
          Toast.makeText(this, "This features is coming soon!", Toast.LENGTH_SHORT).show()
          return@setOnItemSelectedListener false
        }
        else -> {
          return@setOnItemSelectedListener false
        }
      }
    }
  }

  private fun loadFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container_fragment, fragment)
    transaction.commit()
  }

}
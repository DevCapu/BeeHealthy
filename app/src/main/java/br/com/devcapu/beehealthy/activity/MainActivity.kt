package br.com.devcapu.beehealthy.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.lifecycleScope
import br.com.devcapu.beehealthy.local.JsonUtil.Companion.getJsonFromAssets
import br.com.devcapu.beehealthy.local.LocalFoodDataSource
import br.com.devcapu.beehealthy.repository.FoodRepository
import br.com.devcapu.beehealthy.repository.MealRepository
import br.com.devcapu.beehealthy.repository.PatientRepository
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.dao.BeeHealthyDatabase
import br.com.devcapu.beehealthy.graph.MainNavigationGraph
import br.com.devcapu.beehealthy.viewmodel.DiaryViewModel
import br.com.devcapu.beehealthy.viewmodel.AddFoodViewModel

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val diaryViewModel: DiaryViewModel by viewModels {
        val database = BeeHealthyDatabase.getInstance(this)
        val patientRepository = PatientRepository(database.patientDao())
        DiaryViewModel.Factory(patientRepository)
    }

    private val addFoodViewModel: AddFoodViewModel by viewModels {
        val database = BeeHealthyDatabase.getInstance(this)
        val foodRepository = FoodRepository(
            databaseFoodDataSource = database.ingestedFoodDao(),
            localFoodDataSource = LocalFoodDataSource()
        )
        AddFoodViewModel.Factory(foodRepository, MealRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLifecycleObservers()

        setContent {
            BeeHealthyTheme {
                MainNavigationGraph(
                    homeViewModel = diaryViewModel,
                    addFoodViewModel = addFoodViewModel
                )
            }
        }
    }

    private fun configureLifecycleObservers() {
        lifecycleScope.launchWhenCreated {
            diaryViewModel.findAllCategories(
                getJsonFromAssets(this@MainActivity, "categoryList.json")!!
            )
            addFoodViewModel.findAllFoods(
                getJsonFromAssets(this@MainActivity, "foodList.json")!!
            )
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
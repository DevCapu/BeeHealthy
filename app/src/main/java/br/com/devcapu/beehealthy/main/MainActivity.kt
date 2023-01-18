package br.com.devcapu.beehealthy.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.devcapu.beehealthy.common.JsonUtil.Companion.getJsonFromAssets
import br.com.devcapu.beehealthy.common.data.datasource.LocalFoodDataSource
import br.com.devcapu.beehealthy.common.data.repository.FoodRepository
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.config.BeeHealthyDatabase
import br.com.devcapu.beehealthy.diary.ui.DiaryViewModel
import br.com.devcapu.beehealthy.food.add.AddFoodViewModel
import br.com.devcapu.beehealthy.main.navigation.MainNavigationGraph

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
        AddFoodViewModel.Factory(foodRepository)
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
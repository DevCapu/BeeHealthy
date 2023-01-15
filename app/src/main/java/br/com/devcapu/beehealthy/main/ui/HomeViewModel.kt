package br.com.devcapu.beehealthy.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.devcapu.beehealthy.authentication.login.data.LoginRepository
import br.com.devcapu.beehealthy.common.data.datasource.LocalCategoryDataSource
import br.com.devcapu.beehealthy.common.data.repository.CategoryRepository
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.food.nutrition.state.CaloriesUiState
import br.com.devcapu.beehealthy.food.nutrition.state.MacroUiState
import br.com.devcapu.beehealthy.food.nutrition.state.MacrosUiState
import br.com.devcapu.beehealthy.main.ui.state.HomeUIState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    private val loginRepository = LoginRepository()
    private val firebaseAuth = FirebaseAuth.getInstance()

    private var _state = MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state

    init {
        firebaseAuth.currentUser?.email?.let {
            viewModelScope.launch {
                val patient = patientRepository.searchUserWith(email = it)
                patient.bodyCaloriesNeeds?.let {
                    _state.value = _state.value.copy(
                        caloriesUiState = CaloriesUiState(
                            caloriesToCommitObjective = it.caloriesToCommitObjective.value.toString(),
                        ),
                        macrosUiState = MacrosUiState(
                            carbohydrateUiState = MacroUiState(
                                total = it.macros.carbohydrate.toString()
                            ),
                            proteinUiState = MacroUiState(
                                total = it.macros.protein.toString()
                            ),
                            fatUiState = MacroUiState(
                                total = it.macros.fats.toString()
                            )
                        ),
                        onClickLogout = { loginRepository.logout { it() } }
                    )
                }
            }
        }
    }

    fun findAllCategories(categoriesAsJSONString: String) {
        _state.value = _state.value.copy(
            categories = CategoryRepository(LocalCategoryDataSource()).getAll(categoriesAsJSONString)
        )
    }

    class Factory(
        private val patientRepository: PatientRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(patientRepository = patientRepository) as T
        }
    }
}
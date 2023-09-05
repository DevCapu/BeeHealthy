package br.com.devcapu.beehealthy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.devcapu.beehealthy.dao.BeeHealthyDatabase
import br.com.devcapu.beehealthy.local.LocalCategoryDataSource
import br.com.devcapu.beehealthy.repository.CategoryRepository
import br.com.devcapu.beehealthy.repository.PatientRepository
import br.com.devcapu.beehealthy.uistate.CaloriesUiState
import br.com.devcapu.beehealthy.uistate.MacroUiState
import br.com.devcapu.beehealthy.uistate.MacrosUiState
import br.com.devcapu.beehealthy.usecase.DiaryUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiaryViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private var _state = MutableStateFlow(DiaryUiState())
    val state: StateFlow<DiaryUiState> = _state

    init {
        auth.currentUser?.email?.let {
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
                        onClickLogout = { auth.signOut() }
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

    companion object {
        val Factory: Factory = viewModelFactory {
            initializer {
                val context = this[APPLICATION_KEY] as Context
                val dao = BeeHealthyDatabase.getInstance(context).patientDao()
                DiaryViewModel(patientRepository = PatientRepository(dao))
            }
        }
    }
}
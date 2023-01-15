package br.com.devcapu.beehealthy.authentication.register.ui.state

import br.com.devcapu.beehealthy.common.domain.model.patient.health.ActivityLevel
import br.com.devcapu.beehealthy.common.domain.model.patient.health.BiologicalGender
import br.com.devcapu.beehealthy.common.domain.model.patient.health.Objective

data class RegisterUIState(
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val name: String = "",
    val birthday: String = "",
    val weight: String = "",
    val height: String = "",
    val biologicalGender: BiologicalGender = BiologicalGender.MALE,
    val activityLevel: ActivityLevel = ActivityLevel.LOW,
    val objective: Objective = Objective.LOSE,
    val onEmailChanged: (String) -> Unit = { },
    val onPasswordChanged: (String) -> Unit = { },
    val onPasswordConfirmationChanged: (String) -> Unit = { },
    val onNameChanged: (String) -> Unit = { },
    val onBirthdayChanged: (String) -> Unit = { },
    val onWeightChanged: (String) -> Unit = { },
    val onHeightChanged: (String) -> Unit = { },
    val onBiologicalGenderChanged: (BiologicalGender) -> Unit = { },
    val onActivityLevelChanged: (ActivityLevel) -> Unit = { },
    val onObjectiveChanged: (Objective) -> Unit = { },
    val onGoToNextStep: () -> Unit = { },
    val finished: Boolean = false,
) {
    val passwordsAreTheSame = password == passwordConfirmation
}
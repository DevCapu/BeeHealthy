package br.com.devcapu.beehealthy.uistate

import br.com.devcapu.beehealthy.screen.OnboardSteps

data class StepUIState(
    val step: OnboardSteps = OnboardSteps.AUTHENTICATION_REGISTER,
    val onGoToNextStep: () -> Unit = { },
)
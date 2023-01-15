package br.com.devcapu.beehealthy.authentication.register.ui.state

import br.com.devcapu.beehealthy.authentication.register.ui.screen.OnboardSteps

data class StepUIState(
    val step: OnboardSteps = OnboardSteps.AUTHENTICATION_REGISTER,
    val onGoToNextStep: () -> Unit = { },
)
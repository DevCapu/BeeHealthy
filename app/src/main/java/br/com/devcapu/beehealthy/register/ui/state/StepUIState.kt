package br.com.devcapu.beehealthy.register.ui.state

import br.com.devcapu.beehealthy.register.ui.screen.OnboardSteps

data class StepUIState(
    val step: OnboardSteps = OnboardSteps.AUTHENTICATION_REGISTER,
    val onGoToNextStep: () -> Unit = { },
)
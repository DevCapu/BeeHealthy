package br.com.devcapu.beehealthy.domain.model.patient

data class Email(
    val address: String
) {
    override fun toString() = address
}
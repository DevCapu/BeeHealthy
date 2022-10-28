package br.com.devcapu.beehealthy.common.domain.model.patient

import java.util.regex.Pattern

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern
    .compile(
    "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
)

data class Email(
    val address: String,
) {
    val isValid: Boolean
        get() {
            return EMAIL_ADDRESS_PATTERN.matcher(address).matches()
        }

    override fun toString() = address
}
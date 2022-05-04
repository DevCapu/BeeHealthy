package br.com.devcapu.beehealthy.domain.model.patient

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailTest {

    @Test
    fun `should return true when email is valid`() {
        val email = Email("valid@email.com")

        val isValid = email.isValid

        assertTrue(isValid)
    }

    @Test
    fun `should return false when email is not valid`() {
        val email = Email("invalidEmail.com")

        val isValid = email.isValid

        assertFalse(isValid)
    }
}
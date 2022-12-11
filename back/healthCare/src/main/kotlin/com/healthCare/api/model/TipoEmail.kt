package com.healthCare.api.model

import org.apache.logging.log4j.util.Strings

enum class TipoEmail {
    BRANCO, CADASTRADO, EDITADO, INSERIDO, APAGADO;

    companion object {
        fun getTipoEmail(emailCadastrado: String): TipoEmail {
            return getTipoEmail(emailCadastrado, emailCadastrado)
        }

        fun getTipoEmail(emailCadastrado: String, emailConfirmado: String): TipoEmail {
            var emailCadastrado = emailCadastrado
            var emailConfirmado = emailConfirmado
            if (Strings.isBlank(emailCadastrado)) {
                emailCadastrado = ""
            }
            if (Strings.isBlank(emailConfirmado)) {
                emailConfirmado = ""
            }
            return if (emailConfirmado == emailCadastrado) {
                if (emailConfirmado == "") {
                    BRANCO
                } else CADASTRADO
            } else {
                if (emailConfirmado == "") {
                    return APAGADO
                }
                if (emailCadastrado == "") {
                    INSERIDO
                } else EDITADO
            }
        }
    }
}
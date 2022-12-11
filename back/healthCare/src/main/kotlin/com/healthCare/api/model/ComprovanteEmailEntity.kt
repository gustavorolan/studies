package com.healthCare.api.model

import java.util.*
import javax.persistence.*

@Entity
data class ComprovanteEmailEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,

    @Column(nullable = false) val ordemDeCobranca: String,

    @Column(nullable = false) val email: String = "",

    @Column(nullable = false) val documentoCliente:String = "",

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    val tipoEmail: TipoEmail,

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    val status: StatusMensagem,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private val dataMovimento: Date? = Date(),

    @Column(nullable = false)
    private val retransmissoes: Int? = 0
)
package com.healthCare.api.model

enum class StatusMensagem(val externalId: Int, val descricao: String) {
    AGUARDANDO(1, "Aguardando"), LIBERADO(2, "Liberado"), ENVIANDO(3, "Enviando"), ENVIADA_COM_SUCESSO(4, "Enviada com sucesso"), ERRO(5, "Erro"), ERRO_RETRANSMISSOES_EXCEDIDAS(6, "Erro, retransmissoes excedidas"), ERRO_NAO_RETRANSMITIR(7, "Erro, não retransmitir");
}
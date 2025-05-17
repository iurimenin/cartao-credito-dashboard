package com.cartao.models

import kotlinx.serialization.Serializable

@Serializable
data class TransacaoModel(
    val data: String,
    val descricao: String,
    val valor: Double,
    val nome: String,
)

@Serializable
data class TransacaoAgrupada(
    val descricao: String,
    val valorTotal: Double
)

@Serializable
data class DadosAgrupados(
    val transacoes: List<TransacaoAgrupada>
) 
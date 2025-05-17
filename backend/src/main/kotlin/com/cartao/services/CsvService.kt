package com.cartao.services

import com.cartao.models.TransacaoModel
import com.cartao.models.TransacaoAgrupada
import com.cartao.models.DadosAgrupados
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.InputStream

class CsvService {
    fun processarCsv(input: InputStream): DadosAgrupados {

        val csv = String(input.readAllBytes())

        // Pular o cabeçalho e começar da linha 21
        val transacoes = csv.reader().readLines().drop(20)
            .filter { it.isNotBlank() }
            .map { linha ->
                //Data;Descrição;Parcela;Valor;Valor em Dólar;Adicional;Nome;
                val dados = linha.split(";")
                TransacaoModel(
                    data = dados[0],
                    descricao = dados[1].trim(),
                    valor = dados[3]
                        .replace("\"", "")
                        .replace("R$ ", "")
                        .replace(".", "")
                        .replace(",", ".")
                        .trim()
                        .toDoubleOrNull() ?: 0.0,
                    nome = dados[6]
                )
            }

        // Agrupar por descrição
        val transacoesAgrupadas = transacoes
            .filter { it.valor > 0.0 }
            .groupBy { it.descricao }
            .map { (descricao, grupo) ->
                TransacaoAgrupada(
                    descricao = descricao,
                    valorTotal = grupo.sumOf { it.valor }
                )
            }
            .sortedByDescending { it.valorTotal }

        return DadosAgrupados(transacoesAgrupadas)
    }
} 
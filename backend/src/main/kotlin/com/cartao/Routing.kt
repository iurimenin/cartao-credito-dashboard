package com.cartao

import com.cartao.services.CsvService
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val csvService = CsvService()

    routing {
        post("/processar-csv") {
            val multipart = call.receiveMultipart()
            multipart.forEachPart { part ->
                if (part is PartData.FileItem) {
                    try {
                        val dadosAgrupados = csvService.processarCsv(part.streamProvider())
                        call.respond(dadosAgrupados)
                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            mapOf("erro" to "Erro ao processar arquivo: ${e.message}")
                        )
                    }
                }
                part.dispose()
            }
        }
    }
} 
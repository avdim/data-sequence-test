package org.sample

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.DefaultHeaders
import io.ktor.http.content.*
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.io.File

fun startSimpleServer(port:Int) {
  embeddedServer(Netty /*io.ktor.server.cio.CIO*/, port, configure = {
      //конфигурация может быть специфичная для Netty или CIO
      connectionGroupSize
      workerGroupSize
      callGroupSize
  }) {
      install(DefaultHeaders) {

      }
      routing {
          get("/healthz") {
              call.respondText("ok")
          }
          get("/hello") {
              call.respondText { "Hello from Kotlin server" }
          }
          static("/") {
              if (true) {//in jar resources
                  resources("")
                  defaultResource("index.html")
              } else {//in dirs
                  staticRootFolder = listOf(
                          File("src/main/resources"),
                          File("1_jvm_template/src/main/resources")
                  ).first { it.exists() }
                  files(".")
                  default("index.html")
              }
          }
      }
  }.start(wait = false)
}

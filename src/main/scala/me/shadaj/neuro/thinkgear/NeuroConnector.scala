package me.shadaj.neuro.thinkgear

import scala.util.parsing.json.JSONObject
import java.io.OutputStreamWriter
import java.net.Socket
import java.io.BufferedReader
import java.io.InputStreamReader
import Parser._

class NeuroConnector(rawOutput: Boolean = false, jsonFormat: Boolean = true, host: String = "127.0.0.1", port: Int = 13854) extends Iterator[String] {
  val neuroSocket = new Socket(host, port)
  val neuroInput = new BufferedReader(new InputStreamReader(neuroSocket.getInputStream))
  val neuroOutput = new OutputStreamWriter(neuroSocket.getOutputStream)

  def configure(rawOutput: Boolean = false, jsonFormat: Boolean = true) {
    val jsonConfig = new JSONObject(Map("enableRawOutput" -> rawOutput,
      "format" -> (if (jsonFormat) "Json" else "BinaryPacket")))
    println(jsonConfig.toString())
    neuroOutput.write(jsonConfig.toString())
    neuroOutput.flush()
  }

  configure(rawOutput, jsonFormat)

  def hasNext = true
  
  def next = {
    neuroInput.readLine
  }
}
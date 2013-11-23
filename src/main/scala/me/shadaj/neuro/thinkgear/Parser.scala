package me.shadaj.neuro.thinkgear

import scala.util.parsing.json.JSON

import language.implicitConversions

object Parser {
  def parse(input: String): NeuroData = {
    val parsedOption = JSON.parseFull(input)
    if (parsedOption.isDefined) {
      val parsed = parsedOption.get.asInstanceOf[Map[String, Any]]
      if (parsed.isDefinedAt("eSense")) {
        parseEEG(parsed)
      } else if (parsed.isDefinedAt("poorSignalLevel")) {
        parsePoorSignal(parsed)
      } else if (parsed.isDefinedAt("blinkStrength")) {
        parseBlink(parsed)
      } else {
        RawData(parsed)
      }
    } else {
      new PoorSignalLevel(200)
    }

  }

  private def value(jsonMap: Map[String, Any], key: String, defaultValue: Int = 0): Int = {
    jsonMap.getOrElse(key, defaultValue).toString.toDouble.toInt
  }

  def parseBlink(jsonData: Map[String, Any]) = {
    Blink(NeuroData.value(jsonData, "blinkStrength"))
  }

  def parseESense(jsonData: Map[String, Any]): ESense = {
    val attention = value(jsonData, "attention")
    val meditation = value(jsonData, "meditation")
    ESense(attention, meditation)
  }

  def parseEEGPower(jsonMap: Map[String, Any]): EEGPower = {
    val delta = value(jsonMap, "delta")
    val theta = value(jsonMap, "theta")
    val lowAlpha = value(jsonMap, "lowAlpha")
    val highAlpha = value(jsonMap, "highAlpha")
    val lowBeta = value(jsonMap, "lowBeta")
    val highBeta = value(jsonMap, "highBeta")
    val lowGamma = value(jsonMap, "lowGamma")
    val highGamma = value(jsonMap, "highGamma")

    EEGPower(delta, theta, lowAlpha, highAlpha, lowBeta, highBeta, lowGamma, highGamma)
  }
  def parseEEG(jsonData: Map[String, Any]): EEG = {
    val senseMap = jsonData.getOrElse("eSense", Map[String, Any]()).asInstanceOf[Map[String, Any]]
    val sense = parseESense(senseMap)
    val powerMap = jsonData.getOrElse("eegPower", Map[String, Any]()).asInstanceOf[Map[String, Any]]
    val power = parseEEGPower(powerMap)
    val poorSignalLevel = PoorSignalLevel(value(jsonData, "poorSignalLevel", 200))
    EEG(sense, power, poorSignalLevel)
  }

  def parsePoorSignal(jsonData: Map[String, Any]): PoorSignalLevel = {
    PoorSignalLevel(value(jsonData, "poorSignalLevel"))
  }
  
  implicit def convertToNeuroData(string: String): NeuroData = parse(string)
}
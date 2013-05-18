package me.shadaj.neuro.thinkgear

class NeuroData

object NeuroData {
  def value(jsonMap: Map[String, Any], key: String, defaultValue: Int = 0): Int = {
    jsonMap.getOrElse(key, defaultValue).toString.toDouble.toInt
  }
}
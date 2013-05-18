package me.shadaj.neuro.thinkgear

case class RawData(level: Int) extends NeuroData

object RawData {
  import NeuroData._
  def apply(jsonData: Map[String, Any]): RawData = {
    apply(value(jsonData, "rawEeg"))
  }
}
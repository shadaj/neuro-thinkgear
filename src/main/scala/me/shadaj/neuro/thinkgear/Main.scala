package me.shadaj.neuro.thinkgear

import scala.concurrent.ExecutionContext.Implicits.global

object Main {
	def main(args: Array[String]): Unit = {
	  NeuroObserver().take(10).subscribe(data => println(data))
		val neuroInput = (new NeuroConnector(true)).map(Parser.parse)
	}
}
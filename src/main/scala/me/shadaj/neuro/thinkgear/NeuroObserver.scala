package me.shadaj.neuro.thinkgear

import java.io.IOException
import java.net.ConnectException

import scala.concurrent.ExecutionContext

import rx.lang.scala.Observable
import rx.lang.scala.subscriptions.MultipleAssignmentSubscription

object NeuroObserver {
  def apply(rawOutput: Boolean = false,
    jsonFormat: Boolean = true,
    host: String = "127.0.0.1",
    port: Int = 13854)(implicit context: ExecutionContext): Observable[NeuroData] = {
    Observable[NeuroData] { observer =>
      var reading = true
      try {
        val reader = new Runnable {
            val connector = new NeuroConnector(rawOutput, jsonFormat, host, port)
            override def run() {
              while (reading) {
                val nextLine = connector.next
                if (nextLine == null) {
                  observer.onError(new IOException("ThinkGearConnector has been closed"))
                } else {
                  observer.onNext(Parser.parse(nextLine))
                }
              }
            }
          }
        context.execute(reader)
        MultipleAssignmentSubscription(reading = false)
      } catch {
        case e: ConnectException => observer.onCompleted(); MultipleAssignmentSubscription()
      }
    }
  }
}
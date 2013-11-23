package me.shadaj.neuro.thinkgear


object Main {

	def main(args: Array[String]): Unit = {
		val neuroInput = new NeuroIterator(true)

		for( i <- 0 to 1000) {
			val input = neuroInput.next
			println(input)
		}
	}
	
}
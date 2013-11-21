package me.shadaj.neuro.thinkgear


object Main {

	def main(args: Array[String]): Unit = {
		val neuroInput = new NeuroIterator
		neuroInput.configure()

		for( i <- 0 to 100) {
			val input = neuroInput.next
			println(input)
		}
	}
	
}
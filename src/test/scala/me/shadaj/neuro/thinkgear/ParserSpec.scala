import org.scalatest.WordSpec
import org.scalatest.Matchers
import me.shadaj.neuro.thinkgear._

class ParserSpec extends WordSpec with Matchers {

  "A json string" when {
    """{"poorSignalLevel":200}""" should {
      val json = """{"poorSignalLevel":200}"""
      "be parsed in PoorSignalLevel(200)" in {
		Parser.parse(json) shouldBe PoorSignalLevel(200)
	  }
    }

    """{"blinkStrength":41}""" should {
      val json = """{"blinkStrength":41}"""
      "be parsed in Blink(41)" in {
	    Parser.parse(json) shouldBe Blink(41)
	  }
    }

    """{"rawEeg":-123}""" should {
      val json = """{"rawEeg":-123}"""
      "be parsed in RawData(-123)" in {
	    Parser.parse(json) shouldBe RawData(-123)
	  }
    }

    """{"eSense":{"attention":75,"meditation":38},"eegPower":{"delta":247328,"theta":98222,"lowAlpha":27763,"highAlpha":6216,"lowBeta":9065,"highBeta":12236,"lowGamma":7982,"highGamma":5343},"poorSignalLevel":0}""" should {
      val json = """{"eSense":{"attention":75,"meditation":38},"eegPower":{"delta":247328,"theta":98222,"lowAlpha":27763,"highAlpha":6216,"lowBeta":9065,"highBeta":12236,"lowGamma":7982,"highGamma":5343},"poorSignalLevel":0}"""
      "be parsed in " in {
	    Parser.parse(json) shouldBe EEG(ESense(75,38),EEGPower(247328,98222,27763,6216,9065,12236,7982,5343),PoorSignalLevel(0))
	  }
    }

    """{"eSense":{"attention":90,"meditation":47},"eegPower":{"delta":5614,"theta":7445,"lowAlpha":1165,"highAlpha":2645,"lowBeta":4108,"highBeta":15870,"lowGamma":4043,"highGamma":3281},"poorSignalLevel":0}""" should {
      val json = """{"eSense":{"attention":90,"meditation":47},"eegPower":{"delta":5614,"theta":7445,"lowAlpha":1165,"highAlpha":2645,"lowBeta":4108,"highBeta":15870,"lowGamma":4043,"highGamma":3281},"poorSignalLevel":5}"""
      "be parsed in " in {
		Parser.parse(json) shouldBe EEG(ESense(90,47),EEGPower(5614,7445,1165,2645,4108,15870,4043,3281),PoorSignalLevel(5))
	  }
    }
    
  }

}
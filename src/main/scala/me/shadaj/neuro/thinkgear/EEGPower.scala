package me.shadaj.neuro.thinkgear

case class EEGPower(
  delta: Int,
  theta: Int,
  lowAlpha: Int, highAlpha: Int,
  lowBeta: Int, highBeta: Int,
  lowGamma: Int, highGamma: Int
) extends NeuroData
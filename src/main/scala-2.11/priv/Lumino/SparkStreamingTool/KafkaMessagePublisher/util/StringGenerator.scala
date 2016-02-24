package priv.Lumino.SparkStreamingTool.KafkaMessagePublisher.util

import scala.collection.mutable.ListBuffer

/**
  * Created by kufu on 16-1-26.
  */
class StringGenerator {
  val list = {
    val builder = new ListBuffer[Char]
    for(i<-0 until 26){
      builder+=('a'.toInt+i).toChar
    }
    builder.toList
  }

  var index1 = 0
  var index2 = 0
  var num = StringGenerator.NumberBase

  def next():String = {
    val stringBuilder = new StringBuilder
    for(i<-0 until nextInt()){
      if(i>0)
        stringBuilder.append(' ')
      stringBuilder.append(list(index1))
      stringBuilder.append(list(index2))
      index1 = (index1+1)%list.size
      index2 = (index2+3)%list.size
    }
    stringBuilder.toString()
  }

  def nextInt():Int ={
    num = (num+1)%StringGenerator.NumberRange+StringGenerator.NumberBase
    num
  }
}

object StringGenerator{
  val CharNum = 5
  val NumberBase = 5
  val NumberRange = 10
}
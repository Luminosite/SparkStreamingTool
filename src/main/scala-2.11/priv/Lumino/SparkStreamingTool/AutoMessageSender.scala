package priv.Lumino.SparkStreamingTool

import priv.Lumino.SparkStreamingTool.KafkaMessagePublisher.KafkaPublisher
import priv.Lumino.SparkStreamingTool.KafkaMessagePublisher.util.StringGenerator

import scala.collection.mutable.ListBuffer

/**
  * Created by kufu on 16-1-26.
  */
object AutoMessageSender {
  def main (args: Array[String]) {
    if(args.length<2){
      println("Usage:\n\tmessage_sender topic broker...")
    }else{
      var isTopic = true
      var topic:String = null
      var brokers = new ListBuffer[String]
      args.foreach(str=>{
        if(isTopic){
          topic = str
          isTopic=false
        }else{
          brokers+=str
        }
      })
      val publisher = new KafkaPublisher(brokers.toList, topic)
      try{
        val gen = new StringGenerator
        while(true){
          val message = gen.next()
          println(message)
          publisher.send(message)
          Thread.sleep(3000)
        }
      }finally {
        publisher.close()
      }
    }
  }
}

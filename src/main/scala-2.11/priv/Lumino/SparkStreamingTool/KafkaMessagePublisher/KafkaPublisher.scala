package priv.Lumino.SparkStreamingTool.KafkaMessagePublisher

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord

/**
  * Created by kufu on 16-1-26.
  */
class KafkaPublisher(brokerList:List[String], topic:String) {

  var producer:Producer[String, String] = {

    val props = new java.util.Properties()
    val list:java.util.ArrayList[String] = new java.util.ArrayList[String]()
    brokerList.foreach(location=>list.add(location))
    props.put("bootstrap.servers", list)

    //details configuration
    props.put("acks", "all")
    props.put("retries", new Integer(0))
    props.put("batch.size", new Integer(16384))
    props.put("linger.ms", new Integer(1))
    props.put("buffer.memory", new Integer(33554432))

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    new KafkaProducer(props)
  }

  def send(message:String): Unit ={
    val key = "timestamp:"+System.currentTimeMillis()
    producer.send(new ProducerRecord[String, String](topic, key, message))
  }

  def close(): Unit ={
    producer.close()
  }
}

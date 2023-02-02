package com.study.demo.controller;/**
 * @author caonan
 * @Date 2022/10/25 17:53
 * @Description
 */

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author caonan
 * @Date 2022/10/25 17:53
 * @Description
 */
@Controller
@RequestMapping("/kafka")
public class KafkaController {
    @RequestMapping(value = "getProducer",method = RequestMethod.GET)
    public boolean getProducer(@RequestParam("id") String id){
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.44.237.90:9093,10.44.237.91:9094");
        props.put("acks", "all");
        props.put("retries", "0");
        props.put("batch.size", "163");
        props.put("linger.ms", 1);
        props.put("buffer.memory", "33554432");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("timeout.ms", 30000);
        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 100; i++){
            try {
                //test_zookeeper
                RecordMetadata recordMetadata = producer.send(new ProducerRecord<String, String>("test", "producer" + id + Integer.toString(i))).get();
                System.out.println(recordMetadata.offset());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.close();
        return true;
    }
    @RequestMapping(value = "getTest",method = RequestMethod.GET)
    @ResponseBody
    public Map<Integer,Integer> getTest(@RequestParam("id") int id){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i <= 20; i++){
            for (int j= 0;j<=1000000;j++) {
                if ("44.444.444.444".equals("4.156.96.206")){
                    map.put(i,j);
                }
            }
        }
        return map;
    }
}

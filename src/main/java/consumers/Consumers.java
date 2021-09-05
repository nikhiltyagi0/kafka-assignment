package consumers;


import Model.Input;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Consumers {

    public static void main(String[] args) {
        ConsumerListener obj = new ConsumerListener();
        Thread thread = new Thread(obj);
        thread.start();
    }

    public static void consumers() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-group");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer(properties);
        String topics = "user";
        kafkaConsumer.subscribe(Arrays.asList(topics));
        try {
            // Message1
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(10000);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());

                    BufferedWriter buffer = new BufferedWriter(new FileWriter("output.txt", true));
                    buffer.write(record.value() + "\n");
                    buffer.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            kafkaConsumer.close();
        }
    }
}


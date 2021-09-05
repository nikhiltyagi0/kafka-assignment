package Producers;


import Model.Input;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class Producer {
    public static void main(String[] args) {



        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Random random = new Random();
        int minAge = 18;
        int maxAge = 28;
        int age;

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        try {
            for (int i = 0; i < 4; i++) {
                age = random.nextInt((maxAge - minAge) + 1) + minAge;
                Input user = new Input(i, "NIKHIL TYAGI",age, "BTech");
                String userData= ("{\"Id\":"+"\""+Integer.toString(user.getId())+"\","+"\"Name\":"+"\""+user.getName()+"\","+"\"Age\":"+"\""+Integer.toString(user.getAge())+"\","+"\"Course\":"+"\""+user.getCourse()+"\"}");
                System.out.println(userData);
                kafkaProducer.send(new ProducerRecord("user", userData));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}


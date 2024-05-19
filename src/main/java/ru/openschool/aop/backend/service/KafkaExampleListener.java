package ru.openschool.aop.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.model.Bar2;
import ru.openschool.aop.backend.model.Foo1;
import ru.openschool.aop.backend.model.Foo2;

import java.util.List;

@Service
@Slf4j
//@KafkaListener(id = "multiGroup", topics = {"foos", "bars"})
@RequiredArgsConstructor
public class KafkaExampleListener {

    private final KafkaTemplate<Object, Object> template;

//    @KafkaListener(id = "fooGroup", topics = "topic1")
//    public void listen(Foo2 foo2) {
//        log.info("Received: {}", foo2);
//        if (foo2.getFoo().startsWith("fail")) {
//            throw new RuntimeException();
//        }
//        log.info("OK");
//    }

    @KafkaListener(id = "fooGroup1", topics = "topic1")
    public void listen1(List<Foo2> foos2) {
        log.info("Received listen1: {}", foos2);

        foos2.forEach(foo2 -> template.send("topic2", foo2.getFoo().toUpperCase()));
        log.info("Message send");
    }

    @KafkaListener(id = "foosId", topics = "foos")
    public void listen1(Foo1 foo1) {
        log.info("==========  Received listen1: {}", foo1);
    }

    @KafkaListener(id = "fooGroup2", topics = "topic2")
    public void listen2(List<String> in) {
        log.info("Received listen2: {}", in);
    }

    @KafkaListener(id = "dltGroup", topics = "topic1.DLT")
    public void dltListen(byte[] in) {
        log.info("Received from DLT: {}", new String(in));
    }

    @KafkaHandler
    public void foo(Foo2 foo2) {
        log.info("Received Foo2: {}", foo2);
    }

    @KafkaHandler
    public void bar(Bar2 bar2) {
        log.info("Received Bar2: {}", bar2);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.info("Received unknown: {}", object);
    }

    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2000, maxDelay = 10000, multiplier = 2))
    @KafkaListener(id = "fooGroup", topics = "topic3")
    public void listen3(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Received from listen3: {}, topic: {}, offset: {}", in, topic, offset);
        if (in.startsWith("fail")) {
            throw new RuntimeException("failed");
        }
    }

    @DltHandler
    public void listenDlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Received from DLT: {}, topic: {}, offset: {}", in, topic, offset);
    }
}

package com.srini.kafka.streams;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * The type Stream processor.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class StreamProcessor {

    private final Properties streamProps ;

    private final Predicate<String, String> predicate = (s, s2) -> "HDFC".equals(s) || "TCS".equals(s) ;
    /**
     * Build topology and start stream.
     */
    public void buildTopologyAndStartStream(){


        StreamsBuilder streamsBuilder = new StreamsBuilder() ;

        KTable<String, String> kt0 = streamsBuilder.table("test-topic-2") ;
        kt0.toStream().print(Printed.<String,String>toSysOut().withLabel("KT0"));

        KTable<String,String> kt1 = kt0.filter(predicate, Materialized.as("StockStore")) ;

        kt1.toStream().print(Printed.<String,String>toSysOut().withLabel("KT1"));

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), streamProps);
        streams.start();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Stopping Stream");
            streams.close();
        }));


    }
}

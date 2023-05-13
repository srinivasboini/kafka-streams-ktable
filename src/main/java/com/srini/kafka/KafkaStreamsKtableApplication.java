package com.srini.kafka;

import com.srini.kafka.streams.StreamProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class KafkaStreamsKtableApplication implements CommandLineRunner {
	private final StreamProcessor streamProcessor ;

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsKtableApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		streamProcessor.buildTopologyAndStartStream();
	}
}

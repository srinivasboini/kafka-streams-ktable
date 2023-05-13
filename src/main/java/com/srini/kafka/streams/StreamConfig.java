package com.srini.kafka.streams;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Stream config.
 */
@Configuration
@ConfigurationProperties(prefix = "kafka.streams")
@Data
public class StreamConfig  {

    private String applicationId ;
    private String bootstrapServers ;
    private String securityProtocol ;
    private int numberOfThreads ;
    private String saslMechanism ;
    private String saslJaasConfig ;
    private String schemaRegistryUrl ;
    private String schemaAuth ;
    private String schemaAuthInfo ;


}

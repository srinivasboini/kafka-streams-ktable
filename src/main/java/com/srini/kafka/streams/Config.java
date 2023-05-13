package com.srini.kafka.streams;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * The type Config.
 */
@Configuration
@RequiredArgsConstructor
public class Config {


    private final StreamConfig streamConfig ;

    /**
     * Stream props properties.
     *
     * @return the properties
     */
    @Bean
    public Properties streamProps(){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, streamConfig.getApplicationId());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, streamConfig.getBootstrapServers());
        props.put(StreamsConfig.SECURITY_PROTOCOL_CONFIG, streamConfig.getSecurityProtocol());
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, streamConfig.getNumberOfThreads());
        props.put(SaslConfigs.SASL_MECHANISM, streamConfig.getSaslMechanism());
        props.put(SaslConfigs.SASL_JAAS_CONFIG, streamConfig.getSaslJaasConfig() );
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass()) ;
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass()) ;
        props.put("schema.registry.url", streamConfig.getSchemaRegistryUrl());
        props.put("basic.auth.credentials.source", streamConfig.getSchemaAuth());
        props.put("basic.auth.user.info", streamConfig.getSchemaAuthInfo());
        return props ;
    }


}

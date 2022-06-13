package hu.pilopsoft.demo.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.SneakyThrows;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetAddress;

@Configuration
public class PrometheusConfiguration {

    @SneakyThrows({IOException.class})
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(RomProperties romProperties) {
        final String[] tags = new String[]{"application", "ROM", "machine", InetAddress.getLocalHost().getHostName(), "env", romProperties.getEnvironment()};
        return registry -> registry.config().commonTags(tags);
    }

}

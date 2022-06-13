package hu.pilopsoft.demo.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RomProperties.class)
public class RomConfiguration {
}

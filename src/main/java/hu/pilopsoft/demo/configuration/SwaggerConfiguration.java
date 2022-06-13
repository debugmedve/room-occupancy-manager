package hu.pilopsoft.demo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springOpenApi(BuildProperties buildProperties) {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("ROM")
                                .description("room occupancy manager")
                                .contact(
                                        new Contact()
                                                .name("Zoltan Medve")
                                                .url("https://pilopsoft.hu")
                                                .email("zoltan.medve@pilopsoft.hu")
                                )
                                .version(buildProperties.getVersion())
                );
    }
}

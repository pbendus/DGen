package ui.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ui")
@PropertySource("classpath:ui.properties")
public class AppConfig {
}

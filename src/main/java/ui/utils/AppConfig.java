package ui.utils;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("ui.controllers")
@PropertySource("classpath:ui.properties")
public class AppConfig {
}

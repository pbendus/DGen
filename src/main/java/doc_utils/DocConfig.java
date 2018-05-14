package doc_utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("doc_utils")
@PropertySource("classpath:doc.properties")
public class DocConfig {
}

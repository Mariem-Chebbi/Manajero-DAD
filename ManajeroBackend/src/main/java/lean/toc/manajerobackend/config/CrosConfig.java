package lean.toc.manajerobackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // tous les endpoints
                        .allowedOrigins("http://localhost:4200")  // L'origine
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Les méthodes HTTP autorisées
                        .allowedHeaders("*")  // Les headers autorisés
                        .allowCredentials(true);  // Autoriser l'envoi des cookies avec les requêtes
            }
        };
    }

}

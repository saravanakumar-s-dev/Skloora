package org.newfort.sk.Skloora.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/skloora-post-media/**")
                .addResourceLocations("file:D:/Senthil/Personal/SARAVANAKUMAR/programs/Java/Spring/Skloora/skloora-post-media/");
    }
}
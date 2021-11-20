package fr.ufo.bot.spring.tagall.application;

import fr.ufo.bot.spring.tagall.config.TagallConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TagallConfiguration.class)
public class TagallApplication
{
    public static void main(final String[] args)
    {
        SpringApplication.run(TagallApplication.class, args);
    }
}

package fr.ufo.bot.spring.tagall.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TagallApplication.class)
public class TagallApplication
{
    public static void main(final String[] args)
    {
        SpringApplication.run(TagallApplication.class, args);
    }
}

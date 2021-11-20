package fr.ufo.bot.spring.tagall.config;

import com.linecorp.bot.client.LineMessagingClient;
import fr.ufo.bot.spring.tagall.service.TagallService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration spring de l'application
 *
 * @author mcmido
 */
@Configuration
public class TagallConfiguration
{

    /**
     * Constructeur par défaut
     */
    public TagallConfiguration()
    {
        super();
    }

    /**
     * @return un bean permettant de gérer la commande tagall
     */
    @Bean
    public TagallService tagallService(final LineMessagingClient lineMessagingClient)
    {
        return new TagallService(lineMessagingClient);
    }

}

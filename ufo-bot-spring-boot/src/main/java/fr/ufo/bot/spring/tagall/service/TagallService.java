package fr.ufo.bot.spring.tagall.service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.MembersIdsResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ExecutionException;

/**
 * Service permettant de gérer la commande tag
 *
 * @author mcmido
 */
@LineMessageHandler
public class TagallService
{
    private final Logger log = LoggerFactory.getLogger(TagallService.class);

    private final LineMessagingClient lineMessagingClient;

    /**
     * Constructeur par défaut
     *
     * @param lineMessagingClient client permettant de réaliser les appels à l'API
     */
    public TagallService(final LineMessagingClient lineMessagingClient)
    {
        super();
        this.lineMessagingClient = lineMessagingClient;
    }

    @EventMapping
    public Message handleTextMessageEvent(final MessageEvent<TextMessageContent> event)
    {
        if(event.getMessage().getText() != null && event.getMessage().getText().equals("/tagall"))
        {
            log.info("TAGALL : {}", event);
            if(event.getSource() instanceof GroupSource)
            {
                log.info("Message d'un groupe !");
                final GroupSource groupSource = (GroupSource) event.getSource();
                try
                {
                    final MembersIdsResponse membersIdsResponse = lineMessagingClient.getGroupMembersIds(
                                                                                             groupSource.getGroupId(),
                                                                                             null
                                                                                                        )
                                                                                     .get();
                }
                catch(final InterruptedException | ExecutionException e)
                {
                    log.error("Problème pour récupérer les utilisateurs du groupe !", e);
                }

            }
            else if(event.getSource() instanceof RoomSource)
            {
                log.info("Message d'une room !");
                final RoomSource roomSource = (RoomSource) event.getSource();
            }
            else
            {
                log.error("Impossible de savoir si c'est une room ou un groupe !");
            }
        }

        final String originalMessageText = "[Ufo bot] @\uD83C\uDDEB\uD83C\uDDF7McMido|uF0#336 " + event.getMessage()
                                                                                                       .getText();
        return new TextMessage(originalMessageText);
    }

    @EventMapping
    public void handleDefaultMessageEvent(final Event event)
    {
        System.out.println("event: " + event);
    }
}

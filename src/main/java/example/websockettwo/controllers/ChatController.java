package example.websockettwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
// import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import example.websockettwo.entities.ChatMessage;
import example.websockettwo.services.ChatMessageService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate messageTemplate;
    @Autowired
    private ChatMessageService messageService;

    @MessageMapping("/chat/sendMessage")
    // @SendTo("/topic/public")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        saveAndSendChatMessage(chatMessage);
    }

    @MessageMapping("/chat/addUser")
    // @SendTo("/topic/public")
    public void addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        getAllChatMessage();
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        logger.info("add user " + chatMessage.getSender());
        
        
        saveAndSendChatMessage(chatMessage);
    }

    public void getAllChatMessage() {
        List<ChatMessage> messageInDb = messageService.getAllMessage();
        if(messageInDb != null && messageInDb.size() > 0) {
            for(ChatMessage i : messageInDb) {
                sendChatMessageByTemplate(i);
            }
        }
    }

    public void saveAndSendChatMessage(ChatMessage chatMessage) {
        saveChatMessage(chatMessage);
        sendChatMessageByTemplate(chatMessage);
    }

    public void saveChatMessage(ChatMessage chatMessage) {
        messageService.addMessage(chatMessage);
    }

    public void sendChatMessageByTemplate(ChatMessage chatMessage) {
        messageTemplate.convertAndSend("/topic/public", chatMessage);
    }

    /**
     * @param messageTemplate the messageTemplate to set
     */
    public void setMessageTemplate(SimpMessagingTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    
}

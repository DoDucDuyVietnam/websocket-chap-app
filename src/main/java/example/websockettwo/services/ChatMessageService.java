package example.websockettwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.websockettwo.dao.ChatMessageDAO;
import example.websockettwo.entities.ChatMessage;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageDAO messageDAO;

    public void addMessage(ChatMessage chatMessage) {
        messageDAO.save(chatMessage);
    }

    public List<ChatMessage> getAllMessage() {
        return messageDAO.findAll();
    }

    /**
     * @param messageDAO the messageDAO to set
     */
    public void setMessageDAO(ChatMessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }
}
package example.websockettwo.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import example.websockettwo.entities.ChatMessage;

@Repository
public interface ChatMessageDAO extends MongoRepository<ChatMessage, ObjectId> {
    
}
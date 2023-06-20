package ra.model.service.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ra.model.entity.ChatMessage;

@Service
public class ChatService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public ChatMessage receiveGroupChat(@Payload ChatMessage message) {
        return message;
    }
    public ChatMessage receviePrivateChat(@Payload ChatMessage message) {
        simpMessagingTemplate.convertAndSendToUser(message.getSender(), "private", message);
        return message;
    }
}

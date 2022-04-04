package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import platform.webapplication.model.Message;
import platform.webapplication.repository.MessageRepository;
import platform.webapplication.repository.OrderRepository;

import java.util.ArrayList;

public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public ArrayList<Message> findAll() {

        var it = messageRepository.findAll();

        var messages = new ArrayList<Message>();
        it.forEach(e -> messages.add(e));

        return messages;
    }

    public Long count() {

        return messageRepository.count();
    }

    public void deleteById(Integer messageId) {
        messageRepository.deleteById(messageId);
    }
}

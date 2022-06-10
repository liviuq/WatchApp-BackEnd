package platform.webapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import platform.webapplication.entities.Message;
import platform.webapplication.repository.MessageRepository;
import platform.webapplication.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    MessageRepository messageRepository;
    MessageService messageService;
    List<Message> messageList;

    @BeforeEach
    void setUp() {
        messageList = new ArrayList<>();
        this.messageRepository = mock(MessageRepository.class);
        when(messageRepository.findAll()).thenReturn(messageList);
        doNothing().when(messageRepository).deleteById(anyInt());
        this.messageService = new MessageService(messageRepository);
    }

    @Test
    void findAll() {
        //Arrage
        messageList.add(new Message(1, 5, "hello"));
        messageList.add(new Message(4, 3, "bye"));
        messageList.add(new Message(9, 7, "good morning"));
        messageList.add(new Message(2, 4, "good night"));

        //Act
        var actual = messageService.findAll();

        //Assert
        assertEquals(messageList, actual);
    }

    @Test
    void deleteById() {
        //Act
        messageService.deleteById(anyInt());
        //Assert
        verify(messageRepository, times(1)).deleteById(anyInt());
    }
}
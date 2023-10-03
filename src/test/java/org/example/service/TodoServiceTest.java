package org.example.service;

/* 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Optional;

import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void testAdd() {
       
        when(this.todoRepository.save(any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");

        TodoEntity actual = this.todoService.add(expected);
        
       
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void testSearchById() {
        TodoEntity Entity = new TodoEntity();
        Entity.setId(123L);
        Entity.setTitle("title");
        Entity.setOrder(0L);
        Entity.setCompleted(false);
        Optional<TodoEntity> Optional = Optional.of(Entity);
        
        given(this.todoRepository.findById(anyLong()))
                .willReturn(Optional);

        TodoEntity actual = this.todoService.searchById(123L);
        TodoEntity expected = Optional.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());

    }

    @Test
    public void searchByIdFailed() {
        given(this.todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        
        assertEquals(ResponseStatusException.class, () -> {
            this.todoService.searchById(123L);
        });
    }
}
*/
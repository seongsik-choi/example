package org.example.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
// test 필요시, Go to Test -> test 적용 원하는 method 선택 -> junit
public class TodoService {
    
    private final TodoRepository todoRepository;
    //1 리스트 목록에 아이템을 추가
    //2 리스트 목록 중 특정 아이템을 조회
    //3 리스트 전체 목록을 조회
    //4 리스트 목록 중 특정 아이템을 수정
    //5 리스트 목록 중 특정 아이템을 삭제
    //6 리스트 전체 목록을 삭제

    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle()); 
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        // JpaRepository save는 generic 형태로 반환
        return this.todoRepository.save(todoEntity); 
    }

    public TodoEntity searchById(Long id) {
        // 404 Exception 처리
        return this.todoRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    } 

    public List<TodoEntity> serachALl() {
        return this.todoRepository.findAll();
    }

    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id);
        if (request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }

        if(request.getOrder() != null) {
            todoEntity.setOrder(request.getOrder());
        }

        if(request.getCompleted() != null) {
            todoEntity.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntity);
    }

    public void deleteById(Long id) { 
        this.todoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.todoRepository.deleteAll();
    }
}

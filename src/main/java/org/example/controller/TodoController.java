package org.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;
import org.example.service.TodoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/")
// https://www.todobackend.com/specs/index.html 테스트 프론트 등록
// https://www.todobackend.com/client/index.html?http://localhost:9098 // 프론트에 구축한 서버붙이기
public class TodoController {
    
    private final TodoService service;
    /**
     * 리스트 목록에 아이템 추가
     * @param request
     * @return
     */
    @PostMapping()
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        log.info("CREATE : " + request);
        // request : title=타이틀 order=3, completed=null
        
        // Execption
        if(ObjectUtils.isEmpty(request.getTitle())) {
            return ResponseEntity.badRequest().build();
        }
        if(ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }
        if(ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        TodoEntity result = this.service.add(request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    /**
     *  리스트 목록 중 특정 아이템을 조회 - 단건
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        log.info("Read One");
        TodoEntity result = this.service.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    /**
     * 리스트 전체 목록을 조회
     * @return response
     */
    @GetMapping(value = "/users")
    public ResponseEntity<List<TodoResponse>> readAll() {
        log.info("Read ALL");
        List<TodoEntity> list = this.service.serachALl();
        List<TodoResponse> responses = list.stream().map(TodoResponse::new)
                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * 리스트 목록 중 특정 아이템을 수정
     * @param id
     * @param request
     * @return
     */
    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        log.info("Update");
        TodoEntity result = this.service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }
    /**
     * 리스트 목록 중 특정 아이템을 삭제
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        log.info("DELETE id");
        this.service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 리스트 전체 목록을 삭제
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseEntity<?> deteAll() {
        log.info("Delete ALL");
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }
}

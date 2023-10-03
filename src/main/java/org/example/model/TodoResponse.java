package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    // TodoEntity를 parameters로 받는 생성자
    public TodoResponse(TodoEntity todoEntity) { 
        this.id  = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.completed = todoEntity.getCompleted();

        this.url = "http://localhost:9098/" + this.id;
    }

}

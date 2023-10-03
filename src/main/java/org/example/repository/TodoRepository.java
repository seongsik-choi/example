package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.model.TodoEntity;

// JpaRepository 를 상속 -> SimpleJpaRepository 사용
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}

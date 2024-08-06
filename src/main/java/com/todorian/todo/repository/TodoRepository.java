package com.todorian.todo.repository;

import com.todorian.todo.domain.Todo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {
    private final EntityManager em;

    public TodoRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Todo todo) {
        em.persist(todo);
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();
    }

    public void deleteById(Long todoId) {
        em.createQuery("select t from Todo t where t.id = :todoId");
    }
}

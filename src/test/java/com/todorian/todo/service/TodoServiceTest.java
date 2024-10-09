package com.todorian.todo.service;

import com.todorian.todo.application.service.TodoService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired
    private TodoService todoService;
    @Autowired
    private EntityManager em;
//
//    @BeforeEach
//    void setUp() {
//        Todo todo1 = new Todo("집가기");
//        Todo todo2 = new Todo("우유 사기");
//        em.persist(todo1);
//        em.persist(todo2);
//
//        em.flush();
//    }
//
//    private static Stream<Arguments> getTodo() {
//        return Stream.of(
//                Arguments.of(
//                        "오늘의 할일1"
//                ),
//                Arguments.of(
//                        "오늘의 할일2"
//                )
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("getTodo")
//    void createTodo(String todoContent) {
//        TodoCreateRequestDTO todoInfo = new TodoCreateRequestDTO(todoContent);
//        int size = todoService.findAllTodos().size();
//
//        Assertions.assertDoesNotThrow(() -> todoService.createTodo(todoInfo));
//        Assertions.assertEquals(size + 1, todoService.findAllTodos().size());
//    }
//
//    @ParameterizedTest
//    @ValueSource(longs = {1})
//    void findTodoById(Long todoId) {
//        Todo foundTodo = todoService.findTodoById(todoId).orElseThrow();
//
//        Assertions.assertNotNull(foundTodo);
//        Assertions.assertEquals(todoId, foundTodo.getTodoId());
//    }
//
//    @Test
//    void findAllTodos() {
//
//        List<Todo> todos = todoService.findAllTodos();
//
//        Assertions.assertNotNull(todos);
//    }
//
//    @Test
//    void deleteTodo() {
//        List<Todo> todos = todoService.findAllTodos();
//        Assertions.assertEquals(todos.size(), 2);
//        Long todo1Id = todos.get(0).getTodoId();
//
//        todoService.deleteTodo(todo1Id);
//
//        List<Todo> todos2 = todoService.findAllTodos();
//        Assertions.assertEquals(todos2.size(), 1);
//    }

}
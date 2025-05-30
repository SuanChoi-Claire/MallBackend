package org.zerock.mallapi.repository;

//import static org.mockito.ArgumentMatchers.isNull;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.MallapiApplication;
import org.zerock.mallapi.domain.Product;
import org.zerock.mallapi.domain.Todo;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(classes = MallapiApplication.class)
@Log4j2
public class TodoRepositoryTests {
    
    @Autowired
    private TodoRepository todoRepository;








    @Test
    public void testInsert(){
        for (int i = 1; i <= 100; i++){
            Todo todo = Todo.builder()
            .title("Title..." +i)
            .dueDate(LocalDate.of(2023,12,31))
            .writer("user00")
            .build();

            todoRepository.save(todo);
        }

    }

    @Test
    public void testRead(){
        Long tno = 33L;
        java.util.Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info(todo);
    }

    @Test
    public void testModify(){
        Long tno = 22L;
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow(()-> new RuntimeException("해당 Id의 Todo가 존재하지 않습니다."));
        todo.changeTitle("Modified 33 ...");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2023,10,10));

        todoRepository.save(todo);
    }

    @Test
    public void testDelete(){
        Long tno = 1L;
        todoRepository.deleteById(tno);
    }

    @Test
    public void testPaging(){

        Pageable pageable = PageRequest.of (0,10,Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);
        
        log.info(result.getTotalElements());
        result.getContent().stream().forEach(todo -> log.info(todo));
        

    }

}

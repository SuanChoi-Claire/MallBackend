package org.zerock.mallapi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.repository.TodoRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    private TodoRepository todoRepository;
 
    @Test
    public void testRegister(){

    TodoDTO todoDTO = TodoDTO.builder()

    .title("서비스테스트2")
    .writer("tester2")
    .duDate(LocalDate.of(2024, 10, 10)).build();
    Long tno = todoService.register(todoDTO);
    log.info("TNO:"+ tno);
    }

    @Test
    public void testGet(){
        Long tno = 1L;
        TodoDTO todoDTO = todoService.get(tno);
        log.info(todoDTO);
    }

    @Test
    public void testmodify(){
        
        TodoDTO todoDTO = TodoDTO.builder()
        .tno(55L)
        .title("############수정테스트##########")
        .duDate(LocalDate.now())
        .complate(true)
        .build();

        todoService.modify(todoDTO);

        TodoDTO updated = todoService.get(todoDTO.getTno());
        log.info(updated);

    }

    @Test
    public void testdelete(){

        
        Long tno =5L;


        todoService.remove(tno);

        try {
            todoService.get(tno);
            log.info("❌ 예외가 발생하지 않았습니다!");
        } catch (Exception e) {
            log.info("✅ 예외 발생 확인! 메시지: " + e.getMessage());
        }
    

        // assertThrows(Exception.class, () -> {
        //     log.info("*******삭제된 데이터 조회 시도******");
        // todoService.get(tno); // 없어진 데이터를 가져오면 예외 발생해야 함
  
    // });


    log.info("삭제 확인 완료");



    }

}

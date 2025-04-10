package org.zerock.mallapi.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*; 


@Entity
@Table(name = "tbl_todo")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;
    
    private String title;
    private String writer;
    private boolean complete;
    private LocalDate dueDate;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeComplete(boolean complate){
        this.complete = complate;
    }

    public void changeDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }


}

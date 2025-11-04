package com.tsiory.todo.service;

import com.tsiory.todo.models.Todo;
import com.tsiory.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    //CRUD
    //CREATE
    public void CreateTodo(String name ){
        this.todoRepository.save(new Todo(name));
    }

    //READ
    public List<Todo> GetAllTodo(){
        return this.todoRepository.findAll();
    }

    //UPDATE
    public void UpdateTodo(Long id, String name){
        Todo todoUpdate = this.todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todoUpdate.setTodoName(name);
        this.todoRepository.save(todoUpdate);
    }

    //DELETE
    public void DeleteTodo(Long id){
        Todo todoDelete = this.todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        this.todoRepository.delete(todoDelete);
    }

    //Toggle
    public void ToggleTodo(Long id){
        Todo todoToggle = this.todoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Todo not found"));
        todoToggle.setCompleted(!todoToggle.isCompleted());
        this.todoRepository.save(todoToggle);
    }
}

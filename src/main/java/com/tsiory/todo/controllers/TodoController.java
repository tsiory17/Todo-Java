package com.tsiory.todo.controllers;

import com.tsiory.todo.models.Todo;
import com.tsiory.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @GetMapping("/")
    public String ShowMainPage(@RequestParam(required = false) Long editingId, Model model){
        List<Todo> todos = this.todoService.GetAllTodo();
        model.addAttribute("todos", todos);
        model.addAttribute("editingId",editingId);
        return "todo";
    }

    @PostMapping("/add")
    public String AddTodo(@RequestParam String name){
        this.todoService.CreateTodo(name);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String DeleteTodo(@PathVariable Long id){
        this.todoService.DeleteTodo(id);
        return "redirect:/";
    }

    @PostMapping("/toggle/{id}")
    public String ToggleTodo(@PathVariable Long id){
        this.todoService.ToggleTodo(id);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String UpdateTodo(@PathVariable Long id ,@RequestParam String name){
        this.todoService.UpdateTodo(id,name);
        return "redirect:/";
    }


}

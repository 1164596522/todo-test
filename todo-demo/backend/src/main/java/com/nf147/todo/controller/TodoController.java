package com.nf147.todo.controller;

import com.nf147.todo.entity.Todo;
import com.nf147.todo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@CrossOrigin
public class TodoController {

    @Autowired
    private TodoMapper todoMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> getTodo() {
        return todoMapper.getTodos();
    }

    @GetMapping("/unfinished")
    public List<Todo> getUnfinishedTodos(){
        return todoMapper.getUnfinishedTodos();
    }

    @GetMapping("/ongoing")
    public List<Todo> getOngoingTodos(){
        return todoMapper.getOngoingTodos();
    }

    @GetMapping("/completed")
    public List<Todo> getCompletedTodos(){
        return todoMapper.getCompletedTodos();
    }

    @RequestMapping(method = RequestMethod.POST)
    public  List<Todo> addTodo(@RequestBody Todo todo) {
        return todoMapper.addTodo(todo);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/viewdetails")
    public List<Todo> viewDetails(@RequestBody Todo todo){
        return todoMapper.viewDetails(todo);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/makeConduct")
    public void makeConduct(@RequestBody Todo todo) {
        todoMapper.makeConduct(todo);
    }

    @PutMapping("/makeDone")
    public void makeDone(@RequestBody Todo todo) {
        todoMapper.makeDone(todo);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteTodo(@RequestBody Todo todo) {
        todoMapper.deleteTodo(todo);
    }
}

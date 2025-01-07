package service;

import entity.Todolist;
import repository.TodolistRepository;

public class TodolistServiceImpl implements TodolistService{
    private TodolistRepository todolistRepository;

    public TodolistServiceImpl(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    @Override
    public void showTodoList() {
        Todolist[] model = todolistRepository.getAll();

        System.out.println("TODO LIST APP");

        for (Todolist todo: model){
            System.out.println(todo.getId() + ". " + todo.getTodo());
        }
    }

    @Override
    public void addTodoList(String todo) {
        Todolist todolist = new Todolist(todo);
        todolistRepository.add(todolist);
        System.out.println("Sukses menambahkan Todo: " + todo);
    }

    @Override
    public void removeTodoList(Integer number) {
        boolean success = todolistRepository.remove(number);
        if (success){
            System.out.println("Sukses menghapus Todo: " + number);
        } else {
            System.out.println("Gagal menghapus Todo: " + number);
        }
    }
}

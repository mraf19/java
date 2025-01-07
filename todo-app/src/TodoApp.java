import repository.TodolistRepository;
import repository.TodolistRepositoryImpl;
import service.TodolistService;
import service.TodolistServiceImpl;
import view.View;

public class TodoApp {
    public static void main(String[] args) {
        TodolistRepository todolistRepository = new TodolistRepositoryImpl();
        TodolistService todolistService = new TodolistServiceImpl(todolistRepository);
        View view = new View(todolistService);

        view.showTodoList();
    }
}

import repository.TodolistRepository;
import repository.TodolistRepositoryImpl;
import service.TodolistService;
import service.TodolistServiceImpl;
import util.DatabaseUtil;
import view.View;

import javax.sql.DataSource;

public class TodoApp {
    public static void main(String[] args) {
        DataSource dataSource = DatabaseUtil.getDataSource();
        TodolistRepository todolistRepository = new TodolistRepositoryImpl(dataSource);
        TodolistService todolistService = new TodolistServiceImpl(todolistRepository);
        View view = new View(todolistService);

        view.showTodoList();
    }
}

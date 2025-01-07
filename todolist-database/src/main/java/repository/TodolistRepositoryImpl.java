package repository;

import entity.Todolist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodolistRepositoryImpl implements TodolistRepository{

    private DataSource dataSource;

    public TodolistRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Todolist[] getAll() {
        String sql = "SELECT id, todo FROM todolist";

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            List<Todolist> list = new ArrayList<>();

            while (resultSet.next()){
                Todolist todolist = new Todolist();
                todolist.setId(resultSet.getInt("id"));
                todolist.setTodo(resultSet.getString("todo"));

                list.add(todolist);
            }

            return list.toArray(new Todolist[]{});
        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void add(Todolist todolist) {
        String sql = "INSERT INTO todolist(todo) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, todolist.getTodo());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isExists(Integer number){
        String sql = "SELECT id FROM todolist WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, number);
            try (ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }

        } catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean remove(Integer number) {
        String sql = "DELETE FROM todolist WHERE id = ?";

        if(isExists(number)){
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, number);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }
}

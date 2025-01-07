package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        for (int i = 0; i < 100; i++) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "test@email.com");
            statement.setString(2, "test comment " + i);
            statement.executeUpdate();
            statement.close();
        }

        connection.commit();
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        for (int i = 0; i < 100; i++) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "test@email.com");
            statement.setString(2, "test comment " + i);
            statement.executeUpdate();
            statement.close();
        }

        connection.rollback();
        connection.close();
    }
}

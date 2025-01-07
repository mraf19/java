package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {

    @Test
    void testPreparedStatement() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String username = "admin'; #";
        String password = "salah";
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet executed = preparedStatement.executeQuery();

        if(executed.next()){
            System.out.println("Sukses Login: " + executed.getString("username"));
        } else {
            System.out.println("Gagal Login");
        }

        preparedStatement.close();
        connection.close();
    }
}

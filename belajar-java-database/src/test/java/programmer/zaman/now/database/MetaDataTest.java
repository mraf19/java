package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {
    @Test
    void testMetaDataDatabase() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println(metaData.getDatabaseProductName());
        System.out.println(metaData.getDatabaseProductVersion());

        ResultSet tables = metaData.getTables("belajar_java_database", null, null, null);

        while (tables.next()){
            System.out.println("TABLE: " + tables.getString("TABLE_NAME"));
        }

    }

    @Test
    void testMetaDataParameter() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        ParameterMetaData parameterMetaData = statement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());
//        System.out.println(parameterMetaData.getParameterType(1)); not supported for mysql driver
//        System.out.println(parameterMetaData.getParameterType(2)); not supported for mysql driver

        statement.close();
        connection.close();
    }

    @Test
    void testResultSetMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM customers";
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData metaData = resultSet.getMetaData();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.println("Name: " + metaData.getColumnName(i));
            System.out.println("Type: " + metaData.getColumnType(i));
            System.out.println("Type Name: " + metaData.getColumnTypeName(i));
        }

        statement.close();
        connection.close();
    }
}

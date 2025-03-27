package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver_class"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            connection = null;
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());
        }
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s(%s);",
                    tableName,
                    "id SERIAL PRIMARY KEY"
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws Exception {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "DROP TABLE %s",
                        tableName
                );
                statement.execute(sql);
            }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName)  throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s",
                    tableName,
                    columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)  throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при загрузке файла-конфига: " + e.getMessage());
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("greatTable");
            System.out.println(tableEditor.getTableScheme("greatTable"));
            tableEditor.addColumn("greatTable", "one", "int");
            System.out.println(tableEditor.getTableScheme("greatTable"));
            tableEditor.renameColumn("greatTable", "one", "two");
            System.out.println(tableEditor.getTableScheme("greatTable"));
            tableEditor.dropColumn("greatTable", "two");
            System.out.println(tableEditor.getTableScheme("greatTable"));
            tableEditor.dropTable("greatTable");
            tableEditor.getTableScheme("greatTable");
            System.out.println(tableEditor.getTableScheme("greatTable"));
        }
    }
}
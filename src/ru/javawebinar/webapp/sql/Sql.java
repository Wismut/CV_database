package ru.javawebinar.webapp.sql;

import ru.javawebinar.webapp.WebAppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql {
    private final ConnectionFactory connectionFactory;

    public Sql(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            throw new WebAppException("SQL failed: " + sql, e);
        }
    }

}

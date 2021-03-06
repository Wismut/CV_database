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
        execute(sql, ps -> {
            ps.execute();
            return null;
        });
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw new WebAppException("SQL failed: " + sql, e);
        }
    }

    public <T> T execute(SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new WebAppException("Transaction failed", e);
        }
    }

}

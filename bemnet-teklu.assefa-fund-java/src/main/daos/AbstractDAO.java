package main.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> {
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> search() throws SQLException;

    public abstract void update(T object) throws SQLException;

    public abstract void delete(long id) throws SQLException;

    public abstract void create(T object) throws SQLException;

    protected abstract T mapRowToEntity(ResultSet resultSet) throws SQLException;

    protected void executeUpdate(String sql, Object... params) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for (Object param : params) {
                statement.setObject(index++, param);
            }
            statement.executeUpdate();
        }
    }
}

package io.pmutisya.orm.domain.repository.jdbc;

import io.pmutisya.orm.domain.User;
import io.pmutisya.orm.domain.config.JDBCConfiguration;
import io.pmutisya.orm.domain.enumeration.Role;
import io.pmutisya.orm.domain.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJDBCRepository implements UserRepository {

    public void save(User user) throws Exception {
        DataSource dataSource = JDBCConfiguration.getDataSource();

        String query = "INSERT INTO tbl_users (name, login, role, created_at, organisation_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getRole().name());
            statement.setString(4, user.getCreatedAt());
            statement.setLong(5, user.getOrganisationId());

            statement.executeUpdate();
        }
    }

    public User findById(Long id) throws Exception {
        DataSource dataSource = JDBCConfiguration.getDataSource();
        String query = "SELECT * FROM tbl_users WHERE id = ?";

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return extract(resultSet);
            }

        }

        return null;
    }

    private User extract(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setLogin(resultSet.getString("login"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setCreatedAt(resultSet.getString("created_at"));
        user.setOrganisationId(resultSet.getLong("organisation_id"));

        return user;
    }
}

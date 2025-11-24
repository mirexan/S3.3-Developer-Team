package com.itAcademy.agenda.task.repository.mappers;

import com.itAcademy.agenda.common.utils.MySQLDatabaseConnection;
import com.itAcademy.agenda.task.dto.TaskPersistenceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOMapper {
    private final Connection conn;

    public TaskDAOMapper() {
        this.conn = MySQLDatabaseConnection.getInstance();
    }

    public List<TaskPersistenceDTO> executeQueryAndToDtoList(String sqlQuery) {
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            try (ResultSet rs = stmt.executeQuery()) {
                List<TaskPersistenceDTO> dtos = new ArrayList<>();

                while (rs.next()) {
                    dtos.add(toDto(rs));
                }
                return dtos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TaskPersistenceDTO toDto(ResultSet rs) throws SQLException {
        return new TaskPersistenceDTO(
                rs.getInt("id"),
                rs.getString("main_text"),
                rs.getTimestamp("date").toLocalDateTime(),
                rs.getTimestamp("creation_date").toLocalDateTime(),
                rs.getString("priority"),
                rs.getBoolean("completed")
        );
    }

}

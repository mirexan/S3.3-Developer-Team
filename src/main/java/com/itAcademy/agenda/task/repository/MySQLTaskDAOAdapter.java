package com.itAcademy.agenda.task.repository;

import com.itAcademy.agenda.common.utils.MySQLDatabaseConnection;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class MySQLTaskDAOAdapter implements TaskDAO {
    private final Connection conn;
    private final TaskDAOMapper mapper;

    public MySQLTaskDAOAdapter() {
        this.conn = MySQLDatabaseConnection.getInstance();
        this.mapper = new TaskDAOMapper();
    }

    @Override
    public void delete(TaskDTO dto) {
        String sqlQuery = "DELETE FROM task WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            stmt.setInt(1, dto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void save(TaskDTO dto) {
        String sqlQuery = "INSERT INTO task (main_text, date, creation_date, priority) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            stmt.setString(1, dto.getMainText());
            stmt.setDate(2, Date.valueOf(dto.getDate()));
            stmt.setTimestamp(3, Timestamp.valueOf(dto.getCreationDate()));
            stmt.setString(4, dto.getPriority());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void update(TaskDTO dto) {
        String sqlQuery = "UPDATE task SET main_text = ?, date = ?, priority = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            stmt.setString(1, dto.getMainText());
            stmt.setDate(2, Date.valueOf(dto.getDate()));
            stmt.setString(3, dto.getPriority());
            stmt.setInt(4, dto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void markAsCompleted(TaskDTO dto) {
        String sqlQuery = "UPDATE task SET completed = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            stmt.setBoolean(1, dto.getCompleted());
            stmt.setInt(2, dto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Optional<TaskDTO> findById(TaskDTO dto) {
        String sqlQuery = "SELECT * FROM task WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            stmt.setInt(1, dto.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.toDto(rs));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public List<TaskDTO> findAll() {
        return mapper.executeQueryAndToDtoList("SELECT * FROM task");
    }

    @Override
    public List<TaskDTO> listPendent() {
        return mapper.executeQueryAndToDtoList("SELECT * FROM task WHERE completed = 0");
    }

    @Override
    public List<TaskDTO> listCompleted() {
        return mapper.executeQueryAndToDtoList("SELECT * FROM task WHERE completed = 1");
    }
}

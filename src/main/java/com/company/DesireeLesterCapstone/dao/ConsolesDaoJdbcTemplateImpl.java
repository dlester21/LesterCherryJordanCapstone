package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.Consoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ConsolesDaoJdbcTemplateImpl implements ConsolesDao {
    private JdbcTemplate jdbcTemplate;

    //prepared statements to interact with the SQL Server
    private static final String INSERT_CONSOLES_SQL =
            "INSERT INTO console (console_id, model, manufacturer, memory_amount, processor, price, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CONSOLES_SQL =
            "SELECT * FROM console WHERE console_id = ?";
    private static final String SELECT_ALL_CONSOLES =
            "SELECT * FROM console";
    private static final String UPDATE_CONSOLES_SQL =
            "UPDATE console SET model = ?, manufacturer = ?, memory_amount = ?, processor = ?, price = ?, quantity = ? WHERE console_id = ?";
    private static final String DELETE_CONSOLES_SQL =
            "DELETE FROM console WHERE console_id = ?";
    private static final String SELECT_CONSOLES_BY_MANUFACTURER_SQL =
            "SELECT * FROM console WHERE manufacturer = ?";


    @Autowired
    public ConsolesDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Consoles addConsole(Consoles console) {
        jdbcTemplate.update(INSERT_CONSOLES_SQL, console.getId(), console.getModel(), console.getManufacturer(),
                console.getMemoryAmount(), console.getProcessor(), console.getPrice(), console.getQuantity());

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        console.setId(id);

        return console;
    }

    @Override
    public Consoles getConsole(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CONSOLES_SQL, this::mapRowToConsole, id);
        }
        catch (EmptyResultDataAccessException e) {
            //If there are no consoles with the given id, return null
            return null;
        }
    }

    @Override
    public List<Consoles> getAllConsoles() {
        return jdbcTemplate.query(SELECT_ALL_CONSOLES, this::mapRowToConsole);
    }

    @Override
    public void updateConsole(Consoles console) {
        jdbcTemplate.update(UPDATE_CONSOLES_SQL,  console.getModel(), console.getManufacturer(),
                console.getMemoryAmount(), console.getProcessor(), console.getPrice(), console.getQuantity(), console.getId());
    }

    @Override
    public void deleteConsole(int id) {
        jdbcTemplate.update(DELETE_CONSOLES_SQL, id);
    }

    @Override
    public List<Consoles> getConsolesByManufacturer(String Manufacturer) {
        return jdbcTemplate.query(SELECT_CONSOLES_BY_MANUFACTURER_SQL, this::mapRowToConsole, Manufacturer);
    }

    //Row mapper to map where data will go
    private Consoles mapRowToConsole(ResultSet rs, int rowNum) throws SQLException
    {
        Consoles console = new Consoles();
        console.setId(rs.getInt("console_id"));
        console.setModel(rs.getString("model"));
        console.setManufacturer(rs.getString("manufacturer"));
        console.setMemoryAmount(rs.getString("memory_amount"));
        console.setProcessor(rs.getString("processor"));
        console.setPrice(rs.getBigDecimal("price"));
        console.setQuantity(rs.getInt("quantity"));

        return console;
    }
}

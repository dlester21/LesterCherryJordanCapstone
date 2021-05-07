package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class GamesDaoJdbcTemplateImpl implements GamesDao {
    private JdbcTemplate jdbcTemplate;

    //prepared statements to interact with the SQL Server
    private static final String INSERT_GAMES_SQL =
            "INSERT INTO game (game_id, title, esrb_rating, description, price, studio, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_GAMES_SQL =
            "SELECT * FROM game WHERE game_id = ?";
    private static final String SELECT_ALL_GAMES =
            "SELECT * FROM game";
    private static final String UPDATE_GAMES_SQL =
            "UPDATE game SET title = ?, esrb_rating = ?, description = ?, price = ?, studio = ?, quantity = ? WHERE game_id = ?";
    private static final String DELETE_GAMES_SQL =
            "DELETE FROM game WHERE game_id = ?";
    private static final String SELECT_GAMES_BY_STUDIO_SQL =
            "SELECT * FROM game WHERE studio = ?";
    private static final String SELECT_GAMES_BY_ESRB_SQL =
            "SELECT * FROM game WHERE esrb_rating = ?";
    private static final String SELECT_GAMES_BY_TITLE_SQL =
            "SELECT * FROM game WHERE title = ?";


    @Autowired
    public GamesDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Games addGame(Games game) {

        jdbcTemplate.update(INSERT_GAMES_SQL, game.getId(),  game.getTitle(), game.getEsrbRating(), game.getDescription(),
                game.getPrice(), game.getStudio(), game.getQuantity());

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        game.setId(id);

        return game;
    }

    @Override
    public Games getGame(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_GAMES_SQL, this::mapRowToGame, id);
        }
        catch (EmptyResultDataAccessException e) {
            //If there are no games with the given id, return null
            return null;
        }
    }

    @Override
    public List<Games> getAllGames() {
        return jdbcTemplate.query(SELECT_ALL_GAMES, this::mapRowToGame);
    }

    @Override
    public void updateGame(Games game) {
        jdbcTemplate.update(UPDATE_GAMES_SQL, game.getTitle(), game.getEsrbRating(),
                game.getDescription(), game.getPrice(), game.getStudio(),
                game.getQuantity(), game.getId());
    }

    @Override
    public void deleteGame(int id) {
        jdbcTemplate.update(DELETE_GAMES_SQL, id);
    }

    @Override
    public List<Games> getGamesByStudio(String studio) {
        return jdbcTemplate.query(SELECT_GAMES_BY_STUDIO_SQL, this::mapRowToGame, studio);
    }

    @Override
    public List<Games> getGamesByEsrbRating(String esrb) {
        return jdbcTemplate.query(SELECT_GAMES_BY_ESRB_SQL, this::mapRowToGame, esrb);
    }

    @Override
    public List<Games> getGamesByTitle(String title) {
        return jdbcTemplate.query(SELECT_GAMES_BY_TITLE_SQL, this::mapRowToGame, title);
    }

    //Row Mapper to map where data will go
    private Games mapRowToGame(ResultSet rs, int rowNum) throws SQLException
    {
        Games game = new Games();
        game.setId(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setEsrbRating(rs.getString("esrb_rating"));
        game.setDescription(rs.getString("description"));
        game.setPrice(rs.getBigDecimal("price"));
        game.setStudio(rs.getString("studio"));
        game.setQuantity(rs.getInt("quantity"));

        return game;
    }
}

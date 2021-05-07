package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.Consoles;
import com.company.DesireeLesterCapstone.models.Games;
import com.company.DesireeLesterCapstone.models.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GamesDaoTest {

    @Autowired
    ConsolesDao consolesDao;

    @Autowired
    GamesDao gamesDao;

    @Autowired
    TShirtDao tShirtsDao;

    @Before
    public void setUp() throws Exception {

        //cleans out the database
        List<Consoles> consolesList = consolesDao.getAllConsoles();
        for (Consoles b : consolesList) {
            consolesDao.deleteConsole(b.getId());
        }

        List<Games> gamesList = gamesDao.getAllGames();
        for (Games g : gamesList) {
            gamesDao.deleteGame(g.getId());
        }

        List<TShirt> tShirtsList = tShirtsDao.getAllTShirts();
        for (TShirt t : tShirtsList) {
            tShirtsDao.deleteTShirt(t.getId());
        }
    }

    @Test
    public void addGetDeleteGamesTest(){

        //Arrange: no constructor so we choose where we put it
        //game creation

        Games game = new Games();
        game.setTitle("Super Mario Bros.");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Super Mario Bros.");
        game.setPrice(new BigDecimal("5.00"));
        game.setStudio("Ninetendo");
        game.setQuantity(1);
        gamesDao.addGame(game);

        //Act
        //Retrieving game
        Games gameTest = gamesDao.getGame(game.getId());

        //Assert
        assertEquals(gameTest, game);

        //Act
        //Deleting game
        gamesDao.deleteGame(game.getId());
        gameTest = gamesDao.getGame(game.getId());
        assertNull(gameTest);


    }

    @Test
    public void getAllGamesTest() {
        //Arrange
        Games game = new Games();
        game.setTitle("Super Mario Bros.");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Super Mario Bros.");
        game.setPrice(new BigDecimal("5.00"));
        game.setStudio("Ninetendo");
        game.setQuantity(1);
        gamesDao.addGame(game);

        //Arrange
        game= new Games();
        game.setTitle("Sonic");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Sonic");
        game.setPrice(new BigDecimal("6.50"));
        game.setStudio("Ninetendo");
        game.setQuantity(1);
        gamesDao.addGame(game);

     List<Games> gameList = gamesDao.getAllGames();

     assertEquals(gameList.size(), 2);

    }

   @Test
    public void updateGameTest(){

        //Arrange
        Games game = new Games();
        game.setTitle("Super Mario Bros.");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Super Mario Bros.");
        game.setPrice(new BigDecimal("5.00"));
        game.setStudio("Ninetendo");
        game.setQuantity(1);
        gamesDao.addGame(game);

        //Arrange
       game= new Games();
       game.setTitle("Sonic");
       game.setEsrbRating("Everyone");
       game.setDescription("Description for Sonic");
       game.setPrice(new BigDecimal("6.50"));
       game.setStudio("SEGA");
       game.setQuantity(2);
       gamesDao.addGame(game);

       //Arrange
       game= new Games();
       game.setTitle("Sonic 2");
       game.setEsrbRating("Everyone (10+)");
       game.setDescription("Description for Sonic 2");
       game.setPrice(new BigDecimal("8.50"));
       game.setStudio("SEGA");
       game.setQuantity(2);
       gamesDao.addGame(game);


        gamesDao.updateGame(game);

        //Act
        //retrieving game
        Games gameTest = gamesDao.getGame(game.getId());

        //Assert
        assertEquals(gameTest, game);

    }

    @Test
    public void getGamesByStudioTest() {
        //Arrange
        Games game = new Games();
        game.setTitle("Super Mario Bros.");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Super Mario Bros.");
        game.setPrice(new BigDecimal("5.00"));
        game.setStudio("Nintendo");
        game.setQuantity(1);
        gamesDao.addGame(game);
        //Arrange
        game = new Games();
        game.setTitle("Sonic");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Sonic");
        game.setPrice(new BigDecimal("6.50"));
        game.setStudio("SEGA");
        game.setQuantity(2);
        gamesDao.addGame(game);
        //Arrange
        game = new Games();
        game.setTitle("Sonic 2");
        game.setEsrbRating("Everyone (10+)");
        game.setDescription("Description for Sonic 2");
        game.setPrice(new BigDecimal("8.50"));
        game.setStudio("SEGA");
        game.setQuantity(2);
        gamesDao.addGame(game);
        List<Games> gamesList = gamesDao.getGamesByStudio("SEGA");
        assertEquals(gamesList.size(), 2);
    }
    @Test
    public void getGamesByEsrbRatingTest() {
        //Arrange
        Games game = new Games();
        game.setTitle("Super Mario Bros.");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Super Mario Bros.");
        game.setPrice(new BigDecimal("5.00"));
        game.setStudio("Nintendo");
        game.setQuantity(1);
        gamesDao.addGame(game);
        //Arrange
        game = new Games();
        game.setTitle("Sonic");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Sonic");
        game.setPrice(new BigDecimal("6.50"));
        game.setStudio("SEGA");
        game.setQuantity(2);
        gamesDao.addGame(game);
        //Arrange
        game = new Games();
        game.setTitle("Sonic 2");
        game.setEsrbRating("Everyone (10+)");
        game.setDescription("Description for Sonic 2");
        game.setPrice(new BigDecimal("8.50"));
        game.setStudio("SEGA");
        game.setQuantity(2);
        gamesDao.addGame(game);
        List<Games> gamesList = gamesDao.getGamesByEsrbRating("Everyone (10+)");
        assertEquals(gamesList.size(), 1);
    }
    @Test
    public void getGamesByTitleTest() {
        //Arrange
        Games game = new Games();
        game.setTitle("Super Mario Bros.");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Super Mario Bros.");
        game.setPrice(new BigDecimal("5.00"));
        game.setStudio("Nintendo");
        game.setQuantity(1);
        gamesDao.addGame(game);
        //Arrange
        game = new Games();
        game.setTitle("Sonic");
        game.setEsrbRating("Everyone");
        game.setDescription("Description for Sonic");
        game.setPrice(new BigDecimal("6.50"));
        game.setStudio("SEGA");
        game.setQuantity(2);
        gamesDao.addGame(game);
        //Arrange
        game = new Games();
        game.setTitle("Sonic 2");
        game.setEsrbRating("Everyone (10+)");
        game.setDescription("Description for Sonic 2");
        game.setPrice(new BigDecimal("8.50"));
        game.setStudio("SEGA");
        game.setQuantity(2);
        gamesDao.addGame(game);
        List<Games> gamesList = gamesDao.getGamesByTitle("Sonic 2");
        assertEquals(gamesList.size(), 1);
    }
}

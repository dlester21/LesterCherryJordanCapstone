package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Console;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleDaoTest {

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
    public void addGetDeleteConsole() {

        //Arrange: no constructor so we choose where we put it
        //console creation

        Consoles console = new Consoles();
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500 GB");
        console.setProcessor("Custom AMD Zen 2");
        console.setPrice(new BigDecimal("499.00"));
        console.setQuantity(1);
        consolesDao.addConsole(console);

        //Act
        //Retrieving console
        Consoles consoleTest = consolesDao.getConsole(console.getId());

        //Assert
        assertEquals(consoleTest, console);

        //Act
        //Deleting console
        consolesDao.deleteConsole(console.getId());
        consoleTest = consolesDao.getConsole(console.getId());
        assertNull(consoleTest);


    }

    @Test
    public void GetAllConsole() {

        //Arrange: no constructor so we choose where we put it
        //console creation

        Consoles console = new Consoles();
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500 GB");
        console.setProcessor("Custom AMD Zen 2");
        console.setPrice(new BigDecimal("499.00"));
        console.setQuantity(1);
        consolesDao.addConsole(console);

        //Arrange
        console = new Consoles();
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500 GB");
        console.setProcessor("Custom AMD Zen 2");
        console.setPrice(new BigDecimal("499.00"));
        console.setQuantity(1);
        consolesDao.addConsole(console);

        List<Consoles> consolesList = consolesDao.getAllConsoles();

        assertEquals(consolesList.size(), 2);


    }

    @Test
    public void updateConsoles() {
        //Arrange: no constructor so we can pick and choose where we want to put it
        //console creation
        Consoles console= new Consoles();
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500 GB");
        console.setProcessor("Custom AMT Zen 2");
        console.setPrice(new BigDecimal("499.00"));
        console.setQuantity(2);
        console = consolesDao.addConsole(console);


        //setting updated parameters for console
        console.setQuantity(10);
        console.setModel("NEW MODEL");
        console.setManufacturer("NEW MANUFACTURER");
        console.setMemoryAmount("NEW MEMORY AMT");
        console.setProcessor("NEW PROCESSOR");
        console.setPrice(new BigDecimal("6.50"));
        consolesDao.updateConsole(console);

        //Act
        //Retrieving console
        Consoles consoleTest = consolesDao.getConsole(console.getId());

        //Assert
        assertEquals(consoleTest, console);




    }

    @Test
    public void getConsolesByManufacturerTest() {
        //Arrange: no constructor so we choose where we put it
        //console creation
        Consoles console = new Consoles();
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500 GB");
        console.setProcessor("Custom AMD Zen 2");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(1);
        consolesDao.addConsole(console);

        //Arrange
        console = new Consoles();
        console.setModel("Xbox Series X");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("1 TB");
        console.setProcessor("AMD Zen 2");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(1);
        consolesDao.addConsole(console);

        //Arrange
        console = new Consoles();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1 TB");
        console.setProcessor("AMD x86-64 Jaguar");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(1);
        consolesDao.addConsole(console);
        List<Consoles> consolesList = consolesDao.getConsolesByManufacturer("Sony");
        assertEquals(consolesList.size(), 2);
    }

}

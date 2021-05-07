package com.company.DesireeLesterCapstone.dao;


import com.company.DesireeLesterCapstone.models.Consoles;
import com.company.DesireeLesterCapstone.models.Games;
import com.company.DesireeLesterCapstone.models.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtDaoTest {
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
    public void addGetDeleteTShirtTest(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Blue");
        tShirt.setDescription("Description for Blue T-Shirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(2);
        tShirtsDao.addTShirt(tShirt);

        //Act
        TShirt tShirtTest = tShirtsDao.getTShirt(tShirt.getId());

        //Assert
        assertEquals(tShirtTest, tShirt);

        //Act
        //delete t_shirt
        tShirtsDao.deleteTShirt(tShirt.getId());
        tShirtTest = tShirtsDao.getTShirt(tShirt.getId());
        assertNull(tShirtTest);


    }

    @Test
    public void getAllTShirtsTest(){
        //arrange
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Blue");
        tShirt.setDescription("Description for Blue T-Shirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(2);
        tShirtsDao.addTShirt(tShirt);

        //Arrange
        tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Green");
        tShirt.setDescription("Description for Green T-Shirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(1);
        tShirtsDao.addTShirt(tShirt);

        //Arrange
        tShirt = new TShirt();
        tShirt.setSize("XL");
        tShirt.setColor("Yellow");
        tShirt.setDescription("Description for Yellow T-Shirt");
        tShirt.setPrice(new BigDecimal("12.50"));
        tShirt.setQuantity(1);
        tShirtsDao.addTShirt(tShirt);

        List<TShirt> tShirtList = tShirtsDao.getAllTShirts();

        assertEquals(tShirtList.size(), 3);

    }

    @Test
    public void updateTShirtTest(){
        //Arrange: no constructor so we can pick and choose where to put it
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Blue");
        tShirt.setDescription("Description for Blue T-Shirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(2);
        tShirtsDao.addTShirt(tShirt);

        //Setting updated parameters for t-shirt
        tShirt.setQuantity(1);
        tShirt.setSize("NEW SIZE");
        tShirt.setColor("NEW COLOR");
        tShirt.setDescription("NEW DESCRIPTION");
        tShirt.setPrice(new BigDecimal("5.00"));

        tShirtsDao.updateTShirt(tShirt);

        //Act
        //retrieving t-shirt
        TShirt tShirtTest = tShirtsDao.getTShirt(tShirt.getId());

        //assert
        assertEquals(tShirtTest, tShirt);
    }

    @Test
    public void getTShirtsByColorTest(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Blue");
        tShirt.setDescription("Description for Blue T-Shirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(2);
        tShirtsDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("S");
        tShirt.setColor("Red");
        tShirt.setDescription("Description for Red T-Shirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(2);
        tShirtsDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("XL");
        tShirt.setColor("Red");
        tShirt.setDescription("Description for Red T-Shirt");
        tShirt.setPrice(new BigDecimal("20.00"));
        tShirt.setQuantity(2);
        tShirtsDao.addTShirt(tShirt);

        List<TShirt> tShirtList = tShirtsDao.getTShirtsByColor("Red");

        assertEquals(tShirtList.size(), 2);

    }

    @Test
    public void getTShirtsBySizeTest() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Blue");
        tShirt.setDescription("Description for Blue T-Shirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(2);
        tShirtsDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Red");
        tShirt.setDescription("Description for Red T-Shirt");
        tShirt.setPrice(new BigDecimal("15.00"));
        tShirt.setQuantity(2);

        tShirtsDao.addTShirt(tShirt);
        tShirt = new TShirt();
        tShirt.setSize("XL");
        tShirt.setColor("Red");
        tShirt.setDescription("Description for Red T-Shirt");
        tShirt.setPrice(new BigDecimal("20.00"));
        tShirt.setQuantity(2);

        tShirtsDao.addTShirt(tShirt);
        List<TShirt> tShirtList = tShirtsDao.getTShirtsBySize("M");
        assertEquals(tShirtList.size(), 2);
    }

}

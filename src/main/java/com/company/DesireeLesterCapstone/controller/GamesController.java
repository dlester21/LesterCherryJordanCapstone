package com.company.DesireeLesterCapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import com.company.DesireeLesterCapstone.models.*;
import com.company.DesireeLesterCapstone.dao.*;
import java.util.List;


@RestController
public class GamesController
{

    @Autowired
    GamesDao gameDao;

    public GamesController(GamesDao gameDao)
    {
        this.gameDao = gameDao;
    }


    //Game Creation
    @RequestMapping(value = "/game", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Games createGame(@RequestBody Games game) {

        return gameDao.addGame(game);
    }


    //Game by Id
    @RequestMapping(value = "/game/{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Games getGameId(@PathVariable int id )
    {
        return gameDao.getGame(id);
    }


    //Getting all games
    @RequestMapping(value = "/game", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getAllGames()
    {
        return gameDao.getAllGames();
    }


    //Get game by esrb rating
    @RequestMapping(value = "/game/{esrbrating}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getGamesByEsrbRating(@PathVariable String esrbRating)
    {
        return gameDao.getGamesByEsrbRating(esrbRating);
    }

    //Get game by studio
    @RequestMapping(value = "/game/{studio}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getGamesByStudio(@PathVariable String studio)
    {
        return gameDao.getGamesByStudio(studio);
    }

    //Get game by title
    @RequestMapping(value = "/game/{title}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Games> getGameByTitle(@PathVariable String title)
    {
        return gameDao.getGamesByTitle(title);
    }

    //Updating game
    @RequestMapping(value = "/game", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void UpdateGame(@RequestBody Games game)
    {
        gameDao.updateGame(game);
    }

    //Deleting game
    @RequestMapping(value = "/game", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteGame(@RequestBody int id)
    {
        gameDao.deleteGame(id);
    }
}


package com.company.DesireeLesterCapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import com.company.DesireeLesterCapstone.models.*;
import com.company.DesireeLesterCapstone.dao.*;
import java.util.List;




@RestController
public class TShirtController
{
    TShirtDao tShirtDao;

    @Autowired
    public TShirtController(TShirtDao tShirtDao)
    {
        this.tShirtDao = tShirtDao;
    }

    //Game Creation
    @RequestMapping(value = "/tshirt", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public TShirt createTShirt(@RequestBody TShirt tShirt) {

        return tShirtDao.addTShirt(tShirt);
    }

    //Tshirt by Id
    @RequestMapping(value = "/tshirt/{id}", method=RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TShirt getTShirtId(@PathVariable int id )
    {
        return tShirtDao.getTShirt(id);
    }

    //Getting all Tshirts
    @RequestMapping(value = "/tshirt", method=RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<TShirt> getAllTShirts()
    {
        return tShirtDao.getAllTShirts();
    }

    //Get game by color
    @RequestMapping(value = "/tshirt/{color}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable String color)
    {
        return tShirtDao.getTShirtsByColor(color);
    }

    //Get game by size
    @RequestMapping(value = "/tshirt/{size}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable String size)
    {
        return tShirtDao.getTShirtsBySize(size);
    }

    //Updating tshirt
    @RequestMapping(value = "/tshirt", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void UpdateTShirt(@RequestBody TShirt tShirt)
    {
        tShirtDao.updateTShirt(tShirt);
    }

    //Deleting game
    @RequestMapping(value = "/tshirt", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTShirt(@RequestBody int id)
    {
        tShirtDao.deleteTShirt(id);
    }

}

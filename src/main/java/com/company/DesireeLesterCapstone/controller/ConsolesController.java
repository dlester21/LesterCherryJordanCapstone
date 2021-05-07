package com.company.DesireeLesterCapstone.controller;

import com.company.DesireeLesterCapstone.models.Consoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import com.company.DesireeLesterCapstone.models.*;
import com.company.DesireeLesterCapstone.dao.*;
import java.util.List;




@RestController
public class ConsolesController
{
    @Autowired
    ConsolesDao consoleDao;

    public ConsolesController(ConsolesDao consoleDao)
    {
        this.consoleDao = consoleDao;
    }

    //Console Creation
    @RequestMapping(value = "/console", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Consoles createConsole(@RequestBody Consoles console)
    {
        return consoleDao.addConsole(console);
    }

    //Console by id
    @RequestMapping(value = "/console/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Consoles getConsoleId(@PathVariable int id)
    {
        return consoleDao.getConsole(id);
    }

    //Getting all consoles
    @RequestMapping(value = "/console", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Consoles> getAllConsoles()
    {
        return consoleDao.getAllConsoles();
    }

    //Get console by Manufacturer
    @RequestMapping(value = "/console/{manufacturer}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Consoles> getConsolesByManufacturer(@PathVariable String manufacturer)
    {
        return consoleDao.getConsolesByManufacturer(manufacturer);
    }

    //Updating console
    @RequestMapping(value = "/console", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateConsole (@RequestBody Consoles console)
    {
        consoleDao.updateConsole(console);
    }

    //Deleting console
    @RequestMapping(value = "/console", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteConsole(@RequestBody int id)
    {
        consoleDao.deleteConsole(id);
    }

}


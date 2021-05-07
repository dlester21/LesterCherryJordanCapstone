package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.Consoles;

import java.util.List;

public interface ConsolesDao {
    Consoles addConsole(Consoles console);

    Consoles getConsole(int id);

    List<Consoles> getAllConsoles();

    void updateConsole(Consoles console);

    void deleteConsole(int id);

    List<Consoles> getConsolesByManufacturer(String Manufacturer);
}

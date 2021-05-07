package com.company.DesireeLesterCapstone.dao;

import com.company.DesireeLesterCapstone.models.Games;

import java.util.List;

public interface GamesDao {

    Games addGame(Games game);

    Games getGame(int id);

    List<Games> getAllGames();

    void updateGame(Games game);

    void deleteGame(int id);

    List<Games> getGamesByStudio(String studio);

    List<Games> getGamesByEsrbRating(String esrb);

    List<Games> getGamesByTitle(String title);
}

package com.birdbird314.tictactoe.gamestatus;

import com.birdbird314.tictactoe.game.Game;

import java.util.HashMap;
import java.util.Map;

public class GameStatuses {
  private final Map<String, GameStatus> gameStatusMap;

  public GameStatuses() {
    this.gameStatusMap = new HashMap<>();
  }

  public void newGame(String xPlayerId, String oPlayerId) {
    GameStatus newGame = new GameStatus(new Game());
    gameStatusMap.put(xPlayerId, newGame);
    gameStatusMap.put(oPlayerId, newGame);
  }

  public Game current(String playerId) {
    return statusOf(playerId).current();
  }

  public void submit(String playerId, Game game) {
    statusOf(playerId).submit(game);
  }

  private GameStatus statusOf(String playerId) {
    return gameStatusMap.get(playerId);
  }
}

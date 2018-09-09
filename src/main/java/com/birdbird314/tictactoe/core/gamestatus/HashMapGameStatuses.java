package com.birdbird314.tictactoe.core.gamestatus;

import com.birdbird314.tictactoe.core.game.Game;

import java.util.HashMap;
import java.util.Map;

public class HashMapGameStatuses implements GameStatuses {
  private final Map<String, GameStatus> gameStatusMap;

  public HashMapGameStatuses() {
    this.gameStatusMap = new HashMap<>();
  }

  @Override
  public void newGame(String xPlayerId, String oPlayerId) {
    GameStatus newGame = new GameStatus(new Game());
    gameStatusMap.put(xPlayerId, newGame);
    gameStatusMap.put(oPlayerId, newGame);
  }

  @Override
  public Game current(String playerId) {
    return statusOf(playerId).current();
  }

  @Override
  public void submit(String playerId, Game game) {
    statusOf(playerId).submit(game);
  }

  private GameStatus statusOf(String playerId) {
    return gameStatusMap.get(playerId);
  }
}

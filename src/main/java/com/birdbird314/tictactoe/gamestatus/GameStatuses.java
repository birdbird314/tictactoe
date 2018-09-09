package com.birdbird314.tictactoe.gamestatus;

import com.birdbird314.tictactoe.game.Game;

public interface GameStatuses {
  void newGame(String xPlayerId, String oPlayerId);

  Game current(String playerId);

  void submit(String playerId, Game game);
}

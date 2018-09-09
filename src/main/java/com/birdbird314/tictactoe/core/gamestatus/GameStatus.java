package com.birdbird314.tictactoe.core.gamestatus;

import com.birdbird314.tictactoe.core.game.Game;

class GameStatus {
  private Game game;

  GameStatus(Game game) {
    this.game = game;
  }

  public void submit(Game game) {
    this.game = game;
  }

  public Game current() {
    return game;
  }
}

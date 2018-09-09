package com.birdbird314.tictactoe.gamestatus;

import com.birdbird314.tictactoe.game.Game;

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

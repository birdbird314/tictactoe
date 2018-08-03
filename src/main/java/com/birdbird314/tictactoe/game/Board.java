package com.birdbird314.tictactoe.game;

public interface Board {
  default State stateOn(Cell cell) {
    return State.EMPTY;
  }
}

package com.birdbird314.tictactoe.core.game;

public interface Board {
  default State stateOn(Cell cell) {
    return State.EMPTY;
  }
}

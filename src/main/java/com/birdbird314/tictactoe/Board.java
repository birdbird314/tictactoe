package com.birdbird314.tictactoe;

public interface Board {
  default State stateOn(Cell cell) {
    return State.EMPTY;
  }
}

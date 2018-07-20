package com.birdbird314.tictactoe;

import org.junit.Test;

public class GameTest {
  @Test
  public void shouldReturnBoard() {
    Board board = new Game().board();
  }
}

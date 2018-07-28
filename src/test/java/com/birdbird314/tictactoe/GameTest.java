package com.birdbird314.tictactoe;

import org.junit.Test;

import java.util.stream.Stream;

import static com.birdbird314.tictactoe.Cell.BOTTOM_LEFT;
import static com.birdbird314.tictactoe.Cell.BOTTOM_MIDDLE;
import static com.birdbird314.tictactoe.Cell.BOTTOM_RIGHT;
import static com.birdbird314.tictactoe.Cell.MIDDLE_LEFT;
import static com.birdbird314.tictactoe.Cell.MIDDLE_MIDDLE;
import static com.birdbird314.tictactoe.Cell.MIDDLE_RIGHT;
import static com.birdbird314.tictactoe.Cell.UPPER_LEFT;
import static com.birdbird314.tictactoe.Cell.UPPER_MIDDLE;
import static com.birdbird314.tictactoe.Cell.UPPER_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

  private static Stream<Cell> cells() {
    return Stream.of(
        UPPER_LEFT, UPPER_MIDDLE, UPPER_RIGHT,
        MIDDLE_LEFT, MIDDLE_MIDDLE, MIDDLE_RIGHT,
        BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT
    );
  }

  @Test
  public void shouldReturnEmptyBoard() {
    cells().forEach(cell ->
        assertThat(new Game().board().stateOn(UPPER_LEFT)).isEqualTo(State.EMPTY)
    );
  }

  @Test
  public void shouldPutXOnGivenCell() {
    cells().forEach(cell ->
        assertThat(
            new Game()
                .markXOn(cell)
                .right()
                .board()
                .stateOn(cell)
        ).isEqualTo(State.X)
    );
  }

  @Test
  public void shouldFailIfOTriesToStart() {
    Cell anyCell = UPPER_LEFT;
    assertThat(
        new Game().markOOn(anyCell).left()
    ).isEqualTo("Now it's X's turn");
  }

  @Test
  public void shouldPutOOnGivenCellWhenItIsOTurn() {
    Cell anyCell = UPPER_LEFT;
    Cell anyOtherCell = UPPER_MIDDLE;
    assertThat(
        new Game()
            .markXOn(anyCell).right()
            .markOOn(anyOtherCell).right()
            .board().stateOn(anyOtherCell)
    ).isEqualTo(State.O);
  }

  @Test
  public void shouldFailIfXTriesToPlayOnOTurn() {
    Cell anyCell = UPPER_LEFT;
    Cell anyOtherCell = UPPER_MIDDLE;
    assertThat(
        new Game()
            .markXOn(anyCell)
            .right()
            .markXOn(anyOtherCell)
            .left()
    ).isEqualTo("Now it's O's turn");
  }
}

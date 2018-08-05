package com.birdbird314.tictactoe.game;

import org.junit.Test;

import java.util.stream.Stream;

import static com.birdbird314.tictactoe.game.Cell.BOTTOM_LEFT;
import static com.birdbird314.tictactoe.game.Cell.BOTTOM_MIDDLE;
import static com.birdbird314.tictactoe.game.Cell.BOTTOM_RIGHT;
import static com.birdbird314.tictactoe.game.Cell.MIDDLE_LEFT;
import static com.birdbird314.tictactoe.game.Cell.MIDDLE_MIDDLE;
import static com.birdbird314.tictactoe.game.Cell.MIDDLE_RIGHT;
import static com.birdbird314.tictactoe.game.Cell.UPPER_LEFT;
import static com.birdbird314.tictactoe.game.Cell.UPPER_MIDDLE;
import static com.birdbird314.tictactoe.game.Cell.UPPER_RIGHT;
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
    ).isEqualTo(InvalidMove.IT_IS_NOT_YOUR_TURN);
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
    ).isEqualTo(InvalidMove.IT_IS_NOT_YOUR_TURN);
  }

  @Test
  public void shouldFailIfOTriesToMarkOccupiedCell() {
    Cell anyCell = UPPER_LEFT;
    assertThat(
        new Game()
            .markXOn(anyCell)
            .right()
            .markOOn(anyCell)
            .left()
    ).isEqualTo(InvalidMove.CELL_IS_NOT_EMPTY);
  }

  @Test
  public void shouldFailIfXTriesToMarkOccupiedCell() {
    Cell anyCell = UPPER_LEFT;
    Cell anyOtherCell = UPPER_MIDDLE;
    assertThat(
        new Game()
            .markXOn(anyCell)
            .right()
            .markOOn(anyOtherCell)
            .right()
            .markXOn(anyCell)
            .left()
    ).isEqualTo(InvalidMove.CELL_IS_NOT_EMPTY);
  }

  @Test
  public void shouldFailDuringMoveAttemptWhenAfterGameWasFinished() {
    assertThat(
        new Game()
            .markXOn(UPPER_LEFT).right()
            .markOOn(MIDDLE_LEFT).right()
            .markXOn(UPPER_MIDDLE).right()
            .markOOn(MIDDLE_MIDDLE).right()
            .markXOn(UPPER_RIGHT).right()
            .markOOn(MIDDLE_RIGHT)
            .left()
    ).isEqualTo(InvalidMove.GAME_IS_ALREADY_FINISHED);
  }
}

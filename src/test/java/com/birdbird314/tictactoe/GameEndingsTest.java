package com.birdbird314.tictactoe;

import org.junit.Test;

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

public class GameEndingsTest {

  @Test
  public void shouldDetermineProgressOfUnfinishedGame() {
    assertThat(new Game().winner()).isEmpty();
  }

  @Test
  public void shouldDetermineXVictory() {
    assertThat(givenThreeXInUpperRow().winner()).contains(Winner.X);
    assertThat(givenThreeXInMiddleRow().winner()).contains(Winner.X);
    assertThat(givenThreeXInBottomRow().winner()).contains(Winner.X);

    assertThat(givenThreeXInLeftColumn().winner()).contains(Winner.X);
    assertThat(givenThreeXInMiddleColumn().winner()).contains(Winner.X);
    assertThat(givenThreeXInRightColumn().winner()).contains(Winner.X);

    assertThat(givenThreeXInAscendingDiagonal().winner()).contains(Winner.X);
    assertThat(givenThreeXInDescendingDiagonal().winner()).contains(Winner.X);
  }

  @Test
  public void shouldDetermineOVictory() {
    assertThat(givenThreeOInUpperRow().winner()).contains(Winner.O);
  }

  @Test
  public void shouldDetermineDraft() {
    assertThat(givenDraft().winner()).contains(Winner.DRAFT);
  }

  private Game givenDraft() {
    return new Game()
        .markXOn(UPPER_LEFT).right()
        .markOOn(UPPER_MIDDLE).right()
        .markXOn(UPPER_RIGHT).right()
        .markOOn(MIDDLE_LEFT).right()
        .markXOn(MIDDLE_RIGHT).right()
        .markOOn(MIDDLE_MIDDLE).right()
        .markXOn(BOTTOM_LEFT).right()
        .markOOn(BOTTOM_RIGHT).right()
        .markXOn(BOTTOM_MIDDLE).right();
  }

  private Game givenThreeOInUpperRow() {
    return new Game()
        .markXOn(BOTTOM_MIDDLE).right()
        .markOOn(UPPER_LEFT).right()
        .markXOn(MIDDLE_LEFT).right()
        .markOOn(UPPER_MIDDLE).right()
        .markXOn(MIDDLE_MIDDLE).right()
        .markOOn(UPPER_RIGHT).right();
  }

  private Game givenThreeXInUpperRow() {
    return new Game()
        .markXOn(UPPER_LEFT).right()
        .markOOn(MIDDLE_LEFT).right()
        .markXOn(UPPER_MIDDLE).right()
        .markOOn(MIDDLE_MIDDLE).right()
        .markXOn(UPPER_RIGHT).right();
  }

  private Game givenThreeXInMiddleRow() {
    return new Game()
        .markXOn(MIDDLE_LEFT).right()
        .markOOn(UPPER_LEFT).right()
        .markXOn(MIDDLE_MIDDLE).right()
        .markOOn(UPPER_MIDDLE).right()
        .markXOn(MIDDLE_RIGHT).right();
  }

  private Game givenThreeXInBottomRow() {
    return new Game()
        .markXOn(BOTTOM_LEFT).right()
        .markOOn(UPPER_LEFT).right()
        .markXOn(BOTTOM_MIDDLE).right()
        .markOOn(UPPER_MIDDLE).right()
        .markXOn(BOTTOM_RIGHT).right();
  }

  private Game givenThreeXInLeftColumn() {
    return new Game()
        .markXOn(UPPER_LEFT).right()
        .markOOn(UPPER_MIDDLE).right()
        .markXOn(MIDDLE_LEFT).right()
        .markOOn(MIDDLE_MIDDLE).right()
        .markXOn(BOTTOM_LEFT).right();
  }

  private Game givenThreeXInMiddleColumn() {
    return new Game()
        .markXOn(UPPER_MIDDLE).right()
        .markOOn(UPPER_LEFT).right()
        .markXOn(MIDDLE_MIDDLE).right()
        .markOOn(MIDDLE_LEFT).right()
        .markXOn(BOTTOM_MIDDLE).right();
  }

  private Game givenThreeXInRightColumn() {
    return new Game()
        .markXOn(UPPER_RIGHT).right()
        .markOOn(UPPER_LEFT).right()
        .markXOn(MIDDLE_RIGHT).right()
        .markOOn(MIDDLE_LEFT).right()
        .markXOn(BOTTOM_RIGHT).right();
  }

  private Game givenThreeXInAscendingDiagonal() {
    return new Game()
        .markXOn(UPPER_RIGHT).right()
        .markOOn(UPPER_LEFT).right()
        .markXOn(MIDDLE_MIDDLE).right()
        .markOOn(MIDDLE_LEFT).right()
        .markXOn(BOTTOM_LEFT).right();
  }

  private Game givenThreeXInDescendingDiagonal() {
    return new Game()
        .markXOn(UPPER_LEFT).right()
        .markOOn(UPPER_RIGHT).right()
        .markXOn(MIDDLE_MIDDLE).right()
        .markOOn(MIDDLE_LEFT).right()
        .markXOn(BOTTOM_RIGHT).right();
  }
}

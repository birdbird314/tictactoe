package com.birdbird314.tictactoe.core.actions;

import com.birdbird314.tictactoe.core.action.Actions;
import com.birdbird314.tictactoe.core.game.Cell;
import com.birdbird314.tictactoe.core.game.InvalidMove;
import com.birdbird314.tictactoe.core.gamestatus.HashMapGameStatuses;
import com.birdbird314.tictactoe.core.player.HashSetActivePlayers;
import com.birdbird314.tictactoe.core.util.Either;
import com.birdbird314.tictactoe.core.util.Unit;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameInProgressTest {
  private static final String X_PLAYER_ID = "someXPlayerId";
  private static final String O_PLAYER_ID = "someOPlayerId";
  private static final String OTHER_X_PLAYER_ID = "otherXPlayerId";
  private static final String OTHER_O_PLAYER_ID = "otherOPlayerId";

  private Actions actions;

  @Before
  public void setUp() {
    actions = new Actions(new HashSetActivePlayers(), new HashMapGameStatuses());
  }

  @Test
  public void shouldFailWhenWrongPlayerTriesToStartAGame() {
    actions.newGame(X_PLAYER_ID, O_PLAYER_ID).execute();

    Either<InvalidMove, Unit> result = actions.makeMove(O_PLAYER_ID, Cell.BOTTOM_LEFT).execute();

    assertThat(result.left()).isEqualTo(InvalidMove.IT_IS_NOT_YOUR_TURN);
  }

  @Test
  public void shouldNotFailAfterCorrectGameOpening() {
    actions.newGame(X_PLAYER_ID, O_PLAYER_ID).execute();

    Either<InvalidMove, Unit> result = actions.makeMove(X_PLAYER_ID, Cell.BOTTOM_LEFT).execute();

    assertThat(result.right()).isEqualTo(Unit.UNIT);
  }

  @Test
  public void shouldFailWhenSecondPlayerTriesToMarkOccupiedCell() {
    actions.newGame(X_PLAYER_ID, O_PLAYER_ID).execute();
    actions.makeMove(X_PLAYER_ID, Cell.BOTTOM_LEFT).execute();

    Either<InvalidMove, Unit> result = actions.makeMove(O_PLAYER_ID, Cell.BOTTOM_LEFT).execute();

    assertThat(result.left()).isEqualTo(InvalidMove.CELL_IS_NOT_EMPTY);
  }

  @Test
  public void shouldHandleIndependentGamesInParallel() {
    actions.newGame(X_PLAYER_ID, O_PLAYER_ID).execute();
    actions.newGame(OTHER_X_PLAYER_ID, OTHER_O_PLAYER_ID).execute();

    actions.makeMove(X_PLAYER_ID, Cell.BOTTOM_LEFT).execute().right();
    actions.makeMove(OTHER_X_PLAYER_ID, Cell.BOTTOM_LEFT).execute().right();
    actions.makeMove(O_PLAYER_ID, Cell.BOTTOM_MIDDLE).execute().right();
    actions.makeMove(OTHER_O_PLAYER_ID, Cell.BOTTOM_MIDDLE).execute().right();
  }
}

package com.birdbird314.tictactoe.actions;

import com.birdbird314.tictactoe.action.Actions;
import com.birdbird314.tictactoe.game.Cell;
import com.birdbird314.tictactoe.game.InvalidMove;
import com.birdbird314.tictactoe.player.HashSetActivePlayers;
import com.birdbird314.tictactoe.util.Either;
import com.birdbird314.tictactoe.util.Unit;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameInProgressTest {
  private static final String X_PLAYER_ID = "someXPlayerId";
  private static final String O_PLAYER_ID = "someOPlayerId";

  private Actions actions;

  @Before
  public void setUp() {
    actions = new Actions(new HashSetActivePlayers());
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
}
package com.birdbird314.tictactoe;

import com.birdbird314.tictactoe.action.Actions;
import com.birdbird314.tictactoe.util.Either;
import com.birdbird314.tictactoe.util.Unit;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {
  @Test
  public void shouldStartNewGame() {
    new Actions().newGame("someXPlayerId", "someOPlayerId").execute().right();
  }

  @Test
  public void shouldFailStartingANewGameIfXPlayerIsAlreadyPlaying() {
    Actions actions = new Actions();

    actions.newGame("someXPlayerId", "someOPlayerId").execute();
    Either<GameStartFail, Unit> result = actions.newGame("someXPlayerId", "someOtherOPlayerId").execute();

    assertThat(result.left()).isEqualTo(GameStartFail.X_ALREADY_PLAYS);
  }

  @Test
  public void shouldFailStartingANewGameIfOPlayerIsAlreadyPlaying() {
    Actions actions = new Actions();

    actions.newGame("someXPlayerId", "someOPlayerId").execute();
    Either<GameStartFail, Unit> result = actions.newGame("someOtherXPlayerId", "someOPlayerId").execute();

    assertThat(result.left()).isEqualTo(GameStartFail.O_ALREADY_PLAYS);
  }
}

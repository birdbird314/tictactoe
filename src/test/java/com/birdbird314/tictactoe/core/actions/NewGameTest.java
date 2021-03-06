package com.birdbird314.tictactoe.core.actions;

import com.birdbird314.tictactoe.core.action.Actions;
import com.birdbird314.tictactoe.core.action.GameStartFail;
import com.birdbird314.tictactoe.core.gamestatus.HashMapGameStatuses;
import com.birdbird314.tictactoe.core.player.HashSetActivePlayers;
import com.birdbird314.tictactoe.core.util.Either;
import com.birdbird314.tictactoe.core.util.Unit;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewGameTest {
  private Actions actions;

  @Before
  public void setUp() {
    actions = new Actions(new HashSetActivePlayers(), new HashMapGameStatuses());
  }

  @Test
  public void shouldStartNewGame() {
    actions.newGame("someXPlayerId", "someOPlayerId").execute().right();
  }

  @Test
  public void shouldFailStartingANewGameIfXPlayerIsAlreadyPlaying() {
    actions.newGame("someXPlayerId", "someOPlayerId").execute();
    Either<GameStartFail, Unit> result = actions.newGame("someXPlayerId", "someOtherOPlayerId").execute();

    assertThat(result.left()).isEqualTo(GameStartFail.X_ALREADY_PLAYS);
  }

  @Test
  public void shouldFailStartingANewGameIfOPlayerIsAlreadyPlaying() {
    actions.newGame("someXPlayerId", "someOPlayerId").execute();
    Either<GameStartFail, Unit> result = actions.newGame("someOtherXPlayerId", "someOPlayerId").execute();

    assertThat(result.left()).isEqualTo(GameStartFail.O_ALREADY_PLAYS);
  }
}

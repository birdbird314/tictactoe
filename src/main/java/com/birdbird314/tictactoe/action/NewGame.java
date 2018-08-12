package com.birdbird314.tictactoe.action;

import com.birdbird314.tictactoe.player.ActivePlayers;
import com.birdbird314.tictactoe.util.Command;
import com.birdbird314.tictactoe.util.Either;
import com.birdbird314.tictactoe.util.Unit;

import java.util.Optional;

import static com.birdbird314.tictactoe.util.Unit.UNIT;

class NewGame implements Command<GameStartFail, Unit> {
  private final ActivePlayers players;
  private final String xPlayerId;
  private final String oPlayerId;

  NewGame(String xPlayerId, String oPlayerId, ActivePlayers players) {
    this.xPlayerId = xPlayerId;
    this.oPlayerId = oPlayerId;
    this.players = players;
  }

  @Override
  public Either<GameStartFail, Unit> execute() {
    return failReason()
        .map(this::left)
        .orElseGet(this::startNewGame);
  }

  private Optional<GameStartFail> failReason() {
    return Optional.ofNullable(
        players.isPlaying(xPlayerId) ? GameStartFail.X_ALREADY_PLAYS
            : players.isPlaying(oPlayerId) ? GameStartFail.O_ALREADY_PLAYS
            : null
    );
  }

  private Either<GameStartFail, Unit> startNewGame() {
    players.aGameStartedFor(xPlayerId);
    players.aGameStartedFor(oPlayerId);
    return new Either.Right<>(UNIT);
  }

  private Either<GameStartFail, Unit> left(GameStartFail gameStartFail) {
    return new Either.Left<>(gameStartFail);
  }
}

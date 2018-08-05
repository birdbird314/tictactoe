package com.birdbird314.tictactoe.action;

import com.birdbird314.tictactoe.GameStartFail;
import com.birdbird314.tictactoe.util.Command;
import com.birdbird314.tictactoe.util.Either;
import com.birdbird314.tictactoe.util.Unit;

import java.util.Optional;
import java.util.Set;

import static com.birdbird314.tictactoe.util.Unit.UNIT;

class NewGame implements Command<GameStartFail, Unit> {
  private final Set<String> players;
  private final String xPlayerId;
  private final String oPlayerId;

  NewGame(String xPlayerId, String oPlayerId, Set<String> players) {
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
        players.contains(xPlayerId) ? GameStartFail.X_ALREADY_PLAYS
            : players.contains(oPlayerId) ? GameStartFail.O_ALREADY_PLAYS
            : null
    );
  }

  private Either<GameStartFail, Unit> startNewGame() {
    players.add(xPlayerId);
    players.add(oPlayerId);
    return new Either.Right<>(UNIT);
  }

  private Either<GameStartFail, Unit> left(GameStartFail gameStartFail) {
    return new Either.Left<>(gameStartFail);
  }
}

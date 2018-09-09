package com.birdbird314.tictactoe.core.action;

import com.birdbird314.tictactoe.core.gamestatus.GameStatuses;
import com.birdbird314.tictactoe.core.player.ActivePlayers;
import com.birdbird314.tictactoe.core.util.Command;
import com.birdbird314.tictactoe.core.util.Either;
import com.birdbird314.tictactoe.core.util.Unit;

import java.util.Optional;

import static com.birdbird314.tictactoe.core.util.Unit.UNIT;

class NewGame implements Command<GameStartFail, Unit> {
  private final ActivePlayers players;
  private final GameStatuses gameStatuses;
  private final String xPlayerId;
  private final String oPlayerId;

  NewGame(String xPlayerId, String oPlayerId, ActivePlayers players, GameStatuses gameStatuses) {
    this.xPlayerId = xPlayerId;
    this.oPlayerId = oPlayerId;
    this.players = players;
    this.gameStatuses = gameStatuses;
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
    players.startAGameAsX(xPlayerId);
    players.startAGameAsO(oPlayerId);
    gameStatuses.newGame(xPlayerId, oPlayerId);
    return new Either.Right<>(UNIT);
  }

  private Either<GameStartFail, Unit> left(GameStartFail gameStartFail) {
    return new Either.Left<>(gameStartFail);
  }
}

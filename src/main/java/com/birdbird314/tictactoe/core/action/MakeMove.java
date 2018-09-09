package com.birdbird314.tictactoe.core.action;

import com.birdbird314.tictactoe.core.game.Cell;
import com.birdbird314.tictactoe.core.game.Game;
import com.birdbird314.tictactoe.core.game.InvalidMove;
import com.birdbird314.tictactoe.core.gamestatus.GameStatuses;
import com.birdbird314.tictactoe.core.player.ActivePlayers;
import com.birdbird314.tictactoe.core.util.Command;
import com.birdbird314.tictactoe.core.util.Either;
import com.birdbird314.tictactoe.core.util.Unit;

class MakeMove implements Command<InvalidMove, Unit> {
  private final ActivePlayers activePlayers;
  private final GameStatuses gameStatuses;
  private final String playerId;
  private final Cell cellToMark;

  MakeMove(ActivePlayers players, GameStatuses gameStatuses, String playerId, Cell cell) {
    this.activePlayers = players;
    this.gameStatuses = gameStatuses;
    this.playerId = playerId;
    this.cellToMark = cell;
  }

  @Override
  public Either<InvalidMove, Unit> execute() {
    return makeMove().map(game -> {
      gameStatuses.submit(playerId, game);
      return Unit.UNIT;
    });
  }

  private Either<InvalidMove, Game> makeMove() {
    Game game = gameStatuses.current(playerId);
    return activePlayers.isXPlayer(playerId) ? game.markXOn(cellToMark) : game.markOOn(cellToMark);
  }
}

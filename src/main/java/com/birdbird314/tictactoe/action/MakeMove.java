package com.birdbird314.tictactoe.action;

import com.birdbird314.tictactoe.game.Cell;
import com.birdbird314.tictactoe.game.Game;
import com.birdbird314.tictactoe.game.InvalidMove;
import com.birdbird314.tictactoe.player.ActivePlayers;
import com.birdbird314.tictactoe.util.Command;
import com.birdbird314.tictactoe.util.Either;
import com.birdbird314.tictactoe.util.Unit;

class MakeMove implements Command<InvalidMove, Unit> {
  private final ActivePlayers activePlayers;
  private final GameStatus gameStatus;
  private final String playerId;
  private final Cell cellToMark;

  MakeMove(ActivePlayers players, GameStatus gameStatus, String playerId, Cell cell) {
    this.activePlayers = players;
    this.gameStatus = gameStatus;
    this.playerId = playerId;
    this.cellToMark = cell;
  }

  @Override
  public Either<InvalidMove, Unit> execute() {
    return makeMove().map(game -> {
      gameStatus.submit(game);
      return Unit.UNIT;
    });
  }

  private Either<InvalidMove, Game> makeMove() {
    Game game = gameStatus.current();
    return activePlayers.isXPlayer(playerId) ? game.markXOn(cellToMark) : game.markOOn(cellToMark);
  }
}

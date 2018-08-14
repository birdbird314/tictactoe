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
  private final String playerId;
  private final Cell cellToMark;

  MakeMove(ActivePlayers players, String playerId, Cell cell) {
    this.activePlayers = players;
    this.playerId = playerId;
    this.cellToMark = cell;
  }

  @Override
  public Either<InvalidMove, Unit> execute() {
    return makeMove().map(ignore -> Unit.UNIT);
  }

  private Either<InvalidMove, Game> makeMove() {
    Game game = new Game();
    return activePlayers.isXPlayer(playerId) ? game.markXOn(cellToMark) : game.markOOn(cellToMark);
  }
}

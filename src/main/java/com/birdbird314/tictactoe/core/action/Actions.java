package com.birdbird314.tictactoe.core.action;

import com.birdbird314.tictactoe.core.game.Cell;
import com.birdbird314.tictactoe.core.game.InvalidMove;
import com.birdbird314.tictactoe.core.gamestatus.GameStatuses;
import com.birdbird314.tictactoe.core.player.ActivePlayers;
import com.birdbird314.tictactoe.core.util.Command;
import com.birdbird314.tictactoe.core.util.Unit;

public class Actions {
  private final ActivePlayers players;
  private final GameStatuses gameStatuses;

  public Actions(ActivePlayers players, GameStatuses gameStatuses) {
    this.players = players;
    this.gameStatuses = gameStatuses;
  }

  public Command<GameStartFail, Unit> newGame(String xPlayerId, String oPlayerId) {
    return new NewGame(xPlayerId, oPlayerId, players, gameStatuses);
  }

  public Command<InvalidMove, Unit> makeMove(String playerId, Cell cell) {
    return new MakeMove(players, gameStatuses, playerId, cell);
  }
}

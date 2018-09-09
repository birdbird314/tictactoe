package com.birdbird314.tictactoe.action;

import com.birdbird314.tictactoe.game.Cell;
import com.birdbird314.tictactoe.game.InvalidMove;
import com.birdbird314.tictactoe.gamestatus.GameStatuses;
import com.birdbird314.tictactoe.player.ActivePlayers;
import com.birdbird314.tictactoe.util.Command;
import com.birdbird314.tictactoe.util.Unit;

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

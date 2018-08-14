package com.birdbird314.tictactoe.action;

import com.birdbird314.tictactoe.game.Cell;
import com.birdbird314.tictactoe.game.Game;
import com.birdbird314.tictactoe.game.InvalidMove;
import com.birdbird314.tictactoe.player.ActivePlayers;
import com.birdbird314.tictactoe.util.Command;
import com.birdbird314.tictactoe.util.Unit;

public class Actions {
  private final ActivePlayers players;
  private final GameStatus gameStatus = new GameStatus(new Game());

  public Actions(ActivePlayers players) {
    this.players = players;
  }

  public Command<GameStartFail, Unit> newGame(String xPlayerId, String oPlayerId) {
    return new NewGame(xPlayerId, oPlayerId, players);
  }

  public Command<InvalidMove, Unit> makeMove(String playerId, Cell cell) {
    return new MakeMove(players, gameStatus, playerId, cell);
  }
}

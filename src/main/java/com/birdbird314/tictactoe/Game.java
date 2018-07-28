package com.birdbird314.tictactoe;

public class Game {

  private final Board board;
  private final Turn whosTurn;

  private Game(Board board, Turn whosTurn) {
    this.board = board;
    this.whosTurn = whosTurn;
  }

  public Game() {
    this(new Board() {}, Turn.X);
  }

  public Board board() {
    return board;
  }

  public Either<String, Game> markXOn(Cell cellToMark) {
    return Turn.X == whosTurn
        ? new Either.Right<>(new Game(aBoardWith(cellToMark, State.X), Turn.O))
        : new Either.Left<>("Now it's O's turn");
  }

  public Either<String, Game> markOOn(Cell cellToMark) {
    return Turn.O == whosTurn
        ? new Either.Right<>(new Game(aBoardWith(cellToMark, State.O), Turn.X))
        : new Either.Left<>("Now it's X's turn");
  }

  private Board aBoardWith(Cell cellToMark, State stateToPut) {
    return new Board() {
      @Override
      public State stateOn(Cell cell) {
        return cell == cellToMark ? stateToPut : board.stateOn(cell);
      }
    };
  }

  private enum Turn {
    X, O
  }
}

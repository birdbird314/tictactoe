package com.birdbird314.tictactoe;

import java.util.Optional;

public class Game {

  private final Board board;
  private final Turn whosTurn;

  private Game(Board board, Turn whosTurn) {
    this.board = board;
    this.whosTurn = whosTurn;
  }

  public Game() {
    this(new Board() {
    }, Turn.X);
  }

  public Board board() {
    return board;
  }

  public Either<InvalidMove, Game> markXOn(Cell cellToMark) {
    return reasonForInvalidMove(cellToMark, Turn.X)
        .map(this::left)
        .orElse(new Either.Right<>(new Game(aBoardWith(cellToMark, State.X), Turn.O)));
  }

  public Either<InvalidMove, Game> markOOn(Cell cellToMark) {
    return reasonForInvalidMove(cellToMark, Turn.O)
        .map(this::left)
        .orElse(new Either.Right<>(new Game(aBoardWith(cellToMark, State.O), Turn.X)));
  }

  public Optional<Winner> winner() {
    return new OptionalWinner(board).get();
  }

  private Either<InvalidMove, Game> left(InvalidMove reason) {
    return new Either.Left<>(reason);
  }

  private Optional<InvalidMove> reasonForInvalidMove(Cell cellToMark, Turn intendedTurn) {
    if (intendedTurn != whosTurn) {
      return Optional.of(InvalidMove.IT_IS_NOT_YOUR_TURN);
    } else if (board.stateOn(cellToMark) != State.EMPTY) {
      return Optional.of(InvalidMove.CELL_IS_NOT_EMPTY);
    } else {
      return Optional.empty();
    }
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

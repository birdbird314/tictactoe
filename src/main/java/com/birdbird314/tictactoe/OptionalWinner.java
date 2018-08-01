package com.birdbird314.tictactoe;

import java.util.Optional;
import java.util.stream.Stream;

class OptionalWinner {
  private final Board board;

  OptionalWinner(Board board) {
    this.board = board;
  }

  Optional<Winner> get() {
    if (xWins())
      return Optional.of(Winner.X);
    else if (oWins())
      return Optional.of(Winner.O);
    else
      return Optional.empty();
  }

  private boolean xWins() {
    return winningGroups().anyMatch(this::hasOnlyXs);
  }

  private boolean oWins() {
    return winningGroups().anyMatch(this::hasOnlyOs);
  }

  private Stream<Cell[]> winningGroups() {
    Cell[] upperRow = {Cell.UPPER_LEFT, Cell.UPPER_MIDDLE, Cell.UPPER_RIGHT};
    Cell[] middleRow = {Cell.MIDDLE_LEFT, Cell.MIDDLE_MIDDLE, Cell.MIDDLE_RIGHT};
    Cell[] bottomRow = {Cell.BOTTOM_LEFT, Cell.BOTTOM_MIDDLE, Cell.BOTTOM_RIGHT};

    Cell[] leftColumn = {Cell.UPPER_LEFT, Cell.MIDDLE_LEFT, Cell.BOTTOM_LEFT};
    Cell[] middleColumn = {Cell.UPPER_MIDDLE, Cell.MIDDLE_MIDDLE, Cell.BOTTOM_MIDDLE};
    Cell[] rightColumn = {Cell.UPPER_RIGHT, Cell.MIDDLE_RIGHT, Cell.BOTTOM_RIGHT};

    Cell[] ascendingDiagonal = {Cell.BOTTOM_LEFT, Cell.MIDDLE_MIDDLE, Cell.UPPER_RIGHT};
    Cell[] descendingDiagonal = {Cell.UPPER_LEFT, Cell.MIDDLE_MIDDLE, Cell.BOTTOM_RIGHT};

    return Stream.of(upperRow, middleRow, bottomRow, leftColumn, middleColumn, rightColumn, ascendingDiagonal, descendingDiagonal);
  }

  private boolean hasOnlyXs(Cell[] cells) {
    return hasOnly(cells, State.X);
  }

  private boolean hasOnlyOs(Cell[] cells) {
    return hasOnly(cells, State.O);
  }

  private boolean hasOnly(Cell[] cells, State state) {
    return Stream.of(cells)
        .map(board::stateOn)
        .allMatch(state::equals);
  }
}

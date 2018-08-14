package com.birdbird314.tictactoe.util;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This is similar to Either in Scala, but much simpler
 * @param <L> type of 'Left' value, by convention used when something goes wrong
 * @param <R> type of 'Right' value, by convention used when everything is 'Right'
 */
public interface Either<L, R> {
  /**
   * @return result of failed computation if exists, otherwise throws UnsupportedOperationException
   */
  default L left() {
    throw new UnsupportedOperationException();
  }

  /**
   * @return result of successful computation if exists, otherwise throws UnsupportedOperationException
   */
  default R right() {
    throw new UnsupportedOperationException();
  }

  default void ifRight(Consumer<R> consumer) {}

  <U> Either<L, U> map(Function<? super R, ? extends U> mapper);

  class Left<L, R> implements Either<L, R> {
    private final L l;

    public Left(L l) {
      this.l = l;
    }

    @Override
    public L left() {
      return l;
    }

    @Override
    public <U> Either<L, U> map(Function<? super R, ? extends U> mapper) {
      return new Left<>(l);
    }
  }

  class Right<L, R> implements Either<L, R> {
    private final R r;

    public Right(R r) {
      this.r = r;
    }

    @Override
    public R right() {
      return r;
    }

    @Override
    public void ifRight(Consumer<R> consumer) {
      consumer.accept(r);
    }

    @Override
    public <U> Either<L, U> map(Function<? super R, ? extends U> mapper) {
      return new Right<>(mapper.apply(r));
    }
  }
}

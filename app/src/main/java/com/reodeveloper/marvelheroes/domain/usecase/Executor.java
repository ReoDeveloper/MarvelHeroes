package com.reodeveloper.marvelheroes.domain.usecase;

import com.reodeveloper.marvelheroes.common.usecase.UseCase;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Executor {
  private static final int INITIAL_POOL_SIZE = 1;
  private static final int MAX_POOL_SIZE = 2;
  private static final int KEEP_ALIVE_TIME = 10;
  private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
  private static ThreadPoolExecutor threadPoolExecutor;

  private static Executor instance;

  private Executor() {
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
        KEEP_ALIVE_TIME_UNIT, workQueue);
  }

  public static Executor getInstance() {
    synchronized (Executor.class) {
      if (instance == null) {
        instance = new Executor();
      }
    }
    return instance;
  }

  public void execute(final UseCase useCase, final UseCase.Result callback) {
    Runnable runnable = new Runnable() {
      @Override public void run() {
        useCase.execute(callback);
      }
    };
    threadPoolExecutor.execute(runnable);
  }
}

package com.reodeveloper.marvelheroes.common.usecase;

import com.reodeveloper.marvelheroes.common.repository.Repository;
import java.util.List;

public abstract class UseCase<T> {
  protected final Repository<T> repository;

  public UseCase(Repository<T> repository) {
    this.repository = repository;
  }

  public abstract void execute(Result<T> callback);

  public interface Result<T> {
    void success(List<T> result);

    void error(String message);
  }
}

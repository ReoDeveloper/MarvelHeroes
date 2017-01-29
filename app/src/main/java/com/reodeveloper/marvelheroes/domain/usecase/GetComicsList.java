package com.reodeveloper.marvelheroes.domain.usecase;

import android.os.Handler;
import android.os.Looper;
import com.reodeveloper.marvelheroes.common.repository.Repository;
import com.reodeveloper.marvelheroes.common.usecase.UseCase;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import java.util.List;

public class GetComicsList extends UseCase<Comic> {

  private final Integer idCharacter;

  public GetComicsList(Repository<Comic> repository, Integer idCharacter) {
    super(repository);
    this.idCharacter = idCharacter;
  }

  @Override public void execute(final Result<Comic> callback) {
    final List<Comic> items = repository.query(idCharacter);

    Handler mainHandler = new Handler(Looper.getMainLooper());
    mainHandler.post(new Runnable() {
      @Override public void run() {
        if (items != null) {
          callback.success(items);
        } else {
          callback.error("No results available");
        }
      }
    });
  }
}

package com.reodeveloper.marvelheroes.domain.usecase;

import android.os.Handler;
import android.os.Looper;
import com.reodeveloper.marvelheroes.common.repository.Repository;
import com.reodeveloper.marvelheroes.common.usecase.UseCase;
import com.reodeveloper.marvelheroes.data.Specification;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import java.util.List;

public class GetComicsList extends UseCase<Comic> {

  private final Specification specification;

  public GetComicsList(Repository<Comic> repository, int idCharacter, int page) {
    super(repository);
    specification = new Specification();
    specification.setIdCharacter(idCharacter);
    specification.setPage(page);
  }

  public GetComicsList nextPage(){
    int currentPage = specification.getPage();
    specification.setPage(++currentPage);
    return this;
  }

  @Override public void execute(final Result<Comic> callback) {
    final List<Comic> items = repository.query(specification);

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

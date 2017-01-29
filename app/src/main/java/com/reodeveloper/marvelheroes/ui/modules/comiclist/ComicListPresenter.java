package com.reodeveloper.marvelheroes.ui.modules.comiclist;

import android.util.Log;
import com.reodeveloper.marvelheroes.common.usecase.UseCase;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import com.reodeveloper.marvelheroes.domain.usecase.Executor;
import java.util.List;

public class ComicListPresenter implements ComicListContract.Actions {

  private final ComicListContract.View view;
  private final UseCase<Comic> getComicsList;
  private final Executor executor;

  public ComicListPresenter(ComicListContract.View view, UseCase<Comic> getComicsList) {
    this.view = view;
    this.getComicsList = getComicsList;
    executor = Executor.getInstance();
  }

  @Override public void start() {
    executor.execute(getComicsList, new UseCase.Result<Comic>() {
      @Override public void success(List<Comic> result) {
        view.displayComics(result);
      }

      @Override public void error(String message) {
        view.showError(message);
      }
    });
  }
}

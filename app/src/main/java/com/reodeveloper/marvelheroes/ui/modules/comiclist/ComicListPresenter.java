package com.reodeveloper.marvelheroes.ui.modules.comiclist;

import com.reodeveloper.marvelheroes.common.usecase.UseCase;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import com.reodeveloper.marvelheroes.domain.usecase.Executor;
import com.reodeveloper.marvelheroes.domain.usecase.GetComicsList;
import java.util.List;

public class ComicListPresenter implements ComicListContract.Actions {

  private final ComicListContract.View view;
  private final GetComicsList getComicsList;
  private final Executor executor;
  private boolean hasMorePages = true;
  private boolean isLoading = false;

  public ComicListPresenter(ComicListContract.View view, GetComicsList getComicsList) {
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

  @Override public void onLastItemReached() {
    view.showLoading(true);
    isLoading = true;
    if (hasMorePages) {
      isLoading = true;
      executor.execute(getComicsList.nextPage(), new UseCase.Result<Comic>() {
        @Override public void success(List<Comic> result) {
          if (result != null && !result.isEmpty()) {
            view.addComics(result);
          } else {
            hasMorePages = false;
          }
          view.showLoading(false);
          isLoading = false;
        }

        @Override public void error(String message) {
          view.showError(message);
        }
      });
  }
}
}

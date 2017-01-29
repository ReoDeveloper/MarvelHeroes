package com.reodeveloper.marvelheroes.ui.modules.comiclist;

import com.reodeveloper.marvelheroes.domain.model.Comic;
import java.util.List;

public interface ComicListContract {

  interface View {
    void showLoading(boolean show);

    void displayComics(List<Comic> items);

    void addComics(List<Comic> items);

    void showError(String error);
  }

  interface Actions {
    void start();

    void onLastItemReached();
  }
}

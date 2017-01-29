package com.reodeveloper.marvelheroes.ui.modules.comicdetail;

public interface ComicDetailContract {
  interface View {
    void setTitle(String text);

    void setDescription(String text);

    void loadImage(String url);

    void setIsbn(String text);

    void setIssue(double number);

    void setFormat(String text);

    void setPages(String text);
  }

  interface Actions {
    void start(String title, String description, String cover, String isbn, double issue,
        String format, String pages);
  }
}

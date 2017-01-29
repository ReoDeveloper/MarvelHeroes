package com.reodeveloper.marvelheroes.ui.modules.comicdetail;

public class ComicDetailPresenter implements ComicDetailContract.Actions {

  private final ComicDetailContract.View view;

  public ComicDetailPresenter(ComicDetailContract.View view) {
    this.view = view;
  }

  @Override
  public void start(String title, String description, String cover, String isbn, double issue,
      String format, String pages) {
    view.loadImage(cover);
    view.setTitle(title);
    view.setDescription(description);
    view.setIsbn(isbn);
    view.setIssue(issue);
    view.setFormat(format);
    view.setPages(pages);
  }
}
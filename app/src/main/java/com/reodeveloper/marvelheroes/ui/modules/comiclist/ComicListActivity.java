package com.reodeveloper.marvelheroes.ui.modules.comiclist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.reodeveloper.marvelheroes.R;
import com.reodeveloper.marvelheroes.common.provider.UseCaseProvider;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import com.reodeveloper.marvelheroes.ui.modules.comicdetail.ComicDetailActivity;
import java.util.List;

public class ComicListActivity extends AppCompatActivity implements ComicListContract.View {

  private final static int ID_CHARACTER = 1009610; // Spider-Man;
  private ComicListContract.Actions presenter;
  private RecyclerView recyclerComics;
  private GridLayoutManager gridLayoutManager;
  private ProgressBar progressBar;
  private boolean isLoading = false;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerComics = (RecyclerView) findViewById(R.id.recyclerComics);
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    setupRecyclerView();

    presenter = new ComicListPresenter(this, UseCaseProvider.getComicsList(ID_CHARACTER, 0));
    presenter.start();
  }

  private void setupRecyclerView() {
    gridLayoutManager = new GridLayoutManager(this, 2);
    recyclerComics.setLayoutManager(gridLayoutManager);
    recyclerComics.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int horizontalScroll, int verticalScroll) {
        super.onScrolled(recyclerView, horizontalScroll, verticalScroll);
        checkScrollRecyclerView(gridLayoutManager, horizontalScroll, verticalScroll);
      }
    });
  }

  private void checkScrollRecyclerView(LinearLayoutManager layoutManager, int horizontal,
      int vertical) {
    if (vertical > 0) {
      int visibleItemCount = layoutManager.getChildCount();
      int totalItemCount = layoutManager.getItemCount();
      int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
      if (!isLoading) {
        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
          presenter.onLastItemReached();
        }
      }
    }
  }

  @Override public void showLoading(boolean show) {
    isLoading = show;
    progressBar.setVisibility( show ? View.VISIBLE : View.GONE);
  }

  @Override public void displayComics(List<Comic> items) {
    ComicListAdapter adapter =
        new ComicListAdapter(items, new ComicListAdapter.ComicListItemClickListener() {
          @Override public void itemClick(Comic item, ImageView cover) {
            ComicDetailActivity.start(ComicListActivity.this, item, cover);
          }
        });
    recyclerComics.setAdapter(adapter);
  }

  @Override public void addComics(List<Comic> items) {
    ComicListAdapter adapter = (ComicListAdapter) recyclerComics.getAdapter();
    adapter.addItems(items);
  }

  @Override public void showError(String error) {
    Toast.makeText(ComicListActivity.this, error, Toast.LENGTH_SHORT).show();
  }
}

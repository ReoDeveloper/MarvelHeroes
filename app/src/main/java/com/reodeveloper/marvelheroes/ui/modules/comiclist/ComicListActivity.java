package com.reodeveloper.marvelheroes.ui.modules.comiclist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerComics = (RecyclerView) findViewById(R.id.recyclerComics);
    setupRecyclerView();

    presenter = new ComicListPresenter(this, UseCaseProvider.getComicsList(ID_CHARACTER));
    presenter.start();
  }

  private void setupRecyclerView() {
    recyclerComics.setLayoutManager(new GridLayoutManager(this, 2));
  }

  @Override public void showLoading(boolean show) {

  }

  @Override public void displayComics(List<Comic> items) {
    recyclerComics.setAdapter(
        new ComicListAdapter(items, new ComicListAdapter.ComicListItemClickListener() {
          @Override public void itemClick(Comic item) {
            ComicDetailActivity.start(ComicListActivity.this, item);
            Toast.makeText(ComicListActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
          }
        }));
  }

  @Override public void showError(String error) {

  }
}

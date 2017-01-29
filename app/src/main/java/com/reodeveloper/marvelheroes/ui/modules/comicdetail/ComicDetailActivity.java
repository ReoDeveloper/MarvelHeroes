package com.reodeveloper.marvelheroes.ui.modules.comicdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.reodeveloper.marvelheroes.R;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import com.squareup.picasso.Picasso;

public class ComicDetailActivity extends AppCompatActivity implements ComicDetailContract.View {

  public static final String TITLE = "title";
  public static final String DESCR = "description";
  public static final String COVER = "cover";
  public static final String ISBN = "isbn";
  public static final String ISSUE = "issue";
  public static final String FORMAT = "format";
  public static final String PAGES = "pages";

  private CollapsingToolbarLayout collapsingToolbarLayout;
  private ComicDetailContract.Actions presenter;

  public static void start(Context context, Comic item) {
    Intent intent = new Intent(context, ComicDetailActivity.class);
    String cover = item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension();
    intent.putExtra(TITLE, item.getTitle());
    intent.putExtra(DESCR, item.getDescription());
    intent.putExtra(COVER, cover);
    intent.putExtra(ISBN, item.getIsbn());
    intent.putExtra(ISSUE, item.getIssueNumber());
    intent.putExtra(FORMAT, item.getFormat());
    intent.putExtra(PAGES, item.getPageCount());
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    presenter = new ComicDetailPresenter(this);

    Intent intent = getIntent();
    Bundle args = intent.getExtras();
    String title = args.getString(TITLE);
    String descr = args.getString(DESCR);
    String cover = args.getString(COVER);
    String isbn = args.getString(ISBN);
    double issue = args.getDouble(ISSUE);
    String format = args.getString(FORMAT);
    String pages = args.getString(PAGES);
    presenter.start(title, descr, cover, isbn, issue, format, pages);
  }

  private void setText(String text, int resPlaceHolder, int resView){
    TextView textView = (TextView) findViewById(resView);
    if(text != null && !text.isEmpty()){
      String placeholder = getString(resPlaceHolder);
      textView.setText(String.format(placeholder, text));
    }else{
      textView.setVisibility(View.GONE);
    }
  }

  @Override public void loadImage(String url) {
    ImageView cover = (ImageView) findViewById(R.id.imgHero);
    Picasso.with(this).load(url).into(cover);
  }

  @Override public void setIsbn(String text) {
    setText(text, R.string.txt_placeholder_isbn, R.id.txtIsbn);
  }

  @Override public void setIssue(double number) {
    setText(String.valueOf(number), R.string.txt_placeholder_issue, R.id.txtIssueNumber);
  }

  @Override public void setFormat(String text) {
    setText(text, R.string.txt_placeholder_format, R.id.txtFormat);
  }

  @Override public void setPages(String text) {
    setText(text, R.string.txt_placeholder_pages, R.id.txtPageCount);
  }

  @Override public void setTitle(String text) {
    collapsingToolbarLayout =
        (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
    collapsingToolbarLayout.setTitle(text);
  }

  @Override public void setDescription(String text) {
    if(text != null && !text.isEmpty()){
      TextView description = (TextView) findViewById(R.id.txtDescription);
      description.setText(text);
    }else{
      CardView cardDescription = (CardView) findViewById(R.id.lay_card_description);
      cardDescription.setVisibility(View.GONE);
    }
  }
}

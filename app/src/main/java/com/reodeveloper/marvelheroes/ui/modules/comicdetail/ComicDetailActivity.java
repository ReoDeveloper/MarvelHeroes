package com.reodeveloper.marvelheroes.ui.modules.comicdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.reodeveloper.marvelheroes.domain.model.Comic;

public class ComicDetailActivity extends AppCompatActivity {

  public static final String COMIC = "comic";

  public static void start(Context context, Comic item){
    Intent intent = new Intent(context, ComicDetailActivity.class);
    Bundle args = new Bundle();
    args.putSerializable(COMIC, item);
    context.startActivity(intent, args);
  }
}

package com.reodeveloper.marvelheroes.ui.modules.comiclist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.reodeveloper.marvelheroes.R;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import com.squareup.picasso.Picasso;
import java.util.List;

class ComicListAdapter extends RecyclerView.Adapter<ComicListAdapter.ViewHolder> {

  private final List<Comic> items;
  private final ComicListItemClickListener listener;

  ComicListAdapter(List<Comic> items, ComicListItemClickListener listener) {
    this.items = items;
    this.listener = listener;
  }

  public void addItems(List<Comic> items){
    this.items.addAll(items);
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_item, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Comic item = items.get(position);
    holder.bind(item, listener);
    holder.title.setText(item.getTitle());
    String url = item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension();
    Picasso.with(holder.cover.getContext()).load(url).into(holder.cover);
  }

  @Override public int getItemCount() {
    return items.size();
  }

  interface ComicListItemClickListener {
    void itemClick(Comic item);
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView cover;

    ViewHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.txtTitle);
      cover = (ImageView) itemView.findViewById(R.id.imgCover);
    }

    void bind(final Comic item, final ComicListItemClickListener listener) {
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          listener.itemClick(item);
        }
      });
    }
  }
}

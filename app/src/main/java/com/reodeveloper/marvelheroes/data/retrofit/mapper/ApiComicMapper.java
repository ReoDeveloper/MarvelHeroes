package com.reodeveloper.marvelheroes.data.retrofit.mapper;

import com.reodeveloper.marvelheroes.data.Mapper;
import com.reodeveloper.marvelheroes.data.retrofit.model.ApiComic;
import com.reodeveloper.marvelheroes.domain.model.Comic;

public class ApiComicMapper extends Mapper<ApiComic, Comic> {

  @Override public Comic map(ApiComic item) {
    if (item == null) {
      return null;
    }
    int idComic = item.getId();
    // Just to make sure there is always a title, since it could be null
    String title = item.getTitle() != null ? item.getTitle() : "Unnamed comic";

    Comic comic = new Comic(idComic, title);
    comic.setDigitalId(item.getDigitalId());
    comic.setIssueNumber(item.getIssueNumber());
    comic.setDescription(item.getDescription());
    comic.setIsbn(item.getIsbn());
    comic.setFormat(item.getFormat());
    comic.setPageCount(item.getPageCount());
    comic.setThumbnail(item.getThumbnail());
    return comic;
  }
}

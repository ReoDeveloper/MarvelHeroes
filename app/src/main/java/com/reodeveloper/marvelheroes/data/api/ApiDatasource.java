package com.reodeveloper.marvelheroes.data.api;

import com.reodeveloper.marvelheroes.common.repository.DataSource;
import com.reodeveloper.marvelheroes.common.specification.Specification;
import com.reodeveloper.marvelheroes.data.model.ApiComicDataWrapper;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiDatasource implements DataSource<Comic> {
  private final static String BASE_URL = "http://gateway.marvel.com/v1/public/";

  @Override public Comic get(Specification specification) {
    return null;
  }

  @Override public List<Comic> query(Specification specification) {
    return null;
  }

  private interface Service {
    @GET("characters/{characterId}/comics") Call<ApiComicDataWrapper> getAllComicsByCharacterId(
        @Path("characterId") int characterId);
  }
}

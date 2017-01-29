package com.reodeveloper.marvelheroes.data.retrofit;

import com.reodeveloper.marvelheroes.common.repository.DataSource;
import com.reodeveloper.marvelheroes.data.Mapper;
import com.reodeveloper.marvelheroes.data.retrofit.model.ApiComic;
import com.reodeveloper.marvelheroes.data.retrofit.model.ApiComicDataContainer;
import com.reodeveloper.marvelheroes.data.retrofit.model.ApiComicDataWrapper;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ComicRetrofitDatasource implements DataSource<Comic> {
  private final static String BASE_URL = "http://gateway.marvel.com/v1/public/";
  private final Mapper<ApiComic, Comic> mapper;
  private final Service service;

  public ComicRetrofitDatasource(Mapper<ApiComic, Comic> mapper) {
    this.mapper = mapper;
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

    OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build();

    service = retrofit.create(Service.class);
  }

  @Override public Comic get(int id) {
    Call<ApiComicDataWrapper> call = service.getComicById(id);
    Response<ApiComicDataWrapper> callResponse = null;
    try {
      callResponse = call.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if ((callResponse != null) && callResponse.isSuccessful()) {
      ApiComicDataWrapper apiComicDataWrapper = callResponse.body();
      ApiComicDataContainer apiComicDataContainer = apiComicDataWrapper.getData();
      List<ApiComic> apiComic = null;
      if (apiComicDataContainer != null) {
        apiComic = apiComicDataContainer.getResults();
      }
      if (apiComic != null && apiComic.size() > 0) {
        return mapper.map(apiComic.get(0));
      }
    }
    return null;
  }

  @Override public List<Comic> query(int id) {
    Call<ApiComicDataWrapper> call = service.getAllComicsByIdCharacter(id);
    Response<ApiComicDataWrapper> callResponse = null;
    try {
      callResponse = call.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if ((callResponse != null) && callResponse.isSuccessful()) {
      ApiComicDataWrapper apiComicDataWrapper = callResponse.body();
      ApiComicDataContainer apiComicDataContainer = apiComicDataWrapper.getData();
      List<ApiComic> apiComic = null;
      if (apiComicDataContainer != null) {
        apiComic = apiComicDataContainer.getResults();
      }
      if (apiComic != null && apiComic.size() > 0) {
        return mapper.map(apiComic);
      }
    }
    return null;
  }

  private interface Service {
    @GET("characters/{characterId}/comics") Call<ApiComicDataWrapper> getAllComicsByIdCharacter(
        @Path("idCharacter") int idCharacter);

    @GET("comics/{idComic}") Call<ApiComicDataWrapper> getComicById(@Path("idComic") int idComic);
  }
}

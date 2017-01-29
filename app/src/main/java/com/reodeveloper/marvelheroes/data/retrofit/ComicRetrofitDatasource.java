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
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ComicRetrofitDatasource implements DataSource<Comic> {
  private final static String BASE_URL = "http://gateway.marvel.com/v1/public/";

  private final String APIKEY = "6a7ed890b4b941a925202a5630d5b162";
  private final String SECRET = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

  private final Mapper<ApiComic, Comic> mapper;
  private final Service service;

  public ComicRetrofitDatasource(Mapper<ApiComic, Comic> mapper) {
    this.mapper = mapper;
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

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

  @Override public List<Comic> query(int id) {
    Long tsLong = System.currentTimeMillis() / 1000;
    String timestamp = tsLong.toString();
    String hash = new String(Hex.encodeHex(DigestUtils.md5(timestamp + SECRET + APIKEY)));
    Call<ApiComicDataWrapper> call = service.getAllComicsByIdCharacter(id, APIKEY, hash, timestamp);

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
    @GET("characters/{idCharacter}/comics") Call<ApiComicDataWrapper> getAllComicsByIdCharacter(
        @Path("idCharacter") int idCharacter, @Query("apikey") String apikey,
        @Query("hash") String hash, @Query("ts") String timestamp);
  }
}

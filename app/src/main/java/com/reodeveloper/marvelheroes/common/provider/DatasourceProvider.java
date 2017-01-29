package com.reodeveloper.marvelheroes.common.provider;

import com.reodeveloper.marvelheroes.common.repository.DataSource;
import com.reodeveloper.marvelheroes.data.retrofit.ComicRetrofitDatasource;
import com.reodeveloper.marvelheroes.domain.model.Comic;

class DatasourceProvider {
  static DataSource<Comic> retrofitDatasource(){
    return new ComicRetrofitDatasource(MapperProvider.apiComicMapper());
  }
}

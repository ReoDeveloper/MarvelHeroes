package com.reodeveloper.marvelheroes.common.provider;

import com.reodeveloper.marvelheroes.common.repository.Repository;
import com.reodeveloper.marvelheroes.data.ComicsRepository;
import com.reodeveloper.marvelheroes.domain.model.Comic;

class RepositoryProvider {
  static Repository<Comic> comicRepository() {
    ComicsRepository repository = new ComicsRepository(DatasourceProvider.retrofitDatasource());
    return repository;
  }
}

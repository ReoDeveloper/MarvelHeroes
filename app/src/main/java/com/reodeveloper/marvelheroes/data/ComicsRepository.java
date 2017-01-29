package com.reodeveloper.marvelheroes.data;

import com.reodeveloper.marvelheroes.common.repository.DataSource;
import com.reodeveloper.marvelheroes.common.repository.Repository;
import com.reodeveloper.marvelheroes.domain.model.Comic;

public class ComicsRepository extends Repository<Comic> {

  public ComicsRepository(DataSource<Comic> datasource) {
    super(datasource);
  }

}

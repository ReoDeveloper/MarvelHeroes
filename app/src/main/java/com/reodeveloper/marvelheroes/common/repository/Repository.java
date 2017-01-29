package com.reodeveloper.marvelheroes.common.repository;

import com.reodeveloper.marvelheroes.data.Specification;
import java.util.List;

public class Repository<T> {
  protected final DataSource<T> datasource;

  public Repository(DataSource<T> datasource) {
    this.datasource = datasource;
  }

  public List<T> query(Specification specification) {
    return datasource.query(specification);
  }
}
package com.reodeveloper.marvelheroes.common.repository;

import com.reodeveloper.marvelheroes.common.specification.Specification;
import java.util.List;

public class Repository<T> {
  protected final DataSource<T> datasource;

  public Repository(DataSource<T> datasource){
    this.datasource = datasource;
  }

  public T get(Specification specification){
    return datasource.get(specification);
  }

  public List<T> query(Specification specification){
    return datasource.query(specification);
  }

}
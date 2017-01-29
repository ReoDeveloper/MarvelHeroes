package com.reodeveloper.marvelheroes.common.repository;

import java.util.List;

public class Repository<T> {
  protected final DataSource<T> datasource;

  public Repository(DataSource<T> datasource) {
    this.datasource = datasource;
  }

  public List<T> query(int id) {
    return datasource.query(id);
  }
}
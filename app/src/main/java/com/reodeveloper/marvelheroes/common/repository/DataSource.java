package com.reodeveloper.marvelheroes.common.repository;

import java.util.List;

public interface DataSource<T> {
  T get(int id);

  List<T> query(int id);
}

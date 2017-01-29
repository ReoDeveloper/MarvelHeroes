package com.reodeveloper.marvelheroes.common.repository;

import java.util.List;

public interface DataSource<T> {
  List<T> query(int id);
}

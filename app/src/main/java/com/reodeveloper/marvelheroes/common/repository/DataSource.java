package com.reodeveloper.marvelheroes.common.repository;

import com.reodeveloper.marvelheroes.common.specification.Specification;
import java.util.List;

public interface DataSource<T> {
  T get(Specification specification);

  List<T> query(Specification specification);
}

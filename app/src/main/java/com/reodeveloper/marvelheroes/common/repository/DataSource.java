package com.reodeveloper.marvelheroes.common.repository;

import com.reodeveloper.marvelheroes.data.Specification;
import java.util.List;

public interface DataSource<T> {
  List<T> query(Specification specification);
}

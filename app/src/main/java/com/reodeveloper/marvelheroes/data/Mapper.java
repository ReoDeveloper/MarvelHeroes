package com.reodeveloper.marvelheroes.data;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<I, O> {
  public abstract O map(I item);

  public List<O> map(List<I> list) {
    List<O> result = new ArrayList<O>();
    for (I item : list) {
      result.add(map(item));
    }
    return result;
  }
}
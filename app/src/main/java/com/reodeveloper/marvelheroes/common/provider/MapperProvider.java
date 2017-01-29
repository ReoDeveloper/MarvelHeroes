package com.reodeveloper.marvelheroes.common.provider;

import com.reodeveloper.marvelheroes.data.Mapper;
import com.reodeveloper.marvelheroes.data.retrofit.mapper.ApiComicMapper;
import com.reodeveloper.marvelheroes.data.retrofit.model.ApiComic;
import com.reodeveloper.marvelheroes.domain.model.Comic;

class MapperProvider {
  static Mapper<ApiComic, Comic> apiComicMapper(){
    return new ApiComicMapper();
  }
}

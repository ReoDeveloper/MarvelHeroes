package com.reodeveloper.marvelheroes.common.provider;

import com.reodeveloper.marvelheroes.common.usecase.UseCase;
import com.reodeveloper.marvelheroes.domain.model.Comic;
import com.reodeveloper.marvelheroes.domain.usecase.GetComicsList;

public class UseCaseProvider {
  public static UseCase<Comic> getComicsList(int idCharacter){
    return new GetComicsList(RepositoryProvider.comicRepository(), idCharacter);
  }
}

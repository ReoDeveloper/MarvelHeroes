package com.reodeveloper.marvelheroes.common.provider;

import com.reodeveloper.marvelheroes.domain.usecase.GetComicsList;

public class UseCaseProvider {
  public static GetComicsList getComicsList(int idCharacter, int page){
    return new GetComicsList(RepositoryProvider.comicRepository(), idCharacter, page);
  }
}

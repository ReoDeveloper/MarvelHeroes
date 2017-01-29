package com.reodeveloper.marvelheroes.data.retrofit.model

import com.reodeveloper.marvelheroes.domain.model.Image

/**
@see <a href="http://developer.marvel.com/docs">http://developer.marvel.com/docs</a>
Docs state that all fields are optional, so all fields must be nullable
 */

data class ApiComic(var id: Int = 0) {
  var title: String? = null
  var digitalId: Int = 0
  var issueNumber: Double = 0.toDouble()
  var description: String? = null
  var isbn: String? = null
  var format: String? = null
  var pageCount: String? = null
  var thumbnail: Image? = null
}

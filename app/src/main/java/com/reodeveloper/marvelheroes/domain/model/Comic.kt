package com.reodeveloper.marvelheroes.domain.model

import java.io.Serializable

data class Comic(var id: Int, var title: String) : Serializable {
  var digitalId: Int = 0
  var issueNumber: Double = 0.toDouble()
  var description: String? = null
  var isbn: String? = null
  var format: String? = null
  var pageCount: String? = null
  var thumbnail: Image? = null
}

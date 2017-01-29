package com.reodeveloper.marvelheroes.domain.model

data class Comic(var id: Int, var title: String) {
  var digitalId: Int = 0
  var issueNumber: Double = 0.toDouble()
  var description: String? = null
  var isbn: String? = null
  var format: String? = null
  var pageCount: String? = null
  var series: String? = null
  var dates: List<ComicDates>? = null
  var thumbnail: Image? = null
}

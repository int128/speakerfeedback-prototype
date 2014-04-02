package models

case class Item(id: String, title: String, var count: Int = 0) {
  def countUp() = { count += 1 }
}

case class Poll(items: Seq[Item])

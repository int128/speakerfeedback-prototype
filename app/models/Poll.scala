package models

case class Item(id: String, title: String)

case class Poll(items: Seq[Item])

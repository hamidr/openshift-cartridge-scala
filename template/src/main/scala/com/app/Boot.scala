package com.app

import info.mukel.telegrambot4s._, api._, methods._, models._, Implicits._


object WebhookBot extends TelegramBot with Webhook {
  def port = System.getenv("OPENSHIFT_SCALA_PORT").toInt
  def webhookUrl = "https://tg-yprox.rhcloud.com/"

  def toL337(s: String) = s.map("aegiost".zip("4361057").toMap.withDefault(identity))

  override def handleMessage(message: Message): Unit = {
    for (text <- message.text)
      api.request(SendMessage(message.sender, toL337(text)))
  }
}

object Boot extends App {
  WebhookBot.run()
}

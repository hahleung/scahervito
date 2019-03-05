package com.shortener.app

import com.shortener.app.controllers.ValidationForm
import org.scalatra._
import org.scalatra.forms.FormSupport
import org.scalatra.forms.{mapping, label, text, required, maxlength, optional, pattern, number, list}
import org.scalatra.i18n.I18nSupport

class Router extends ScalatraServlet with FormSupport with I18nSupport {
  val form = mapping(
    "text" -> label("Text", text(required, maxlength(100))),
    "email" -> label("Email", optional(text(pattern(".+@.+"), maxlength(20)))),
    "number" -> label("Number", optional(number())),
    "checkbox" -> list(text())
  )(ValidationForm.apply)

  get("/") {
    views.html.form()
  }

  post("/") {
    validate(form)(
      errors => BadRequest(views.html.hello()),
      form => views.html.result(form)
    )
  }

}

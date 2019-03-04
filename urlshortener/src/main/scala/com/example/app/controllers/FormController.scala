package com.example.app.controllers

import org.scalatra._
import org.scalatra.i18n.I18nSupport
import org.scalatra.forms._

case class ValidationForm(text: String, email: Option[String], number: Option[Int], checkbox: Seq[String])

//https://github.com/scalatra/scalatra-website-examples/blob/master/2.6/formats/scalatra-forms/build.sbt
//object FormController extends FormSupport with I18nSupport {
//  val form = mapping(
//    "text" -> label("Text", text(required, maxlength(100))),
//    "email" -> label("Email", optional(text(pattern(".+@.+"), maxlength(20)))),
//    "number" -> label("Number", optional(number())),
//    "checkbox" -> list(text())
//  )(ValidationForm.apply)
//
//  def post() =
//    validate(form)(
//      errors => BadRequest(html.form()),
//      form => html.result(form)
//    )
//}


package com.example.app.controllers

case class ValidationForm(text: String, email: Option[String], number: Option[Int], checkbox: Seq[String])


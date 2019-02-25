package com.example.app

import org.scalatra._

class ScalatraServlet extends ScalatraServlet {

  get("/") {
    views.html.hello()
  }

}

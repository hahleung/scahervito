package com.example.app

import org.scalatra._
//import com.example.app.controllers.FormController

class Router extends ScalatraServlet {

  get("/") {
    views.html.hello()
  }

//  post("/")(FormController.post())

}

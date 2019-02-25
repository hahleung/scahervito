package com.example.app

import org.scalatra.test.scalatest._

class ScalatraServletTests extends ScalatraFunSuite {

  addServlet(classOf[ScalatraServlet], "/*")

  test("GET / on ScalatraServlet should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}

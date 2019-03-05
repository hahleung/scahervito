package com.shortener.app

import org.scalatra.test.scalatest._

class RouterSpec extends ScalatraFunSuite {

  addServlet(classOf[Router], "/*")

  test("GET / on Router should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}

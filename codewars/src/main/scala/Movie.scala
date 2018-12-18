object Movie {
  def movie(cardPrice: Int, ticketPrice: Int, percentage: Double): Int = {
    @annotation.tailrec
    def go(numberOfTickets: Int, sumWithMemberShip: Double, sumByTicket: Int, getSumWithMemberShip: (Double, Int) => Double, getSumByTicket: Int => Int): Int =
      if (sumByTicket > sumWithMemberShip.toInt)
        numberOfTickets
      else {
        val newNumberOfTickets = numberOfTickets + 1
        val newSumWithMemberShip = getSumWithMemberShip(sumWithMemberShip, newNumberOfTickets)
        val newSumByTicket = getSumByTicket(newNumberOfTickets)

        go(newNumberOfTickets, newSumWithMemberShip, newSumByTicket, getSumWithMemberShip, getSumByTicket)
      }

    val getSumWithMemberShip = (subSum: Double, numberOfTickets: Int) => getSumWithMemberShipG(ticketPrice, percentage, subSum, numberOfTickets)
    val getSumByTicket = (numberOfTickets: Int) => getSumByTicketG(ticketPrice, numberOfTickets)

    go(0, cardPrice, 0, getSumWithMemberShip, getSumByTicket)
  }

  private def getSumWithMemberShipG(ticketPrice: Int, percentage: Double, subSum: Double, numberOfTickets: Int): Double =
    subSum + ticketPrice * math.pow(percentage, numberOfTickets)

  private def getSumByTicketG(ticketPrice: Int, numberOfTickets: Int): Int =
    ticketPrice * numberOfTickets

}

//Testing: 0, 10, 0.95
//Actual: 1
//Expect: 2

//Testing: 114, 50, 0.44
//Actual: 3
//Expect: 4

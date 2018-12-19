object Movie {
  def movie(cardPrice: Int, ticketPrice: Int, percentage: Double): Int = {
    @annotation.tailrec
    def go(numberOfTickets: Int, sumWithMemberShip: Double, sumByTicket: Int, getSumWithMemberShip: (Double, Int) => Double, getSumByTicket: Int => Int): Int = {
      if (sumByTicket > (math round sumWithMemberShip).toInt)
        numberOfTickets
      else {
        val newNumberOfTickets = numberOfTickets + 1
        val newSumWithMemberShip = getSumWithMemberShip(sumWithMemberShip, newNumberOfTickets)
        val newSumByTicket = getSumByTicket(newNumberOfTickets)

        go(newNumberOfTickets, newSumWithMemberShip, newSumByTicket, getSumWithMemberShip, getSumByTicket)
      }
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

// import scala.annotation.tailrec
// object Movie {
//   def movie(card: Int, ticket: Int, perc: Double): Int = {
//     @tailrec
//     def countVisits(n: Int, priceA: Int, priceB: Double, memberTicket: => Double): Int = {
//       if (math.ceil(priceB) < priceA) n
//       else countVisits(n + 1, priceA + ticket, priceB + memberTicket, memberTicket * perc)
//     }
//     countVisits(0, 0, card, ticket * perc)
//   }
// }

// object Movie {
//   def movie(card: Int, ticket: Int, perc: Double): Int =
//     Iterator
//       .from(1)
//       .scanLeft(card.toDouble) { case (total, i) => total + ticket * math.pow(perc, i) }
//       .zipWithIndex
//       .takeWhile { case (total, i) => total.ceil >= ticket * i }
//       .size
// }

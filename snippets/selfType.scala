trait User {
  def username: String
}

trait Tweeter {
  this: User =>  // reassign this
  def tweet(tweetText: String) = println(s"$username: $tweetText")
}

class VerifiedTweeter(val username_ : String) extends Tweeter with User {  // We mixin User because Tweeter required it
	def username = s"real $username_"
}

// class TestTweeter extends Tweeter {
//   def test = "it does not work because of `this: User =>`"
// }
// println(new TestTweeter().test)

val realBeyoncé = new VerifiedTweeter("Beyoncé")
realBeyoncé.tweet("Just spilled my glass of lemonade")

object SmallestIntegerFinder {
  def findSmallestInt(nums: List[Int]): Int = nums.min

  // def findSmallestInt(nums:List[Int]):Int = { nums.sorted.apply(0) }
  // def findSmallestInt(nums:List[Int]):Int = nums reduce math.min
  // def findSmallestInt(nums:List[Int]):Int = nums.foldLeft(nums(0))((a, b) => if (a > b) b else a)
}

package study01

object  FunctionExamples {
  def main(args: Array[String]) {
    println("Returned Value : " + addInt(5, 7));
    val add =(a:Int,b:Int)=>a+b
    val i = add(9, 10)
    println(i)
    val int = factorial(10)
    println(int)
    println(apply(layout,10));

    //闭包
    def makeAdder(adder: Int): Int => Int = {
      (x: Int) => x + adder
    }

    val addFive = makeAdder(5)
    val addTen = makeAdder(10)

    println(addFive(3))  // 输出 8
    println(addTen(3))   // 输出 13

    val books = List("hadoop", "Hive", "HDFS")
    println(books)
    def copies3(strings: List[String]): List[String] = {
      strings.map(s => s * 3)
    }
    // 事例
    println(copies3(List("a", "bb", "ccc")))

    def addStar(strings: List[String]): List[String] = {
      strings.map(s => s + "*")
    }
    // 示例用法
    println(addStar(List("a", "bb", "ccc")))

    def square(numbers: List[Int]): List[Int] = {
      numbers.map(n => n * n)
    }
    println(square(List(1, 2, 3)))
  }

  def addInt(a: Int, b: Int): Int = {
    var sum: Int = 0
    sum = a + b
    return sum
  }

  /**
   * 递归函数
   * @param n
   * @return
   */
  def factorial(n: BigInt): BigInt = {
    println(n)
    if (n <= 1)
      1
    else
      n * factorial(n - 1)
  }

  /**
   * 高阶函数
   * @param f
   * @param v
   * @return
   */
  // 函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v
  def apply(f: Int => String, v: Int) = f(v)
  def layout[A](x: A) = "[" + x.toString() + "]"
}

package study01

import scala.io.StdIn

object WhileExamples {
  def main(args: Array[String]): Unit = {
    print("请输入阶层（int）：")
    val x: Int = StdIn.readInt()
    var i = 1;
    while (i <= x) {
      var j = 1;
      while (j <= i) {
        print(s"$i * $j = " + i * j+"\t")
        j += 1
      }
      println()
      i += 1
    }
  }
}

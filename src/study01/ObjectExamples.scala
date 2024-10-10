package study01

// region 继承

class Point(val xc: Int,val yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println ("x 的坐标点: " + x);
    println ("y 的坐标点: " + y);
  }
}

class Location(override val xc: Int, override val yc: Int,
               val zc :Int) extends Point(xc, yc){
  var z: Int = zc

   def move(dx: Int, dy: Int, dz: Int) {
    x = x + dx
    y = y + dy
    z = z + dz
    println ("x 的坐标点 : " + x);
    println ("y 的坐标点 : " + y);
    println ("z 的坐标点 : " + z);
  }
}

// endregion
// region 重写抽象方法
class Person {
  private var name = ""

  override def toString = getClass.getName + "[name=" + name + "]"
}

class Employee extends Person {
  var salary = 0.0

  override def toString = super.toString + "\n[salary=" + salary + "]"
}

// endregion
object ObjectExamples {
  def main(args: Array[String]) {
    val pt = new Point(10, 20);

    // 移到一个新的位置
    pt.move(10, 10);

    val location = new Location(10, 20, 25)
    location.move(10, 15, 5)
    println("===============")
    val fred = new Employee
//    fred.name = "Fred"
    fred.salary = 50000
    println(fred.toString)
    val add =(a:Int,b:Int)=>a+b
    println(add(1,2))
  }
}

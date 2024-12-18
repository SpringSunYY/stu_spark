import org.apache.spark.sql.{Row, SparkSession}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import java.util.Properties

object SparkWriteMySQL {
  def main(args: Array[String]): Unit = {
    // 屏蔽日志
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    println("大数据升本一班-2024303050102-杨勇")
    val sparkSession = SparkSession.builder()
      .appName("SparkWriteMySQL")
      .master("local[*]") // 单机模式
      .config("spark.driver.memory", "2g") // 增加 Driver 内存
      .getOrCreate()
    try {
      // 数据准备
      val studentRDD = sparkSession.sparkContext.parallelize(Array("11 Zhangsan M 27", "12 Lilei F 25")).map(_.split(" "))
      val schema = StructType(List(StructField("id", IntegerType, true), StructField("name", StringType, true), StructField("gender", StringType, true), StructField("age", IntegerType, true)))
      val rowRDD = studentRDD.map(p => Row(p(0).toInt, p(1).trim, p(2).trim, p(3).toInt))
      val studentDF = sparkSession.createDataFrame(rowRDD, schema)
      // MySQL 配置
      val prop = new Properties()
      prop.put("user", "root")
      prop.put("password", "yy0908..")
      prop.put("driver", "com.mysql.cj.jdbc.Driver") // 使用最新 MySQL 驱动
      // 数据写入
      studentDF.write.mode("append").jdbc("jdbc:mysql://localhost:3306/mysql_score_db", "mysql_score_db.student_spark", prop)
      println("数据写入成功")
    } catch {
      case e: Exception =>
        e.printStackTrace()
        println("任务中断，捕获到异常")
    } finally {
      // 关闭 SparkSession
      sparkSession.stop()
    }
  }
}

package com.yy.sparkContext

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{Dataset, SparkSession}

object DataSetWordCount {
  def main(args: Array[String]): Unit = {
    // 屏蔽日志
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    println("大数据升本一班-2024303050102-杨勇")
    val sparkSession = SparkSession.builder()
      .appName("DataSetWordCount")
      .master("local[*]") // 单机模式
      .config("spark.driver.memory", "2g") // 增加 Driver 内存
      .getOrCreate()
    val projectPath = System.getProperty("user.dir")
    val filePath = projectPath + "/src/main/resources/data/words.txt"
    // 读取文本数据
    import sparkSession.implicits._
    val data = sparkSession.read.text("file:///" + filePath).as[String]
    println(data)
    val words = data.flatMap(value => value.split(" "))
    val groupWords = words.groupByKey(_.toLowerCase)
    val counts = groupWords.count()
    counts.show()
    sparkSession.stop()
  }
}

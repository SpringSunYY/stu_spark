package com.yy.sparkContext

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

/*
* SparkContext是应用启动时创建的Spark上下文对象，是进行Spark应用开发的主要接口，
* 是Spark上层应用与底层实现的中转站。
* */
object Wordcount {
  def main(args: Array[String]) {
    //Create conf object
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[*]") // 设置为local模式，可以使用所有CPU核
      .set("spark.local.dir", "E:\\Spark\\Code\\Study\\myStudy\\myStudySpark\\temp") // 指定本地目录
    //create spark context object
    val sc = new SparkContext(conf)
    //Check whether sufficient params are supplied
    if (args.length < 2) {
      println("Usage: ScalaWordCount <input> <output>")
      System.exit(1)
    }
    //Read file and create RDD
    val rawData = sc.textFile(args(0))
    //convert the lines into words using flatMap operation
    val words = rawData.flatMap(line => line.split(" "))
    //count the individual words using map and reduceByKey operation
    val wordCount = words.map(word => (word, 1)).reduceByKey(_ + _)
    //Save the result
    wordCount.saveAsTextFile(args(1))
    //stop the spark context
    sc.stop
  }
}

package com.yy.tesm

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Team1 {
  def main(args: Array[String]): Unit = {
    val config:SparkConf = new SparkConf().setAppName("MapOperator").setMaster("local[3]")
    val sc = new SparkContext(config)
    val rdd:RDD[Int] = sc.parallelize(21 to 25)
    val mapRDD:RDD[Int] = rdd.map(x => x * 5)
    println("大数据升本一班-2024303050102-杨勇")
    mapRDD.collect().foreach(println(_))
  }
}

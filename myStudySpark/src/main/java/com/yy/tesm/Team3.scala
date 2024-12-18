package com.yy.tesm

import org.apache.spark.sql.{SparkSession, functions}

import java.util.Properties

object Team3 {
  def main(args: Array[String]): Unit = {
    // 创建 SparkSession
    val spark = SparkSession.builder().appName("Spark SQL Example").master("local[*]")
      .config("spark.executor.memory", "4g")
      .config("spark.driver.memory", "4g")
      .getOrCreate()
    // 设置日志级别为 ERROR，关闭 INFO 级别日志
    spark.sparkContext.setLogLevel("ERROR")
    // 设置 JDBC 连接参数
    val url = "jdbc:mysql://localhost:3306/mysql_spark"
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "yy0908..")
    prop.put("driver", "com.mysql.cj.jdbc.Driver") // 使用最新 MySQL 驱动
    // 2.1）读取 MySQL 中的 employee 表并按 DSL 风格输出结果
    val employeeDF = spark.read
      .jdbc(url, "employee", prop)
    // 显示数据
    println("2.1）读取数据并按 DSL 风格输出结果：")
    employeeDF.show()
    // 2.2）根据 COMPANY 分组，求 SALARY 的和、最大值、最小值和平均值并输出结果
    val aggregatedDF = employeeDF.groupBy("COMPANY")
      .agg(
        functions.sum(functions.col("SALARY").cast("long")).alias("Total_Salary"), // 显式转换为 long 类型
        functions.max(functions.col("SALARY").cast("long")).alias("Max_Salary"),
        functions.min(functions.col("SALARY").cast("long")).alias("Min_Salary"),
        functions.avg(functions.col("SALARY").cast("double")).alias("Avg_Salary") // 显式转换为 double 类型
      )
    println("2.2）根据 COMPANY 分组并求 SALARY 的和、最大值、最小值和平均值：")
    aggregatedDF.show()
    // 2.3）将 SALARY 增加 2000 并输出结果
    val updatedDF = employeeDF.withColumn("Updated_Salary", functions.col("SALARY") + 2000)
    println("2.3）将 SALARY 增加 2000 并输出结果：")
    updatedDF.show()
    // 2.4）按 SALARY 和 AGE 降序排列并输出结果
    val sortedDF = employeeDF.orderBy(functions.col("SALARY").desc, functions.col("AGE").desc)
    println("2.4）按 SALARY 和 AGE 降序排列并输出结果：")
    sortedDF.show()
    // 输出学号等信息
    println("大数据升本一班-2024303050102-杨勇")
    // 停止 SparkSession
    spark.stop()
  }
}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount App")
    val sc: SparkContext = new SparkContext(conf)

    // 读取每行数据
    val lines: RDD[String] = sc.textFile("E:\\Spark\\Code\\Study\\myStudy\\myStudySpark\\src\\main\\resources\\data/wc.txt")
    // 获取所有单词
    val words: RDD[String] = lines.flatMap(_.split(" "))
    // 将单词转为 map，例如：(spark, 1)
    val wordMap: RDD[(String, Int)] = words.map((_, 1))
    // 根据 key 进行聚合，得出词频集合
    val results: RDD[(String, Int)] = wordMap.reduceByKey(_ + _)

    results.collect().foreach(result => println(result))
  }
}
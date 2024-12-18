import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    //屏蔽日志
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount App")
    val sc: SparkContext = new SparkContext(conf)

    // 读取每行数据
    val lines: RDD[String] = sc.textFile("E:\\Spark\\Code\\Study\\myStudy\\myStudySpark\\src\\main\\resources\\data/words.txt")
    // 获取所有单词
    val words: RDD[String] = lines.flatMap(_.split(" "))
    // 将单词转为 map，例如：(spark, 1)
    val wordMap: RDD[(String, Int)] = words.map((_, 1))
    // 根据 key 进行聚合，得出词频集合
    val results: RDD[(String, Int)] = wordMap.reduceByKey(_ + _)

    results.collect().foreach(result => println(result))

    //    val inputFile = "file:///home/hadoop/word.txt"
    //    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")
    //    val sc = new SparkContext(conf)
    //    val textFile = sc.textFile(inputFile)
    //    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
    //    wordCount.foreach(println)
  }
}
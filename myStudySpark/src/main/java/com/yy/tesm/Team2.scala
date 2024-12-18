import org.apache.spark.{SparkConf, SparkContext}

object Team2 {
  def main(args: Array[String]): Unit = {
    // 1. 初始化 Spark 配置和上下文
    val config = new SparkConf().setAppName("WordFrequencyCount").setMaster("local[3]")
    val sc = new SparkContext(config)
    // 2. 读取文件
    val projectPath = System.getProperty("user.dir")
    val filePath = projectPath+"/src/main/resources/data/words.txt"
    val lines = sc.textFile(filePath)
    // 3. 进行词频统计
    val words = lines.flatMap(line => line.split(","))
    val wordCount = words.map(word => (word, 1)).reduceByKey(_ + _)
    // 4. 打印结果
    wordCount.collect().foreach { case (word, count) =>
      println(s"$word: $count")
    }
    println("大数据升本一班-2024303050102-杨勇")
  }
}

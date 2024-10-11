package study01

object EscapeCharacterExamples {
  def main(args: Array[String]): Unit = {
    val backspace = "Hello\bWorld"     // "HelloWorld"
    val tab = "Hello\tWorld"           // "Hello    World"
    val newline = "Hello\nWorld"       // "Hello
    // World"
    val formFeed = "Hello\fWorld"      // "HelloWorld"
    val carriageReturn = "Hello\rWorld"// "World"
    val doubleQuote = "He said, \"Hello, World!\""  // "He said, "Hello, World!""
    val singleQuote = '\"'             // '"'
    val backslash = "This is a backslash: \\"  // "This is a backslash: \"

    // 输出示例
    println(s"Backspace: $backspace")
    println(s"Tab: $tab")
    println(s"Newline: $newline")
    println(s"FormFeed: $formFeed")
    println(s"CarriageReturn: $carriageReturn")
    println(s"DoubleQuote: $doubleQuote")
    println(s"SingleQuote: $singleQuote")
    println(s"Backslash: $backslash")
  }
}
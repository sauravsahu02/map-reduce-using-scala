package com.mapreduce.hadoop

import java.util

import org.apache.hadoop.io.{IntWritable, Text}
import org.scalatest.FunSuite

/**
 * Created by Saurav 24 May 2021
 */

class WordCountUsingHadoopTest extends FunSuite {
  test("calculate sum of iterables") {
    val iterable = new util.ArrayList[IntWritable](util.Arrays.asList(
      new IntWritable(1),
      new IntWritable(2),
      new IntWritable(1)))

    assert(WordCountUsingHadoop.calculateSum(iterable) == 4)
  }

  test("test getWordsInLine") {
    val line = "Hello World, how are you"
    assert(WordCountUsingHadoop.getWordsInLine(new Text(line)).sameElements(Array("Hello", "World,", "how", "are", "you")))
  }
}

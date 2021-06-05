package com.mapreduce.hadoop

import java.lang

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.{Job, Mapper, Reducer}
/**
 * Created by Saurav Sahu : 23 May 2021
 */
object WordCountUsingHadoop {

  val wordDelimiter = " "

  def getWordsInLine(value : Text) = value.toString.split(wordDelimiter)

  type Mapper_T = Mapper[Object /* InputData */, Text /* Line */, Text /* Word */, IntWritable /* OccurrenceCount */]

  class LineTokenizerMapper extends Mapper_T {
    val word = new Text
    val singleOccurrence = new IntWritable(1)
    override def map(key: Object, value: Text, context: Mapper_T#Context): Unit = {
      getWordsInLine(value).foreach(token =>{
        word.set(token)
        context.write(word, singleOccurrence)
      })
    }
  }

  def calculateSum(values: lang.Iterable[IntWritable]) = {
    var iterator = values.iterator
    var total = 0
    while (iterator.hasNext) total += iterator.next.get()
    total
  }

  type Reducer_T = Reducer[Text /* Word */, IntWritable /* Occurrence */, Text /* Word */, IntWritable /* Occurrences */]

  class OccurrencesReducer extends Reducer_T{
    val totalOccurrences = new IntWritable(1)
    override def reduce(key: Text, values: lang.Iterable[IntWritable], context: Reducer_T#Context): Unit = {
      totalOccurrences.set(calculateSum(values))
      context.write(key, totalOccurrences)
    }
  }

  def main(args: Array[String]): Unit ={
    val mapReduceHadoopJob = Job.getInstance(new Configuration, "Word Count Using Hadoop")
    mapReduceHadoopJob.setJarByClass(this.getClass)
    mapReduceHadoopJob.setMapperClass(classOf[LineTokenizerMapper])
    mapReduceHadoopJob.setReducerClass(classOf[OccurrencesReducer])
    mapReduceHadoopJob.setOutputKeyClass(classOf[Text])
    mapReduceHadoopJob.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(mapReduceHadoopJob, new Path(args(0)))
    FileOutputFormat.setOutputPath(mapReduceHadoopJob, new Path(args(1)))
    System.exit(mapReduceHadoopJob.waitForCompletion(true).compare(true))
  }
}

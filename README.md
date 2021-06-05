## MapReduce using Scala

Run this command to generate a fat-jar `WordCountUsingHadoop-1.0.jar`  : 
```sbt assembly```

Demo run:

```Demo-WordCountOnHadoop:]$ echo "Hello world hello hello" > input.txt
Demo-WordCoundOnHadoop:]$ cat input.txt
Hello world hello hello
Demo-WordCountOnHadoop:]$ docker exec -it hadoop-namenode bash
(docker) [root@hadoop-namenode /]# hadoop fs -ls /user/root/
(docker) [root@hadoop-namenode /]# hadoop fs -copyFromLocal input.txt /user/root/input
(docker) [root@hadoop-namenode /]# hadoop jar WordCountUsingHadoop-1.0.jar /user/root/input output
(docker) [root@hadoop-namenode /]# hadoop fs -ls /user/root/
Found 2 items
-rw-r--r--   2 root supergroup         24 2021-05-24 05:53 /user/root/input
drwxr-xr-x   - root supergroup          0 2021-05-24 05:57 /user/root/output
(docker) [root@hadoop-namenode /]# hadoop fs -ls /user/root/output
Found 2 items
-rw-r--r--   2 root supergroup          0 2021-05-24 05:57 /user/root/output/_SUCCESS
-rw-r--r--   2 root supergroup         24 2021-05-24 05:57 /user/root/output/part-r-00000
(docker) [root@hadoop-namenode /]# hadoop fs -cat /user/root/output/part-r-00000
Hello	1
hello	2
world	1
```

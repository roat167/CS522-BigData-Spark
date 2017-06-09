# Introduction:

This application is written in scala and is used to to analyse apache access_log. The output will
- display the unique ip address
- and count number of request between March, 8th 2004 and March, 11th 2004.

# Instruction:

Assumed that you have Apache Hadoop, Spark, maven already installed on your machine:
I developed on Cloudera. You can see the Presentation.pdf file to see how to install it.

## Step 1: building jar
	In SparkProject directory, right click => Open in Terminal
	Type : ./build.sh
## Step 2: run the program
	Type : ./run.sh

#### Directory: 
	src/ 		source code
	target/		classes files and other
	input/ 		contains access_log file
	build.sh	script file for building classes and jar
	run.sh		script for processing log file and generate output files	


 

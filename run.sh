#!/bin/sh

#./build.sh

#cleaning
hadoop fs -rm -r -f /user/cloudera/input
hadoop fs -rm -r -f /user/cloudera/output
#
#placing input data
hadoop fs -put input /user/cloudera/
#
#submitting JAR file
#hadoop jar hadoop1 "partone/MyPair" /user/cloudera/input /user/cloudera/output
spark-submit --class sparkproject.Application target/project-0.0.1-SNAPSHOT.jar 

#output directory check
if [ -d "outputspark" ]; then
	rm -r -f outputspark
fi
#
mkdir outputspark
#extracting output
hadoop fs -get /user/cloudera/output/* ./outputspark


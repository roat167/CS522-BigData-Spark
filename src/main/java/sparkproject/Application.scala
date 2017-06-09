package sparkproject
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext._
import java.util.TimeZone

object Application {
	def main(args: Array[String]) {
	
		//External file
		val filePath = "input/access_log"
	    val conf = new SparkConf().setAppName("Spark Application")
	    
	    // local[2] meaning two threads 
	    // which represents “minimal” parallelism
	    // help detect bugs that only exist when we run in a distributed context.
	    conf.setMaster("local[2]")  
	    
	    val sc = new SparkContext(conf)
	    
	   	//create an RDD using external data, with 3partitions
	    val fileRDD = sc.textFile(filePath, 3).cache()   	
	   	
	   	val format = new java.text.SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z")
		format.setTimeZone(TimeZone.getTimeZone("GMT"))
		 
	   	val minDate = format.parse("08/Mar/2004:00:00:01 +0800")
	   	val maxDate = format.parse("11/Mar/2004:00:00:01 +0800")
	   	 
	    val mappedRDD = fileRDD.map(line =>  new AccessLog(line))    
    		.filter(s => format.parse(s.requestDate.toString).after(minDate)
    			 	&& format.parse(s.requestDate.toString).before(maxDate) 
    		)
    		.map(k => (k.ipAddress, 1))
    		.reduceByKey(_+_)
	
	   	//save result to output directory
	   	mappedRDD.saveAsTextFile("output");    
    }
}
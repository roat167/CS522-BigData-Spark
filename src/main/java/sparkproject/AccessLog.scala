package sparkproject

import java.io._

class AccessLog(val logLine: String) extends java.io.Serializable {
	var ipAddress: String = null
	var respondCode: String = null
	var requestDate: String = null	
	
	//val pattern = new Regex("""(.*?) - - \[(.*?)\] "(.*?)" (\d+) """, "ip", "date", "uri", "resCode")  
	//64.242.88.10 - - [07/Mar/2004:16:47:12 -0800] "GET /robots.txt HTTP/1.1" 200 68
				
	val ipArr: Array[String] = logLine.split(" - - ")
	val dateArr: Array[String] = ipArr(1).split("]")
	val resCodeArr: Array[String] = ipArr(1).split(" ")
	
	//set values
	this.ipAddress = ipArr(0)		
	this.requestDate = dateArr(0).drop(1)
	this.respondCode = resCodeArr(resCodeArr.size - 2)
	
	override def toString: String = "Respond Code is "+ respondCode + " for request from IP: " + ipAddress 	+ " on Date: " + requestDate	
	
}

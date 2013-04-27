/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-2-18下午03:31:16</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.monodb;

import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-2-18 </p>
 * @version V1.0  
 */
public class QuickTour{

	public static void main(String[] args) {
		try {
			MongoClient mongoClient = new MongoClient("10.120.21.51",27017);
			
			DB db = mongoClient.getDB("jdb");
			BasicDBObject doc = new BasicDBObject();
			doc.append("name1", "jan");
//			
//			Set<String> colls = db.getCollectionNames();
////			
////			for (String s : colls) {
////			    System.out.println(s);
////			}
//			
			DBCollection coll = db.getCollection("jdb");
			coll.insert(doc);
//			
//			
			DBObject myDoc = coll.findOne();
			System.out.println(myDoc);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

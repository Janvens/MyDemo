/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-3-15下午03:03:28</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.monodb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.jan.monodb.model.Person;
import com.mongodb.Mongo;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-3-15 </p>
 * @version V1.0  
 */
public class MongoApp{

	private static final Log log = LogFactory.getLog(MongoApp.class);
	
	public static void main(String[] args) throws Exception{
		MongoOperations mongoOps = new MongoTemplate(new Mongo(),"database");
		
		mongoOps.insert(new Person("jon",12));
		log.info(mongoOps.findOne(new Query(Criteria.where("name").is("jon")), Person.class));
		
//		log.info(mongoOps.findOne(new Query(Criteria.where("name").is("Joe")), Person.class));
		
		mongoOps.dropCollection("person");
		
	}
	
}

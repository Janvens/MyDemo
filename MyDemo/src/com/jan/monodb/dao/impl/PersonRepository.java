/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-3-15上午11:11:48</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.monodb.dao.impl;

import java.util.List;
import java.util.regex.Pattern;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.jan.monodb.dao.AbstractRepository;
import com.jan.monodb.model.Person;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-3-15 </p>
 * @version V1.0  
 */
public class PersonRepository implements AbstractRepository{
	
	private MongoTemplate mongoTemplate;

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see com.jan.monodb.dao.AbstractRepository#insert(com.jan.monodb.model.Person)
	 */
	public void insert(Person person) {
		this.mongoTemplate.insert(person);
		
	}

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see com.jan.monodb.dao.AbstractRepository#findOne(java.lang.String)
	 */
	public Person findOne(String id) {
		return this.mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Person.class);
	}

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see com.jan.monodb.dao.AbstractRepository#findAll()
	 */
	public List<Person> findAll() {
		return this.mongoTemplate.find(new Query(), Person.class);
	}

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see com.jan.monodb.dao.AbstractRepository#findByRegex(java.lang.String)
	 */
	public List<Person> findByRegex(String regex) {
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Criteria criteria = new Criteria("name").regex(pattern.toString());
		return this.mongoTemplate.find(new Query(criteria), Person.class);
	}

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see com.jan.monodb.dao.AbstractRepository#removeOne(java.lang.String)
	 */
	public void removeOne(String id) {
		Criteria criteria = Criteria.where("id").in(id);
		if(criteria == null){
			Query query = new Query(criteria);
			if(query != null && mongoTemplate.findOne(query, Person.class)!=null){
				this.mongoTemplate.remove(mongoTemplate.findOne(query, Person.class));
			}
		}
		
	}

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see com.jan.monodb.dao.AbstractRepository#removeAll()
	 */
	public void removeAll() {
		List<Person> list = this.findAll();
		if(list !=null){
			for(Person person : list){
				this.mongoTemplate.remove(person);
			}
		}
		
	}

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see com.jan.monodb.dao.AbstractRepository#findAndModify(java.lang.String)
	 */
	public void findAndModify(String id) {
		this.mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), new Update().inc("age", 3),Person.class);
	}

	/**
	 * @param mongoTemplate the mongoTemplate to set
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}

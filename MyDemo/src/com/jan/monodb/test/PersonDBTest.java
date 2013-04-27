/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-3-15上午11:29:42</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.monodb.test;

import javax.annotation.Resource;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jan.monodb.dao.impl.PersonRepository;
import com.jan.monodb.model.Person;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-3-15 </p>
 * @version V1.0  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring_config.xml")
public class PersonDBTest extends TestCase{

	@Resource
	private PersonRepository personRepository;
	
	@Test
	public void testMongoDb(){
		Person person = new Person("jan",1);
		personRepository.insert(person);
	}
	
}

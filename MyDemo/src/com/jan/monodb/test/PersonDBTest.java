/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-3-15����11:29:42</p>
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
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-3-15 </p>
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

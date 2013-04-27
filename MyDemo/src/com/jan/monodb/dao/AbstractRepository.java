/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-3-15����11:10:55</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.monodb.dao;

import java.util.List;
import com.jan.monodb.model.Person;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-3-15 </p>
 * @version V1.0  
 */
public interface AbstractRepository{
	
	public void insert(Person person);  
    public Person findOne(String id);  
    public List<Person> findAll();  
    public List<Person> findByRegex(String regex);  
    public void removeOne(String id);  
    public void removeAll();  
    public void findAndModify(String id); 
}

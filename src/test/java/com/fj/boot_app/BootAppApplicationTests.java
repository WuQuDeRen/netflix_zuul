package com.fj.boot_app;

import com.alibaba.fastjson.JSON;
import com.fj.boot.app.dao.PersonDao;
import com.fj.boot.app.domain.PersonDO;
import com.fj.boot.app.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class BootAppApplicationTests {

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private PersonService personService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void test() {
		int a = 23;
		List<PersonDO> personList = personDao.query();
		System.out.println(personList);
		List<PersonDO> testList = personDao.query();

	}


	public static void main(String[] args) throws ParseException {
//		AtomicBoolean active = new AtomicBoolean();
//		System.out.println(JSON.toJSONString(active.get()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.parse("2019-05-31 12:00:00");

	}

	@Test
	public void testSession() {
		personService.query();
	}

	@Test
	public void testSessionTwo() {
		personService.queryTwo();
	}


	@Test
	public void testCollection() {
		List<PersonDO> personDOS = personDao.listPerson();
		System.out.println(personDOS);

	}


}

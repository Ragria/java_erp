package java_erp.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java_erp.dao.impl.TitleDaoImpl;
import java_erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	
	private TitleDao dao;

	@Before
	public void setUp() throws Exception {
		dao = TitleDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
		dao = null;
	}

	@Test
	public void testSelectTitleByAll() {
		System.out.println("testSelectTitleByAll()");
		ArrayList<Title> list = dao.selectTitleByAll();
		Assert.assertNotEquals(0, list.size());
		
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectTitleByNo() {
		System.out.println("testSelectTitleByNo()");
		Title selectTitle = dao.selectTitleByNo(new Title(2));
		System.out.println(selectTitle);
	}

	@Test
	public void testInsertTitle() {
		System.out.println("testInsertTitle()");
		Title newTitle = new Title(3, "과장");
		
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println("res >> " + res);
	}

	@Test
	public void testUpdateTitle() {
		System.out.println("testUpdateTitle()");
		Title updateTitle = new Title(3, "대리");
		
		int res = dao.updateTitle(updateTitle);
		Assert.assertEquals(1, res);
		System.out.println("res >>> " + res);
	}

	@Test
	public void testDeleteTitle() {
		System.out.println("testDeleteTitle()");
		Title deleteTitle = new Title(3);
		
		int res = dao.deleteTitle(deleteTitle);
		Assert.assertEquals(1, res);
		System.out.println("res >>> " + res);
	}

}

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

import java_erp.dao.impl.DepartmentDaoImpl;
import java_erp.dto.Department;
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private static DepartmentDao dao ;;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("테스트 클래스 시작 전 - setUpBeforeClass()");
		dao =  DepartmentDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("테스트 클래스 시작 후 - tearDownAfterClass()");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Test메서드 수행 전 - setUp()");
	
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test메서드 수행 후 - tearDown()");
	}

	@Test
	public void test01SelectDepartmentByAll() {
		System.out.println("Test 메서드 -  test01SelectDepartmentByAll()");
		ArrayList<Department> list = dao.selectDepartmentByAll();
		Assert.assertNotEquals(0, list.size());
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test02SelectDepartmentByNo() {
		System.out.println("Test 메서드 -  test02SelectTitleByNo()");
		Department selectDept = dao.selectDepartmentByNo(new Department(2));
		Assert.assertNotNull(selectDept);
		System.out.println(selectDept);
	}

	@Test
	public void test03InsertDepartment() {
		System.out.println("Test 메서드 - test03InsertDepartment()");
		Department newDept = new Department(4, "총무", 25);
		int res = dao.insertDepartment(newDept);
		Assert.assertEquals(1, res);
		
		dao.deleteDepartment(newDept);
	}

	@Test
	public void test04UpdateDepartment() {
		System.out.println("Test 메서드 - test04UpdateDepartment()");
		//추가
		Department dept = new Department(4, "총무", 25);
		dao.insertDepartment(dept);
		//수정-검증
		dept.setDeptName("인사");
		dept.setFloor(19);
		int res = dao.updateDepartment(dept);
		Assert.assertEquals(1, res);
		//변경유무 출력
		Department seldept = dao.selectDepartmentByNo(dept);
		System.out.println(seldept);
		//삭제
		dao.deleteDepartment(seldept);
		
	}

	@Test
	public void test05DeleteDepartment() {
		System.out.println("Test 메서드 - test05DeleteDepartment()");
		//추가
		Department dept = new Department(4, "총무", 25);
		dao.insertDepartment(dept);
		//삭제 - 검증
		int res = dao.deleteDepartment(dept);
		Assert.assertEquals(1, res);
		
		Department seldept = dao.selectDepartmentByNo(dept);
		Assert.assertNull(seldept);	
	}

}

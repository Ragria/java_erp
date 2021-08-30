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

import java_erp.dao.impl.EmployeeDaoImpl;
import java_erp.dto.Department;
import java_erp.dto.Employee;
import java_erp.dto.Title;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();



	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void testSelectEmployeeAll() {
		System.out.println("testSelectEmployeeAll()");
		ArrayList<Employee> list = dao.selectEmployeeAll();
		Assert.assertNotEquals(0, list.size());
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectEmployeeByNo() {
		System.out.println("testSelectEmployeeByNo()");
		Employee selectEmp = dao.selectEmployeeByNo(new Employee(4377));
		Assert.assertNotNull(selectEmp);
		System.out.println(selectEmp);
	}
//////////////////////////////////////////////
	@Test
	public void test01InsertEmployee() {
		System.out.println("test01InsertEmployee()");
		Employee newEmp = new Employee(1010, "신혜리", null, null, 2500000, null);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		dao.deleteEmployee(newEmp);
	}
	@Test
	public void test02InsertEmployee() {
		System.out.println("test02InsertEmployee()");
		Title title = new Title(2);
		Employee newEmp = new Employee(1011, "신혜리1", title, null, 2500000, null);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		dao.deleteEmployee(newEmp);
	}
	@Test
	public void test03InsertEmployee() {
		System.out.println("test03InsertEmployee()");
		Title title = new Title(2);
		Employee manager = new Employee(4377);
		Employee newEmp = new Employee(1012, "신혜리2", title, manager, 2500000, null);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		dao.deleteEmployee(newEmp);
	}
	@Test
	public void test04InsertEmployee() {
		System.out.println("test04InsertEmployee()");
		Title title = new Title(2);
		Employee manager = new Employee(4377);
		Department dno = new Department(1);
		Employee newEmp = new Employee(1013, "신혜리3", title, manager, 2500000, dno);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		dao.deleteEmployee(newEmp);
	}
//////////////////////////////////////////////
	@Test
	public void test01UpdateEmployee() {
		System.out.println("test01UpdateEmployee()");
		Employee newEmp = new Employee(1010, "신혜리", null, null, 2500000, null);
		dao.insertEmployee(newEmp);
		
		//수정
		newEmp.setEmpName("최명기");
		newEmp.setSalary(1500000);
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);		
		
		dao.deleteEmployee(newEmp);
	}
	@Test
	public void test02UpdateEmployee() {
		System.out.println("test02UpdateEmployee()");
		Title title = new Title(2);
		Employee manager = new Employee(4377);
		Department dno = new Department(1);
		Employee newEmp = new Employee(1013, "신혜리3", title, manager, 2500000, dno);
		dao.insertEmployee(newEmp);
		
		//수정
		newEmp.setTitle(new Title(1));
		newEmp.setManager(new Employee(1005));
		newEmp.setDno(new Department(2));
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);		
		
		dao.deleteEmployee(newEmp);
		
	}

	@Test
	public void testDeleteEmployee() {
		System.out.println("testDeleteEmployee()");
		Employee newEmp = new Employee(1004, "석주명", null, null, 4000000, null);
		dao.insertEmployee(newEmp);
		
		int res = dao.deleteEmployee(newEmp);
		Assert.assertEquals(1, res);
		
	}

}

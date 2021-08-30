package java_erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import java_erp.dao.EmployeeDao;
import java_erp.dto.Department;
import java_erp.dto.Employee;
import java_erp.dto.Title;
import java_erp.util.jdbcUtil;

public class EmployeeDaoImpl implements EmployeeDao{
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();

	@Override
	public ArrayList<Employee> selectEmployeeAll() {
		String sql = "select empno, empname, title, manager, salary, dno from employee";
		ArrayList<Employee> list = null;
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(); ){
			if (rs.next()) {
				list = new ArrayList<Employee>();
				do {
					list.add(getEmployee(rs));
				}while(rs.next());	
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "select empno, empname, title, manager, salary, dno from employee where empno = ?";
		try	(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, emp.getEmpNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?)";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				){
			
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			//값이 null인 것을 감안(title, manager, dno)
			try {
				pstmt.setInt(3, emp.getTitle().gettNo());
			}catch (NullPointerException e) {
				pstmt.setNull(3, Types.INTEGER);
			}
			try {
				pstmt.setInt(4, emp.getManager().getEmpNo());
			}catch (NullPointerException e) {
				pstmt.setNull(4, Types.INTEGER);
			}
			pstmt.setInt(5, emp.getSalary());
			try {
				pstmt.setInt(6, emp.getDno().getDeptNo());
			}catch (NullPointerException e) {
				pstmt.setNull(6, Types.INTEGER);
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql = "update employee"
					+ "	set empname = ?,  salary = ?, title = ?, manager = ?, dno = ?"
					+ "	where empno = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				){
		pstmt.setString(1, emp.getEmpName());
		pstmt.setInt(2, emp.getSalary());
		//값이 null인 것을 감안(title, manager, dno)
		try {
			pstmt.setInt(3, emp.getTitle().gettNo());
		}catch (NullPointerException e) {
			pstmt.setNull(3, Types.INTEGER);
		}
		try {
			pstmt.setInt(4, emp.getManager().getEmpNo());
		}catch (NullPointerException e) {
			pstmt.setNull(4, Types.INTEGER);
		}
		try {
			pstmt.setInt(5, emp.getDno().getDeptNo());
		}catch (NullPointerException e) {
			pstmt.setNull(5, Types.INTEGER);
		}
		pstmt.setInt(6, emp.getEmpNo());
		System.out.println("pstmt : " + pstmt);
		return pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "delete from employee where empno = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
			)
		{
			pstmt.setInt(1, emp.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static EmployeeDaoImpl getInstance() {
		return instance;
	}
	
	private Employee getEmployee(ResultSet rs) throws SQLException {
		//empno, empname, title, manager, salary, dno
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		
		Title title = rs.getInt("title") == 0 ? null : new Title(rs.getInt("title"));
		Employee manager = rs.getInt("manager") == 0 ? null : new Employee(rs.getInt("manager"));
		int salary = rs.getInt("salary");
		Department dept = rs.getInt("dno") == 0 ? null : new Department(rs.getInt("dno"));
		return new Employee(empNo, empName, title, manager, salary, dept);
	}


}

package java_erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java_erp.dao.DepartmentDao;
import java_erp.dto.Department;
import java_erp.dto.Title;
import java_erp.util.jdbcUtil;

/**
 * Impl은 무조건 singleton Pattern 구현
 *
 */

public class DepartmentDaoImpl implements DepartmentDao {
	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();
	
	private DepartmentDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	

	public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public ArrayList<Department> selectDepartmentByAll() {
		String sql = "select deptno, deptname, floor from department";
		ArrayList<Department> list = null;
		try (Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			
			list = new ArrayList<Department>();
			while(rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*
		 *1. DB접속(시간이 많이 걸림) <- Connection Pool
		 *2. SQL을 Database에 맞는 명령문으로 변경(스트링 -> 명령문)
		 *[3. SQL에서 ? 를 입력 매개변수 값으로 치환해서 SQL명령문을 완성]
		 *4. SQL명령문 실행(select : executeQuery 
		 *					Insert, Update, delete : executeUpdate)
		 *5. 만약 executeQuery일 경우 : SQL결과(ResultSet)를 클래스로 변환
		  */		
		
		return list;
	}


	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptNo");
		String deptName = rs.getString("deptName");
		int floor = rs.getInt("floor");
		Department newDept = new Department(deptNo, deptName, floor);
		return newDept;
	}

	@Override
	public Department selectDepartmentByNo(Department department) {
		
		/*
		 *1. DB접속(시간이 많이 걸림) <- Connection Pool
		 *2. SQL을 Database에 맞는 명령문으로 변경(스트링 -> 명령문)
		 *[3. SQL에서 ? 를 입력 매개변수 값으로 치환해서 SQL명령문을 완성]
		 *4. SQL명령문 실행(select : executeQuery 
		 *					Insert, Update, delete : executeUpdate)
		 *5. 만약 executeQuery일 경우 : SQL결과(ResultSet)를 클래스로 변환
		  */	
		String sql = "select deptno, deptname, floor from department where deptno = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
			){
			pstmt.setInt(1, department.getDeptNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int insertDepartment(Department department) {
		/*
		 *1. DB접속(시간이 많이 걸림) <- Connection Pool
		 *2. SQL을 Database에 맞는 명령문으로 변경(스트링 -> 명령문)
		 *[3. SQL에서 ? 를 입력 매개변수 값으로 치환해서 SQL명령문을 완성]
		 *4. SQL명령문 실행(select : executeQuery 
		 *					Insert, Update, delete : executeUpdate)
		 *5. 만약 executeQuery일 경우 : SQL결과(ResultSet)를 클래스로 변환
		  */
		String sql = "insert into department values(?, ?, ?)";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
			)
		{
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateDepartment(Department department) {
		/*
		 *1. DB접속(시간이 많이 걸림) <- Connection Pool
		 *2. SQL을 Database에 맞는 명령문으로 변경(스트링 -> 명령문)
		 *[3. SQL에서 ? 를 입력 매개변수 값으로 치환해서 SQL명령문을 완성]
		 *4. SQL명령문 실행(select : executeQuery 
		 *					Insert, Update, delete : executeUpdate)
		 *5. 만약 executeQuery일 경우 : SQL결과(ResultSet)를 클래스로 변환
		  */
		String sql = "update department set deptname = ? where deptno = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)
			)
		{
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getDeptNo());
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDepartment(Department department) {
		/*
		 *1. DB접속(시간이 많이 걸림) <- Connection Pool
		 *2. SQL을 Database에 맞는 명령문으로 변경(스트링 -> 명령문)
		 *[3. SQL에서 ? 를 입력 매개변수 값으로 치환해서 SQL명령문을 완성]
		 *4. SQL명령문 실행(select : executeQuery 
		 *					Insert, Update, delete : executeUpdate)
		 *5. 만약 executeQuery일 경우 : SQL결과(ResultSet)를 클래스로 변환
		  */
		String sql = "delete from department where deptno = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)
			)
		{
			pstmt.setInt(1, department.getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


}

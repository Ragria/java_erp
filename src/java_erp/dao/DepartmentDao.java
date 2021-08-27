package java_erp.dao;

import java.util.ArrayList;

import java_erp.dto.Department;
import java_erp.dto.Title;

public interface DepartmentDao {
	ArrayList<Department> selectDepartmentByAll();
	Department selectTitleByNo(Title department);
	
	int insertDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(Department department);
}

package java_erp.dto;

public class Department {
	private int deptNo;		//부서번호
	private String deptName;//부서명
	private int floor;		//위치
	
	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	public Department(int deptNo) {
		this.deptNo = deptNo;
	}
	
	public Department(int deptNo, String deptName, int floor) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.floor = floor;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "Department [deptNo=" + deptNo + ", deptName=" + deptName + ", floor=" + floor + "]";
	}
	
}

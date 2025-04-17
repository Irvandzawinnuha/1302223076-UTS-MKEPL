package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	private boolean isForeigner;
	private boolean gender;
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	private String spouseName;
	private String spouseIdNumber;
	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

/*sesudah di refactoring setMonthlySalary dari line 56 - 72*/
	public void setMonthlySalary(int grade) {
		int baseSalary = 0;
		switch (grade) {
			case 1:
				baseSalary = 3000000;
				break;
			case 2:
				baseSalary = 5000000;
				break;
			case 3:
				baseSalary = 7000000;
				break;
			default:
				baseSalary = 0;
		}
		monthlySalary = isForeigner ? (int)(baseSalary * 1.5) : baseSalary;
	}


	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}
	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	

	//sesudah di refactoring getAnnualIncomeTax dari line 92 - 113
	public int getAnnualIncomeTax() {
		int monthsWorked = calculateMonthsWorkedThisYear();
		boolean isSingle = spouseIdNumber.equals("");
		int numberOfChildren = childIdNumbers.size();
		
		return TaxFunction.calculateTax(
			monthlySalary,
			otherMonthlyIncome,
			monthsWorked,
			annualDeductible,
			isSingle,
			numberOfChildren
		);
	}
	private int calculateMonthsWorkedThisYear() {
		LocalDate date = LocalDate.now();
		if (date.getYear() == yearJoined) {
			return date.getMonthValue() - monthJoined;
		} else {
			return 12;
		}
	}

}

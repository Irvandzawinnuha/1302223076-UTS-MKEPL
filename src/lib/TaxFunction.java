package lib;
public class TaxFunction {
	//kode setelah di refactoring dan menambahkan class TaxData.java dari line 16 - 29
	public static int calculateTax(TaxData data) {
		int tax = 0;
		if (data.numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		int maxChildren = Math.min(data.numberOfChildren, 3);
		int nonTaxableIncome = 54000000;
		if (data.isMarried) {
			nonTaxableIncome += 4500000 + (maxChildren * 1500000);
		}
		double taxableIncome = ((data.monthlySalary + data.otherMonthlyIncome) * data.numberOfMonthWorking) - data.deductible - nonTaxableIncome;
		tax = (int) Math.round(0.05 * taxableIncome);
		return Math.max(tax, 0);
		}

}

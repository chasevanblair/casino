
public class Person {
	private String name;
	private double balance;
	
	public Person(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return name + ", $" + balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}

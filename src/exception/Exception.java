package exception;

import java.io.*;
import java.rmi.RemoteException;
import javax.naming.InsufficientResourcesException;

/*检查性异常：最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。例如要打开一个不存在文件时，一个异常就发生了，这些异常在编译时不能被简单地忽略。
运行时异常： 运行时异常是可能被程序员避免的异常。与检查性异常相反，运行时异常可以在编译时被忽略。
错误： 错误不是异常，而是脱离程序员控制的问题。错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译也检查不到的。
*/
/*catch 不能独立于 try 存在。
在 try/catch 后面添加 finally 块并非强制性要求的。
try 代码后不能既没 catch 块也没 finally 块。
try, catch, finally 块之间不能添加任何代码。
*/
public class Exception {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			int a[] = new int[2];
			System.out.println("Access element three :" + a[3]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception thrown  :" + e);
		}
		System.out.println("Out of the block");

		/*
		 * try{ // 程序代码 }catch(异常类型1 异常的变量名1){ // 程序代码 }catch(异常类型2 异常的变量名2){ //
		 * 程序代码 }finally{ // 程序代码 }
		 */
		int a[] = new int[2];
		try {

			System.out.println("Access element three :" + a[3]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception thrown  :" + e);
		} finally {
			a[0] = 6;
			System.out.println("First element value: " + a[0]);
			System.out.println("The finally statement is executed");

		}

		// 自定义类的测试
		CheckingAccount c = new CheckingAccount(101);
		System.out.println("Depositing $500...");
		c.deposit(500.00);
		try {
			System.out.println("\nWithdrawing $100...");
			c.withdraw(100.00);
			System.out.println("\nWithdrawing $600...");
			c.withdraw(600.00);
		} catch (InsufficientFundsException e) {
			System.out.println("Sorry, but you are short $" + e.getAmount());
			e.printStackTrace();
		}
	}

	/*
	 * 如果一个方法没有捕获一个检查性异常，那么该方法必须使用 throws 关键字来声明。throws 关键字放在方法签名的尾部。
	 * 
	 * 也可以使用 throw 关键字抛出一个异常，无论它是新实例化的还是刚捕获到的。
	 */
	public void withdraw(double amount) throws RemoteException, InsufficientResourcesException {
		if (amount > 0)
			throw new RemoteException();
		else
			throw new InsufficientResourcesException();
	}
}

/*
 * 在 Java 中你可以自定义异常。编写自己的异常类时需要记住下面的几点。
 * 
 * 所有异常都必须是 Throwable 的子类。如果希望写一个检查性异常类， 则需要继承 Exception 类。如果你想写一个运行时异常类， 那么需要继承
 * RuntimeException 类。
 * 
 * 可以像 下面这样定义自己的异常类： 只继承Exception 类来创建的异常类是检查性异常类。 下面的
 * InsufficientFundsException 类是用户定义的异常类，它继承自 Exception。 一个异常类和其它任何类一样，包含有变量和方法。
 */
@SuppressWarnings("serial")
class InsufficientFundsException extends Throwable {
	private double amount;

	public InsufficientFundsException(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}
}

// 可以抛出自定义异常的类
class CheckingAccount {
	private double balance;
	private int number;

	public CheckingAccount(int number) {
		this.number = number;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) throws InsufficientFundsException {
		if (amount <= balance) {
			balance -= amount;
		} else {
			double needs = amount - balance;
			throw new InsufficientFundsException(needs);
		}
	}

	public double getBalance() {
		return balance;
	}

	public int getNumber() {
		return number;
	}
}
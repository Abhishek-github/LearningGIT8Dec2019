package com.myTCS.TCS_Hib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Employee_Salary_Populator 
{

	static Configuration cfg = new Configuration().configure().addAnnotatedClass(Employee_Salary_Management_System_POJO.class);
	static SessionFactory sf = cfg.buildSessionFactory();
	static Session sn = sf.openSession();
	static Transaction tx = sn.beginTransaction();

	
	public static void main(String[] args) throws InterruptedException
	{
			
		System.out.println("Please choose an option:");
		Thread.sleep(800);
		System.out.println("1. Get an employee as per Id");
		System.out.println("2. Delete an employee record as per Id");
		System.out.println("3. Insert a new employee record");
		System.out.println("4. Get all employees records");
		
		System.out.println("Enter your choice:");
		Scanner cin = new Scanner(System.in);
		
		int userOption = cin.nextInt();
		
		if(userOption == 1)
		{
			getEmployeeByid();
		}
		
		else if(userOption == 2)
		{
			deleteEmployeeByid();
		}
		
		else if(userOption == 3)
		{
			addNewEmployee();
		}
		
		else if(userOption == 4)
		{
			getAllEmployees();
		}
		
		
		else
		{
			System.out.println("Invalid option");
			
		}
		
	}


	private static void getAllEmployees() throws InterruptedException 
	{
		System.out.println("Getting details of all employees...");
		Thread.sleep(2400);
		
		Criteria info = sn.createCriteria(Employee_Salary_Management_System_POJO.class);
		
		ArrayList<Employee_Salary_Management_System_POJO> allEmp = (ArrayList<Employee_Salary_Management_System_POJO>) info.list();
		
		tx.commit();
		
		System.out.println(allEmp);
		
	}

	
	
	public static void addServletEmployee(String name, int empId, String department, int salary) throws InterruptedException
	{
		System.out.println("addServletEmployee() method called");
		
		Employee_Salary_Management_System_POJO newEmp = new Employee_Salary_Management_System_POJO();
		newEmp.setDepartment(department);
		newEmp.setEmployee_id(empId);
		newEmp.setEmployee_name(name);
		newEmp.setSalary(salary);

		Serializable sessionId = sn.save(newEmp);
		System.out.println("session returned a serializable object: "+sessionId);
		
		tx.commit();	
		
//		once we call commit() session will get closed.
		
		System.out.println("Details inserted successfully !!");
		
		
	}
	

	public static void addNewEmployee() throws InterruptedException 
	{
		System.out.println("Enter below details of new Employee:");
		
		System.out.println("Name: ");
		Scanner cinName = new Scanner(System.in);
		String newEmployeeName = cinName.nextLine();
		
		System.out.println("Employee Id: ");
		Scanner cinId = new Scanner(System.in);
		int newEmployeeId = cinId.nextInt();
		
		System.out.println("Department: ");
		Scanner cinDeptt = new Scanner(System.in);
		String newEmployeeDeptt = cinDeptt.nextLine();
		
		System.out.println("Salary: ");
		Scanner cinSalary = new Scanner(System.in);
		int newEmployeeSalary = cinSalary.nextInt();
		
		System.out.println("Inserting details into DB...");
		
		Thread.sleep(2000);

		Employee_Salary_Management_System_POJO newEmp = new Employee_Salary_Management_System_POJO();
		newEmp.setDepartment(newEmployeeDeptt);
		newEmp.setEmployee_id(newEmployeeId);
		newEmp.setEmployee_name(newEmployeeName);
		newEmp.setSalary(newEmployeeSalary);

		Serializable sessionId = sn.save(newEmp);
		System.out.println("session returned a serializable object: "+sessionId);
		
		tx.commit();	
		
//		once we call commit() session will get closed.
		
		System.out.println("Details inserted successfully !!");
		
	}

	private static void deleteEmployeeByid() throws InterruptedException 
	{
		System.out.println("Enter ID of employee whose record you want to delete from system - ");
		Scanner cin = new Scanner(System.in);
		
		int userEnteredId = cin.nextInt();
		
		System.out.println("Checking records...");
		
		Thread.sleep(2000);
		
		Object dataFromDB = sn.get(Employee_Salary_Management_System_POJO.class, new Integer(userEnteredId));
		
		if(dataFromDB == null)
		{
			System.out.println("No data exists in system for the entered Employee Id: "+userEnteredId);
		}
		
		else
		{
		Employee_Salary_Management_System_POJO userData = (Employee_Salary_Management_System_POJO) dataFromDB;
		sn.delete(userData);
		tx.commit();
		
		System.out.println("Record deleted successfully !!");
		}
	}

	private static void getEmployeeByid() throws InterruptedException 
	{
		System.out.println("Enter ID of employee whose salary you want to check - ");
		Scanner cin = new Scanner(System.in);
		
		int userEnteredId = cin.nextInt();
		
		System.out.println("Fetching records...");
		
		Thread.sleep(2000);

		Object dataFromDB = sn.get(Employee_Salary_Management_System_POJO.class, new Integer(userEnteredId));
		
		if(dataFromDB == null)
		{
			System.out.println("No data exists in system for the entered Employee Id: "+userEnteredId);
		}
		
		else
		{
		Employee_Salary_Management_System_POJO userData = (Employee_Salary_Management_System_POJO) dataFromDB;
	
		System.out.println("Deptt: "+userData.getDepartment()+", Emp. Id: "+userData.getEmployee_id()+", Emp. Name: "+userData.getEmployee_name()+", Salary: "+userData.getSalary());
		
		tx.commit();
		}
		
	}
	
}

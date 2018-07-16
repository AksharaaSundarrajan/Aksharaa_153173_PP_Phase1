package com.cg.mypaymentapp.test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;


public class TestClass {

	
	WalletService service;
	
	@Before
	public void initData(){
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9900112212", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
			service= new WalletServiceImpl(data);
			
	}
	//1
	@Test(expected=InvalidInputException.class)
	public void  testShowBalanceInvalidNumber()
	{
		String mobileNumber = "9898989";
		service.showBalance(mobileNumber);
	}
	//2
	@Test
	public void  testShowBalanceValidNumber()
	{
		String mobileNumber = "9900112212";
		service.showBalance(mobileNumber);
		
	}
	//22
	@Test
	public void createAccountValid()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		String number = "9963242422";
		BigDecimal amount = new BigDecimal("1234.02");
		cust = service.createAccount(name, number, amount);
		
	}
	//3
	@Test(expected=InvalidInputException.class)
	public void createAccountInvalidName1()
	{
		Customer cust = new Customer();
		String name =" ";
		String number = "9963242422";
		BigDecimal amount = new BigDecimal("1234.02");
		cust = service.createAccount(name, number, amount);
		
	}
	//4
	@Test(expected=NullPointerException.class)
	public void createAccountInvalidName2()
	{
		
		String name =null;
		String number = "9963242422";
		BigDecimal amount = new BigDecimal("1234.02");
		service.createAccount(name, number, amount);
		
	}
	
	//5
	@Test(expected=InvalidInputException.class)
	public void createAccountInvalidMobileNo()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		String number = "99632";
		BigDecimal amount = new BigDecimal("1234.02");
		cust = service.createAccount(name, number, amount);
		
	}
	//6
	@Test(expected=InvalidInputException.class)
	public void createAccountInvalidAmount1()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		String number = "9963242422";
		BigDecimal amount = new BigDecimal("0.0");
		cust = service.createAccount(name, number, amount);
		
	}	
	
	//7
	//Check!!!!
	/*@Test(expected=InvalidInputException.class)
	public void createAccountInvalidAmount2()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		String number = "9963242422";
		BigDecimal amount = new BigDecimal("twenty");
		cust = service.createAccount(name, number, amount);
		
	}*/
	//20
	@Test(expected=InvalidInputException.class)
	public void createAccountInvalidAmount3()
	{
		Customer cust = new Customer();
		String name ="Aksharaa";
		String number = "9963242422";
		BigDecimal amount = new BigDecimal("-100.00");
		cust = service.createAccount(name, number, amount);
		
	}
	//23
	@Test
	public void fundTransferValid(){
		String sourceMobile ="9900112212";
		String targetMobile = "9963242422";
		service.fundTransfer(sourceMobile, targetMobile, new BigDecimal("10.00"));
		}
	//8
	@Test(expected=InvalidInputException.class)
	public void fundTransferInvalidSourceMobile()
	{
		Customer cust = new Customer();
		String sourceMobile ="909090";
		String targetMobile = "9963242422";
		BigDecimal amount = new BigDecimal("1000.00");
		cust = service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//9
	@Test(expected=InvalidInputException.class)
	public void fundTransferInvalidTargetMobile()
	{
		Customer cust = new Customer();
		String sourceMobile ="9963242422";
		String targetMobile = "909090";
		BigDecimal amount = new BigDecimal("1000.00");
		cust = service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//10
	@Test(expected=InvalidInputException.class)
	public void fundTransferInvalidAmount()
	{
		String sourceMobile ="9922950519";
		String targetMobile = "9963242422";
		BigDecimal amount = new BigDecimal("0.00");
		service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//21
	@Test(expected=InvalidInputException.class)
	public void fundTransferInvalidAmount2()
	{
		String sourceMobile ="9922950519";
		String targetMobile = "9963242422";
		BigDecimal amount = new BigDecimal("-100.00");
		service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//11
	@Test(expected=InvalidInputException.class)
	public void fundTransferSourceEqualsTarget()
	{
		
		String sourceMobile ="9922950519";
		String targetMobile = "9922950519";
		BigDecimal amount = new BigDecimal("100.00");
		service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//12
	@Test(expected=InsufficientBalanceException.class)
	public void fundTransferInsufficientBalance()
	{
		Customer cust = new Customer();
		String sourceMobile ="9922950519";
		String targetMobile = "9963242422";
		BigDecimal amount = new BigDecimal("1000000.00");
		cust = service.fundTransfer(sourceMobile, targetMobile, amount);
		
	}
	//24
	@Test
	public void depositAmountValid()
	{
		Customer cust = new Customer();
		String mobile ="9963242422";
		BigDecimal amount = new BigDecimal("100.00");
		cust = service.depositAmount(mobile, amount);
	}
	//13
	@Test(expected=InvalidInputException.class)
	public void depositAmountInvalidNumber()
	{
		Customer cust = new Customer();
		String mobile ="909090";
		BigDecimal amount = new BigDecimal("100.00");
		cust = service.depositAmount(mobile, amount);
	}
	//14
	@Test(expected=InvalidInputException.class)
	public void depositAmountInvalidAmount1()
	{
		Customer cust = new Customer();
		String mobile ="9963242422";
		BigDecimal amount = new BigDecimal("0.00");
		cust = service.depositAmount(mobile, amount);
	}
	//15
	@Test(expected=InvalidInputException.class)
	public void depositAmountInvalidAmount2()
	{
		Customer cust = new Customer();
		String mobile ="9963242422";
		cust = service.depositAmount(mobile, new BigDecimal(-100));
	}
	//25
	@Test
	public void withdrawAmountValid()
	{
		Customer cust = new Customer();
		String mobile ="9963242422";
		BigDecimal amount = new BigDecimal("10.00");
		cust = service.withdrawAmount(mobile, amount);
	}
	//16
	@Test(expected=InvalidInputException.class)
	public void withdrawAmountInvalidNumber()
	{
		Customer cust = new Customer();
		String mobile ="909090";
		BigDecimal amount = new BigDecimal("100.00");
		cust = service.withdrawAmount(mobile, amount);
	}
	//17
	@Test(expected=InvalidInputException.class)
	public void withdrawAmountInvalidAmount1()
	{
		Customer cust = new Customer();
		String mobile ="9963242422";
		BigDecimal amount = new BigDecimal("0.00");
		cust = service.withdrawAmount(mobile, amount);
	}
	//18
	@Test(expected=InvalidInputException.class)
	public void withdrawAmountInvalidAmount2()
	{
		Customer cust = new Customer();
		String mobile ="9963242422";
		BigDecimal amount = new BigDecimal("-100.00");
		cust = service.withdrawAmount(mobile, amount);
	}
	//19
	@Test(expected=InsufficientBalanceException.class)
	public void withdrawAmountInsufficientBalance()
	{
		Customer cust = new Customer();
		String mobile ="9963242422";
		BigDecimal amount = new BigDecimal("1000000.00");
		cust = service.withdrawAmount(mobile, amount);
	}
	

}

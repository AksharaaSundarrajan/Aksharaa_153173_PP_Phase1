
package com.cg.mypaymentapp.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transaction;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;


public class WalletServiceImpl implements WalletService{

private WalletRepo repo= new WalletRepoImpl();
//WalletRepo repo1= new WalletRepoImpl();

//Customer customer = new Customer();
Customer customer;

Wallet wallet;

private Map<String, Customer> data= new HashMap<>();
private ArrayList<String> transactionDetails;
private Map<String, ArrayList<String>> transactions;

/*public WalletServiceImpl(Map<String, Customer> data){
		repo= new WalletRepoImpl(data);
	}*/
	public WalletServiceImpl(WalletRepo repo) {
		this.repo = repo;
	}

	public WalletServiceImpl() 
	{
		repo = new WalletRepoImpl();
	}

	public WalletServiceImpl(Map<String, Customer> data2) {
		// TODO Auto-generated constructor stub
		repo = new WalletRepoImpl(data2);
		
	}

	public Customer createAccount(String name, String mobileNo, BigDecimal amount)
	{
		
		if(numberIsValid(mobileNo) && nameIsValid(name) && amountIsValid(amount))
		{
			customer = new Customer();
			wallet = new Wallet();
		//	customer = new Customer(name,mobileNo,new Wallet(amount));
		customer.setMobileNo(mobileNo);
		customer.setName(name);	
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		//System.out.println(customer);
		//data.put(mobileNo, customer);
		boolean b = repo.save(customer);
		if(b)
		{
			System.out.println("Account successfully created");
			repo.setTransactions(new Transaction(mobileNo, java.time.LocalDate.now(), "CREATE ACCOUNT", "SUCCESS", customer.getWallet().getBalance()));
		}
		}
		else
		{
			throw new InvalidInputException("Invalid input");
		}
		
		return customer;
				
	}

	private boolean amountIsValid(BigDecimal amount) {
		// TODO Auto-generated method stub
		int val = amount.compareTo(new BigDecimal("0"));
		if(val==0 || val<0)
			return false;
		return true;
	}
	public boolean nameIsValid(String name) {
		if(name.equals(null)|| name.equals(" "))
			return false;
		return true;
	}
	public boolean numberIsValid(String mobileNo) 
	{
		if(String.valueOf(mobileNo).matches("[1-9][0-9]{9}"))
		{return true;}
	return false;
	}
	
	
	public Customer showBalance(String mobileNo) 
	{	if(!numberIsValid(mobileNo))
		
		{
		throw new InvalidInputException("Invalid phone number");
		}
		
		Customer customer=repo.findOne(mobileNo);
		if(customer!=null)
			{
			return customer;
			}
		else
			throw new InvalidInputException("Customer does not exist");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) 
	{	Customer cust1 = null;
		Customer cust2 = null;
		if(sourceMobileNo.equals(targetMobileNo))
		{
			throw new InvalidInputException("Source and target mobile numbers cannot be the same");
		}
		if(numberIsValid(sourceMobileNo)&& numberIsValid(targetMobileNo))
		{
		cust1=repo.findOne(sourceMobileNo);
		cust2 = repo.findOne(targetMobileNo);
		}
		else 
		{
			throw new InvalidInputException("Invalid source or target mobile number");
		}
		Wallet balance1 = cust1.getWallet();
		Wallet balance2 = cust2.getWallet();
	if(amountIsValid(amount)) {
		if(balance1.getBalance().compareTo(amount)>=0)
		{
			BigDecimal deductedBalance = balance1.getBalance().subtract(amount);
			
			balance1.setBalance(deductedBalance);
			BigDecimal addedbalance = balance2.getBalance().add(amount);
			repo.setTransactions(new Transaction(sourceMobileNo, java.time.LocalDate.now(), "FUND TRANSFER", "SUCCESS", customer.getWallet().getBalance()));
			balance2.setBalance(addedbalance);
			repo.setTransactions(new Transaction(targetMobileNo, java.time.LocalDate.now(), "FUND TRANSFER", "SUCCESS", cust2.getWallet().getBalance()));
			return cust1;
		}
		else
		{
			throw new InsufficientBalanceException("Cannot transfer, insufficient balance");
		}}
	else
	{
		throw new InvalidInputException("Invalid amount");
	}
		
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) 
	{
		customer = new Customer();
		if(numberIsValid(mobileNo))
			{customer = repo.findOne(mobileNo);}
		else 
		{
			throw new InvalidInputException("Invalid mobile number");
		}
		customer = repo.findOne(mobileNo);
		wallet = new Wallet();
		wallet = customer.getWallet();
		if(amountIsValid(amount))
		{
			BigDecimal balanceAfterDeposit = wallet.getBalance().add(amount);
			wallet.setBalance(balanceAfterDeposit);
			repo.setTransactions(new Transaction(mobileNo, java.time.LocalDate.now(), "DEPOSIT", "SUCCESS", customer.getWallet().getBalance()));
		}
		else
		{
			throw new InvalidInputException("Enter correct amount");
		}
		return customer;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) 
	{
		customer = new Customer();
		if(numberIsValid(mobileNo))
			{customer = repo.findOne(mobileNo);}
		else 
		{
			throw new InvalidInputException("Invalid mobile number");
		}
		customer = repo.findOne(mobileNo);
		wallet = new Wallet();
		wallet = customer.getWallet();
		if(amountIsValid(amount))
		{
			if(wallet.getBalance().compareTo(amount)>0 || wallet.getBalance().compareTo(amount)==0)
			{
			BigDecimal balanceAfterWithdrawal = wallet.getBalance().subtract(amount);
			wallet.setBalance(balanceAfterWithdrawal);
			/*transactionDetails.add("You withdrew "+amount.toString());
			transactions.put(mobileNo, transactionDetails);*/
			repo.setTransactions(new Transaction(mobileNo, java.time.LocalDate.now(), "WITHDRAWAL", "SUCCESS", customer.getWallet().getBalance()));
			}
			else
			{
				throw new InsufficientBalanceException("Insufficient balance, cannot withdraw");
			}
		}
		else
		{
			throw new InvalidInputException("Enter correct amount");
		}
		return customer;

	}
	public ArrayList<Transaction>getTransactionDetails(String mobileNumber) 
	{
		return repo.getTransactionDetails(mobileNumber);
	}
	
	}

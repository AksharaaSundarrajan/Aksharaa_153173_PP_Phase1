package com.cg.mypaymentapp.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transaction;
import com.cg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo{
	Customer customer = new Customer();
	private Map<String, Customer> data; 
	private Map<String, ArrayList<Transaction>> transactionDetails;
	private ArrayList<Transaction> transactions;
	public WalletRepoImpl(Map<String, Customer> data) 
	{
		super();
		this.data = data;
	}
	public WalletRepoImpl() 
	{
		data = new HashMap<String, Customer>(); 
		transactions = new ArrayList<>();
		transactionDetails = new HashMap<String, ArrayList<Transaction>>();
	}

	public boolean save(Customer customer) 
	{
	if(findOne(customer.getMobileNo())==null)
		{
		data.put(customer.getMobileNo(), customer);
		return true;
	}
	
	return false;
			
	}

	public Customer findOne(String mobileNo) 
	{	
		Customer customer = null;
		if(data.containsKey(mobileNo))
		{
			customer = data.get(mobileNo);
			return customer;
		}
		else
		{
			return null;
		}
		
	}
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction transaction) {
		this.transactions.add(transaction);
		String number = transaction.getMobileNumber();
		setTransactionDetails(number,transactions);
		
	}
	public ArrayList<Transaction> getTransactionDetails(String mobileNumber) 
	{
		
		return transactionDetails.get(mobileNumber);
	}
	@Override
	public void setTransactionDetails(String num, ArrayList<Transaction> transactions) 
	{
		transactionDetails.put(num, transactions);
		
	}

	
	
	
}

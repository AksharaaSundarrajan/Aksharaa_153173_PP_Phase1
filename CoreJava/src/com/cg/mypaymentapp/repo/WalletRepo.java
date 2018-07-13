package com.cg.mypaymentapp.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transaction;

public interface WalletRepo {

	public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
	public void setTransactionDetails(String num, ArrayList<Transaction> transactionDetails);
	ArrayList<Transaction> getTransactionDetails(String mobileNumber);
	void setTransactions(Transaction transaction);
	ArrayList<Transaction> getTransactions();
	
}

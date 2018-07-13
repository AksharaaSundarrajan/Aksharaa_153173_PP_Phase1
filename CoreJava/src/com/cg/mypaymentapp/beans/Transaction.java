package com.cg.mypaymentapp.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Transaction 
{
	String mobileNumber;
	LocalDate dateOfTransaction;
	String transactionType;
	String transactionStatus;
	BigDecimal amount;
	
	
	public Transaction(String mobileNumber, LocalDate dateOfTransaction, String transactionType, String transactionStatus,
			BigDecimal amount) {
		super();
		this.mobileNumber = mobileNumber;
		this.dateOfTransaction = dateOfTransaction;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.amount = amount;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		return true;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public LocalDate getDateOfTransaction() {
		return dateOfTransaction;
	}


	public void setDateOfTransaction(LocalDate dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public String getTransactionStatus() {
		return transactionStatus;
	}


	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	@Override
	public String toString() {
		return "Transaction [mobileNumber=" + mobileNumber + ", dateOfTransaction=" + dateOfTransaction
				+ ", transactionType=" + transactionType + ", transactionStatus=" + transactionStatus + ", amount="
				+ amount + "]";
	}
	
	
	
}

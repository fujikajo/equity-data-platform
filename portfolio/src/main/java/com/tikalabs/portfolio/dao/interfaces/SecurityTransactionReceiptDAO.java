package com.tikalabs.portfolio.dao.interfaces;

import java.util.List;

import com.tikalabs.portfolio.model.SecurityTransactionReceipt;

public interface SecurityTransactionReceiptDAO {

	void addTransactionReceipt(SecurityTransactionReceipt receipt);

	SecurityTransactionReceipt getTransactionReceiptById(int id);

	List<SecurityTransactionReceipt> getAllTransactionReceipts();

	void updateTransactionReceipt(SecurityTransactionReceipt receipt);

	void deleteTransactionReceipt(int id);

}

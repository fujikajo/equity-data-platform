package com.tikalabs.portfolio.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tikalabs.core.database.jdbc.DataSourceFactory;
import com.tikalabs.portfolio.model.SecurityTransactionReceipt;

public class TransactionReceiptService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionReceiptDAO.class);

	private TransactionReceiptDAO receiptDAO;

	public TransactionReceiptService(Properties properties) {
		try {
			this.receiptDAO = new TransactionReceiptDAO(DataSourceFactory.createDataSource(properties));
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public void processAndSaveReceipts(List<SecurityTransactionReceipt> receiptList) {
		// Beispiel für eine Geschäftslogik, die vor dem Speichern ausgeführt wird
		for (SecurityTransactionReceipt receipt : receiptList) {
			// Führen Sie hier spezifische Geschäftsregeln aus
		}

		// Speichern der Belege in der Datenbank
		for (SecurityTransactionReceipt receipt : receiptList) {
			receiptDAO.insertReceipt(receipt);
		}
	}

	// Weitere Methoden für Geschäftslogik und Transaktionsmanagement
}

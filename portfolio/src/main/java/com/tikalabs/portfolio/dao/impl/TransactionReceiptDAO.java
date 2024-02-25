package com.tikalabs.portfolio.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tikalabs.core.database.jdbc.DBAccessor;
import com.tikalabs.portfolio.model.SecurityTransactionReceipt;

public class TransactionReceiptDAO {
	private static final Logger logger = LoggerFactory.getLogger(TransactionReceiptDAO.class);

	private DBAccessor dbAccessor;

	public TransactionReceiptDAO(DataSource dataSource) {
		this.dbAccessor = new DBAccessor(dataSource);
		logger.info("Verbindung zur Datenbank : " + this.dbAccessor.hasConnection());
		ensureTableExists();
	}

	public void insertReceipt(SecurityTransactionReceipt receipt) {
		String sql = "INSERT INTO TransactionReceipts (date, account, depotId, type, isin, quantity, price, amount, fees, taxes, totalPrice, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		dbAccessor.update(sql, receipt.getDate(), receipt.getAccount(), receipt.getDepotId(), receipt.getType(),
				receipt.getIsin(), receipt.getQuantity(), receipt.getPrice(), receipt.getAmount(), receipt.getFees(),
				receipt.getTaxes(), receipt.getTotalPrice(), receipt.getNote());
	}

	public List<SecurityTransactionReceipt> findAllReceipts() {
		String sql = "SELECT * FROM TransactionReceipts";
		return dbAccessor.query(sql, (resultSet) -> {
			List<SecurityTransactionReceipt> receipts = new ArrayList<>();
			while (resultSet.next()) {
				SecurityTransactionReceipt receipt = new SecurityTransactionReceipt();
				receipt.setTransactionId(resultSet.getInt("transactionId"));
				receipt.setDate(resultSet.getDate("date").toLocalDate()); // Umwandlung von Date zu LocalDate
				receipt.setAccount(resultSet.getInt("account"));
				receipt.setDepotId(resultSet.getInt("depotId"));
				receipt.setType(resultSet.getString("type"));
				receipt.setIsin(resultSet.getString("isin"));
				receipt.setQuantity(resultSet.getDouble("quantity"));
				receipt.setPrice(resultSet.getDouble("price"));
				receipt.setAmount(resultSet.getDouble("amount"));
				receipt.setFees(resultSet.getDouble("fees"));
				receipt.setTaxes(resultSet.getDouble("taxes"));
				receipt.setTotalPrice(resultSet.getDouble("totalPrice"));
				receipt.setNote(resultSet.getString("note"));
				receipts.add(receipt);
			}
			return receipts;
		});
	}

	private void ensureTableExists() {
		// SQL-Anweisung zur Überprüfung, ob die Tabelle existiert
		String sqlCheck = "SELECT 1 FROM TransactionReceipts LIMIT 1;";

		// SQL-Anweisung zum Erstellen der Tabelle, falls sie nicht existiert
		String sqlCreate = "CREATE TABLE IF NOT EXISTS TransactionReceipts (...);";

		try {
			dbAccessor.query(checkTableExistsQuery());
		} catch (SQLException e) {
			// Tabelle existiert nicht, also wird versucht, sie zu erstellen
			dbAccessor.update(createTableQuery());
			logger.info("Tabelle 'TransactionReceipts' wurde erfolgreich erstellt.");
		}
	}

	private String createTableQuery() {
		return "CREATE TABLE IF NOT EXISTS TransactionReceipts (" + "transactionId INT AUTO_INCREMENT PRIMARY KEY, "
				+ "date DATE, " + "account INT, " + "depotId INT, " + "type VARCHAR(255), " + "isin VARCHAR(255), "
				+ "quantity DOUBLE, " + "price DOUBLE, " + "amount DOUBLE, " + "fees DOUBLE, " + "taxes DOUBLE, "
				+ "totalPrice DOUBLE, " + "note VARCHAR(255), "
				+ " UNIQUE (date, ISIN, price, depotId, account, type, quantity, totalPrice, note));";
	}

	private String checkTableExistsQuery() {
		return "SELECT 1 FROM TransactionReceipts LIMIT 1;";

	}

	// Weitere Methoden zum Aktualisieren und Löschen von Belegen...
}

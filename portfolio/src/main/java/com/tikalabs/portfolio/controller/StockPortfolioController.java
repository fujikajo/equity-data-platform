package com.tikalabs.portfolio.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tikalabs.portfolio.dao.impl.TransactionReceiptService;
import com.tikalabs.portfolio.io.ReceiptReader;
import com.tikalabs.portfolio.model.SecurityTransactionReceipt;

public class StockPortfolioController {

	private static final Logger logger = LoggerFactory.getLogger(StockPortfolioController.class);

	public StockPortfolioController() {

	}

	public void execute() {

		ReceiptReader receiptReader = new ReceiptReader(
				"C:\\Users\\tittm\\Documents\\Depot\\portfolio\\portfolio_csv\\2024\\Alle_Buchungen_Kai.csv");
		List<SecurityTransactionReceipt> list = receiptReader.parseCSV();

		Properties props = loadProperties();
		TransactionReceiptService dbService = new TransactionReceiptService(props);
		dbService.processAndSaveReceipts(list);
	}

	protected Properties loadProperties() {

		Properties props = new Properties();
		try (InputStream input = StockPortfolioController.class.getClassLoader()
				.getResourceAsStream("database.properties")) {
			if (input == null) {
				logger.error("Sorry, unable to find database.properties");
				return props;
			}

			// Lade die Properties-Datei
			props.load(input);

			// Zugriff auf die Properties
			System.out.println(props.getProperty("dbType"));
			// Weitere Zugriffe auf die Properties hier
		} catch (IOException ex) {
			logger.error("Sorry, unable to find database.properties");
		}
		return props;
	}

}

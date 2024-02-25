package com.tikalabs.portfolio.io;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tikalabs.core.io.csv.parser.AbstractCSVParser;
import com.tikalabs.core.string.utils.NumericConverter;
import com.tikalabs.portfolio.model.SecurityTransactionReceipt;

public class ReceiptReader extends AbstractCSVParser<SecurityTransactionReceipt> {

	private static final Logger logger = LoggerFactory.getLogger(ReceiptReader.class);

	public ReceiptReader(String filePath) {
		super(filePath);

	}

	@Override
	protected SecurityTransactionReceipt parseRecord(CSVRecord csvRecord) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		SecurityTransactionReceipt receipt = new SecurityTransactionReceipt();
		LocalDateTime dateTime = LocalDateTime.parse(csvRecord.get(SecurityTransactionReceipt.DATE_FIELD), formatter);
		receipt.setDate(dateTime.toLocalDate());
		receipt.setType(csvRecord.get("Typ").toUpperCase());
		receipt.setIsin(csvRecord.get("ISIN"));
		receipt.setNote(csvRecord.get("Notiz"));

		if (csvRecord.get("Konto").toUpperCase().contains("ONVISTA")) {
			receipt.setDepotId(SecurityTransactionReceipt.ONVISTA_DEPOT);
		} else if (csvRecord.get("Konto").toUpperCase().contains("SCALABLE")
				|| csvRecord.get("Konto").toUpperCase().contains("BAADERBANK")) {
			receipt.setDepotId(SecurityTransactionReceipt.SCALABLE_CAPITAL_DEPOT);
		} else {
			logger.error(csvRecord.get("Konto") + " Depot kann nicht ermittelt werden.");
		}

		if (csvRecord.get("Konto").toUpperCase().contains("KAI")) {
			receipt.setAccount(SecurityTransactionReceipt.ACCOUNT_KAI);
		} else if (csvRecord.get("Konto").toUpperCase().contains("ANJEZA")) {
			receipt.setAccount(SecurityTransactionReceipt.ACCOUNT_ANJEZA);
		} else {
			logger.error(csvRecord.get("Konto") + " Account kann nicht ermittelt werden.");
		}

		// Verwenden Sie die Hilfsmethode für Felder, die eine Umwandlung erfordern
		receipt.setQuantity(parseDoubleWithFallback(csvRecord, "Stück", 0.0, Locale.GERMANY));
		receipt.setPrice(parseDoubleWithFallback(csvRecord, "Kurs", 0.0, Locale.GERMANY));
		receipt.setAmount(parseDoubleWithFallback(csvRecord, "Betrag", 0.0, Locale.GERMANY));
		receipt.setFees(parseDoubleWithFallback(csvRecord, "Gebühren", 0.0, Locale.GERMANY));
		receipt.setTaxes(parseDoubleWithFallback(csvRecord, "Steuern", 0.0, Locale.GERMANY));
		receipt.setTotalPrice(parseDoubleWithFallback(csvRecord, "Gesamtpreis", 0.0, Locale.GERMANY));

		return receipt;
	}

	private double parseDoubleWithFallback(CSVRecord csvRecord, String fieldName, double fallbackValue, Locale locale) {
		try {
			return NumericConverter.parseDouble(csvRecord.get(fieldName), locale);
		} catch (ParseException e) {
			logger.error(fieldName + " kann nicht gelesen werden. Falsches Zahlenformat.", e);
			return fallbackValue;
		}
	}

}

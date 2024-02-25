package com.tikalabs.portfolio.model;

import java.time.LocalDate;

public class SecurityTransactionReceipt {

	public static final String DATE_FIELD = "Datum";
	public static final int ONVISTA_DEPOT = 0;
	public static final int SCALABLE_CAPITAL_DEPOT = 1;
	public static final int ACCOUNT_KAI = 0;
	public static final int ACCOUNT_ANJEZA = 1;

	private int transactionId;
	private LocalDate date;
	private int depotId;
	private String type;
	private String isin;
	private double quantity;
	private double price;
	private double amount;
	private double fees;
	private double taxes;
	private double totalPrice;
	private int account;
	private String note;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getDepotId() {
		return depotId;
	}

	public void setDepotId(int depotId) {
		this.depotId = depotId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}

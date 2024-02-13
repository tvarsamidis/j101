package gr.codehub.j101.p03stream;

import java.math.BigDecimal;

class Invoice {
	String invoiceNo;
	BigDecimal price;
	BigDecimal quantity;

	public Invoice(String invoiceNo, BigDecimal price, BigDecimal quantity) {
		this.invoiceNo = invoiceNo;
		this.price = price;
		this.quantity = quantity;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}

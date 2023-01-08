/**
 * 
 */
package com.solactive.tick.model;

import java.util.Objects;

/**
 * @author Jeena A V
 *
 */
public class Tick {

	private long timeStamp;
	private double price;
	private double closePrice;
	private String currency;
	private String ric;

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRic() {
		return ric;
	}

	public void setRic(String ric) {
		this.ric = ric;
	}

	public Tick(long timeStamp, double price, double closePrice, String currency, String ric) {
		this.timeStamp = timeStamp;
		this.price = price;
		this.closePrice = closePrice;
		this.currency = currency;
		this.ric = ric;
	}

	public Tick() {
	}

	@Override
	public String toString() {
		return "Tick [timeStamp=" + timeStamp + ", price=" + price + ", closePrice=" + closePrice + ", currency="
				+ currency + ", ric=" + ric + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(closePrice, currency, price, ric, timeStamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tick other = (Tick) obj;
		return Double.doubleToLongBits(closePrice) == Double.doubleToLongBits(other.closePrice)
				&& Objects.equals(currency, other.currency)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(ric, other.ric) && timeStamp == other.timeStamp;
	}
    
}

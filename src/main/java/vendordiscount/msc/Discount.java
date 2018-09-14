package vendordiscount.msc;

public class Discount
{
	private int fromDate;
	private int toDate;
	private int priceInDollar;
	
	public Discount(Discount d) {
		this(d.getFromDate(), d.getToDate(), d.getPriceInDollar());
	}

	public Discount(int fromDate, int toDate, int priceInDollar) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.priceInDollar = priceInDollar;
	}

	public int getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(int fromDate)
	{
		this.fromDate = fromDate;
	}

	public int getToDate()
	{
		return toDate;
	}

	public void setToDate(int toDate)
	{
		this.toDate = toDate;
	}

	public int getPriceInDollar()
	{
		return priceInDollar;
	}

	public void setPriceInDollar(int priceInDollar)
	{
		this.priceInDollar = priceInDollar;
	}
	
	public String toString() {
		return String.format("(%s, %s, $%sK)", fromDate, toDate, priceInDollar/1000);
	}
}

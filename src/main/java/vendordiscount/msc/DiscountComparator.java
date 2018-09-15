package vendordiscount.msc;

import java.util.Comparator;

public class DiscountComparator implements Comparator<Discount>
{

	@Override
	public int compare(Discount o1, Discount o2)
	{
		if (o1.getFromDate() != o2.getFromDate())
			return o1.getFromDate() - o2.getFromDate();
		if (o1.getToDate() != o2.getToDate())
			return o1.getToDate() - o2.getToDate();
		return o1.getPriceInDollar()-o2.getPriceInDollar();		
	}	
	
}

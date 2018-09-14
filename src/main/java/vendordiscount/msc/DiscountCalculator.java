package vendordiscount.msc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.stereotype.Component;

@Component
public class DiscountCalculator
{
	public List<Discount> calculate(List<Discount> data) {
		int[] discounts = new int[31];
		Arrays.fill(discounts, Integer.MAX_VALUE);
		
		for (Discount d : data)
			for (int i=d.getFromDate(); i<=d.getToDate(); ++i)
				discounts[i-1] = Math.min(discounts[i-1], d.getPriceInDollar());
		
		List<Discount> res = new ArrayList();
		Discount prv = new Discount(1,0,discounts[0]);
		for (int i=0; i<31; ++i) {
			if (prv.getPriceInDollar() == discounts[i])
				continue;
			else {
				prv.setToDate(i);
				if (prv.getPriceInDollar() != Integer.MAX_VALUE)
					res.add(prv);
				prv = new Discount(i+1,0,discounts[i]);
			}
		}
		prv.setToDate(31);
		if (prv.getPriceInDollar() != Integer.MAX_VALUE)
			res.add(prv);
		return res;
	}

	public List<Discount> calculate1(List<Discount> data)
	{
		Collections.sort(data, new DiscountComparator());
		
		List<Discount> res = new ArrayList();
		
		Queue<Discount> ends = new PriorityQueue(new DiscountComparator());
		
		for (Discount discount : data) {
			Discount r = new Discount(discount.getFromDate(), discount.getToDate(), discount.getPriceInDollar());
			
			ends.add(new Discount(0, discount.getToDate(), discount.getPriceInDollar()));

			if (!res.isEmpty()) {
				Discount last = res.get(res.size()-1);
				if (discount.getFromDate() <= last.getToDate()) {
					if (discount.getPriceInDollar() < last.getPriceInDollar()) {
						last.setToDate(discount.getFromDate()-1);
						res.add(new Discount(discount.getFromDate(), discount.getToDate(), discount.getPriceInDollar()));
					}
				}
			}
			
			Discount last = res.get(res.size()-1);
			while (ends.size()>0)
				if (ends.peek().getToDate() < last.getToDate()) {
					ends.poll();
				}
				else {
					
				}
			
			res.add(r);
		}
		
		return res;
	}

}
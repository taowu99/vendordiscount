package vendordiscount.msc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
		data.add(new Discount(1,31,Integer.MAX_VALUE));
		
		Collections.sort(data, new DiscountComparator());
		
		List<Discount> res = new ArrayList();
		
		Queue<Discount> ends = new PriorityQueue<Discount>(new Comparator<Discount>(){
			@Override
			public int compare(Discount d1, Discount d2) {
				if (d1.getPriceInDollar() != d2.getPriceInDollar())
					return d1.getPriceInDollar() - d2.getPriceInDollar();
				return d1.getToDate() - d2.getToDate();
			}
		});
		
		for (Discount discount : data) {
			
			ends.add(new Discount(0, discount.getToDate(), discount.getPriceInDollar()));
			
			if (res.isEmpty()) {
				res.add(new Discount(discount));
			}
			else {
				Discount last = res.get(res.size()-1);
				
				while (last.getToDate()<discount.getFromDate() && ends.size()>0) {
					Discount end = ends.poll();
					if (end.getToDate() < last.getToDate()) 
						continue;
					res.add(new Discount(last.getToDate()+1, end.getToDate(), end.getPriceInDollar()));
					last = res.get(res.size()-1);
				}
				
				if (discount.getPriceInDollar() < last.getPriceInDollar())
				{
					ends.add(new Discount(last));
					last.setToDate(discount.getFromDate() - 1);
					res.add(new Discount(discount));

					last = res.get(res.size() - 1);
				}
			}
		}
		
		Discount last = res.get(res.size()-1);
		while (ends.size()>0) {
			Discount end = ends.poll();
			if (end.getToDate() < last.getToDate())
				continue;
			res.add(new Discount(last.getToDate() + 1, end.getToDate(), end.getPriceInDollar()));
			last = res.get(res.size() - 1);
		}
		
		Iterator<Discount> itr = res.iterator();
		while (itr.hasNext())
			if (itr.next().getPriceInDollar() == Integer.MAX_VALUE)
				itr.remove();
		return res;
	}

}

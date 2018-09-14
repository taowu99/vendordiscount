package vendordiscount.msc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DiscountCalculatorTest
{
	@Test
	public void testCalculation() {
		List<Discount> discounts = new ArrayList();
    	discounts.add(new Discount(1,4,20000));
    	discounts.add(new Discount(3,6,15000));
    	discounts.add(new Discount(2,8,25000));
    	discounts.add(new Discount(7,12,11000));
    	discounts.add(new Discount(1,31,22000));
    	
		DiscountCalculator cal = new DiscountCalculator();
		
		for (Discount d : cal.calculate(discounts))
			System.out.println(d.toString());
	}

	@Test
	public void testCalculation1() {
		List<Discount> discounts = new ArrayList();
		discounts.add(new Discount(1,4,20000));
		discounts.add(new Discount(3,6,15000));
		discounts.add(new Discount(2,8,25000));
		discounts.add(new Discount(7,12,11000));
		discounts.add(new Discount(1,31,22000));
		
		DiscountCalculator cal = new DiscountCalculator();
		
		for (Discount d : cal.calculate1(discounts))
			System.out.println(d.toString());
	}
}

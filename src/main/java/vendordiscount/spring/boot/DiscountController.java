package vendordiscount.spring.boot;

import org.springframework.web.bind.annotation.RestController;

import vendordiscount.msc.Discount;
import vendordiscount.msc.DiscountCalculator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class DiscountController {
	
	@Autowired
	protected DiscountCalculator discountCalculator;

    @RequestMapping("/VendorBestPrices")
    public String index() {
        StringBuilder response = new StringBuilder();
        List<Discount> discounts = discountCalculator.calculate0(prepareData());
        for (Discount discount : discounts)
        	response.append(discount.toString());
        
        return response.toString();
    }

    protected List<Discount> prepareData() {
    	List<Discount> discounts = new ArrayList();
    	discounts.add(new Discount(1,4,20000));
    	discounts.add(new Discount(3,6,15000));
    	discounts.add(new Discount(2,8,25000));
    	discounts.add(new Discount(7,12,11000));
    	discounts.add(new Discount(1,31,22000));
    	
    	return discounts;
    }
}

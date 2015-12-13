package promotion;

import domain.CartItem;
import domain.Discount;
import inter.ShopData;
import parser.DiscountParser;

import java.util.List;

public class DiscountPromotion implements Promotion{

    List<Discount> allDiscounts;

    public DiscountPromotion() {
        DiscountParser disCountParser = new DiscountParser();
        allDiscounts = disCountParser.parse(ShopData.DISCOUNT_ITEMS);
    }

    @Override
    public double getPromotion(CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        for(Discount disCount : allDiscounts){
            if(disCount.getBarcode().equals(barcode)){
                return disCount.getDiscount()/100;
            }
        }
        return 1;
    }

}

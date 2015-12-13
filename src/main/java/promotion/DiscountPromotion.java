package promotion;

import domain.CartItem;
import domain.Discount;
import inter.ShopData;
import parser.DiscountParser;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class DiscountPromotion implements Promotion{

    Map<String, Discount> allDiscounts;

    public DiscountPromotion() {
        DiscountParser disCountParser = new DiscountParser();
        allDiscounts = disCountParser
                            .parse(ShopData.DISCOUNT_ITEMS)
                            .stream()
                            .collect(toMap(Discount::getBarcode, identity()));
    }

    @Override
    public double getPromotion(CartItem cartItem) {
        String barcode = cartItem.getBarcode();

        return allDiscounts.containsKey(barcode)? allDiscounts.get(barcode).getDiscount() / 100: 1;
    }

}

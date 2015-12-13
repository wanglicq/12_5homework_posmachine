package promotion;

import domain.CartItem;
import domain.Discount;
import domain.Item;
import inter.ShopData;
import org.javatuples.Pair;
import parser.DiscountParser;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class DiscountPromotion implements Promotion{

    Map<String, Discount> allDiscounts;

    public DiscountPromotion() {
        DiscountParser disCountParser = new DiscountParser();
        allDiscounts = disCountParser.parse(ShopData.DISCOUNT_ITEMS)
                                     .stream()
                                     .collect(toMap(Discount::getBarcode, identity()));
    }

    @Override
    public Pair<Double, Double> getPromotion(Item item, CartItem cartItem) {
        String barcode = cartItem.getBarcode();

        double price = item.getPrice();
        if (allDiscounts.containsKey(barcode)) {
            price = item.getPrice() * allDiscounts.get(barcode).getDiscount() / 100;
        }

        return new Pair<>(price, 0.0);
    }
}

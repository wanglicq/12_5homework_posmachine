package promotion;

import domain.CartItem;
import domain.Item;
import domain.SecondHalfPrice;
import inter.ShopData;
import org.javatuples.Pair;
import parser.SecondHalfPriceParser;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class SecondHalfPricePromotion implements Promotion {

    private Set<String> allSecondHalfPrice;

    public SecondHalfPricePromotion() {
        SecondHalfPriceParser secondHalfPriceParser = new SecondHalfPriceParser();
        allSecondHalfPrice = secondHalfPriceParser.parse(ShopData.SECOND_HALF_PRICE)
                                                  .stream()
                                                  .map(SecondHalfPrice::getBarcode)
                                                  .collect(toSet());
    }

    @Override
    public Pair<Double, Double> getPromotion(Item item, CartItem cartItem) {
        double price = item.getPrice();
        double savePrice = getSavePrice(price, cartItem);

        return new Pair<>(price, savePrice);
    }

    private double getSavePrice(double price, CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        Integer saveQuantity = cartItem.getQuantity() / 2;

        return allSecondHalfPrice.contains(barcode)? price / 2 * saveQuantity: 0;
    }
}

package promotion;

import domain.CartItem;
import domain.Item;
import domain.SecondHalfPrice;
import inter.ShopData;
import parser.SecondHalfPriceParser;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class SecondHalfPricePromotion implements Promotion {

    private Collection<Item> allItems;
    private Set<String> allSecondHalfPrice;

    public SecondHalfPricePromotion(Collection<Item> allItems) {
        this.allItems = allItems;

        SecondHalfPriceParser secondHalfPriceParser = new SecondHalfPriceParser();

        allSecondHalfPrice = secondHalfPriceParser
                                .parse(ShopData.SECOND_HALF_PRICE)
                                .stream()
                                .map(SecondHalfPrice::getBarcode)
                                .collect(toSet());
    }

    @Override
    public double getPromotion(CartItem cartItem) {
        double price = getPrice(cartItem);
        double savePrice = getSavePrice(price, cartItem);

        return savePrice;
    }

    private double getSavePrice(double price, CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        Integer saveQuantity = cartItem.getQuantity() / 2;

        return allSecondHalfPrice.contains(barcode)? price / 2 * saveQuantity: 0;
    }


    private double getPrice(CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        for (Item item : allItems) {
            if (item.getBarcode().equals(barcode)) {
                return item.getPrice();
            }
        }
        return 0;
    }
}

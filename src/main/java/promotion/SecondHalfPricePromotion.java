package promotion;

import domain.CartItem;
import domain.Item;
import domain.SecondHalfPrice;
import inter.ShopData;
import parser.SecondHalfPriceParser;

import java.util.Collection;
import java.util.List;

public class SecondHalfPricePromotion implements Promotion {

    private Collection<Item> allItems;
    private List<SecondHalfPrice> allSecondHalfPrice;

    public SecondHalfPricePromotion(Collection<Item> allItems) {
        this.allItems = allItems;

        SecondHalfPriceParser secondHalfPriceParser = new SecondHalfPriceParser();
        allSecondHalfPrice = secondHalfPriceParser.parse(ShopData.SECOND_HALF_PRICE);
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
        for (SecondHalfPrice secondHalfPrice : allSecondHalfPrice) {
            if (secondHalfPrice.getBarcode().equals(barcode)) {
                return price / 2 * saveQuantity;
            }
        }
        return 0;
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

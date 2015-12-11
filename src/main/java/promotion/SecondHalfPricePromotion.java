package promotion;

import domain.CartItem;
import domain.Item;
import domain.SecondHalfPrice;
import inter.ShopData;
import parser.ItemParser;
import parser.SecondHalfPriceParser;

import java.util.List;

public class SecondHalfPricePromotion implements Promotion {

    List<Item> allItems = new ItemParser().parse(ShopData.ITEMS_DATA);
    SecondHalfPriceParser secondHalfPriceParser = new SecondHalfPriceParser();
    List<SecondHalfPrice> allSecondHalfPrice = secondHalfPriceParser.parse(ShopData.SECOND_HALF_PRICE);

    public SecondHalfPricePromotion(List<Item> allItems) {
        this.allItems = allItems;
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

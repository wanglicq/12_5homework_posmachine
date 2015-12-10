package inter;

import domain.CartItem;
import domain.Item;
import promotion.DiscountPromotion;
import promotion.SecondHalfPricePromotion;

import java.util.List;

public final class PosMachine {
    private final List<Item> allItems;


    public PosMachine(final List<Item> allItems) {
        this.allItems = allItems;
    }

    public double calculate(final List<CartItem> cartItems) {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += calculateSubtotal(cartItem);
        }
        return total;
    }

    private double calculateSubtotal(final CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        double discount = new DiscountPromotion().getPromotion(cartItem);
        double discountPrice = setDiscountPrice(barcode, discount);
        double saveInSecondHalfPrice = new SecondHalfPricePromotion(allItems).getPromotion(cartItem);
        return cartItem.getQuantity() * discountPrice - saveInSecondHalfPrice;
    }

    private double setDiscountPrice(String barcode, double discount) {
        double originPrice = queryItemPrice(barcode);
        for(Item item : allItems){
            if(item.getBarcode().equals(barcode)){
                item.setPrice(originPrice * discount);
            }
        }
        return originPrice * discount;
    }


    private double queryItemPrice(final String barcode) {
        for (Item item : allItems) {
            if (item.getBarcode().equals(barcode)) {
                return item.getPrice();
            }
        }

        throw new IllegalArgumentException("unknown item");
    }
}

package inter;

import domain.CartItem;
import domain.Item;
import promotion.DiscountPromotion;
import promotion.SecondHalfPricePromotion;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public final class PosMachine {

    private final Map<String, Item> allItems;

    public PosMachine(final List<Item> allItems) {
        this.allItems = allItems.stream().collect(toMap(Item::getBarcode, identity()));
    }

    public double calculate(final List<CartItem> cartItems) {
        return cartItems.stream().mapToDouble(this::calculateSubtotal).sum();
    }

    private double calculateSubtotal(final CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        double discount = new DiscountPromotion().getPromotion(cartItem);
        double discountPrice = setDiscountPrice(barcode, discount);
        double saveInSecondHalfPrice = new SecondHalfPricePromotion(allItems.values()).getPromotion(cartItem);
        return cartItem.getQuantity() * discountPrice - saveInSecondHalfPrice;
    }

    private double setDiscountPrice(String barcode, double discount) {
        double originPrice = queryItemPrice(barcode);

        Item item = allItems.get(barcode);
        item.setPrice(originPrice * discount);

        return item.getPrice();
    }

    private double queryItemPrice(final String barcode) {
        return allItems.get(barcode).getPrice();
    }
}

package inter;

import domain.CartItem;
import domain.Item;
import org.javatuples.Pair;
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
        Item item = allItems.get(barcode);

        Pair<Double, Double> discountResult = new DiscountPromotion().getPromotion(item, cartItem);
        double discountPrice = setDiscountPrice(barcode, discountResult.getValue0());

        Pair<Double, Double> secHalfResult = new SecondHalfPricePromotion().getPromotion(item, cartItem);

        return cartItem.getQuantity() * discountPrice - secHalfResult.getValue1();
    }

    private double setDiscountPrice(String barcode, double price) {
        Item item = allItems.get(barcode);
        item.setPrice(price);

        return item.getPrice();
    }
}

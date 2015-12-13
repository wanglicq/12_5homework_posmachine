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
    private DiscountPromotion discountPromotion;
    private SecondHalfPricePromotion secondHalfPricePromotion;

    public PosMachine(final List<Item> allItems,
                      DiscountPromotion discountPromotion,
                      SecondHalfPricePromotion secondHalfPricePromotion) {

        this.allItems = allItems.stream().collect(toMap(Item::getBarcode, identity()));
        this.discountPromotion = discountPromotion;
        this.secondHalfPricePromotion = secondHalfPricePromotion;
    }

    public double calculate(final List<CartItem> cartItems) {
        return cartItems.stream().mapToDouble(this::calculateSubtotal).sum();
    }

    private double calculateSubtotal(final CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        Item item = allItems.get(barcode);

        Pair<Double, Double> discountResult = discountPromotion.getPromotion(item, cartItem);
        double discountPrice = setDiscountPrice(barcode, discountResult.getValue0());

        Pair<Double, Double> secHalfResult = secondHalfPricePromotion.getPromotion(item, cartItem);

        return cartItem.getQuantity() * discountPrice - secHalfResult.getValue1();
    }

    private double setDiscountPrice(String barcode, double price) {
        Item item = allItems.get(barcode);
        item.setPrice(price);

        return item.getPrice();
    }
}

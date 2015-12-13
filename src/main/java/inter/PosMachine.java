package inter;

import domain.CartItem;
import domain.Item;
import org.javatuples.Pair;
import promotion.Promotion;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public final class PosMachine {

    private final Map<String, Item> allItems;
    private Promotion promotion;

    public PosMachine(final List<Item> allItems, Promotion promotion) {

        this.allItems = allItems.stream().collect(toMap(Item::getBarcode, identity()));
        this.promotion = promotion;
    }

    public double calculate(final List<CartItem> cartItems) {
        return cartItems.stream().mapToDouble(this::calculateSubtotal).sum();
    }

    private double calculateSubtotal(final CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        Item item = allItems.get(barcode);

        Pair<Double, Double> promotionResult = promotion.getPromotion(item, cartItem);
        double newPrice = promotionResult.getValue0();
        double savePrice = promotionResult.getValue1();

        return cartItem.getQuantity() * newPrice - savePrice;
    }
}

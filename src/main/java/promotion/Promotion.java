package promotion;

import domain.CartItem;
import domain.Item;
import org.javatuples.Pair;

public interface Promotion {
    Pair<Double, Double> getPromotion(Item item, CartItem cartItem);
}

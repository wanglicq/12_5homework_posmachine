package promotion;

import domain.CartItem;

public interface Promotion {
    double getPromotion(CartItem cartItem);
}

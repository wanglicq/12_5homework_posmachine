package posTest;

import domain.CartItem;
import org.junit.Test;
import promotion.DiscountPromotion;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountPromotionTest {
    @Test
    public void should_return_1_when_item3_has_no_discount() throws Exception {
        CartItem cartItem = new CartItem("ITEM000003", 2);
        double discount = new DiscountPromotion().getPromotion(cartItem);
        assertThat(discount, is(1d));
    }

    @Test
    public void should_return_discount_when_item1_has_discount() throws Exception {
        CartItem cartItem = new CartItem("ITEM000001", 3);
        double discount = new DiscountPromotion().getPromotion(cartItem);
        assertThat(discount, is(0.75d));
    }
}

package promotion;

import domain.CartItem;
import domain.Item;
import org.javatuples.Pair;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountPromotionTest {
    @Test
    public void should_return_1_when_item3_has_no_discount() throws Exception {
        Item item = new Item("ITEM000003", 50.0);
        CartItem cartItem = new CartItem("ITEM000003", 2);
        Pair<Double, Double> discountRes = new DiscountPromotion().getPromotion(item, cartItem);
        double newPrice = discountRes.getValue0();
        double savePrice = discountRes.getValue1();

        assertThat(newPrice, is(50.0));
        assertThat(savePrice, is(0.0));
    }

    @Test
    public void should_return_discount_when_item1_has_discount() throws Exception {
        Item item = new Item("ITEM000001", 40.0);
        CartItem cartItem = new CartItem("ITEM000001", 3);
        Pair<Double, Double> discountRes = new DiscountPromotion().getPromotion(item, cartItem);

        double newPrice = discountRes.getValue0();
        double savePrice = discountRes.getValue1();

        assertThat(newPrice, is(30.0));
        assertThat(savePrice, is(0.0));
    }
}

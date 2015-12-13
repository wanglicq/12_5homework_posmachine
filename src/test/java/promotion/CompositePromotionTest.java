package promotion;

import domain.CartItem;
import domain.Item;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CompositePromotionTest {

    private CompositePromotion promotion;

    @Before
    public void setUp() throws Exception {
        DiscountPromotion discountPromotion = new DiscountPromotion();
        SecondHalfPricePromotion secondHalfPricePromotion = new SecondHalfPricePromotion();

        promotion = new CompositePromotion(discountPromotion, secondHalfPricePromotion);
    }

    @Test
    public void item1_should_have_both_discount_and_second_half() throws Exception {

        Item item = new Item("ITEM000001", 40.0);
        CartItem cartItem = new CartItem("ITEM000001", 2);

        Pair<Double, Double> discountRes = promotion.getPromotion(item, cartItem);
        double newPrice = discountRes.getValue0();
        double savePrice = discountRes.getValue1();

        assertThat(newPrice, is(30.0));
        assertThat(savePrice, is(15.0));
    }

    @Test
    public void item3_should_have_only_second_half() throws Exception {

        Item item = new Item("ITEM000003", 50.0);
        CartItem cartItem = new CartItem("ITEM000003", 2);

        Pair<Double, Double> discountRes = promotion.getPromotion(item, cartItem);
        double newPrice = discountRes.getValue0();
        double savePrice = discountRes.getValue1();

        assertThat(newPrice, is(50.0));
        assertThat(savePrice, is(25.0));
    }

    @Test
    public void item5_should_have_only_discount() throws Exception {

        Item item = new Item("ITEM000005", 60.0);
        CartItem cartItem = new CartItem("ITEM000005", 2);

        Pair<Double, Double> discountRes = promotion.getPromotion(item, cartItem);
        double newPrice = discountRes.getValue0();
        double savePrice = discountRes.getValue1();

        assertThat(newPrice, is(54.0));
        assertThat(savePrice, is(0.0));
    }
}

package promotion;

import domain.CartItem;
import domain.Item;
import inter.ShopData;
import org.javatuples.Pair;
import org.junit.Test;
import parser.ItemParser;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SecondHalfPricePromotionTest {
    @Test
    public void should_return_0_when_item5_has_no_second_half_price() throws Exception {
        List<Item> allItems = new ItemParser().parse(ShopData.ITEMS_DATA);
        Item item = new Item("ITEM000005", 60.0);
        CartItem cartItem = new CartItem("ITEM000005", 2);
        Pair<Double, Double> secHalfRes = new SecondHalfPricePromotion().getPromotion(item, cartItem);
        double newPrice = secHalfRes.getValue0();
        double savePrice = secHalfRes.getValue1();
        assertThat(newPrice, is(60.0));
        assertThat(savePrice, is(0.0));
    }

    @Test
    public void should_return_25_when_item3_has_second_half_price() throws Exception {
        List<Item> allItems = new ItemParser().parse(ShopData.ITEMS_DATA);
        Item item = new Item("ITEM000003", 50.0);
        CartItem cartItem = new CartItem("ITEM000003", 2);
        Pair<Double, Double> secHalfRes = new SecondHalfPricePromotion().getPromotion(item, cartItem);
        double newPrice = secHalfRes.getValue0();
        double savePrice = secHalfRes.getValue1();
        assertThat(newPrice, is(50.0));
        assertThat(savePrice, is(25.0));
    }


}

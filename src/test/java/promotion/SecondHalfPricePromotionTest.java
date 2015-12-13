package promotion;

import domain.CartItem;
import domain.Item;
import inter.ShopData;
import org.junit.Test;
import parser.ItemParser;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SecondHalfPricePromotionTest {
    @Test
    public void should_return_0_when_item5_has_no_second_half_price() throws Exception {
        List<Item> allItems = new ItemParser().parse(ShopData.ITEMS_DATA);
        CartItem cartItem = new CartItem("ITEM000005", 2);
        double savePrice = new SecondHalfPricePromotion(allItems).getPromotion(cartItem);
        assertThat(savePrice, is(0d));
    }

    @Test
    public void should_return_25_when_item3_has_second_half_price() throws Exception {
        List<Item> allItems = new ItemParser().parse(ShopData.ITEMS_DATA);
        CartItem cartItem = new CartItem("ITEM000003", 2);
        double savePrice = new SecondHalfPricePromotion(allItems).getPromotion(cartItem);
        assertThat(savePrice, is(25d));
    }


}

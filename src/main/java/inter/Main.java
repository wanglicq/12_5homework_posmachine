package inter;

import domain.CartItem;
import domain.Item;
import parser.ItemParser;
import parser.ShoppingCartParser;
import promotion.CompositePromotion;
import promotion.DiscountPromotion;
import promotion.SecondHalfPricePromotion;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ItemParser itemParser = new ItemParser();
        List<Item> allItems = itemParser.parse(ShopData.ITEMS_DATA);

        ShoppingCartParser shoppingCartParser = new ShoppingCartParser();
        List<CartItem> cartItems = shoppingCartParser.parse(ShopData.SHOPPING_CART_DATA);

        CompositePromotion promotion = new CompositePromotion(new DiscountPromotion(), new SecondHalfPricePromotion());
        PosMachine posMachine = new PosMachine(allItems, promotion);
        double total = posMachine.calculate(cartItems);

        System.out.println("总价:" + total);
    }
}

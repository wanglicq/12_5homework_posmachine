package inter;

import domain.CartItem;
import domain.Item;
import parser.ItemParser;
import parser.ShoppingCartParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ItemParser itemParser = new ItemParser();
        List<Item> allItems = itemParser.parse(ShopData.ITEMS_DATA);

        ShoppingCartParser shoppingCartParser = new ShoppingCartParser();
        List<CartItem> cartItems = shoppingCartParser.parse(ShopData.SHOPPING_CART_DATA);

        PosMachine posMachine = new PosMachine(allItems);
        double total = posMachine.calculate(cartItems);

        System.out.println("总价:" + total);
    }
}

package org.example;

public class Customer {
    public void order(String menueName, Menu menu, Cooking cooking) {
        MenuItem menuItem = menu.choose(menueName);
        Cook cook = cooking.makeCook(menuItem);
    }
}

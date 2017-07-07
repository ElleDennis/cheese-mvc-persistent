package org.launchcode.models;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.NotNull;

/**
 * Created by lisette on 5/7/17.
 */
public class AddMenuItem {

    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;


    public AddMenuItem() {}

    public AddMenuItem(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public Menu getMenu() {
        return menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }



}

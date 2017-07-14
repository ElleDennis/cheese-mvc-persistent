package org.launchcode.controllers;

import org.launchcode.models.AddMenuItemForm;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static java.awt.SystemColor.menu;

/**
 * Created by lisette on 28/6/17.
 */
@RequestMapping(value = "menu")
@Controller
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    CheeseDao cheeseDao;

    @RequestMapping(value = "")
    private String index(Model model) {
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Les menus");
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("title", "Creer un menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Creer un menu");
            return "menu/add";
        }
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {

        Menu menu = menuDao.findOne(menuId);

        model.addAttribute("title", menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menuId", menu.getId());
        return "menu/view";
    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {

        Menu menu = menuDao.findOne(menuId);
        Iterable<Cheese> cheeses = cheeseDao.findAll();

        AddMenuItemForm form = new AddMenuItemForm(menu, cheeses);

        model.addAttribute("title", "Ajouter le fromage au menu: " + menu.getName());
        model.addAttribute("form", form);
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Creer un menu");
            model.addAttribute("form", form);
            return "menu/add-item";
        }

        int menuId = form.getMenuId();
        int cheeseId = form.getCheeseId();
        Menu theMenu = menuDao.findOne(menuId);
        Cheese newCheese = cheeseDao.findOne(cheeseId);
        theMenu.addItem(newCheese);
        menuDao.save(theMenu);
        return "redirect:/menu/view/" + theMenu.getId();

    }
}


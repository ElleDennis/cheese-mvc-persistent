package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lisette on 21/6/17. And my code below.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size
    private String name;

    void Category() {

    }

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Cheese> cheeses = new ArrayList<>();

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName() { this.name = name; }


}

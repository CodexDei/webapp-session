package org.aguzman.apiservlet.webapp.headers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    public List<ItemCar> items;

    public Cart(){

        items = new ArrayList<>();
    }

    public List<ItemCar> getItems() {
        return items;
    }

    public void addItems(ItemCar itemCar){

        if (items.equals(itemCar)){

            Optional<ItemCar> optionalItemCar = items.stream()
                    .filter(i -> i.equals(itemCar))
                    .findAny();
            if (optionalItemCar.isPresent()){
                ItemCar i = optionalItemCar.get();
                i.setQuantity(i.getQuantity()+1);
            }

        }else{
            items.add(itemCar);
        }
    }

    public int getTotal(){

        return items.stream().mapToInt(ItemCar::getTotal).sum();
    }
}

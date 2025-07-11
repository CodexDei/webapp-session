package org.aguzman.apiservlet.webapp.headers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    public List<ItemCart> items;

    public Cart(){

        items = new ArrayList<>();
    }

    public List<ItemCart> getItems() {
        return items;
    }

    public void addItems(ItemCart itemCart){

        if (items.contains(itemCart)){

            Optional<ItemCart> optionalItemCar = items.stream()
                    .filter(i -> i.equals(itemCart))
                    .findAny();
            if (optionalItemCar.isPresent()){
                ItemCart i = optionalItemCar.get();
                i.setQuantity(i.getQuantity()+1);
            }

        }else{
            items.add(itemCart);
        }
    }

    public int getTotal(){

        return items.stream().mapToInt(ItemCart::getTotal).sum();
    }
}

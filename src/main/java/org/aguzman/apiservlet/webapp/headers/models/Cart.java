package org.aguzman.apiservlet.webapp.headers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private List<ItemCart> items;

    public Cart(){

        this.items = new ArrayList<>();
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
            this.items.add(itemCart);
        }
    }

    public int getTotal(){

        return items.stream().mapToInt(ItemCart::getTotal).sum();
    }


    public void removeProducts(List<String> productsId) {

        if (productsId != null){

            productsId.forEach(productoId -> removeProduct(productoId));
        }
    }

    private void removeProduct(String productId) {

        Optional<ItemCart> product = findProduct(productId);
        product.ifPresent(itemCart -> items.remove(itemCart));
    }

    public void updateQuantity(String productId, int quantity){

        Optional<ItemCart> product = findProduct(productId);
        product.ifPresent(itemCart -> itemCart.setQuantity(quantity));
    }

    private Optional<ItemCart> findProduct(String productId) {

        return items.stream()
                .filter(item -> productId.equals(Long.toString(item.getProduct().getId())))
                .findAny();
    }




}

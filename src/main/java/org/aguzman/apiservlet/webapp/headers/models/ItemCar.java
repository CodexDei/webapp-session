package org.aguzman.apiservlet.webapp.headers.models;

import java.util.Objects;

public class ItemCar {

    private int quantity;
    private Product product;

    public ItemCar(int quantity, Product product){

        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getTotal(){

        return quantity * product.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemCar itemCar = (ItemCar) o;
        return Objects.equals(product.getId(), itemCar.product.getId()) &&
               Objects.equals(product.getName(), itemCar.product.getName());
    }

}

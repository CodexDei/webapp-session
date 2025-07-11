package org.aguzman.apiservlet.webapp.headers.models;

import java.util.Objects;

public class ItemCart {

    private int quantity;
    private Product product;

    public ItemCart(int quantity, Product product){

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
        ItemCart itemCart = (ItemCart) o;
        return Objects.equals(product.getId(), itemCart.product.getId()) &&
               Objects.equals(product.getName(), itemCart.product.getName());
    }
}

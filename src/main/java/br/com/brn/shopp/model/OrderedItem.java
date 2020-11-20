package br.com.brn.shopp.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class OrderedItem extends AbstractEntityImpl{
    @ManyToOne
    @JoinColumn(name="idorder", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;
    private Long idOrder;
    @ManyToOne
    @JoinColumn(name="idproduct", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;
    private Long idProduct;
    private Double unitPrice;
    private Double amount;
    private Double totalPrice;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        if (order != null) {
            this.idOrder = order.getId();
        }
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            this.idProduct = product.getId();
        }
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    }

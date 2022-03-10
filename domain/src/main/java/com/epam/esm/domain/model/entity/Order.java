package com.epam.esm.domain.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contract")
public class Order extends CustomEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int id;
    @Column(name = "c_price")
    private BigDecimal price;
    @Column(name = "c_date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User  user;
    @ManyToOne
    @JoinColumn(name = "gift_id")
    private Gift gift;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private CustomTag tag;

    public Order() {
    }

    public Order(int id, BigDecimal price, LocalDate date, User user, Gift gift, CustomTag tag) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.user = user;
        this.gift = gift;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public CustomTag getTag() {
        return tag;
    }

    public void setTag(CustomTag tag) {
        this.tag = tag;
    }
}

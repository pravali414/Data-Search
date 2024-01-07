package com.bezkoder.spring.datajpa.model;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_Id")
    private String customer_Id;
    @Column(name = "store_id")
    private String store_Id;
    @Column(name = "first_name")
    private String first_Name;
    @Column(name="email")
    private String email;
    @Column(name="address_id")
    private int address_Id;
    @Column(name="active")
    private int active;
    @Column(name="create_date")
    private Timestamp create_Date;



    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStore_Id() {
        return store_Id;
    }

    public void setStore_Id(String store_Id) {
        this.store_Id = store_Id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddress_Id() {
        return address_Id;
    }

    public void setAddress_Id(int address_Id) {
        this.address_Id = address_Id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Timestamp getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(Timestamp create_Date) {
        this.create_Date = create_Date;
    }

    public Customers(Long id, String customer_Id, String store_Id, String first_Name, String email, int address_Id, int active, Timestamp create_Date) {
        this.id = id;
        this.customer_Id = customer_Id;
        this.store_Id = store_Id;
        this.first_Name = first_Name;
        this.email = email;
        this.address_Id = address_Id;
        this.active = active;
        this.create_Date = create_Date;
    }

    public Customers() {
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", customer_Id='" + customer_Id + '\'' +
                ", store_Id='" + store_Id + '\'' +
                ", first_Name='" + first_Name + '\'' +
                ", email='" + email + '\'' +
                ", address_Id=" + address_Id +
                ", active=" + active +
                ", create_Date=" + create_Date +
                '}';
    }
}





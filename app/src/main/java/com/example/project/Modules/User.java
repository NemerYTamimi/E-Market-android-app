package com.example.project.Modules;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean gender;
    private String phoneNumber;
    private String city;
    private String goods;
    private String favGoods;
    private static List<Good>Myfav=new ArrayList<>();
    private static List<Good>Mycart=new ArrayList<>();

    public  List<Good> getMycart() {
        return Mycart;
    }

    public  void setMycart(Good good) {
        Mycart.add(good);
    }
    public  List<Good> getMyfav() {
        return Myfav;
    }

    public  void setMyfav(Good good) {
        Myfav.add(good);
    }
    public  void clearMyfev() {
        Myfav.removeAll(User.Myfav);
    }
    public  void clearMycart() {
        Myfav.removeAll(User.Myfav);
    }
    public String getFavGoods() {
        return favGoods;
    }

    public void setFavGoods(String favGoods) {
        this.favGoods = favGoods;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }


    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public User(String email, String firstName, String lastName, String password, boolean gender, String phoneNumber, String city) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMale() {
        return gender;
    }

    public void setMale(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

}

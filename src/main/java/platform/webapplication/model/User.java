package platform.webapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String user_name;
    private String user_password;
    private String first_name;
    private String last_name;
    private String mail;
    private Date birth_date;
    private String city;
    private String county;
    private String address;
    private String postal_code;
    private String phone_number;
    private Integer cart_id;

    public User() {
    }

    public User(Integer id, String user_name, String user_password, String first_name,
                String last_name, String mail, Date birth_date, String city, String county,
                String address, String postal_code, String phone_number, Integer card_id) {
        this.id = id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.birth_date = birth_date;
        this.city = city;
        this.county = county;
        this.address = address;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
        this.cart_id = card_id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public String getUser_password() {
        return this.user_password;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public String getMail() {
        return this.mail;
    }

    public Date getBirth_date() {
        return this.birth_date;
    }

    public String getCity() {
        return this.city;
    }

    public String getCounty() {
        return this.county;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPostal_code() {
        return this.postal_code;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public Integer getCart_id() {
        return this.cart_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setCart_id(Integer card_id) {
        this.cart_id = card_id;
    }
}
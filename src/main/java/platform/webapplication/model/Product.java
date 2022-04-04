package platform.webapplication.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private String name;
    private Float price;
    private Date date;
    private Integer stock;
    private Integer year;
    private String strap;
    private String glass;
    private String strapColor;
    private Integer water_resistence;
    private String carcase;
    private String carcase_form;
    private String carcase_color;
    private Integer carcase_thickness;
    private Byte alarm;
    private Byte time;
    private String mechanism;
    private Integer rating;
    private String gen;

    public Product() {

    }

    public Product(Integer id, Integer user_id, String name, Float price, Date date,
                   Integer stock, Integer year, String strap, String glass, String strapColor,
                   Integer water_resistence, String carcase, String carcase_form, String carcase_color,
                   Integer carcase_thickness, Byte alarm, Byte time, String mechanism, Integer rating, String gen) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.stock = stock;
        this.year = year;
        this.strap = strap;
        this.glass = glass;
        this.strapColor = strapColor;
        this.water_resistence = water_resistence;
        this.carcase = carcase;
        this.carcase_form = carcase_form;
        this.carcase_color = carcase_color;
        this.carcase_thickness = carcase_thickness;
        this.alarm = alarm;
        this.time = time;
        this.mechanism = mechanism;
        this.rating = rating;
        this.gen = gen;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getUser_id() {
        return this.user_id;
    }

    public String getName() {
        return this.name;
    }

    public Float getPrice() {
        return this.price;
    }

    public Date getDate() {
        return this.date;
    }

    public Integer getStock() {
        return this.stock;
    }

    public Integer getYear() {
        return this.year;
    }

    public String getStrap() {
        return this.strap;
    }

    public String getGlass() {
        return this.glass;
    }

    public String getStrapColor() {
        return this.strapColor;
    }

    public Integer getWater_resistence() {
        return this.water_resistence;
    }

    public String getCarcase() {
        return this.carcase;
    }

    public String getCarcase_form() {
        return this.carcase_form;
    }

    public String getCarcase_color() {
        return this.carcase_color;
    }

    public Integer getCarcase_thickness() {
        return this.carcase_thickness;
    }

    public Byte getAlarm() {
        return this.alarm;
    }

    public Byte getTime() {
        return this.time;
    }

    public String getMechanism() {
        return this.mechanism;
    }

    public Integer getRating() {
        return this.rating;
    }

    public String getGen() {
        return this.gen;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setStrap(String strap) {
        this.strap = strap;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setStrapColor(String strapColor) {
        this.strapColor = strapColor;
    }

    public void setWater_resistence(Integer water_resistence) {
        this.water_resistence = water_resistence;
    }

    public void setCarcase(String carcase) {
        this.carcase = carcase;
    }

    public void setCarcase_form(String carcase_form) {
        this.carcase_form = carcase_form;
    }

    public void setCarcase_color(String carcase_color) {
        this.carcase_color = carcase_color;
    }

    public void setCarcase_thickness(Integer carcase_thickness) {
        this.carcase_thickness = carcase_thickness;
    }

    public void setAlarm(Byte alarm) {
        this.alarm = alarm;
    }

    public void setTime(Byte time) {
        this.time = time;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
}

package platform.webapplication.models.Products;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductFilters {
    private List<String> brands = new ArrayList<String>();
    private List<Integer> years = new ArrayList<Integer>();
    private List<String> straps = new ArrayList<String>();
    private List<String> glasses = new ArrayList<String>();
    private List<String> strap_colors = new ArrayList<String>();
    private List<String> water_resistences = new ArrayList<String>();
    private List<String> carcases = new ArrayList<String>();
    private List<String> carcase_forms = new ArrayList<String>();
    private List<String> carcase_colors = new ArrayList<String>();
    private List<String> carcase_thicknesses = new ArrayList<String>();
    private List<String> mechanisms = new ArrayList<String>();
    private List<String> genders = new ArrayList<String>();
    private List<String> categories = new ArrayList<String>();
    private List<String> conditions = new ArrayList<String>();
    private List<String> models = new ArrayList<String>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFilters that = (ProductFilters) o;
        return statusCode == that.statusCode && Objects.equals(brands, that.brands) && Objects.equals(years, that.years) && Objects.equals(straps, that.straps) && Objects.equals(glasses, that.glasses) && Objects.equals(strap_colors, that.strap_colors) && Objects.equals(water_resistences, that.water_resistences) && Objects.equals(carcases, that.carcases) && Objects.equals(carcase_forms, that.carcase_forms) && Objects.equals(carcase_colors, that.carcase_colors) && Objects.equals(carcase_thicknesses, that.carcase_thicknesses) && Objects.equals(mechanisms, that.mechanisms) && Objects.equals(genders, that.genders) && Objects.equals(categories, that.categories) && Objects.equals(models, that.models) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brands, years, straps, glasses, strap_colors, water_resistences, carcases, carcase_forms, carcase_colors, carcase_thicknesses, mechanisms, genders, categories, models, error, statusCode);
    }

    public void addBrand(String brand){
        this.brands.add(brand);
    }


    public void addYears(Integer year){
        this.years.add(year);
    }

    public void addStrap(String strap){
        this.straps.add(strap);
    }

    public void addGlass(String glass){
        this.glasses.add(glass);
    }

    public void addStrapColors(String strap_color){
        this.strap_colors.add(strap_color);
    }

    public void addWaterResistence(String water_resistence){
        this.water_resistences.add(water_resistence);
    }

    public void addCarcase(String carcase){
        this.carcases.add(carcase);
    }

    public void addCarcaseForm(String carcase_form){
        this.carcase_forms.add(carcase_form);
    }

    public void addCarcaseColor(String  carcase_color){
        this.carcase_colors.add(carcase_color);
    }

    public void addCarcaseTickness(String carcase_thickness){
        this.carcase_thicknesses.add(carcase_thickness);
    }

    public void addMechanism(String mechanism){
        this.mechanisms.add(mechanism);
    }

    public void addGender(String gender){
        this.genders.add(gender);
    }

    public void addCategory(String categorie){
        this.categories.add(categorie);
    }

    public void addCondition(String condition) { this.conditions.add(condition);}

    public void addModel(String model){
        this.models.add(model);
    }
}

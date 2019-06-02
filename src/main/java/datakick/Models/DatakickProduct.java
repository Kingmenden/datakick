package datakick.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@Builder
public class DatakickProduct {
    @Getter @Setter
    double alcohol_by_volume;
    @Getter @Setter
    String author;
    @Getter @Setter
    String brand_name;
    @Getter @Setter
    int calories;
    @Getter @Setter
    double carbohydrates;
    @Getter @Setter
    double cholesterol;
    @Getter @Setter
    double fat;
    @Getter @Setter
    int fat_calories;
    @Getter @Setter
    String gtin14;
    @Getter @Setter
    Image[] images;
    @Getter @Setter
    String ingredients;
    @Getter @Setter
    double monounsaturated_fat;
    @Getter @Setter
    String name;
    @Getter @Setter
    int pages;
    @Getter @Setter
    double polyunsaturated_fat;
    @Getter @Setter
    double potassium;
    @Getter @Setter
    double protein;
    @Getter @Setter
    String publisher;
    @Getter @Setter
    double saturated_fat;
    @Getter @Setter
    String serving_size;
    @Getter @Setter
    String servings_per_container;
    @Getter @Setter
    String size;
    @Getter @Setter
    double sodium;
    @Getter @Setter
    double sugars;
    @Getter @Setter
    double trans_fat;
}

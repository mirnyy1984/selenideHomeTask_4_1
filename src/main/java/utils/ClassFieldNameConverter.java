package utils;

import com.codeborne.selenide.SelenideElement;
import pages.homePage.HomePage;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ClassFieldNameConverter {
    public static void main(String[] args) {
       // convertNameToString(HomePage.class);
    }

    public static String convertNameToString(Class currentClass, SelenideElement element) {


        Field[] fields = currentClass.getDeclaredFields();

        for (Field f : fields) {
            System.out.println(f.getName());
        }


        return "";
    }
}

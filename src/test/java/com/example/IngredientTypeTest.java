package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.fail;

public class IngredientTypeTest {

    @Test
    public void testEnumValuesAreCorrect() {
        // Проверяет, что оба типа существуют и возвращаются корректно
        Assertions.assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        Assertions.assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void testEnumHasTwoValuesOnly() {
        // Проверяет, что перечисление содержит ровно два элемента
        Assertions.assertEquals(2, IngredientType.values().length);
    }

    @Test
    public void testToStringReturnsCorrectNames() {
        // Проверяет, что строковое представление совпадает с именем константы
        Assertions.assertEquals("SAUCE", IngredientType.SAUCE.toString());
        Assertions.assertEquals("FILLING", IngredientType.FILLING.toString());
    }

    @Test
    public void testInvalidValueThrowsException() {
        // Проверяет, что попытка получить несуществующее значение вызывает исключение
        Assertions.assertThrows(IllegalArgumentException.class, () -> IngredientType.valueOf("UNKNOWN"));
    }
    //Проверка поддержки пустого типа:
    @Test
    public void testEmptyIngredientType() {
        try {        IngredientType.valueOf("");
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException ex) {        // Expected behavior
             }
    }
        //Неподдерживаемые типы:
    @Test
    public void testUnsupportedIngredientTypes() {
        try {        IngredientType.valueOf("UNSUPPORTED_TYPE");
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException ex) {        // Expected behavior
            }
    }
}

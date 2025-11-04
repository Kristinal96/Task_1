package com.example;

import org.junit.jupiter.api.Test;
import praktikum.Bun;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BunTest {

    @Test
    public void testGettersCorrectlyReturnValues() {
        // Проверяет корректность возврата значений геттерами
        Bun bun = new Bun("Classic Bread", 100.0f);
        assertEquals("Classic Bread", bun.getName());
        assertEquals(100.0f, bun.getPrice());
    }
    @Test
    public void testConstructorSetsValuesCorrectly() {
        // Проверяет, что конструктор правильно сохраняет введённые значения
        Bun bun = new Bun("White Bread", 50.0f);
        assertEquals("White Bread", bun.getName());
        assertEquals(50.0f, bun.getPrice());
    }

    @Test
    public void testEmptyOrNullNameAccepted() {
        // Проверяет, что можно задать пустое или нулевое имя
        Bun emptyNamedBun = new Bun("", 50.0f);
        assertEquals("", emptyNamedBun.getName());

        Bun nullNamedBun = new Bun(null, 50.0f);
        assertNull(nullNamedBun.getName());
    }

    @Test
    public void testNegativePriceAllowed() {
        // Проверяет, что возможна установка отрицательной цены
        Bun negativePriceBun = new Bun("Black Bread", -10.0f);
        assertEquals(-10.0f, negativePriceBun.getPrice());
    }

    @Test
    public void testDirectPropertyModification() {
        // Проверяет прямое изменение свойств
        Bun modifiableBun = new Bun("Whole Wheat", 100.0f);
        modifiableBun.name = "Rye";      // Прямая модификация имени
        modifiableBun.price = 150.0f;   // Прямая модификация цены
        assertEquals("Rye", modifiableBun.getName());
        assertEquals(150.0f, modifiableBun.getPrice());
    }

    @Test
    public void testImmutabilityOfPublicFields() {
        // Проверяет неизменность открытых полей при прямом доступе
        Bun immutableBun = new Bun("Classic White", 50.0f);
        immutableBun.name = "Classic Black";
        immutableBun.price = 100.0f;
        assertEquals("Classic Black", immutableBun.getName()); // Имя изменяется напрямую
        assertEquals(100.0f, immutableBun.getPrice());       // Цена изменяется напрямую
    }

    @Test
    public void testSimpleGetterFunctionality() {
        // Проверяет простоту работы геттеров
        Bun simpleBun = new Bun("Cheap Bread", 20.0f);
        assertEquals("Cheap Bread", simpleBun.getName());
        assertEquals(20.0f, simpleBun.getPrice());
    }

    @Test
    public void testZeroPriceBunCreation() {
        // Проверяет создание булочки с ценой ноль
        Bun zeroPriceBun = new Bun("Free Sample", 0.0f);
        assertEquals(0.0f, zeroPriceBun.getPrice());
    }
    @Test
    public void testMaxLengthName() {
        // Максимальная длина имени (например, 255 символов)
        String maxLengthName = "A".repeat(255);
        Bun longNameBun = new Bun(maxLengthName, 100.0f);
        assertEquals(maxLengthName, longNameBun.getName());
    }

    //Минимальная положительная цена булочки:
    @Test
    public void testMinPositivePrice() {
        Bun minPriceBun = new Bun("Small Bun", Float.MIN_VALUE);
        assertEquals(Float.MIN_VALUE, minPriceBun.getPrice());
    }
    //Отрицательная цена булочки:
    @Test
    public void testNegativePrice() {
        Bun negPriceBun = new Bun("Discount Bun", -100.0f);
        assertEquals(-100.0f, negPriceBun.getPrice());
    }
    //Прямое изменение свойств булочки:
    @Test
    public void testDirectFieldChange() {
        Bun changeableBun = new Bun("Original Name", 100.0f);
        changeableBun.name = "Changed Name";
        changeableBun.price = 200.0f;
        assertEquals("Changed Name", changeableBun.getName());
        assertEquals(200.0f, changeableBun.getPrice());
    }
    //Пустое имя булочки:
    @Test
    public void testEmptyBunName()
    {    Bun bun = new Bun("", 100.0f);
        assertEquals("", bun.getName());
    }

    //Максимальная длина имени булочки:
    @Test
    public void testMaximumBunNameLength() {
        String veryLongName = "A".repeat(255);
        Bun bun = new Bun(veryLongName, 100.0f);
        assertEquals(veryLongName, bun.getName());
    }

    //Отрицательная цена булочки:
    @Test
    public void testNegativeBunPrice() {
        Bun bun = new Bun("Dark Bun", -100.0f);
        assertEquals(-100.0f, bun.getPrice());
    }

    //Установка булочки без имени:
    @Test
    public void testSettingBunWithoutName() {
        Bun bun = new Bun(null, 100.0f);
        assertNull(bun.getName());
    }
}


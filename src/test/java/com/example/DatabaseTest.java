package com.example;

import org.junit.jupiter.api.Test;
import praktikum.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    public void testAvailableBuns() {
        // Инициализируем базу данных
        Database database = new Database();

        // Получаем список доступных булок
        List<Bun> buns = database.availableBuns();

        // Проверяем, что список не пустой
        assertNotNull(buns);
        assertFalse(buns.isEmpty());

        // Проверяем, что в списке есть булки с ожидаемыми названиями
        assertTrue(buns.stream().anyMatch(bun -> bun.getName().equals("black bun")));
        assertTrue(buns.stream().anyMatch(bun -> bun.getName().equals("white bun")));
        assertTrue(buns.stream().anyMatch(bun -> bun.getName().equals("red bun")));
    }

    @Test
    public void testAvailableIngredients() {
        // Инициализируем базу данных
        Database database = new Database();

        // Получаем список доступных ингредиентов
        List<Ingredient> ingredients = database.availableIngredients();

        // Проверяем, что список не пустой
        assertNotNull(ingredients);
        assertFalse(ingredients.isEmpty());

        // Проверяем, что в списке есть ингредиенты с ожидаемыми названиями
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("hot sauce")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("sour cream")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("chili sauce")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("cutlet")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("dinosaur")));
        assertTrue(ingredients.stream().anyMatch(ingredient -> ingredient.getName().equals("sausage")));
    }
}
package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.*;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private static Burger burger;
    private Bun mockBun;
    private static Ingredient mockIngredient1;
    private static Ingredient mockIngredient2;
    private static Ingredient mockIngredient3;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // инициализирует аннотации Mock

        // Создание макетов для теста
        mockBun = new Bun("test bun", 50.0f);
        mockIngredient1 = new Ingredient(IngredientType.SAUCE, "ketchup", 10.0f);
        mockIngredient2 = new Ingredient(IngredientType.FILLING, "cheese", 20.0f);
        mockIngredient3 = new Ingredient(IngredientType.FILLING, "lettuce", 15.0f);

        // Подготовка тестового экземпляра
        burger = new Burger();
        burger.setBuns(mockBun);
    }

    /**
     * Базовые тесты на установку булочки и расчет общей стоимости
     */
    @Nested
    @DisplayName("Тестирование установки булочки")
    class TestSetBuns {

        @Test
        void shouldSetBunAndCalculateTotalPrice() {
            // Добавляем ингредиенты
            burger.addIngredient(mockIngredient1);
            burger.addIngredient(mockIngredient2);

            // Ожидаемая общая стоимость
            float expectedPrice = mockBun.getPrice() * 2 + mockIngredient1.getPrice() + mockIngredient2.getPrice(); // две булочки + сумма всех ингредиентов

            // Проверяем итоговую сумму
            assertEquals(expectedPrice, burger.getPrice(), "Общая стоимость должна учитывать обе булочки");
        }
    }

    /**
     * Тестирование добавления и удаления ингредиентов
     */
    @Nested
    @DisplayName("Тестирование методов добавления и удаления ингредиентов")
    class TestAddRemoveIngredients {

        @ParameterizedTest(name = "Удаление индекса {0}")
        @ValueSource(ints = {0, 1})
        void shouldRemoveIngredientByValidIndex(int validIndex) {
            // Добавляем три ингредиента
            burger.addIngredient(mockIngredient1);
            burger.addIngredient(mockIngredient2);
            burger.addIngredient(mockIngredient3);

            // Удаляем ингредиент по индексу
            burger.removeIngredient(validIndex);

            // Проверяем количество оставшихся ингредиентов
            assertEquals(2, burger.ingredients.size(), "Количество ингредиентов должно уменьшиться на 1");
        }

        @Test
        void shouldNotRemoveIngredientWhenIndexIsOutOfBounds() {
            // Проверяем поведение при удалении неверного индекса
            Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(-1));
            assertTrue(exception instanceof IndexOutOfBoundsException, "Должно выбрасываться исключение IndexOutOfBoundsException");
        }
    }

    /**
     * Тестирование переноса ингредиента на новую позицию
     */
    @Nested
    @DisplayName("Тестирование метода перемещения ингредиентов")
    class TestMoveIngredient {

        @ParameterizedTest(name = "Перемещение ингредиента из {0} в {1}")
        @CsvSource({"0,1", "1,2", "2,0"})
        void shouldMoveIngredientToNewPosition(int fromIndex, int toIndex) {
            // Добавляем три ингредиента
            burger.addIngredient(mockIngredient1);
            burger.addIngredient(mockIngredient2);
            burger.addIngredient(mockIngredient3);

            // Запоминаем исходный порядок
            Ingredient originalFirst = burger.ingredients.get(fromIndex);

            // Перемещаем ингредиент
            burger.moveIngredient(fromIndex, toIndex);

            // Проверяем новое положение ингредиента
            assertSame(originalFirst, burger.ingredients.get(toIndex), "Ингредиенты должны поменять местоположение");
        }

        @Test
        void shouldThrowErrorOnInvalidIndexes() {
            // Наполняем Burger тремя ингредиентами
            burger.addIngredient(mockIngredient1);
            burger.addIngredient(mockIngredient2);
            burger.addIngredient(mockIngredient3);

            // Пытаемся перемещать ингредиенты с недопустимыми индексами
            Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(-1, 0)); // отрицательный индекс
            assertTrue(exception instanceof IndexOutOfBoundsException, "Метод должен выбросить исключение при некорректных индексах");

            exception = assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(3, 0)); // выход за пределы размера
            assertTrue(exception instanceof IndexOutOfBoundsException, "Метод должен выбросить исключение при выходе за границы списка");
        }
    }

    /**
     * Тестирование метода формирования рецепта (чек)
     */
    @Nested
    @DisplayName("Тестирование вывода чека")
    class TestGetReceipt {

        @Test
        void shouldPrintCorrectReceiptWithAllIngredients() {
            // Добавляем три ингредиента
            burger.addIngredient(mockIngredient1);
            burger.addIngredient(mockIngredient2);
            burger.addIngredient(mockIngredient3);

            // Получаем чек
            String receipt = burger.getReceipt();

            // Проверяем наличие булочки и каждого ингредиента в чеке
            assertTrue(receipt.contains(mockBun.getName()), "Название булочки должно присутствовать в чеке");
            assertTrue(receipt.contains(mockIngredient1.getName()), "Имя первого ингредиента должно присутствовать в чеке");
            assertTrue(receipt.contains(mockIngredient2.getName()), "Имя второго ингредиента должно присутствовать в чеке");
            assertTrue(receipt.contains(mockIngredient3.getName()), "Имя третьего ингредиента должно присутствовать в чеке");
        }
    }
    @Test
    void shouldHaveZeroPriceWithoutIngredients() {
        // Без добавления ингредиентов
        assertEquals(mockBun.getPrice() * 2, burger.getPrice(), "Цена должна равняться цене двух булочек");
    }

    @Test
    void shouldThrowErrorWhenMovingNonexistentIngredient() {
        // Бургеру ничего не добавляется

        // Попробуйте переместить несуществующий ингредиент
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(0, 1));
        assertTrue(exception instanceof IndexOutOfBoundsException, "Должно бросаться исключение при попытке переместить несуществующий ингредиент");
    }

    @Test
    void shouldHandleZeroPricedIngredients() {
        // Добавляем ингредиенты с нулевыми ценами
        Ingredient freeSauce = new Ingredient(IngredientType.SAUCE, "free sauce", 0.0f);
        burger.addIngredient(freeSauce);

        // Общая цена должна учитывать только цены булочек
        assertEquals(mockBun.getPrice() * 2, burger.getPrice(), "Общее количество должно учитываться без бесплатных ингредиентов");
    }

    @Test
    void shouldPreserveOrderAfterRemoval() {
        // Добавляем ингредиенты в определенном порядке
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        // Удаляем второй ингредиент
        burger.removeIngredient(1);

        // Первый и третий ингредиенты остаются на местах
        assertSame(mockIngredient1, burger.ingredients.get(0));
        assertSame(mockIngredient3, burger.ingredients.get(1));
    }

    @Test
    void shouldAllowAddingDuplicateIngredients() {
        // Добавляем одинаковые ингредиенты дважды
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient1);

        // Должно сохраниться два элемента
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    void shouldGenerateCorrectReceiptWithoutIngredients() {
        // Только булочка присутствует
        String receipt = burger.getReceipt();

        // Чек должен содержать название булочки
        assertTrue(receipt.contains(mockBun.getName()), "Название булочки должно присутствовать в чеке");
    }

    //Проверка суммы стоимости бургера при нескольких булочках:
    @Test
    public void testMultipleBunsInBurger() {
        Burger burger = new Burger();
        Bun bun1 = new Bun("Wheat Bun", 100.0f);
        Bun bun2 = new Bun("Sesame Bun", 150.0f);
        burger.setBuns(bun1);    burger.setBuns(bun2);
        assertEquals(bun2, burger.bun); // Последняя установленная булочка должна остаться
    }

        //Удаление всех ингредиентов:
        @Test
        public void testRemoveAllIngredients() {
            Burger burger = new Burger();
            Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "Mayonnaise", 10.0f);
            Ingredient ing2 = new Ingredient(IngredientType.FILLING, "Chicken Patty", 20.0f);
            burger.addIngredient(ing1);    burger.addIngredient(ing2);    burger.removeIngredient(0);
            burger.removeIngredient(0);    assertEquals(0, burger.ingredients.size());
        }

        //Ошибка при перемещении вне диапазона:
        @Test
        public void testMoveIngredientOutOfRange() {
            Burger burger = new Burger();
            Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "Ketchup", 10.0f);
            burger.addIngredient(ing1);
            boolean caught = false;
            try {        burger.moveIngredient(0, 10); // Индексация вне диапазона
                 } catch (IndexOutOfBoundsException ex) {        caught = true;
            }
            assertTrue(caught);
        }

        //Установленная булочка с нулевым названием:
        @Test
        public void testBunWithNullName() {
            Burger burger = new Burger();
            Bun bun = new Bun(null, 100.0f);
            burger.setBuns(bun);
            assertNull(burger.bun.getName());
        }
}
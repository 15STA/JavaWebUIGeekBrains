package ru.geekbrains.webui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(TimingExtension.class)
public class Tests {

   private static Logger logger = LoggerFactory.getLogger(Tests.class);

    @BeforeAll
    static void beforeAllTests() {
        logger.info("\n Провека функции вычисления площади треугольника по трем сторонам");
    }

    @Test
    @DisplayName("ВЫПОЛНЯЕТСЯ условие существования треугольника")
    void testTrueTriangle() {
        Triangle triangle1 = new Triangle(10,10,10);
        double expected = 43.30127;
        double result = triangle1.triangleSquare(triangle1);
        Assertions.assertTrue(Math.abs(result-expected)<0.0001);
    }

    @Test
    @DisplayName("НЕ ВЫПОЛНЯЕТСЯ условие существования треугольника")
    void testFalseTriangle() {
        Triangle triangle2 = new Triangle(0,4,5);
        Throwable exception = assertThrows(ArithmeticException.class, () -> triangle2.triangleSquare(triangle2));
        assertEquals("Треугольник с заданными сторонами не существует", exception.getMessage());

    }

    @Test
    @DisplayName("Тест на отрицательные значения для сторон треугольника")
    void testNegativeValue() {
        Triangle triangle2 = new Triangle(-6,-8,-3);
        Throwable exception = assertThrows(ArithmeticException.class, () -> triangle2.triangleSquare(triangle2));
        assertEquals("Треугольник с заданными сторонами не существует", exception.getMessage());

    }

    @Test
    @DisplayName("Тест на самый маленький int треугольник")
//    @ParameterizedTest
//    @ValueSource (classes = {Triangle.class })
    void testTinyTriangle()  {
        Triangle triangle1 = new Triangle(1,1,1);
        double expected = 0.433;
        double result = triangle1.triangleSquare(triangle1);
        Assertions.assertTrue(Math.abs(result-expected)<0.0001);
    }

    @Test
    @DisplayName("Тест на Большой int треугольник")
    void testBigTriangle()  {
        Triangle triangle1 = new Triangle(600000000,800000000,300000000);
        double expected = 76444424257103280.0;
        double result = triangle1.triangleSquare(triangle1);
        Assertions.assertTrue(Math.abs(result-expected)<0.0001);
    }


    @AfterEach
    public void tearDown(){
        logger.info("Тест выполнен");
    }

}

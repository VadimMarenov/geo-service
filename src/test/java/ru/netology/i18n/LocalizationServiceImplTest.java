package ru.netology.i18n;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    LocalizationService sut;

    @BeforeAll
    public static void init() {
        System.out.println("TESTS STARTED");
    }

    @BeforeEach
    public void started() {
        sut = new LocalizationServiceImpl();
        System.out.println("test started");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("TESTS FINISHED");
    }

    @BeforeEach
    public void finished() {
        System.out.println("test finished");
    }

    @Test
    public void test_locale_when_Russia() {
        Country country = Country.RUSSIA;
        String actual = sut.locale(country);
        String expected = "Добро пожаловать";

        assertEquals(expected, actual);
    }

    @Test
    public void test_locale_when_USA() {
        Country country = Country.USA;
        String actual = sut.locale(country);
        String expected = "Welcome";

        assertEquals(expected, actual);
    }

}
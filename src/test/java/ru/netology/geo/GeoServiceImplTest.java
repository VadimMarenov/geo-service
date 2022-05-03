package ru.netology.geo;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;


import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoServiceImplTest {
    GeoService sut;

    @BeforeAll
    public static void init() {
        System.out.println("TESTS STARTED");
    }

    @BeforeEach
    public void started() {
        sut = new GeoServiceImpl();
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
    void test_by_ip_when_localhost() {
        String ip = "127.0.0.1";
        Location actual = sut.byIp(ip);

        //City
        assertEquals(null, actual.getCity());
        //Country
        assertEquals(null, actual.getCountry());
        //Street
        assertEquals(null, actual.getStreet());
        //Building
        assertEquals(0, actual.getBuiling());

    }
    @Test
    void test_by_ip_when_Moscow() {
        String ip = "172.0.32.11";
        Location actual = sut.byIp(ip);

        //City
        assertEquals("Moscow", actual.getCity());
        //Country
        assertEquals(Country.RUSSIA, actual.getCountry());
        //Street
        assertEquals( "Lenina",actual.getStreet());
        //Building
        assertEquals(15, actual.getBuiling());

    }
    @Test
    void test_by_ip_when_NY() {
        String ip = "96.44.183.149";
        Location actual = sut.byIp(ip);

        //City
        assertEquals("New York", actual.getCity());
        //Country
        assertEquals(Country.USA, actual.getCountry());
        //Street
        assertEquals( " 10th Avenue",actual.getStreet());
        //Building
        assertEquals(32, actual.getBuiling());

    }

}
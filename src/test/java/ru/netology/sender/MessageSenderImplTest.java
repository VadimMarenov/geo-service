package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    GeoService geoService;
    LocalizationService localizationService;

    @BeforeAll
    public static void init() {
        System.out.println("TESTS STARTED");
    }

    @BeforeEach
    public void started() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        System.out.println("test started");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("TESTS FINISHED");
    }

    @AfterEach
    public void finished() {
        System.out.println("test finished");
    }

    @Test
    void test_message_sender_when_russian_ip() {
        String expected = "Добро пожаловать";
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.168.26.79");
        String actual = messageSenderImpl.send(headers);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_message_sender_when_eng_ip() {
        String expected = "Welcome";
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.174.16.32");
        String actual = messageSenderImpl.send(headers);
        Assertions.assertEquals(expected, actual);
    }
}


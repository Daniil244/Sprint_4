package yandexTest;

import model.OrderPage;
import model.RentalPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;
    private final String name;
    private final String secondName;
    private final String address;
    private final String phoneNumber;
    private final String date;
    private final String color;
    private final String comment;
    private final boolean isHead;

    public CreateOrderTest(String name, String secondName, String address, String phoneNumber, String date, String color, String comment, boolean isHead) {
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.color = color;
        this.comment = comment;
        this.isHead = isHead;
    }

    @Parameterized.Parameters
    public static Object[][] faqData() {
        return new Object[][]{
                {"Филип", "Киркоров", "Москва, Красная площадь, 1", "89996661221", "22.12.2024", "черный", "Коммент", false},
                {"Даниил", "Вершинин", "Санкт-Петербург, ул.Красного фонаря, 5", "88887773443", "23.12.2025", "серый", "Все будет гуд", true},
        };
    }

    @Before
    public void start() {
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        //driver = new ChromeDriver(options);

        FirefoxOptions optionsF = new FirefoxOptions();
        optionsF.addArguments("--start-maximized");
        driver = new FirefoxDriver(optionsF);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(PAGE_URL);

    }

    @Test
    public void orderCreateTest() throws InterruptedException {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.cookieButtonClick();
        orderPage.fillOrderPage(name, secondName, address, phoneNumber, isHead);
        RentalPage rentPage = new RentalPage(driver);
        rentPage.fillRentalPage(date, color, comment);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}


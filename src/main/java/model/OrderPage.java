package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {

    private final WebDriver driver;
    private final By cookButtonLocator = By.xpath("//button[contains(@class, 'App_CookieButton')]");

    // Страница оформления заказа

    // Кнопка «Заказать» вверху страницы
    private static final By UP_ORDER_Button = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    // Кнопка «Заказать» внизу страницы
    private static final By LOW_ORDER_Button = By.xpath(".//button[text()='Заказать']/parent::div[@class='Home_FinishButton__1_cWm']");
    // Сайт
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";
    // Поле "Имя"
    public static final By NAME_INPUT = By.xpath("//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    public static final By SURNAME_INPUT = By.xpath("//input[@placeholder='* Фамилия']");

    // Поле "Адрес"
    public static final By ADDRESS_INPUT = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    public static final By METRO_STATION_INPUT = By.xpath("//input[@placeholder='* Станция метро']");

    // метро "Сокольники"
    public static final By METRO_STATION = By.xpath(".//li[@data-index='0']");

    // Поле "Телефон"
    public static final By PHONE_INPUT = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    // Локатор для кнопки "Далее"
    public static final By NEXT_BUTTON = By.xpath("//button[text()='Далее']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для нажатия на кнопку "Заказать" исходя из значения параметра Верхняя или Нижняя кнопка
    public void clickOrder(boolean isHead) {
        if (isHead) {
            driver.findElement(UP_ORDER_Button).click();
        } else {
            WebElement element = driver.findElement(LOW_ORDER_Button);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.findElement(LOW_ORDER_Button).click();
        }

    }

    //Метод заполнения поля Имя
    public void setName(String name) {
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    //Метод заполнения поля Фамилия
    public void setSecondName(String secondName) {
        driver.findElement(SURNAME_INPUT).sendKeys(secondName);
    }

    //Метод заполнения поля Адрес
    public void setAddress(String addressName) {
        driver.findElement(ADDRESS_INPUT).sendKeys(addressName);
    }

    //Метод для открытия списка станций метро
    public void openStationList() {
        driver.findElement(METRO_STATION_INPUT).click();
    }

    //Метод для выбора станции метро из списка
    public void choiceMetroStation() {
        driver.findElement(METRO_STATION).click();
    }

    //Метод заполнения поля телефон
    public void setPhone(String phoneNumber) {
        driver.findElement(PHONE_INPUT).sendKeys(phoneNumber);
    }

    //Метод для нажатия на кнопку Далее
    public void nextStep() {
        driver.findElement(NEXT_BUTTON).click();
    }


    //Метод для заполнения формы и перехода на следующий шаг
    public void fillOrderPage(String name, String secondName, String address, String phoneNumber, boolean isHead) {
        clickOrder(isHead); //Нажать на кнопку заказать
        setName(name); // Заполнить поле Имя
        setSecondName(secondName); // Заполнить поле Фамилия
        setAddress(address); // Заполнить поле Адрес
        openStationList(); // Открыть список станций метро
        choiceMetroStation(); // Выбрать станцию метро
        setPhone(phoneNumber); // Заполнить поле Телефон
        nextStep(); // Нажать на кнопку Далее
    }

    // Убираем всплывающее окно
    public void cookieButtonClick() {
        WebElement cookieButton = driver.findElement(cookButtonLocator);
        cookieButton.click();
    }
}

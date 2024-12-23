package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

public class RentalPage {

    private final WebDriver driver;


    // Дата заказа (Когда привезти самокат)
    public static final By FIELD_DATA_ORDER = By.xpath("//input[contains(@placeholder,'Когда')]");

    // Cрок аренды
    public static final By FIELD_RENTAL_PERIOD = By.xpath("//div[@class = 'Dropdown-placeholder']");

    // Выбор срока аренды из выпадающего списка
    public static final By CHOOSING_THE_RENTAL_PERIOD = By.xpath("//div[@class = 'Dropdown-option' and text() = 'трое суток']");
    // Выбор цвета самоката
    public static final By SCOOTER_COLORS_BLACK = By.xpath("//input[@id = 'black' and @class = 'Checkbox_Input__14A2w']");
    private final By SCOOTER_COLORS_GREY = By.xpath("//input[@id='grey']");
    // Комментарий для курьера
    public static final By COMMENT_FOR_THE_COURIER = By.xpath("//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN']");
    // Кнопка "Заказать"
    public static final By ORDER_BUTTON = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");

    // Кнопка "Да" в окне оформить заказ
    public static final By YES_BUTTON = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    // Модальное окно с текстом Заказ оформлен
    private static final By ORDER_SUCCESS = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public RentalPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для выбора даты заказа самоката
    public void setDateInput(String date) {
        WebElement element = driver.findElement(FIELD_DATA_ORDER);
        element.sendKeys(date);
        element.sendKeys(Keys.ESCAPE);
    }

    //Метод для выбора срока аренды самоката
    public void setRentCountDays() {
        driver.findElement(FIELD_RENTAL_PERIOD).click();
        driver.findElement(CHOOSING_THE_RENTAL_PERIOD).click();
    }

    //Метод для выбора цвета самоката
    public void setColor(String color) {
        if (color.equals("черный")) {
            driver.findElement(SCOOTER_COLORS_BLACK).click();
        } else if (color.equals("серый")) {
            driver.findElement(SCOOTER_COLORS_GREY).click();
        }
    }

    //Метод для заполнения поля Комментарий курьеру
    public void setCommentInput(String comment) {
        driver.findElement(COMMENT_FOR_THE_COURIER).sendKeys(comment);
    }

    //Метод для клика на кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(ORDER_BUTTON).click();
    }

    //Метод для клика на кнопку "Да" в модальном окне "Хотите оформить заказ?"
    public void clickYesButton() {
        driver.findElement(YES_BUTTON).click();
    }

    //Метод для проверки отображения Модального окна "Заказ оформлен"
    public void isOrderSuccess() {
        Assert.assertTrue("Элемент не отображается", driver.findElement(ORDER_SUCCESS).isDisplayed());
    }

    //Метод для заполнения формы и перехода на следующий шаг
    public void fillRentalPage(String date, String color, String comment) throws InterruptedException {
        setDateInput(date);
        setRentCountDays();
        setColor(color);
        setCommentInput(comment);
        clickOrderButton();
        clickYesButton();
        isOrderSuccess();

    }
}

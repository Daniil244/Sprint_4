package yandexTest;

import model.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;


import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class QuestionTest {
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;
    private Integer index;
    private String expectedText;
    private final String expectedAnswer;


    public QuestionTest(Integer index, String expectedText, String expectedAnswer) {
        this.index = index;
        this.expectedText = expectedText;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] faqData() {
        //Генерируем тестовые данные (номер и ответ на вопрос)
        return new Object[][]{
                {0,
                        "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},

                {1,
                        "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2,
                        "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3,
                        "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4,
                        "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5,
                        "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6,
                        "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7,
                        "Я жизу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(PAGE_URL);

    }


    @Test
    public void shouldCheckQuestionAndAnswer() throws InterruptedException {
        MainPage questionsPage = new MainPage(driver);

        String questionActualText = questionsPage.getQuestionActualText(By.id("accordion__heading-" + index));
        questionsPage.clickQuestion(By.id("accordion__heading-" + index));
        //Thread.sleep(5000);
        String answerActualText = questionsPage.getAnswerActualText(By.id("accordion__panel-" + index));

        // Проверить соответствие текста вопроса и ответа ожидаемым значениям
        Assert.assertEquals("Текст вопроса не соответствует ожидаемому: ", expectedText, questionActualText);
        Assert.assertEquals("Текст ответа не соответствует ожидаемому: ", expectedAnswer, answerActualText);
    }


    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}

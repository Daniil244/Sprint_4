package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class MainPage {


    private final WebDriver driver;
    //private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By cookButtonLocator = By.xpath("//button[contains(@class, 'App_CookieButton')]");


    // Убираем всплывающее окно
    public void cookieButtonClick() {
        WebElement cookieButton = driver.findElement(cookButtonLocator);
        cookieButton.click();
    }

    // Найти элемент вопроса
    public String getQuestionActualText(By importantQuestionLocator){
        WebElement answerElement = driver.findElement(importantQuestionLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", answerElement);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(importantQuestionLocator));
        return answerElement.getText();
    }

    // Найти элемент ответа
    public String getAnswerActualText(By importantAnswerLocator){
        WebElement answerElement = driver.findElement(importantAnswerLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", answerElement);
        return answerElement.getText();
    }

    //Нажать на вопрос
    public void clickQuestion(By importantQuestionLocator){
        WebElement questionElement = driver.findElement(importantQuestionLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);
        questionElement.click();
    }

}

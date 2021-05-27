package ru.netology;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryOrderTest {


    @Test
    public void shouldSubmitRequest() {
        open("http://localhost:9999");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id='city'] input").setValue("Сыктывкар");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id='date'] input").setValue("28.05.2021");
        $("[data-test-id='name'] input").setValue("Пупкин Вася ");
        $("[data-test-id='phone'] input").setValue("+79976543212");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $(" [data-test-id=notification]").waitUntil(visible, 1500000)
                .shouldHave(text("Успешно! Встреча успешно забронирована на " + date));

    }

    @Test
    public void shouldSubmitRequestFailCity() {
        open("http://localhost:9999");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id='city'] input").setValue("samara");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id='date'] input").setValue("28.05.2021");
        $("[data-test-id='name'] input").setValue("Пупкин Вася ");
        $("[data-test-id='phone'] input").setValue("+79976543212");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    public void shouldSubmitRequestFailDate() {
        String date = LocalDate.now().plusDays(0).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id='name'] input").setValue("Пупкин Вася ");
        $("[data-test-id='phone'] input").setValue("+79976543212");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=date] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void shouldSubmitRequestFailName() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id='name'] input").setValue("Orange");
        $("[data-test-id='phone'] input").setValue("+79976543212");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldSubmitRequestFailName2() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79976543212");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }


    @Test
    public void shouldSubmitRequestFailNumber() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id='name'] input").setValue("Петрик Матрик");
        $("[data-test-id='phone'] input").setValue("+799212");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $(".input_theme_alfa-on-white.input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }


    @Test
    public void shouldSubmitRequestCheckBox() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id='name'] input").setValue("Петрик Матрик");
        $("[data-test-id='phone'] input").setValue("+7998765432");
        $(".button__content").click();
        $("[data-test-id='agreement']").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }


}



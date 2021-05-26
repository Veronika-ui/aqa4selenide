package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
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



//        $(withText("Успешно!")).waitUntil(visible, 15000);
//      element(By.xpath("//div[class ='notification__title']")).shouldHave(text("Успешно!"));
//   $(" [data-test-id=notification]").waitUntil(visible, 1500000)
//                .shouldHave(text("Успешно! Встреча успешно забронирована на " + date))

        }

    }



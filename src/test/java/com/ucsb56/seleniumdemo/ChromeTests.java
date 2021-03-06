package com.ucsb56.seleniumdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class ChromeTests {

  @LocalServerPort
  int port;

  private WebDriver driver;

  @BeforeClass
  public static void setupClass() {
    WebDriverManager.chromedriver().setup();
  }

  @Before
  public void setupTest() {
    driver = new ChromeDriver();
  }

  @After
  public void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void googleTest() {
    // Your test code here
    driver.get("https://www.google.com");
    String title = driver.getTitle();

    assert (title.equals("Google"));
  }

  @Test
  public void indexTest() {
    driver.get("http://localhost:" + port);
    WebElement working = driver.findElement(By.id("working"));

    assert (working.isDisplayed());
    assert (working.getText().equals("It works!"));
  }
}
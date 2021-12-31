package webpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PrintAllLinks {

    WebDriver driver;

    @BeforeTest
    void openWeb() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

    }

    @Test
    void validateLinks() throws IOException {

        List<WebElement> elementList = driver.findElements(By.tagName("a"));
        System.out.println("Total Webpage links is:" + elementList.size());

        List<WebElement> imageLinks = driver.findElements(By.tagName("img"));
        System.out.println("Total image links is:" + imageLinks.size());

        elementList.addAll(imageLinks);

        HttpURLConnection con = null;
        for (WebElement element : elementList){
            String url = element.getAttribute("href");

            if (url != null && !url.contains("javascript")){
                con = (HttpURLConnection)(new URL(url)).openConnection();
                con.connect();
                con.setConnectTimeout(3000);
                int resCode = con.getResponseCode();
                System.out.println("Connection Status for URL:"+url+" " +"is"+" " +resCode);

            }
        }
        con.disconnect();  
        driver.quit();

    }

}

package webpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckAllElement {


    WebDriver driver;

    @BeforeTest
    void openWeb() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/gp/goldbox?ref_=nav_cs_gb");
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    void checkBox(){

        String ele = "Music";
        List<String> lst = new ArrayList<>();
        List<WebElement> boxes = driver.findElements(By.xpath("//div[@class='CheckboxFilter-module__gridFilterOption_hdG5xZdR2ZvDkQKkl_d49']"));


        for (WebElement box : boxes) {
            String s = box.getText();
            lst.add(s);
            System.out.println(s);
        }

        if(lst.contains(ele))
        {
            driver.findElement(By.xpath("//input[@type='checkbox']//following-sibling::span[text()='" + ele + "']")).click();
            System.out.println(ele + " " +"Element is Present");
        }else
        {
            System.out.println(ele + " " +"Element is Absent");

        }
    }

}

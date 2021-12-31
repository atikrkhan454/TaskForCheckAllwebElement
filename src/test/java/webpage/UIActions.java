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

public class UIActions {
    WebDriver driver;

    @BeforeTest
    void openWeb() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    @Test()
    void getLink() {
        List<WebElement> elementList = driver.findElements(By.tagName("a"));
        //System.out.println("Total Webpage links is:" + elementList.size());

        for (WebElement obj : elementList) {
            System.out.println(obj.getText());
            String link = obj.getAttribute("href");
            System.out.println(link);
        }

    }
    @Test(priority = 1)
    void linkCount(){
        List<WebElement> elementList = driver.findElements(By.tagName("a"));
        System.out.println("Total Webpage links is:" + elementList.size());


    }
    @Test(priority = 2)
    void checkBox(){
        String ele = "Sports";
        List<String> lst = new ArrayList<>();
        List<WebElement> boxes = driver.findElements(By.xpath("//div[@class='custom-control custom-checkbox custom-control-inline']"));


        for (WebElement box : boxes) {
            String s = box.getText();
            lst.add(s);
            System.out.println(s);
        }

        if(lst.contains(ele))
        {
            driver.findElement(By.xpath("//div[@class='custom-control custom-checkbox custom-control-inline']//following-sibling::label[text()='" + ele + "']")).click();
            System.out.println(ele + " " +"Element is Present");

        }else 
        {
            System.out.println(ele + " " +"Element is Absent");

        }

    }
    @Test(priority = 3)
    void listItems(){
        List<WebElement> listitem=driver.findElements(By.tagName("li"));
        int listsize= listitem.size();
        System.out.println("No of list item ="+listsize);
        for (WebElement list:listitem){
            String lis= list.getText();
            System.out.println("All the list are"+lis);
        }

    }
    @Test(priority = 4)
    void cancelDialogueBox(){
        driver.navigate().to("https://demoqa.com/alerts");
        WebElement alert =driver.findElement(By.xpath("//div[@class='col']//button[@id='alertButton']"));
        String alertmsg= alert.getText();
        System.out.println("Alert msg ="+alertmsg);
        alert.click();
        driver.switchTo().alert().dismiss();
    }
    @Test(priority = 5)
    void confirmDialogBox(){
        driver.navigate().to("https://demoqa.com/alerts");
        WebElement alert =driver.findElement(By.xpath("//div[@class='col']//button[@id='confirmButton']"));
        String alertmsg= alert.getText();
        System.out.println("Alert msg ="+alertmsg);
        alert.click();
        driver.switchTo().alert().accept();
    }
}

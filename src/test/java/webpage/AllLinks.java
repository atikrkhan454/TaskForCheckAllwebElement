package webpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class AllLinks {
    static WebDriver driver;
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
        clickAllHyperLinksByTagName("a");


    }
    public static void clickAllHyperLinksByTagName(String tagName){
        // int numberOfElementsFound = getNumberOfElementsFound(By.tagName(tagName));
        List<WebElement> links=getNumberOfElementsFound(By.tagName(tagName));
        int PazeSize = links.size();
        System.out.println("Total number of links "+ PazeSize);
        for (int i=0;i< links.size();i++){
            System.out.println(" link: " + i);
            WebElement E1 = links.get(i);
            System.out.println(E1.getText());
            String url = E1.getAttribute("href");
            System.out.println(url);
        }

        for (int num = 0; num < PazeSize; num++) {

            getElementWithIndex(By.tagName(tagName), num).click();
            List<WebElement> link1 = driver.findElements(By.tagName("a"));
            int PazeSize1 = link1.size();
            System.out.println(PazeSize1);
            for (int ilink=0;ilink<PazeSize1;ilink++){

                System.out.println(link1.get(ilink).getAttribute("href"));
            }

            driver.navigate().back();
        }
    }

    public static List<WebElement> getNumberOfElementsFound(By by) {
        return driver.findElements(by);
    }

    public static WebElement getElementWithIndex(By by, int num) {
        return driver.findElements(by).get(num);
    }
}
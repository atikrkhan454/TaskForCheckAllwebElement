package webpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Login {

    public WebDriver driver;

    @BeforeTest
    public void login1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
        Thread.sleep(20000);
    }
    @Test
    public void data() throws IOException {
        File src = new File("F:\\Task-23\\Task\\output.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet Sheet1 = wb.getSheetAt(0);
        Sheet1.createRow(0).createCell(0);
        List<WebElement> links=driver.findElements(By.tagName("a"));
        String url;
        HttpURLConnection huc;
        int respCode;
        int cell =0;
        for(int i=0;i<links.size();i++) {
            WebElement alinks = links.get(i);
            String allinks = alinks.getAttribute("href");
            System.out.println(allinks);
            XSSFRow row = Sheet1.createRow(i);
            XSSFCell excelCell = row.createCell(cell);
            excelCell.setCellValue(allinks);

        }
        for (WebElement link : links) {
            url = link.getAttribute("href");
            System.out.println(url);
            if (url == null || url.isEmpty()) {
                System.out.println("URL is not yet published");
                continue;
            }
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();
                for (int i = 1; i <= Sheet1.getLastRowNum(); i++) {
                    XSSFCell resultCell = Sheet1.getRow(i).getCell(1);

                    if (respCode >= 400) {
                        System.out.println(url + " is a broken link");
                        resultCell.setCellValue("FAIL");
                    } else {
                        System.out.println(url + " is a valid link");
                        resultCell.setCellValue("PASS");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fout = new FileOutputStream(src);
        wb.write(fout);
    }
}

package manualTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
public class FDCalculator {
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\interestRates.xlsx";

        System.out.println(filePath);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.sbisecurities.in/calculators/fd-calculator");

        WebElement fdInvestment = driver.findElement(By.xpath("//input[@id='input_fd_investment' and @name = 'fd_investment']"));
        fdInvestment.sendKeys("50000");

        WebElement fdTenure = driver.findElement(By.xpath("//input[@id='input_fd_investment' and @name = 'years']"));
        fdTenure.sendKeys("10");

        WebElement interest = driver.findElement(By.xpath("//input[@id='input_interest' and @name = 'interest']"));
        interest.sendKeys("4.5");

        Select select = new Select(driver.findElement(By.xpath("//select[@id='frequency']")));
        select.selectByValue("Yearly");

        WebElement submitBtn = driver.findElement(By.xpath("//button[text()='Calculate']"));
        submitBtn.click();

        //output
        String text = driver.findElement(By.xpath("//div[@id='lumpsum_return']/p[contains(text(), 'Amount at Maturity')]/span")).getText();
        System.out.println(text);

        fdInvestment.sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE, "10000");
        fdTenure.sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE, "9");
        interest.sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE, "9.5");
        select.selectByValue("Monthly");
        submitBtn.click();
        System.out.println(driver.findElement(By.xpath("//div[@id='lumpsum_return']/p[contains(text(), 'Amount at Maturity')]/span")).getText());
    }

}

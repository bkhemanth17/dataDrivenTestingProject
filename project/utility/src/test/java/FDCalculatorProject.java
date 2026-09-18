import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utilityPackage.utilityClass;


import java.io.IOException;
import java.time.Duration;

public class FDCalculatorProject {
    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.sbisecurities.in/calculators/fd-calculator");

        //file path
        String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\interestRates.xlsx";
        String sheetName = "Sheet1";
        System.out.println(filePath);

        //row
        int rows = utilityClass.getRow(filePath, sheetName);
//        System.out.println(rows);

        //cells
        int cell = utilityClass.getCellCount(filePath,sheetName,rows);
//        System.out.println(cell);

        String data = "";

        for(int i=1; i<=rows; i++){
            String price = utilityClass.getCellData(filePath,sheetName,i,0);
            String roInterest = utilityClass.getCellData(filePath,sheetName,i,1);
            String tenure = utilityClass.getCellData(filePath,sheetName,i,2);
            String timePeriod = utilityClass.getCellData(filePath,sheetName,i,3);

            //amount string to double conversion
            String maturityValue = utilityClass.getCellData(filePath,sheetName,i,4);
            String cleanedString = maturityValue.replaceAll("[^0-9.]", "");
            double expectedAmount = Double.parseDouble(cleanedString);

            String exp_value = utilityClass.getCellData(filePath,sheetName,i,5);

            //scrapping to application
            driver.findElement(By.xpath("//input[@id='input_fd_investment' and @name = 'fd_investment']")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE, price);
            driver.findElement(By.xpath("//input[@id='input_interest' and @name = 'interest']")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE, roInterest);
            driver.findElement(By.xpath("//input[@id='input_fd_investment' and @name = 'years']")).sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE, timePeriod);
            Select tenureSelector = new Select(driver.findElement(By.xpath("//select[@id='frequency']")));
            tenureSelector.selectByValue(tenure);


            //click
            driver.findElement(By.xpath("//button[text()='Calculate']")).click();

            String text = driver.findElement(By.xpath("//div[@id='lumpsum_return']/p[contains(text(), 'Amount at Maturity')]/span")).getText();
            String cleanedString1 = text.replaceAll("[^0-9.]", "");
            double finalAmount = Double.parseDouble(cleanedString1);

            //validation
            if(expectedAmount==finalAmount){
                System.out.println("test passed");
                utilityClass.setCellData(filePath,sheetName,i,6,"pass");
                utilityClass.fillGreenColour(filePath,sheetName,i,6);
            }else{
                System.out.println("test failed");
                utilityClass.setCellData(filePath,sheetName,i,6,"fail");
                utilityClass.fillRedColour(filePath,sheetName,i,6);
            }

        }
        driver.quit();

    }
}

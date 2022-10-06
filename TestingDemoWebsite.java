import java.awt.Frame;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestingDemoWebsite {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// Headless Setup
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		
		// Environment Setup 	
		System.setProperty("webdriver.chrome.driver","C:\\Web Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Browser Setup and info
		driver.get("https://testautomationpractice.blogspot.com/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));			// implicit wait < global >	
		// Alert Concept 
		WebElement alert_button = driver.findElement(By.xpath("//button[contains(text(),'Click Me')]"));
		alert_button.click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(1000);
		System.out.println(alert.getText());
		alert.accept();
		
		// Date Picker
		
		WebElement date = driver.findElement(By.cssSelector("input#datepicker"));
		System.out.println(date.isDisplayed());
		date.click();
		Thread.sleep(1000);
		
		for (int i=8;i<=13;i++) {
			
		
			Thread.sleep(1000);
			WebElement next = driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']"));
			next.click();
			String date_title = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			
			if(date_title.equals("December")) {
				
				WebElement day = driver.findElement(By.linkText("30"));
				System.out.println(day.isDisplayed());
				day.click();
				System.out.println("30th of December month is selected");
				break;
			
			}
		
		}
		
		// HTML TABLE 
		
		int row = driver.findElements(By.xpath("//table[@name='BookTable']//tbody/tr")).size();
		int col = driver.findElements(By.xpath("//table[@name='BookTable']//tbody/tr[1]/th")).size();
		System.out.println(row);		// 7 
		System.out.println(col);		// 4
		
		for (int i=2;i<=row;i++) {
			
			for(int j=1;j<=col;j++) {
				String row_col_data = driver.findElement(By.xpath("//table[@name='BookTable']//tbody/tr["+i+"]/td["+j+"]")).getText();
				System.out.println("  "+row_col_data);
				
			}
			System.out.println();
		}
		
		// Copy text on WebSite()
		
		String text = driver.findElement(By.xpath("//span[contains(text(),'Message &&&123456')]")).getText();
		
		System.out.println(text);
		
		// Drop Down Element
		WebElement s = driver.findElement(By.id("speed"));
		Select dd = new Select(s);
		dd.selectByVisibleText("Slow");
		
		// Multiple Window Handles
		WebElement search = driver.findElement(By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']"));
		search.sendKeys("selenium");
		driver.findElement(By.xpath("//input[@class='wikipedia-search-button']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenium")));
		
		List <WebElement> links = driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']/a"));
		System.out.println(links.size());
		//Thread.sleep(5000);
		for (int i=0;i<links.size();i++) {
			
			links.get(i).click();
			}
	
		Set<String> handles = driver.getWindowHandles();
		Iterator <String> it = handles.iterator();
		while(it.hasNext()) {								// While loop works as boolean function (true/false) - if condition is true it will enter
			driver.switchTo().window(it.next());			// the loop or else will come outside right away
			System.out.println(driver.getTitle());
		}
		
		// Frame Concept
		driver.switchTo().frame(0);									// Switch to frame by index no
		//driver.switchTo().frame("frame-one1434677811");				// Switch to frame by id or name 
		WebElement First_name = driver.findElement(By.cssSelector("input#RESULT_TextField-1"));			// tag and id (css selector)
		System.out.println("first name is displayed : "+First_name.isDisplayed());
		First_name.sendKeys("Bill");
		
		WebElement Last_name = driver.findElement(By.cssSelector("input#RESULT_TextField-2"));				// tag and id ( css selector )
		System.out.println("last name is displayed :"+Last_name.isDisplayed());
		Last_name.sendKeys("Goldberg");
		
		WebElement Phone = driver.findElement(By.id("RESULT_TextField-3"));						// id locator stratergy 
		System.out.println("phone no is displayed : "+Phone.isDisplayed());
		Phone.sendKeys("928392834023");
		
		WebElement Country = driver.findElement(By.cssSelector("input#RESULT_TextField-4[name=RESULT_TextField-4]"));
		System.out.println("Country is displayed : " + Country.isDisplayed());
		Country.sendKeys("usa");
		
		WebElement City = driver.findElement(By.cssSelector("input.text_field[name=RESULT_TextField-5]"));
		System.out.println("City is Displayed : "+City.isDisplayed());
		City.sendKeys("new jersey");
		
		WebElement Email = driver.findElement(By.cssSelector("input.text_field[id=RESULT_TextField-6]"));
		System.out.println("Email is displayed : "+Email.isDisplayed());
		Email.sendKeys("abc@gmail.com");
		
		// Radio Button 
		WebElement male = driver.findElement(By.xpath("//label[contains(text(),'Male')]"));
		System.out.println("male is displayed : "+male.isDisplayed());
		
		male.click();
		System.out.println("male is clicked : "+male.isSelected());
		
		// Drop Down Element 
		WebElement drop_down = driver.findElement(By.cssSelector("select.drop_down"));
		System.out.println("drop down is displayed : "+drop_down.isDisplayed());
		
		Select dd1 = new Select(drop_down);				// for drop down we have to use Select Class
		dd1.selectByVisibleText("Afternoon");			// using selectbyvisible text method
		
		  
		//List <WebElement> opt = dd1.getOptions();
		//System.out.println(opt.size());
		
		driver.switchTo().defaultContent();
		
		// Double Click
		WebElement click = driver.findElement(By.xpath("//button[contains(text(),'Copy Text')]"));
		System.out.println("click is displayed : "+click.isDisplayed());
		Actions action = new Actions(driver);
		
		action.doubleClick(click).build().perform();
		System.out.println("element is double clicked");
		
		// Drag and Drop
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droppable']"));
		action.dragAndDrop(source, target).build().perform();		
		
		System.out.println("drag and drop is done");
		
		// Slider
		WebElement slider = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
		System.out.println("slider is dislplayed : "+slider.isDisplayed());
		action.dragAndDropBy(slider, 350, 0).build().perform();
		
		// Resizable 
		WebElement resize = driver.findElement(By.id("resizable"));
		System.out.println("resize is displayed : "+resize.isDisplayed());
		action.moveToElement(resize).dragAndDropBy(resize, 150, 10).build().perform();
		
//driver.close();
	}
}

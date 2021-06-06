package phase1;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AmazonProject {
	
	public static void main(String[] args) throws IOException   {
		
		String catagory="";
		String searchitem="";
		
	    System.getProperty("chrome.driver.webdriver", "chromedriver");
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		    Statement st=connect.createStatement();
		    ResultSet rs=st.executeQuery("select * from Amazon");
 		    while(rs.next())
			 {
				 System.out.println(rs.getString(2)+" "+rs.getString(3));
				  catagory=rs.getString(2);
				  searchitem=rs.getString(3);
				 		
					
							
			 }
 		    
 		   WebElement catagory1=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));	
			Select obj2=new Select(catagory1);
			obj2.selectByVisibleText(catagory);
			WebElement searchvalue=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));				
			searchvalue.sendKeys(searchitem);				
			WebElement gobutton=driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
			gobutton.click();
			//no.of mobile available 
			List<WebElement> listofcell=driver.findElements(By.xpath("//*[@class='a-size-medium a-color-base a-text-normal']"));
			System.out.println("No.of items available for mobile: "+listofcell.size());
			
			for(int i=0;i<listofcell.size();i++)
			{
				System.out.println("No.etails of the each items: "+listofcell.get(i).getText());
			}
			
			TakesScreenshot obj=driver;
			File myfile=driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(myfile, new File("test.png"));
			
			
			
			
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
		
	
		
	
	}

}

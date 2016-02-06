import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.ByteArrayDataOutput;


public class test_1 {
	
	protected static String usrmetafield = "";
	protected static String psswrdmetafield = "";
	protected static String subBtnmetafield = "";
	protected static String ancTagmetafield = "";
	protected static String usrName = "3627761";
	protected static String usrPass = "Vikram121!@!";
	protected static WebElement tempEle3 ;
	protected static Boolean IS_MFA_CHALLENGED = false;
	protected static Boolean NO_BTN_FOUND = false;
	
	public static void main( String []args ) throws Exception{


		System.setProperty("webdriver.chrome.driver", "D:\\eclipse-jee-mars-1-win32-x86_64\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.navigate().to("https://vermont457retire.gwrs.com/login.do"); 
		for (WebElement frame : driver.findElements(By.tagName("frame"))) {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(frame);
			System.out.println("frame:: "+": " +driver.findElement(By.tagName("body")).getAttribute("outerHTML") );

		}

		for (WebElement frame : driver.findElements(By.tagName("frame"))) {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(frame);
			System.out.println("frame:: "+": " +driver.findElement(By.tagName("body")).getAttribute("outerHTML") );

		}
		driver.navigate().to("https://www.yesbank.in/retail-bankings.html");
		driver.findElement(By.xpath("//*[@id=\"jsn-mainbody\"]/div[2]/div[2]/form/div[1]/span")).click();
		List<WebElement> inpTags = driver.findElements(By.tagName("input"));
		List<WebElement> btnTags = driver.findElements(By.tagName("button"));
		System.out.println("length is "+inpTags.size());
		String name ;
		WebElement tempEle;
		List<WebElement> tMp = new ArrayList<WebElement>();
		List<WebElement> usrNme = new ArrayList<WebElement>();
		List<WebElement> pssWrd = new ArrayList<WebElement>();
		List<WebElement> subBtn = new ArrayList<WebElement>();
		List<WebElement> ancTag = new ArrayList<WebElement>();
		Iterator<WebElement> itr = inpTags.iterator();
		Iterator<WebElement> itr1 = btnTags.iterator();
		while(itr.hasNext()) {
			tempEle = itr.next();
			name = tempEle.getAttribute("outerHTML");
			if(!(name.contains("Hidden") || (name.contains("none") && name.contains("display")) || name.contains("search") ||name.contains("HIDDEN") || name.contains("hidden") || (name.contains("checkbox")))){
				tMp.add(tempEle);
			}
		}
		while(itr1.hasNext()) {
			tempEle = itr1.next();
			name = tempEle.getAttribute("outerHTML");
			if(!(name.contains("Hidden") || (name.contains("none") && name.contains("display")) || name.contains("search") ||name.contains("HIDDEN") || name.contains("hidden"))){
				tMp.add(tempEle);
			}
		}
		System.out.println(tMp.size()+" "+tMp);
		getUserNmeFields(driver,tMp,usrNme);
		getPasswrdFields(driver,tMp,pssWrd);
		getsubBtnnFields(driver,tMp,subBtn,ancTag);
		if(usrNme.size()==0 && pssWrd.size()==0){
			System.out.println("login url has been changed or not correct or not in login page please check the url");
		}
		tryLogin(driver,subBtn,usrNme,pssWrd,ancTag);
		IS_MFA_CHALLENGED = isOnMfa(driver);
		/*if(IS_MFA_CHALLENGED){
			oneStepChallange(driver);//for security questions
			twoStepChallange(driver);//for otp
		}
		isLoginSuccssful(driver);*/
	}

	private static void isLoginSuccssful(WebDriver driver) {
		// TODO Auto-generated method stub
		if(isOnPostLoginPage(driver)){
			System.out.println("login successfull");
		}else{
			System.out.println("login failed...needs attention");
		}
		
	}

	private static boolean isOnPostLoginPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void twoStepChallange(WebDriver driver) {
		// TODO handle otp
		
	}

	private static void oneStepChallange(WebDriver driver) {
		// TODO handle security questions
		
	}

	private static Boolean isOnMfa(WebDriver driver) {
		// TODO add check for is on mfa or security questions page
		if(false){
		
		}else{
			
		}
		return null;
	}

	private static void tryLogin(WebDriver driver, List<WebElement> subBtn,List<WebElement> usrNme, List<WebElement> pssWrd, List<WebElement> ancTag) {
		// TODO Auto-generated method stub
		Iterator<WebElement> itr1 = usrNme.iterator();
		Iterator<WebElement> itr2 = pssWrd.iterator();
		Iterator<WebElement> itr3 = subBtn.iterator();
		Iterator<WebElement> itr4 = ancTag.iterator();
		while(itr1.hasNext()){
			usrmetafield = itr1.next().getAttribute("name");
			while(itr2.hasNext()){
				psswrdmetafield = itr2.next().getAttribute("name");
				System.out.println("loooooooooooooook here"+psswrdmetafield);
				if(subBtn.size() != 0){
					while(itr3.hasNext()){
						System.out.println(subBtnmetafield);
						subBtnmetafield = itr3.next().getAttribute("name");;
						driver.findElement(By.name(usrmetafield)).sendKeys(usrName);
						driver.findElement(By.name(psswrdmetafield)).sendKeys(usrPass);
						driver.findElement(By.name(subBtnmetafield)).click();
					}
				}else{
					while(itr4.hasNext()){
						NO_BTN_FOUND = true;
						System.out.println(ancTagmetafield);
						driver.findElement(By.name(usrmetafield)).sendKeys(usrName);
						driver.findElement(By.name(psswrdmetafield)).sendKeys(usrPass);
						itr4.next().click();
					}
				}
			}
		}
	}

	private static void getsubBtnnFields(WebDriver driver, List<WebElement> tMp,List<WebElement> subBtn, List<WebElement> ancTag) {
		// TODO Auto-generated method stub
		ListIterator<WebElement> itr = tMp.listIterator();
		while(itr.hasNext()){
			tempEle3 = itr.next();
			String temp = tempEle3.getAttribute("outerHTML");
			if((temp.toLowerCase().contains("type=\"submit\"")) && (temp.toLowerCase().contains("login") || temp.toLowerCase().contains("sign in") || temp.toLowerCase().contains("submit")  || temp.toLowerCase().contains("signin") || temp.toLowerCase().contains("log in"))){
				subBtn.add(tempEle3);
			}else if((temp.toLowerCase().contains("<button") || temp.toLowerCase().contains("type=\"button\"")) && (temp.toLowerCase().contains("login") || temp.toLowerCase().contains("sign in") || temp.toLowerCase().contains("submit")  || temp.toLowerCase().contains("signin") || temp.toLowerCase().contains("log in"))){
				subBtn.add(tempEle3);
			}
		}
		System.out.println("buttons are "+subBtn.size());
		if(subBtn.size() == 0){
			getAnchorLink(driver,ancTag);
		}else{
			System.out.println(subBtn);
		}
	}

	private static void getAnchorLink(WebDriver driver, List<WebElement> ancTag) {
		// TODO Auto-generated method stub
		List<WebElement> anchrTags = driver.findElements(By.tagName("a"));
		System.out.println("anchor tags in page are "+anchrTags.size());
		Iterator<WebElement> itr12 = anchrTags.iterator();
		while (itr12.hasNext() ) {
			tempEle3 = itr12.next();
			String name123 = tempEle3.getAttribute("outerHTML");
			if((name123.toLowerCase().contains("login") || name123.toLowerCase().contains("log in") || name123.toLowerCase().contains("signin") || name123.toLowerCase().contains("sign in")) && name123.toLowerCase().contains("onclick=") && !name123.toLowerCase().contains("forgot")){
				System.out.println(name123);
				ancTag.add(tempEle3);
			}
		}
	}

	private static void getPasswrdFields(WebDriver driver, List<WebElement> tMp,List<WebElement> pssWrd) {
		// TODO Auto-generated method stub
		ListIterator<WebElement> itr = tMp.listIterator();
		while(itr.hasNext()){
			tempEle3 = itr.next();
			String temp = tempEle3.getAttribute("outerHTML");
			if((temp.toLowerCase().contains("type=\"password\""))){
				pssWrd.add(tempEle3);
			}
		}
		System.out.println("psswrd size "+pssWrd.size());
		System.out.println(pssWrd);
		if(pssWrd.size() == 0){
			handleNextPagePass(driver);
		}
	}

	private static void handleNextPagePass(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("password is on next page which needs to be hanfdled");
	}

	private static void getUserNmeFields(WebDriver driver, List<WebElement> tMp,List<WebElement> usrNme) {
		// TODO Auto-generated method stub
		ListIterator<WebElement> itr = tMp.listIterator();
		int i=0;
		while(itr.hasNext()){
			tempEle3 = itr.next();
			String temp = tempEle3.getAttribute("outerHTML");
			if(((temp.toLowerCase().contains("type=\"text\"") || temp.toLowerCase().contains("login") ||temp.toLowerCase().contains("log in") || temp.toLowerCase().contains("signin") || temp.toLowerCase().contains("sign in") || temp.toLowerCase().contains("access")) &&( !temp.toLowerCase().contains("<button") && !temp.toLowerCase().contains("type=\"submit\"") && !temp.toLowerCase().contains("type=\"password\""))) && ((temp.toLowerCase().contains("user") || temp.toLowerCase().contains("login") || temp.toLowerCase().contains("access") || temp.toLowerCase().contains("log in") || temp.toLowerCase().contains("maxlength") || temp.toLowerCase().contains("autocomplete") ))){
				usrNme.add(tempEle3);
			}
		}
		System.out.println("usrname fields are "+usrNme.size());
		System.out.println(usrNme);
	}
}
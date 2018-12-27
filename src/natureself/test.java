package natureself;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.util.PublicSuffixList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import operation.operation;

public class test {
	public static void main(String args[]) throws InterruptedException, MalformedURLException {
		inputter_test();
//		inputter_test2();


	}

	public static void inputter_test() throws InterruptedException {
		WebDriver browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		operation.login(browser, "test00", "test00");// 内测系统
		// operation.login(browser,"www.einmatrix.com/#!/signin","test00","test00");//生产环境
		operation.ChooseStudy(browser, 3);
		Thread.sleep(500);
		
		int caseNumber = 205;

		
		
		operation.ChooseOperation(browser, "病例管理");
		operation.ChooseOperation(browser, "首页");
//		operation.addBatchCase(browser,caseNumber,1,"mameng"); //批量添加病例
//		operation.addRandomCase(browser,"mameng2");//随机添加病例
		operation.addBatchCase(browser,4,"mameng");
		String addCaseid = operation.addRandomCase(browser,"mameng2");
		operation.selectCaseStage(browser, addCaseid, 1, "mameng2");
//		operation.selectCaseStage(browser, ""+caseNumber, 1, "mameng2");
//		operation.searchCase(browser,"202");//查找病例
//		operation.selectCaseStage(browser,"102",1,"m2");//选择病例阶段-生产环境
		
		operation.stopCaseAndCheck(browser);//终止病例以及检查

//		 browser.findElement(By.partialLinkText("mameng2")).click();//选择分组
//		 operation.getCaseState(browser,"202");
//		 operation.checkCaseState(browser,"202","填写中");
//		 operation.getCaseStageState(browser,"202",1);
//		 operation.checkCaseStageState(browser,"202",1,"填写中");

		String textValue = "测试text";
		String textareaValue = "测试textarea";
//		operation.inputRadio(browser,2);
//		operation.inputRadioComplete(browser);
//		operation.inputCheckbox(browser);
//		operation.inputText(browser, textValue);
//		operation.inputTextarea(browser, textareaValue);
//		operation.inputNumber(browser, "12");
//		operation.inputSelect(browser);
//		operation.inputScale(browser);
//		operation.inputDatetimeDate(browser, 2016, 8, 30);
//		operation.inputDatetimeTime(browser);
//		
//		operation.findCaseBox(browser, 1, 1, 3);
//		operation.inputUkn(browser);
//		operation.inputNa(browser);
//		checkNumber(browser);
		
//		operation.caseSaveButtonAndNext(browser);

//		operation.findCaseBox(browser, 1, 1, 4);
//		operation.inputList(browser, 2);
//
//		operation.caseSaveButtonAndNext(browser);
//		operation.inputTable(browser,2);
//		
//		operation.caseSaveButtonAndNext(browser);
//		operation.inputMatrix(browser);
//		
//		operation.caseSaveButton(browser);


//		 operation.checkSection(browser);
//		 operation.checkRadio(browser);
//		 operation.checkCheckbox(browser);
//		 operation.checkText(browser, textValue);
//		 operation.checkTextarea(browser,textareaValue);
//		 operation.checkNumber(browser,"12");
//		 operation.checkSelect(browser);
//		 operation.checkScale(browser);
//		 operation.checkDateTime(browser);
		


//		 operation.caseStageSubmitButton(browser);

	}
	
	
	//复杂问题的测试
	public static void inputter_test2() throws InterruptedException {
		
		WebDriver browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		operation.login(browser, "test00", "test00");// 内测系统
		// operation.login(browser,"www.einmatrix.com/#!/signin","test00","test00");//生产环境
		operation.ChooseStudy(browser, 3);
		Thread.sleep(500);
		
		int caseNumber = 115;
		
		
		operation.ChooseOperation(browser, "病例管理");
//		operation.batchAddCase(browser,caseNumber,1,"mameng"); //添加病例
		// operation.searchCase(browser,"202");//查找病例
		// operation.selectCaseStage(browser,"102",1,"m2");//选择病例阶段-生产环境
		operation.selectCaseStage(browser, ""+caseNumber, 1, "mameng");
		operation.findCaseBox(browser, 1, 1, 7);
		
		
//		int m = new Random().nextInt(3);
		operation.inputRadio(browser,1);
		operation.inputRadioComplete(browser);
		System.out.println("1");
		operation.inputList(browser, 2);
		operation.inputTable(browser,2);
//		operation.inputMatrix(browser);
		operation.inputText(browser, "测试text");
		System.out.println("2");
		operation.inputScale(browser);
		System.out.println("ok");
	}


    }




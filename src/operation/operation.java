package operation;

import org.apache.xpath.operations.Div;
import org.apache.xpath.operations.Equals;
import org.eclipse.jetty.util.statistic.SampleStatistic;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class operation {
	public static void login(WebDriver browser, String uesrname, String password) throws InterruptedException {
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 这行代码必须有，不然就会出现找不到控件
		browser.get("http://192.168.100.139:3000/#!/signin");
		browser.findElement(By.xpath("//input[@ng-model='email_phone']")).sendKeys(uesrname);
		browser.findElement(By.xpath("//input[@ng-model='password']")).sendKeys(password);
		browser.findElement(By.xpath("//input[@value='登录']")).click();
		System.out.println("已登录，账号为：" + uesrname);
		Thread.sleep(500);
	}

	public static void login(WebDriver browser, String url, String uesrname, String password)
			throws InterruptedException {
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 这行代码必须有，不然就会出现找不到控件
		browser.get("http://" + url);
		browser.findElement(By.xpath("//input[@ng-model='email_phone']")).sendKeys(uesrname);
		browser.findElement(By.xpath("//input[@ng-model='password']")).sendKeys(password);
		browser.findElement(By.xpath("//input[@value='登录']")).click();
		System.out.println("已登录，账号为：" + uesrname);
		Thread.sleep(500);
	}

	// 选择项目 输入为int number 代表第几个项目
	public static void ChooseStudy(WebDriver browser, int number) throws InterruptedException {
		List<WebElement> sumHtmls = browser.findElements(By.cssSelector(".study.ng-scope"));
		sumHtmls.get(number - 1).click();
		int n = number - 1;
		System.out.println("已选择第" + n + "个项目");
	}

	// 选择研究项目(重载) 填入 要选择的项目的名称 实现 点击包含此名称的项目操作
	public static void ChooseStudy(WebDriver browser, String index) throws InterruptedException {
		String xpathString = "//span[contains(text()," + "'" + index + "'" + ")]";
		browser.findElement(By.xpath(xpathString)).click(); // 也就是这个字符中有空格等
	}

	// 选择项目中功能
	public static void ChooseOperation(WebDriver browser, String OperationName) throws InterruptedException {
		List<WebElement> dropdown = browser.findElements(By.xpath(".//ul[@class='nav navbar-nav ng-scope']"));
		WebElement user = browser.findElement(By.xpath(".//div[@class='navbar-right dropdown']"));
		Actions builder = new Actions(browser);
		if (OperationName.contains("首页")) {
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.detail']")).click();
			System.out.println("已选择功能：返回首页");
		}
		//录入员的功能
		else if (OperationName.equals("病例管理")) {
			builder.moveToElement(dropdown.get(1)).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.cases']")).click();
			System.out.println("已选择功能：病例管理");
		} else if (OperationName.equals("添加病例")) {
			builder.moveToElement(dropdown.get(1)).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.cases']")).click();
			Thread.sleep(1000);
			browser.findElement(By.cssSelector(".study-set-btn")).click();
			System.out.println("已选择功能：添加病例");
		}else if (OperationName.equals("表单任务")) {
			builder.moveToElement(dropdown.get(2)).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.caseEvents']")).click();
			System.out.println("已选择功能：表单任务");
		} //个人信息页面
		else if (OperationName.contains("个人信息")) {
			builder.moveToElement(user).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.profile']")).click();
			System.out.println("已选择功能：查看个人信息");
		}
		else if (OperationName.contains("登出")) {
			builder.moveToElement(user).perform();
			browser.findElement(By.xpath(".//a[@ng-click='vm.logout()']")).click();
			System.out.println("已选择功能：登出");
		}
		// 下面是项目管理员的操作
		else if (OperationName.equals("机构设置")) {
			builder.moveToElement(dropdown.get(1)).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.sites']")).click();
			System.out.println("已选择功能：机构设置");
		} else if (OperationName.equals("人员设置")) {
			builder.moveToElement(dropdown.get(1)).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.roles({site_id:null})']")).click();
			System.out.println("已选择功能：人员设置");
		} else if (OperationName.equals("表单管理")) {
			builder.moveToElement(dropdown.get(1)).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.crfs']")).click();
			System.out.println("已选择功能：表单管理");
		} else if (OperationName.equals("分组和阶段管理")) {
			builder.moveToElement(dropdown.get(1)).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.groupevents']")).click();
			System.out.println("已选择功能：分组和阶段管理");
		}else if (OperationName.equals("术语字典")) {
			builder.moveToElement(dropdown.get(3)).perform();
			browser.findElement(By.xpath(".//a[@ui-sref='m.study.dictionaries']")).click();
			System.out.println("已选择功能：查看术语字典");
		}
		Thread.sleep(5000);
	}

	// 在病例管理界面添加病例（）
	public static void addCase(WebDriver browser, String ObjectNumber, String Name, String idNumber, String centreName,
			String groupName) throws InterruptedException {
		Thread.sleep(1000);
		browser.findElement(By.cssSelector(".study-set-btn")).click();
		Thread.sleep(1000);
		WebElement popWindows = browser.findElement(By.xpath("//div[@class='modal-body case-container']"));
		popWindows.findElement(By.xpath(".//input[@name='uid']")).sendKeys(ObjectNumber + "");
		popWindows.findElement(By.xpath(".//input[@name='sname']")).sendKeys(Name);
		popWindows.findElement(By.xpath(".//input[@name='sid']")).sendKeys(idNumber);
		new Select(popWindows.findElement(By.xpath(".//select[@name='siteid']"))).selectByVisibleText(centreName);
		new Select(popWindows.findElement(By.xpath(".//select[@name='groupid']"))).selectByVisibleText(groupName);
		browser.findElement(By.cssSelector(".modal-footer"))
				.findElement(By.xpath(".//input[@type='button' and @value='添加']")).click();
		Thread.sleep(1000);
	}

	
	// 在病例管理界面添加病例 编号为当前时间
	public static String addRandomCase(WebDriver browser, String groupname) throws InterruptedException {

		String caseid = System.currentTimeMillis() + "";
		String idNumber = "10";
		idNumber += caseid;

		operation.addCase(browser, caseid, "测试" + caseid, idNumber, "测试机构", groupname);
		Thread.sleep(500);
		browser.findElement(By.cssSelector(".modal-confirm-btn")).click();
		Thread.sleep(500);
		System.out.println("成功添加病例，编号为：" + caseid);
		return caseid;
	}
	
	// 在病例管理界面批量添加病例
		public static void addBatchCase(WebDriver browser, int i, int j, String groupname) throws InterruptedException {
			int a = i;
			for (; i < a + j; i++) {
				String idNumber = "100000000000";
				String ObjectNumber = "";
				idNumber += i;
				ObjectNumber += i;

				operation.addCase(browser, ObjectNumber, "测试" + i, idNumber, "测试机构", groupname);
				Thread.sleep(500);
				browser.findElement(By.cssSelector(".modal-confirm-btn")).click();
				Thread.sleep(500);
				System.out.println("成功添加病例，编号为：" + i);
			}
		}
	
	public static void addBatchCase(WebDriver browser, int n, String groupname) throws InterruptedException {
		
		for (int i=0;i<n; i++) {
			addRandomCase(browser,groupname);
		}
	}

	// 选择病例阶段以便进行填写 (研究对象编号，阶段的序号,分组）
	public static void selectCaseStage(WebDriver browser, String objectNumber, int stageNumber, String groupName)
			throws InterruptedException {
		Thread.sleep(500);
		browser.findElement(By.partialLinkText(groupName)).click();
		Thread.sleep(500);
		List<WebElement> trlist = browser.findElements(By.xpath("//tbody/tr"));
		List<WebElement> tdList = null;
		int flag = 0;
		for (WebElement tr : trlist) {
			tdList = tr.findElements(By.xpath("./td"));
			if (tdList.get(1).getText().equals(objectNumber)) {
				flag = 1;
				break;
			}
		}
		if (flag == 0) {
			System.out.println("所要求的病例阶段未能找到");
			return;
		}
		Thread.sleep(1000);
		tdList.get(5 + stageNumber).findElement(By.xpath("./a")).click();
		System.out.println("已成功找到要求的病例阶段，编号：" + objectNumber + " 第" + stageNumber + "阶段");

		Thread.sleep(500);
	}

	// 病例的保存按钮
	public static void caseSaveButton(WebDriver browser) throws InterruptedException {
		Thread.sleep(500);
		browser.findElement(By.xpath("//button[@ng-click='vm.save()']")).click();
		try {
			Thread.sleep(500);
			WebElement popWindows = browser.findElement(By.xpath("//div[@class='error-list-container ng-scope']"));
			Thread.sleep(500);
			popWindows.findElement(By.partialLinkText("继续保存")).click();

			Thread.sleep(1000);
			System.out.println("当前页面未全部填写，继续保存");

		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("当前页面已全部填写");
		}

		WebElement savetip = browser.findElement(By.xpath(".//div[@ng-repeat='alert in alerts']"));
		System.out.println(savetip.getText());
	}

	// 保存并到下一表单
	public static void caseSaveButtonAndNext(WebDriver browser) throws InterruptedException {
		Thread.sleep(500);
		browser.findElement(By.xpath("//button[@class='webapp-btn next']")).click();
		Thread.sleep(500);
		System.out.println("保存并到下一个表单");
		try {
			WebElement popWindows = browser.findElement(By.xpath("//div[@class='error-list-container ng-scope']"));
			Thread.sleep(500);
			popWindows.findElement(By.partialLinkText("继续保存")).click();
			System.out.println("当前页面未全部填写，已继续保存");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("当前页面已全部填写");
		}
		WebElement saveAndNexttip = browser.findElement(By.cssSelector("div[ng-transclude]"));
		System.out.println(saveAndNexttip.getText());

	}

	// 病例的阶段性提交
	public static void caseStageSubmitButton(WebDriver browser) throws InterruptedException {
		Thread.sleep(1000);
		browser.findElement(By.partialLinkText("提交")).click();
		Thread.sleep(500);
		WebElement popWindows = browser.findElement(By.xpath("//div[@class='modal-dialog modal-sm']"));
		Thread.sleep(500);
		popWindows.findElement(By.xpath("//button[@ng-click='vm.close()']")).click();
		System.out.println("已点击提交按钮");
	}

	// 终止病例--病例填写页面
	public static void stopCase(WebDriver browser) throws InterruptedException {
		browser.findElement(By.linkText("终止病例填写")).click();
		Thread.sleep(1000);
		// 比较复杂了。需要填写时间等原因
		WebElement body = browser.findElement(By.xpath("//div[@class='modal-body endcase-container']"));
		WebElement bottom = browser.findElement(By.xpath("//div[@class='modal-footer']"));

		body.findElement(By.xpath(".//input[@type='text' and @ng-model='date']")).click();
		body.findElements(By.cssSelector(".picker__day.picker__day--infocus")).get(15).click();// 退出日期

		WebElement selectElement = body.findElement(By.xpath(".//select[@name='select']"));// 在哪个阶段退出
		new Select(selectElement).selectByIndex(1);
		body.findElement(By.xpath(".//input[@type='checkbox']")).click();// 退出原因
		bottom.findElement(By.xpath(".//button[@data-dismiss='modal' and @ng-click='confirm()']")).click();// 确认

		System.out.println("病例已终止");
	}

	// 终止病例并检查状态
	public static void stopCaseAndCheck(WebDriver browser) throws InterruptedException {
		stopCase(browser);

		WebElement infobox = browser.findElement(By.xpath("//div[@class='general-info-box']"));
		WebElement number = infobox.findElement(By.xpath("./ul/li/span[3]"));
		String caseId = number.getText();// 获取病例ID

		checkCaseButton(browser, "终止");

		Thread.sleep(1000);
		ChooseOperation(browser, "病例管理");// 回到病历管理界面

		browser.findElement(By.partialLinkText("mameng2")).click();// 选择分组
		// getCaseState(browser,caseId);
		operation.checkCaseState(browser, caseId, "已终止");

	}

	// 搜索病例，输入为病例编号
	public static void searchCase(WebDriver browser, String caseId) throws InterruptedException {

		WebElement searchButton = browser.findElement(By.xpath(".//a[@ng-click='vm.searchCasePopup()']"));
		searchButton.click();
		WebElement searchBox = browser.findElement(By.cssSelector(".modal-content"));
		searchBox.findElement(By.xpath(".//input[@ng-model='vm.searchCase.uid']")).sendKeys(caseId);
		searchBox.findElement(By.partialLinkText("搜索")).click();

	}

	// 获取病例的状态 输入为病例编号
	public static String getCaseState(WebDriver browser, String caseId) throws InterruptedException {
		// Thread.sleep(500);
		// browser.findElement(By.partialLinkText(groupNumber)).click();
		Thread.sleep(500);
		List<WebElement> trlist = browser.findElements(By.xpath("//tbody/tr"));
		List<WebElement> tdList = null;
		String sate = null;
		for (WebElement tr : trlist) {
			tdList = tr.findElements(By.xpath("./td"));
			if (tdList.get(1).getText().equals(caseId)) {
				WebElement caseState = tdList.get(2).findElement(By.xpath("./span"));
				sate = caseState.getText();
				System.out.println("编号为" + caseId + "的病例状态为[" + sate + "]");
				// if (caseState.getAttribute("ng-if").contains("ONGOING")) {
				// System.out.println("编号为" + caseId + "的病例状态为[填写中]");
				// }
			}
		}
		return sate;
	}

	public static void checkCaseState(WebDriver browser, String caseId, String expectState)
			throws InterruptedException {
		String actualState = getCaseState(browser, caseId);
		if (actualState.equals(expectState)) {
			System.out.println("编号为" + caseId + "的病例状态检查正确");
		} else {
			System.out.println("注意错误啦");
			System.out.println("编号为" + caseId + "的病例状态检查不正确");
		}

	}

	// 获取病例的阶段状态 输入为病例编号，阶段数
	public static String getCaseStageState(WebDriver browser, String caseId, int stageNumber)
			throws InterruptedException {
		// Thread.sleep(500);
		// browser.findElement(By.partialLinkText(groupNumber)).click();
		Thread.sleep(500);
		List<WebElement> trlist = browser.findElements(By.xpath("//tbody/tr"));
		List<WebElement> tdList = null;
		String state1 = null;
		for (WebElement tr : trlist) {
			tdList = tr.findElements(By.xpath("./td"));
			if (tdList.get(1).getText().equals(caseId)) {
				WebElement caseStage1 = tdList.get(5 + stageNumber).findElement(By.xpath(".//button"));
				state1 = caseStage1.getText();
				System.out.println("编号为" + caseId + "的病例的第" + stageNumber + "个阶段的状态为[" + state1 + "]");

				// 以下为试图再检查病例填写页面的阶段状态，有报错
				// caseStage1.click();
				// Thread.sleep(2000);
				// List <WebElement> caseStage2 =
				// browser.findElements(By.cssSelector(".event-state.ng-binding"));
				// String state2 = caseStage2.get(stageNumber-1).getText();
				// System.out.println("编号为" + caseId + "的病例的第" + stageNumber +
				// "个阶段的状态2为[" + state2 + "]");
				// browser.navigate().back();//后退
				// Thread.sleep(2000);
			}
		}
		return state1;
	}

	// 检查病例的阶段状态 输入为病例编号，阶段数，预期状态
	public static void checkCaseStageState(WebDriver browser, String caseId, int stageNumber, String expectState)
			throws InterruptedException {
		String actualState = getCaseStageState(browser, caseId, stageNumber);
		if (actualState.equals(expectState)) {
			System.out.println("编号为" + caseId + "的病例的第" + stageNumber + "个阶段的状态检查正确");
		} else {
			System.out.println("注意错误啦");
			System.out.println("编号为" + caseId + "的病例的第" + stageNumber + "个阶段的状态检查不正确");
		}
	}

	// 病例填写页面--检查button的状态
	public static void checkCaseButton(WebDriver browser, String caseStageState) throws InterruptedException {
		Thread.sleep(1000);
		WebElement savebox = browser.findElement(By.xpath(".//div[@class='save-box ng-scope']"));
		WebElement saveButton = savebox.findElement(By.xpath("./button[1]"));
		WebElement saveAndNextButton = savebox.findElement(By.xpath("./button[2]"));
		if (saveButton.getAttribute("class").contains("disabled")) {
			System.out.println("保存按钮已不可用");
		}
		if (saveAndNextButton.getAttribute("class").contains("disabled")) {
			System.out.println("保存并到下一个表单按钮已不可用");
		}
		if (caseStageState.contains("提交")) {
			WebElement submitButton = browser.findElement(By.linkText("提交"));
			if (!submitButton.getAttribute("class").contains("disabled")) {

			}
		}
		if (caseStageState.contains("终止")) {
			WebElement stopButton = browser.findElement(By.xpath(".//a[@class='ended webapp-btn ng-scope']"));
			System.out.println("终止按钮已变为:" + stopButton.getText());
		}
	}

	public static void findCaseBox(WebDriver browser, int stageNo, int crfNo, int sectionNo)
			throws InterruptedException {
		List<WebElement> caseStages = browser.findElements(By.cssSelector(".event-detail-box.ng-scope"));
		System.out.println("该病例共有:" + caseStages.size() + "个阶段");
		WebElement caseStage = caseStages.get(stageNo - 1);
		caseStage.findElement(By.xpath("./div")).click();
		List<WebElement> caseStageCrfs = browser.findElements(By.cssSelector(".crf-container.ng-scope"));
		System.out.println("当前阶段共有:" + caseStageCrfs.size() + "个表单");
		WebElement caseStageCrf = caseStageCrfs.get(crfNo - 1);
		caseStageCrf.findElement(By.xpath("./div")).click();
		List<WebElement> caseStageCrfSections = browser
				.findElements(By.xpath(".//li[@ng-repeat='section in crf.sections track by $index']"));
		System.out.println("当前表单下共有:" + caseStageCrfSections.size() + "个表");
		WebElement currentSection = caseStageCrfSections.get(sectionNo - 1);
		currentSection.click();
		// System.out.println("当前section为：" +
		// currentSection.findElement(By.xpath("./a")).getText());
		Thread.sleep(1000);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 以下为输入函数///
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// radio 默认点击第一个,包含其他项则选择其他项，并填入"测试othertext"
	public static void inputRadio(WebDriver browser) throws InterruptedException {
		try {
			List<WebElement> radios = browser.findElements(By.cssSelector(".radio-inner-container"));

			for (int i = 0; i < radios.size(); i++) {
				List<WebElement> radioOptions = radios.get(i).findElements(By.xpath("./div"));
				if (!radios.get(i).isSelected()) {
					radioOptions.get(0).click();
				}
				for (WebElement radioOption : radioOptions) {
					if (radioOption.getAttribute("ng-if") != null) {
						radioOption.click();
						inputOtherText(browser, "测试othertext");
					}
				}

			}
		} catch (NoSuchElementException e) {
		}
	}

	// radio 输入int number 为选择第几个选项
	public static void inputRadio(WebDriver browser, int number) throws InterruptedException {
		List<WebElement> radios = browser.findElements(By.cssSelector(".radio-inner-container"));

		for (int i = 0; i < radios.size(); i++) {
			List<WebElement> radioOptions = radios.get(i).findElements(By.xpath("./div/label"));
			if (radioOptions.size() < number) {
				radioOptions.get(radioOptions.size() - 1).click();
			} else {
				radioOptions.get(number - 1).click();
			}
		}
		inputOtherText(browser, "测试othertext");
	}

	// radio补全 默认选择第一个 也可用于子控件中radio的选择
	public static void inputRadioComplete(WebDriver browser) {
		List<WebElement> radioContainers = browser.findElements(By.cssSelector(".radio-inner-container"));
		for (int i = 0; i < radioContainers.size(); i++) {
			List<WebElement> radios = radioContainers.get(i).findElements(By.xpath("./div/label/input"));
			int flag = 0;
			for (WebElement radio : radios) {
				if (radio.isSelected()) {
					flag = 1;
				}
			}
			if (flag == 0) {
				radioContainers.get(i).findElement(By.xpath("./div/label")).click();
			}
		}

	}

	// radio （重载） 子页面下的radio输入 默认点击第一个
	public static void inputRadio(WebDriver browser, WebElement elementParent) throws InterruptedException {
		try {
			List<WebElement> radios = elementParent.findElements(By.cssSelector(".radio-inner-container"));
			for (int i = 0; i < radios.size(); i++) {
				List<WebElement> radioOptions = radios.get(i).findElements(By.xpath("./div"));
				if (!radios.get(i).isSelected()) {
					radioOptions.get(0).click();
				}
				for (WebElement radioOption : radioOptions) {
					if (radioOption.getAttribute("ng-if") != null) {
						radioOption.click();
						inputOtherText(browser, elementParent, "测试othertext");
					}
				}
			}
		} catch (NoSuchElementException e) {
		}
	}

	// matrix-radio 每个选项轮流点击（即默认选择最后一个选项）
	public static void inputMatrixRadio(WebDriver browser) {
		try {
			List<WebElement> radioContainers = browser
					.findElements((By.xpath(".//div[@class='widget-radio ng-scope' and @ng-switch-when='radio']")));// 使用css对复合查找方便些

			for (WebElement radioContainer : radioContainers) {
				List<WebElement> radios = radioContainer.findElements(By.xpath("./label/input"));
				int flag = 0;
				for (WebElement radio : radios) {
					if (radio.isSelected()) {
						flag = 1;
					}
				}
				if (flag == 0) {
					radioContainer.findElement(By.xpath("./label")).click();
				}
			}
		} catch (NoSuchElementException e) {
		}
	}

	// matrix-radio （重载）
	public static void inputMatrixRadio(WebDriver browser, WebElement elementParent) {
		try {
			List<WebElement> radioContainers = elementParent
					.findElements((By.xpath(".//div[@class='widget-radio ng-scope' and @ng-switch-when='radio']")));// 使用css对复合查找方便些
			for (WebElement radioContainer : radioContainers) {
				List<WebElement> radios = radioContainer.findElements(By.xpath("./label/input"));
				int flag = 0;
				for (WebElement radio : radios) {
					if (radio.isSelected()) {
						flag = 1;
					}
				}
				if (flag == 0) {
					radioContainer.findElement(By.xpath("./label")).click();
				}
			}
		} catch (NoSuchElementException e) {
		}
	}

	// checkbox 默认全选 并在有其他项的情况下填入"测试othertext"
	public static void inputCheckbox(WebDriver browser) throws InterruptedException {
		List<WebElement> checkboxs = browser.findElements(By.cssSelector(".checkbox-inner-container"));
		for (WebElement checkbox : checkboxs) {
			List<WebElement> checkboxOptions = checkbox.findElements(By.xpath("./div/label"));
			for (int i = 0; i < checkboxOptions.size(); i++) {
				checkboxOptions.get(i).click();
			}
		}
		inputOtherText(browser, "测试othertext");

	}

	// checkbox 输入int a，int b 为:选择从a开始到b的选项
	public static void inputCheckbox(WebDriver browser, int a, int b) throws InterruptedException {
		List<WebElement> checkboxs = browser.findElements(By.cssSelector(".checkbox-inner-container"));
		for (WebElement checkbox : checkboxs) {
			List<WebElement> checkboxOptions = checkbox.findElements(By.xpath("./div/label"));
			for (int i = a - 1; i < b; i++) {
				checkboxOptions.get(i).click();
			}
		}
	}

	// checkbox （重载） 子页面下的checkbox输入 默认全选
	public static void inputCheckbox(WebDriver browser, WebElement elementParent) throws InterruptedException {
		try {
			List<WebElement> checkboxs = elementParent.findElements(By.cssSelector(".checkbox-inner-container"));
			for (WebElement checkbox : checkboxs) {
				List<WebElement> checkboxOptions = checkbox.findElements(By.xpath("./div/label"));
				for (int i = 0; i < checkboxOptions.size(); i++) {
					checkboxOptions.get(i).click();
					Thread.sleep(500);

				}
			}
		} catch (NoSuchElementException e) {
		}
	}

	// matrix--chexkbox,包含全局的普通的checkbox
	public static void inputMatrixCheckbox(WebDriver browser) throws InterruptedException {
		try {
			List<WebElement> checkboxs = browser.findElements(
					(By.xpath(".//div[@class='widget-checkbox ng-scope' and @ng-switch-when='checkbox']")));
			for (int i = 0; i < checkboxs.size(); i++) {
				checkboxs.get(i).click();
				Thread.sleep(500);
			}
		} catch (NoSuchElementException e) {
		}
	}

	// matrix--chexkbox，（重载） 默认全选
	public static void inputMatrixCheckbox(WebDriver browser, WebElement elementParent) throws InterruptedException {
		try {
			List<WebElement> checkboxs = elementParent.findElements(
					(By.xpath(".//div[@class='widget-checkbox ng-scope' and @ng-switch-when='checkbox']")));
			for (int i = 0; i < checkboxs.size(); i++) {
				checkboxs.get(i).click();
				Thread.sleep(500);
			}
		} catch (NoSuchElementException e) {
		}
	}

	// text 输入为string类型
	public static void inputText(WebDriver browser, String value) throws InterruptedException {
		List<WebElement> inputTexts = browser.findElements(By.xpath(".//input[@type='text' and @ng-model='vm.value']"));

		for (int i = 0; i < inputTexts.size(); i++) {
			if (inputTexts.get(i).getAttribute("autocomplete").equals("off")) // text是属于字典的情况
			{
				inputDictText(browser);
			} else {
				inputTexts.get(i).clear();
				inputTexts.get(i).sendKeys(value);
			}
		}
	}

	// 术语字典 随机输入一个字母，然后自动匹配
	public static void inputDictText(WebDriver browser) throws InterruptedException {
		List<WebElement> inputDictTexts = browser
				.findElements(By.xpath(".//input[@type='text' and @autocomplete='off']"));
		for (WebElement inputDictText : inputDictTexts) {
			String a_z = "abcdefghijklmnopqrstuvwxyz";
			Random n = new Random();
			int m = n.nextInt(a_z.length());
			char l = a_z.charAt(m);
			String value = "" + l;
			inputDictText.clear();
			inputDictText.sendKeys(value);
			Thread.sleep(1000);
			inputDictText.sendKeys(Keys.DOWN);
			inputDictText.sendKeys(Keys.ENTER);
		}
	}

	// 术语字典 （重载） 随机输入一个字母，然后自动匹配
	public static void inputDictText(WebDriver browser, WebElement elementParent) throws InterruptedException {
		List<WebElement> inputDictTexts = elementParent
				.findElements(By.xpath(".//input[@type='text' and @autocomplete='off']"));
		for (WebElement inputDictText : inputDictTexts) {
			String a_z = "abcdefghijklmnopqrstuvwxyz";
			Random n = new Random();
			int m = n.nextInt(a_z.length());
			char l = a_z.charAt(m);
			String value = "" + l;
			inputDictText.clear();
			inputDictText.sendKeys(value);
			Thread.sleep(1000);
			inputDictText.sendKeys(Keys.DOWN);
			inputDictText.sendKeys(Keys.ENTER);
		}
	}

	// text （重载） 子页面下的Text输入string
	public static void inputText(WebDriver browser, WebElement elementParent, String value)
			throws InterruptedException {

		List<WebElement> inputTexts = elementParent
				.findElements(By.xpath(".//input[@type='text' and @ng-model='vm.value']"));
		for (int i = 0; i < inputTexts.size(); i++) {
			if (inputTexts.get(i).getAttribute("autocomplete").equals("off")) {
				inputDictText(browser, elementParent);
			} else {
				inputTexts.get(i).clear();
				inputTexts.get(i).sendKeys(value);
			}
		}
	}

	// 填写radio/checkbox中的other项的text值
	public static void inputOtherText(WebDriver browser, String value) throws InterruptedException {
		for (WebElement inputOtherText : browser
				.findElements(By.xpath(".//input[@type='text' and @ng-model='vm.otherValue']"))) {
			inputOtherText.clear();
			inputOtherText.sendKeys(value);
		}
	}

	// 填写radio/checkbox中的other项的text值 (重载)
	public static void inputOtherText(WebDriver browser, WebElement elementParent, String value)
			throws InterruptedException {
		for (WebElement inputOtherText : elementParent
				.findElements(By.xpath(".//input[@type='text' and @ng-model='vm.otherValue']"))) {
			inputOtherText.clear();
			inputOtherText.sendKeys(value);
		}
	}

	// textarea 输入类型为string
	public static void inputTextarea(WebDriver browser, String value) throws InterruptedException {
		for (WebElement inputTextarea : browser
				.findElements(By.cssSelector("textarea.textarea-content.ng-pristine.ng-valid")))

		{
			inputTextarea.clear();
			inputTextarea.sendKeys(value);
		}
	}

	// Textarea （重载） 子页面下的Textarea输入 string
	public static void inputTextarea(WebDriver browser, WebElement elementParent, String value)
			throws InterruptedException {
		for (WebElement inputTextarea : elementParent
				.findElements(By.cssSelector("textarea.textarea-content.ng-pristine.ng-valid")))

		{
			inputTextarea.clear();
			inputTextarea.sendKeys(value);
		}
	}

	// number 输入类型为string
	public static void inputNumber(WebDriver browser, String value) {
		for (WebElement inputNumber : browser
				.findElements(By.xpath(".//input[@type='number' and @ng-model='vm.value']"))) {
			if (inputNumber.getAttribute("readonly") == null) {
				inputNumber.clear();
				inputNumber.sendKeys(value);
			}
		}
	}

	// number 输入类型为int
	public static void inputNumber(WebDriver browser, int value) {
		for (WebElement inputNumber : browser
				.findElements(By.xpath(".//input[@type='number' and @ng-model='vm.value']"))) {
			if (inputNumber.getAttribute("readonly") == null) {
				inputNumber.clear();
				inputNumber.sendKeys("" + value);
			}
		}
	}

	// number （重载） 子页面下的number输入 string
	public static void inputNumber(WebDriver browser, WebElement elementParent, String value) {
		for (WebElement inputNumber : elementParent
				.findElements(By.xpath(".//input[@type='number' and @ng-model='vm.value']"))) {
			inputNumber.clear();
			inputNumber.sendKeys(value);
		}
	}

	// number （重载） 子页面下的number输入 int
	public static void inputNumber(WebDriver browser, WebElement elementParent, int value) {
		for (WebElement inputNumber : elementParent
				.findElements(By.xpath(".//input[@type='number' and @ng-model='vm.value']"))) {
			inputNumber.clear();
			inputNumber.sendKeys("" + value);
		}
	}

	// select 随机选择
	public static void inputSelect(WebDriver browser) throws InterruptedException {
		List<WebElement> selectContainers = browser.findElements(By.xpath(".//select[@ng-model='vm.select']"));
		for (WebElement selectContainer : selectContainers) {
			Random n = new Random();
			int number = n.nextInt(selectContainers.size());
			new Select(selectContainer).selectByIndex(number + 1);
			System.out.println(number + 1);
		}

	}

	// select 输入int number 为选择第几个选项
	public static void inputSelect(WebDriver browser, int number) throws InterruptedException {
		try {
			List<WebElement> selectContainers = browser.findElements(By.xpath(".//select[@ng-model='vm.select']"));
			for (WebElement selectContainer : selectContainers) {
				new Select(selectContainer).selectByIndex(number);
			}
		} catch (NoSuchElementException e) {
		}

	}

	// select （重载） 子页面下的select 随机选择
	public static void inputSelect(WebDriver browser, WebElement elementParent) throws InterruptedException {
		try {
			List<WebElement> selectContainers = elementParent
					.findElements(By.xpath(".//select[@ng-model='vm.select']"));
			for (WebElement selectContainer : selectContainers) {
				Random n = new Random();
				int number = n.nextInt(selectContainers.size());
				new Select(selectContainer).selectByIndex(number + 1);
				System.out.println(number + 1);
			}
		} catch (NoSuchElementException e) {
		}

	}

	// scale 随机选择
	public static void inputScale(WebDriver browser) throws InterruptedException {
		List<WebElement> scales = browser.findElements(By.xpath(".//div[@class='widget-scale-group']"));
		for (WebElement scale : scales) {
			List<WebElement> scaleOptions = scale.findElements(By.xpath("./div"));
			Random n = new Random();
			int number = n.nextInt(scaleOptions.size());
			scaleOptions.get(number).click();
		}
	}

	// scale 输入为int 选择第几个输入
	public static void inputScale(WebDriver browser, int number) throws InterruptedException {
		List<WebElement> scales = browser.findElements(By.xpath(".//div[@class='widget-scale-group']"));
		for (WebElement scale : scales) {
			scale.findElements(By.xpath("./div")).get(number - 1).click();
		}
	}

	// scale （重载） 子页面下的scale 随机选择
	public static void inputScale(WebDriver browser, WebElement elementParent) throws InterruptedException {
		try {
			List<WebElement> scales = elementParent.findElements(By.xpath(".//div[@class='widget-scale-group']"));
			for (WebElement scale : scales) {
				List<WebElement> scaleOptions = scale.findElements(By.xpath("./div"));
				Random n = new Random();
				int number = n.nextInt(scaleOptions.size());
				scaleOptions.get(number).click();
			}
		} catch (NoSuchElementException e) {
		}
	}

	// datetime的date 默认输入当年当月的15号
	public static void inputDatetimeDate(WebDriver browser) throws InterruptedException {
		List<WebElement> datetimes = browser.findElements(By.className("widget-datetime"));
		for (int i = 0; i < datetimes.size(); i++) {
			if (!datetimes.get(i).findElement(By.xpath("./parent::div")).getAttribute("class").contains("ng-hide")) // 是否隐藏
			{
				if (datetimes.get(i).findElement(By.xpath("./input")).getAttribute("placeholder")
						.equals("yyyy/mm/dd")) {
					datetimes.get(i).click();
					Thread.sleep(1000);
					List<WebElement> datewindow = datetimes.get(i)
							.findElements(By.cssSelector(".picker__day.picker__day--infocus"));// 这个竟然找的是儿子的
					datewindow.get(14).click();
					Thread.sleep(1000);
				}
			}

		}
	}

	// datetime的date （重载） 默认输入当年当月的15号
	public static void inputDatetimeDate(WebDriver browser, WebElement elementParent) throws InterruptedException {
		List<WebElement> datetimes = elementParent.findElements(By.className("widget-datetime"));
		for (int i = 0; i < datetimes.size(); i++) {
			if (!datetimes.get(i).findElement(By.xpath("./parent::div")).getAttribute("class").contains("ng-hide")) // 是否隐藏
			{
				if (datetimes.get(i).findElement(By.xpath("./input")).getAttribute("placeholder")
						.equals("yyyy/mm/dd")) {
					datetimes.get(i).click();
					Thread.sleep(1000);
					List<WebElement> datewindow = datetimes.get(i)
							.findElements(By.cssSelector(".picker__day.picker__day--infocus"));// 这个竟然找的是儿子的
					datewindow.get(14).click();
					Thread.sleep(1000);
				}
			}

		}
	}

	// datetime 输入年，月，日
	public static void inputDatetimeDate(WebDriver browser, int year, int month, int date) throws InterruptedException {
		List<WebElement> datetimes = browser.findElements(By.className("widget-datetime"));
		for (int i = 0; i < datetimes.size(); i++) {
			if (!datetimes.get(i).findElement(By.xpath("./parent::div")).getAttribute("class").contains("ng-hide")) // 是否隐藏
			{
				if (datetimes.get(i).findElement(By.xpath("./input")).getAttribute("placeholder")
						.equals("yyyy/mm/dd")) {
					datetimes.get(i).click();
					Thread.sleep(1000);
					WebElement datewindow = datetimes.get(i).findElement(By.xpath("./div/div/div/div/div"));// 这个竟然找的是儿子的

					WebElement dateyear = datewindow.findElement(By.xpath(".//select[@class='picker__select--year']"));// 查找年
					new Select(dateyear).selectByIndex(year - 1897);
					Thread.sleep(1000);

					WebElement datemonth = datewindow
							.findElement(By.xpath(".//select[@class='picker__select--month']"));// 查找月
					new Select(datemonth).selectByIndex(month - 1);
					Thread.sleep(1000);

					datetimes.get(i).findElements(By.cssSelector(".picker__day.picker__day--infocus")).get(date - 1)
							.click();
					Thread.sleep(1000);
				}
			}

		}
	}

	// datetime的time 手动编辑的默认输入12:00
	public static void inputDatetimeTime(WebDriver browser) throws InterruptedException {
		List<WebElement> datetimes = browser.findElements(By.className("widget-datetime"));
		for (int i = 1; i < datetimes.size(); i++)

		{

			if (!datetimes.get(i).findElement(By.xpath("./parent::div")).getAttribute("class").contains("ng-hide"))// 查看其父控件不是隐藏项目
			{
				if (datetimes.get(i).findElement(By.xpath("./input")).getAttribute("placeholder").equals("hh:mm")) {

					datetimes.get(i).click(); // 点击之后才能分辨是否可以自动编辑
					Thread.sleep(1000);
					if (datetimes.get(i).findElement(By.xpath("./input")).getAttribute("aria-expanded").equals("false"))// 是否为手动编辑
					{
						datetimes.get(i).findElement(By.xpath("./input")).sendKeys("12:00");
						continue;
					}
					List<WebElement> timepicklist = datetimes.get(i)
							.findElements(By.xpath(".//li[@class='picker__list-item']"));
					timepicklist.get(timepicklist.size() / 2).click(); // 先不点击
					Thread.sleep(1000);
				}
			}
		}
	}

	//// datetime的time (重载) 手动编辑的默认输入12:00
	public static void inputDatetimeTime(WebDriver browser, WebElement elementParent) throws InterruptedException {
		List<WebElement> datetimes = elementParent.findElements(By.className("widget-datetime"));
		for (int i = 1; i < datetimes.size(); i++)

		{

			if (!datetimes.get(i).findElement(By.xpath("./parent::div")).getAttribute("class").contains("ng-hide"))// 查看其父控件不是隐藏项目
			{
				if (datetimes.get(i).findElement(By.xpath("./input")).getAttribute("placeholder").equals("hh:mm")) {

					datetimes.get(i).click(); // 点击之后才能分辨是否可以自动编辑
					Thread.sleep(1000);
					if (datetimes.get(i).findElement(By.xpath("./input")).getAttribute("aria-expanded").equals("false"))// 是否为手动编辑
					{
						datetimes.get(i).findElement(By.xpath("./input")).sendKeys("12:00");
						continue;
					}
					List<WebElement> timepicklist = datetimes.get(i)
							.findElements(By.xpath(".//li[@class='picker__list-item']"));
					timepicklist.get(timepicklist.size() / 2).click(); // 先不点击
					Thread.sleep(1000);
				}
			}
		}
	}

	// 不详
	public static void inputUkn(WebDriver browser) throws InterruptedException {
		List<WebElement> Ukns = browser
				.findElements(By.xpath(".//input[@type='checkbox' and @ng-model='vm.flag.UKN']"));
		for (WebElement Ukn : Ukns) {
			Ukn.findElement(By.xpath("./parent::label")).click();
		}
	}

	// 不适用
	public static void inputNa(WebDriver browser) throws InterruptedException {
		List<WebElement> Ukns = browser.findElements(By.xpath(".//input[@type='checkbox' and @ng-model='vm.flag.NA']"));
		for (WebElement Ukn : Ukns) {
			Ukn.findElement(By.xpath("./parent::label")).click();
		}
	}

	public static void fillIn(WebDriver browser) {
         
	}

	/*
	 * 检测 有提示的 InputNumber控件 的提示是否正确(检测不符合控件限制后显示出正确的提示语) 输入 String
	 * targetElementId 为目标控件的id 输入 WebElement defaultElement 为默认的空点
	 * 控件(建议为当前页面不带提示的可输入控件),此控件的点击目的是刷新 目标控件的 提示 输入 数组[String]类型的 testListArray
	 * 为对目标函数的填入 测试集 填入 String expectString 为测试集下的期望 提示值 填入 String iswarning
	 * 为"warning",检测warning类型的提示,否则检测 error类型的提示
	 */
	public static void checkInputNumberTips(WebDriver browser, String targetElementId, WebElement defaultElement,
			String[] testListArray, String expectString, String iswarning) throws InterruptedException {
		List<String> testList = java.util.Arrays.asList(testListArray);
		Actions action = new Actions(browser);
		WebElement parent = browser.findElement(By.id(targetElementId));
		WebElement target = parent.findElement(By.xpath("./input"));
		WebElement tips;

		for (int i = 0; i < testList.size(); i++) {
			target.clear();
			target.sendKeys(testList.get(i));
			Thread.sleep(500);
			action.click(defaultElement).perform();

			if (iswarning.equals("warning"))
				tips = parent.findElement(By.xpath("./span"));
			else {
				tips = parent.findElement(By.xpath("./div/span"));
			}

			if (!tips.getText().equals(expectString)) // 当没有提示的时候直接报错,都不会执行if中的代码
			{// 报错
				System.out.println("有InputNumberTips不符合要求值." + "期望的tips" + expectString + "目前的tips值是" + tips.getText());
				throw new RuntimeException("some InputNumberTips is not value of expectation");
			}

		}
		System.out.println("ok");

	}

	/*
	 * 检测 有提示的 InputNumber控件 的提示是否正确(检测符合控件限制后,严禁显示任何提示) 输入 String
	 * targetElementId 为目标控件的id 输入 WebElement defaultElement 为默认的空点
	 * 控件(建议为当前页面不带提示的可输入控件),此控件的点击目的是刷新 目标控件的 提示 输入 数组[String]类型的 testListArray
	 * 为对目标函数的填入 测试集 填入 String iswarning 为"warning",检测warning类型的提示,否则检测
	 * error类型的提示
	 */
	public static void checkInputNumberTips(WebDriver browser, String targetElementId, WebElement defaultElement,
			String[] testListArray, String iswarning) {
		List<String> testList = java.util.Arrays.asList(testListArray);
		Actions action = new Actions(browser);
		WebElement parent = browser.findElement(By.id(targetElementId));
		WebElement target = parent.findElement(By.xpath("./input"));

		for (int i = 0; i < testList.size(); i++) {
			target.clear();
			target.sendKeys(testList.get(i));
			action.click(defaultElement).perform();

			if (iswarning.equals("warning")) {
				if (parent.findElement(By.xpath("./input/following-sibling::*")).getTagName().equals("span")) {
					System.out.println("有InputNumberTips不符合要求值." + "期望不出现tips." + "目前的tips值是"
							+ parent.findElement(By.xpath("./*")).getTagName());
					throw new RuntimeException("some InputNumberTips is not value of expectation");
				}
			} else {
				if (parent.findElement(By.xpath("./*")).getTagName().equals("div")) {
					System.out.println("有InputNumberTips不符合要求值." + "期望不出现tips." + "目前的tips值是"
							+ parent.findElement(By.xpath("./*")).getTagName());
					throw new RuntimeException("some InputNumberTips is not value of expectation");
				}
			}
			// 不该出现warning,或者error的时候,
			// input后有span,span后有div input前有div后也有
			// 正确情况 input后又div input后有div 不考虑
		}
	}

	// list 输入n个list
	public static void inputList(WebDriver browser, int n) throws InterruptedException {
		try {
			List<WebElement> listTabs = browser.findElements(By.xpath(".//li[@ng-click='vm.selectList(list)']"));
			WebElement addListButton = browser.findElement(By.xpath(".//button[@ng-click='vm.addList()']"));
			for (int i = listTabs.size(); i < listTabs.size() + n; i++) {
				addListButton.click();
				Thread.sleep(500);
				List<WebElement> listcontainers = browser
						.findElements(By.xpath(".//div[@values='vm.listValues[list.uid]']"));
				operation.inputRadio(browser, listcontainers.get(i));
				operation.inputCheckbox(browser, listcontainers.get(i));
				operation.inputText(browser, listcontainers.get(i), "测试text-list");
				operation.inputTextarea(browser, listcontainers.get(i), "测试textarea-list");
				operation.inputNumber(browser, listcontainers.get(i), "12");
				operation.inputSelect(browser, listcontainers.get(i));
				operation.inputScale(browser, listcontainers.get(i));
				operation.inputDatetimeDate(browser, listcontainers.get(i));
				operation.inputDatetimeTime(browser, listcontainers.get(i));
				operation.inputTable(browser, listcontainers.get(i), 2);
				operation.inputMatrixRadio(browser, listcontainers.get(i));
				operation.inputMatrixCheckbox(browser, listcontainers.get(i));
			}
		} catch (NoSuchElementException e) {
		}

	}

	// table 输入n 添加table的n行数据
	public static void inputTable(WebDriver browser, int n) throws InterruptedException {
		try {
			Thread.sleep(500);
			WebElement addTablebutton = browser.findElement(By.partialLinkText("添加数据"));
			for (int i = 0; i < n; i++) {
				addTablebutton.click();
				Thread.sleep(500);
				operation.inputText(browser, "测试text-table");
				operation.inputRadio(browser);
				operation.inputCheckbox(browser, 1, 2);
				operation.inputTextarea(browser, "测试textarea-table");
				operation.inputNumber(browser, "12");
				operation.inputScale(browser, 3);
				operation.inputSelect(browser, 2);
				operation.inputDatetimeDate(browser);
				operation.inputDatetimeTime(browser);
				Thread.sleep(500);
				browser.findElement(By.partialLinkText("保存")).click();
			}
		} catch (NoSuchElementException e) {
		}
	}

	// table（重载） 子页面下的 输入n 添加table的n行数据
	public static void inputTable(WebDriver browser, WebElement elementParent, int n) throws InterruptedException {
		try {
			Thread.sleep(500);
			WebElement addTablebutton = elementParent.findElement(By.partialLinkText("添加数据"));
			for (int i = 0; i < n; i++) {
				addTablebutton.click();
				Thread.sleep(1000);

				// WebElement tableWindow =
				// browser.findElement(By.cssSelector(".widget-modal-container。ng-scope"));//不知为何找不到
				WebElement tableWindow = browser
						.findElement(By.xpath("//div[@class='widget-modal-container ng-scope']"));
				operation.inputRadio(browser, tableWindow);
				operation.inputCheckbox(browser, tableWindow);
				operation.inputText(browser, tableWindow, "测试text-table");
				operation.inputTextarea(browser, tableWindow, "测试textarea-table");
				operation.inputNumber(browser, tableWindow, "12");
				operation.inputScale(browser, tableWindow);
				operation.inputSelect(browser, tableWindow);
				operation.inputDatetimeDate(browser, tableWindow);
				operation.inputDatetimeTime(browser, tableWindow);
				tableWindow.findElement(By.xpath(".//a[@class='widget-modal-confirm-btn']")).click();

			}
		} catch (NoSuchElementException e) {
		}
	}

	// matrix
	public static void inputMatrix(WebDriver browser) throws InterruptedException {
		try {
			operation.inputMatrixRadio(browser);
			operation.inputMatrixCheckbox(browser);
			operation.inputText(browser, "测试text-matrix");
			operation.inputTextarea(browser, "测试textarea-matrix");
			operation.inputNumber(browser, "12");
			operation.inputSelect(browser, 2);
			operation.inputDatetimeDate(browser);
			operation.inputDatetimeTime(browser);
		} catch (NoSuchElementException e) {
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//
	public static void checkRadio(WebDriver browser) {
		List<WebElement> radioContainers = browser.findElements(By.cssSelector(".radio-inner-container"));
		for (int i = 0; i < radioContainers.size(); i++) {
			List<WebElement> radios = radioContainers.get(i).findElements(By.xpath("./div/label/input"));
			int flag = 0;
			for (WebElement radio : radios) {
				if (radio.isSelected()) {
					flag = 1;
				}
			}
			if (flag == 0) {
				System.out.println("第" + (i + 1) + "个radio未被选择");
			}
		}
		System.out.println("radio检查完毕");
	}

	public static void checkCheckbox(WebDriver browser) {
		List<WebElement> checkboxContainers = browser.findElements(By.cssSelector(".checkbox-inner-container"));
		for (int i = 0; i < checkboxContainers.size(); i++) {

			List<WebElement> checkboxs = checkboxContainers.get(i).findElements(By.xpath("./div/label/input"));
			int flag = 0;
			for (WebElement checkbox : checkboxs) {
				if (checkbox.isSelected()) {
					flag = 1;
				}
			}
			if (flag == 0) {
				System.out.println("第" + (i + 1) + "个checkbox未被选择");
			}
		}
		System.out.println("checkbox检查完毕");
	}
	// 检查text 输入text为要检查的值

	public static void checkText(WebDriver browser, String text) throws InterruptedException {
		Thread.sleep(500);
		List<WebElement> inputTexts = browser.findElements(By.xpath("//input[@type='text' and @ng-model='vm.value']"));
		for (int i = 0; i < inputTexts.size(); i++) {
			String temp = inputTexts.get(i).getAttribute("value");
			if (inputTexts.get(i).getAttribute("autocomplete").equals("off")) {
				System.out.println("第" + (i + 1) + "个Text是术语字典，value为：" + temp);
			}

			else {
				if (!temp.equals(text)) {
					System.out.println("第" + (i + 1) + "个Text检查不正确，value为：" + temp);

				}
			}
		}
		System.out.println("Text检查完毕");
	}

	public static void checkTextarea(WebDriver browser, String textarea) throws InterruptedException {
		Thread.sleep(500);
		List<WebElement> inputTextareas = browser
				.findElements(By.cssSelector("textarea.textarea-content.ng-pristine.ng-valid"));
		for (int i = 0; i < inputTextareas.size(); i++) {
			String temp = inputTextareas.get(i).getAttribute("value");
			if (!temp.equals(textarea)) {
				System.out.println("第" + (i + 1) + "个Textarea检查不正确，value为：" + temp);
			}
		}
		System.out.println("Textarea检查完毕");
	}

	public static void checkNumber(WebDriver browser, String number) throws InterruptedException {
		Thread.sleep(500);
		List<WebElement> inputNumbers = browser
				.findElements(By.xpath("//input[@type='number' and @ng-model='vm.value']"));
		for (int i = 0; i < inputNumbers.size(); i++) {
			String temp = inputNumbers.get(i).getAttribute("value");
			if (inputNumbers.get(i).getAttribute("readonly") != null) // 自动计算的问题
			{
				System.out.println("第" + (i + 1) + "个Number为自动计算，value为：" + temp);
			} else {
				if (!temp.equals(number)) {
					System.out.println("第" + (i + 1) + "个Number检查不正确，value为：" + temp);
				}
			}
		}
		System.out.println("Number检查完毕");
	}

	public static void checkSelect(WebDriver browser) {
		List<WebElement> selectContainers = browser.findElements(By.xpath("//select[@ng-model='vm.select']"));
		for (int i = 0; i < selectContainers.size(); i++) {
			String temp = selectContainers.get(i).getAttribute("value");
			System.out.println("第" + (i + 1) + "个select的value为：" + temp);
		}

	}

	public static void checkSelect(WebDriver browser, String slectValue) {
		List<WebElement> selectContainers = browser.findElements(By.xpath("//select[@ng-model='vm.select']"));
		for (int i = 0; i < selectContainers.size(); i++) {
			String temp = selectContainers.get(i).getAttribute("value");
			if (!temp.equals(slectValue)) {
				System.out.println("第" + (i + 1) + "个select检查不正确，该select的value为：" + temp);
			}
		}

	}

	public static void checkScale(WebDriver browser) {
		List<WebElement> scaleContainers = browser.findElements(By.xpath("//div[@class='widget-scale-group']"));
		for (int i = 0; i < scaleContainers.size(); i++) {

			List<WebElement> scales = scaleContainers.get(i).findElements(By.xpath("./div/label/input"));
			int flag = 0;
			for (int x = 0; x < scales.size(); x++) {
				if (scales.get(x).isSelected()) {
					flag = 1;
					System.out.println("第" + (i + 1) + "个scale的第" + (x + 1) + "个选项被选择");
				}
			}
			if (flag == 0) {
				System.out.println("第" + (i + 1) + "个scale未被选择");
			}
		}
		System.out.println("scale检查完毕");

	}

	public static void checkSection(WebDriver browser) {
		List<WebElement> sections = browser.findElements(By.xpath("//div[@class='section-container']"));
		for (int i = 0; i < sections.size(); i++) {
			String sectionValue = sections.get(i).findElement(By.xpath("./span")).getText();
			System.out.println("第" + i + "个section为：" + sectionValue);
		}
	}

	public static void checkDateTime(WebDriver browser) {
		List<WebElement> datetimes = browser.findElements(By.className("widget-datetime"));
		int a = 1;
		for (int i = 0; i < datetimes.size(); i++) {

			if ((!datetimes.get(i).findElement(By.xpath("./parent::div")).getAttribute("class").contains("ng-hide"))) {
				if (datetimes.get(i).findElement(By.xpath("./input")).getAttribute("value") == null
						|| datetimes.get(i).findElement(By.xpath("./input")).getAttribute("value").equals("")) {
					System.out.println("第" + a + "个DateTime值为空");
				}
				a = a + 1;
			}
		}
		System.out.println("DateTime检查完毕");
	}

	public static void checkNumber(WebDriver browser) throws InterruptedException {
		List<WebElement> Inputlists = browser.findElements(By.xpath("//input[@type='number']"));
		Random n = new Random();
		String m = "" + n.nextInt(100);
		Inputlists.get(0).clear();
		Inputlists.get(0).sendKeys(m);
		Inputlists.get(1).clear();
		Inputlists.get(1).sendKeys(m);

		operation.checkInputNumberTips(browser, "number_3_input", Inputlists.get(0), new String[] { "1", "7", "100" },
				"必须是3的倍数", "");
		operation.checkInputNumberTips(browser, "number_3_input", Inputlists.get(0), new String[] { "3", "6", "99" },
				"");

		Inputlists.get(3).clear();
		Inputlists.get(3).sendKeys(m);

		operation.checkInputNumberTips(browser, "number_5_input", Inputlists.get(0),
				new String[] { "-1", "0", "9.9999", "7" }, "必须大于等于10", "");
		operation.checkInputNumberTips(browser, "number_5_input", Inputlists.get(0),
				new String[] { "10", "11", "11.1", "99" }, "");

		operation.checkInputNumberTips(browser, "number_6_input", Inputlists.get(0),
				new String[] { "20.1", "99", "100.1" }, "必须小于等于20", "");
		operation.checkInputNumberTips(browser, "number_6_input", Inputlists.get(0),
				new String[] { "-10", "-0.1", "0.1", "10", "19.9", "20" }, "");

		operation.checkInputNumberTips(browser, "number_7_input", Inputlists.get(0), new String[] { "-1.1", "100.1" },
				"所填必须为整数", "warning");
		operation.checkInputNumberTips(browser, "number_7_input", Inputlists.get(0),
				new String[] { "0.0", "-100", "100", "10" }, "warning");

		operation.checkInputNumberTips(browser, "number_8_input", Inputlists.get(0),
				new String[] { "-1.1", "1.1", "0", "20", "20.1" }, "9-16,范围之外,请您再次核对", "warning");
		operation.checkInputNumberTips(browser, "number_8_input", Inputlists.get(0),
				new String[] { "9", "9.5", "10", "16" }, "warning");

		operation.checkInputNumberTips(browser, "number_9_input", Inputlists.get(0),
				new String[] { "-1.1", "1.1", "0", "20", "20.1" }, "9-16,范围之外,请您再次核对", "");
		operation.checkInputNumberTips(browser, "number_9_input", Inputlists.get(0),
				new String[] { "9", "9.5", "10", "16" }, "");

		Inputlists.get(9).clear();
		Inputlists.get(9).sendKeys(m);

		operation.checkInputNumberTips(browser, "number_11_input", Inputlists.get(0),
				new String[] { "-1.1", "1.1", "0", "20", "20.1" }, "9-16,范围之外,请您再次核对", "warning");
		operation.checkInputNumberTips(browser, "number_11_input", Inputlists.get(0),
				new String[] { "9", "9.5", "10", "16" }, "warning");

		operation.checkInputNumberTips(browser, "number_12_input", Inputlists.get(0),
				new String[] { "-1.1", "1.1", "0", "20", "20.1" }, "9-16,范围之外,请您再次核对", "");
		operation.checkInputNumberTips(browser, "number_12_input", Inputlists.get(0),
				new String[] { "9", "9.5", "10", "16" }, "");

		operation.checkInputNumberTips(browser, "number_13_input", Inputlists.get(0),
				new String[] { "-1", "0", "0.0", "1", "-1.11", "20.11", "20.201" }, "小数点后一位", "");
		operation.checkInputNumberTips(browser, "number_13_input", Inputlists.get(0),
				new String[] { "-1.1", "1.1", "16.8" }, "");

		operation.caseSaveButton(browser);
		browser.get(browser.getCurrentUrl());
		Thread.sleep(1000);
		Inputlists = browser.findElements(By.xpath("//input[@type='number']"));
		// 检测数据
		String[] names = { "100", "100", "99", "1.2", "99", "20", "10", "16", "16", "100", "16", "16", "16.8" };
		List<String> sectionsExp = java.util.Arrays.asList(names);

		for (int i = 0; i < Inputlists.size(); i++) {
			if (!Inputlists.get(i).getAttribute("value").equals(sectionsExp.get(i))) {
				System.out.println("有InputNumber不符合要求值." + "期望InputNumber:" + sectionsExp.get(i) + "目前的InputNumber值是:"
						+ Inputlists.get(i).getAttribute("value"));
				throw new RuntimeException("some InputNumber is not value of expectation");
			}
		}
	}

}
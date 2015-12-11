package com.etouch.net.tests;

import org.apache.commons.logging.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.etouch.net.common.BaseTest;
import com.etouch.net.pages.AboutUsPage;
import com.etouch.taf.core.TestBedManager;
import com.etouch.taf.core.config.TestBedManagerConfiguration;
import com.etouch.taf.core.datamanager.excel.TafExcelDataProvider;
import com.etouch.taf.core.datamanager.excel.TestParameters;
import com.etouch.taf.core.datamanager.excel.annotations.IExcelDataFiles;
import com.etouch.taf.core.datamanager.excel.annotations.ITafExcelDataProviderInputs;
import com.etouch.taf.core.exception.PageException;
import com.etouch.taf.tools.jira.Jira;
import com.etouch.taf.util.LogUtil;
import com.etouch.taf.util.SoftAssertor;
import com.etouch.taf.webui.selenium.WebPage;

@IExcelDataFiles(excelDataFiles = { "file1=src/test/resources/testdata/eTouchTestData.xls" })
public class AboutUsPageTest extends BaseTest {
	private WebPage webPage;
	static Log log = LogUtil.getLog(AboutUsPageTest.class);
	private AboutUsPage aboutUsPage;
	String aboutUseUrl = "/about-us/index.html";

	private String url1 = TestBedManagerConfiguration.getInstance()
			.getWebConfig().getURL();
	private String url = url1 + aboutUseUrl;

	@BeforeClass(alwaysRun = true)
	public void prepareBeforeClass() throws Exception {
		try {
			webPage = new WebPage();
			aboutUsPage = new AboutUsPage(url, webPage);
		}
		catch (Exception e) {
			log.info("Error is:" + e);
			SoftAssertor.addVerificationFailure(e.getMessage());
		}
	}


	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 1 , enabled=true, groups={"Smoke", "Regression"}, description="Verifies About Us landing page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "AboutUs", dataKey = "LandingPage")
	public void testVerifyLandingPage(TestParameters inputs){
		try {
			SoftAssertor.assertEquals(webPage.getCurrentUrl(), inputs.getParamMap().get("URL"), "AboutUsPage URL not matched");
			SoftAssertor.assertEquals(webPage.getDriver().getTitle(), inputs.getParamMap().get("Title"), "AboutUsPage Title not matched");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case AboutUs LandingPage failed -------------<"+e.getMessage());
		}catch(Exception e){
			log.error(">--------------Test case AboutUs LandingPage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	// This test verify the header and paragraph content on Aboutus page.
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 2,enabled=true, groups={"Regression"}, description="Verifies Paragraphs on About Us page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "AboutUs", dataKey = "AboutUsPage_Table")
	public void verifyParagraphsOnAboutUsPage(TestParameters inputs) {
		try {
			log.info("Verifying the Header on About Us Page");
			verifyGivenHeaderValues(inputs,aboutUsPage.getHeaderText(),aboutUsPage.getFontSizeOfHeader(),aboutUsPage.getFontColorOfHeader(),aboutUsPage.getFontFamilyOfHeader(),"Header");
			log.info("Verified the Header on AboutUs Page");

			log.info("Verifying the paragraph1 on About Us Page");
			verifyGivenParaValues(inputs,aboutUsPage.getPara1Text(),aboutUsPage.getFontSizeOfPara1(),aboutUsPage.getFontColorOfPara1(),aboutUsPage.getFontFamilyOfPara1(),"Para1");
			log.info("Verified the paragraph1 on About Us Page");

			log.info("Verifying the paragraph2 on About Us Page");
			verifyGivenParaValues(inputs,aboutUsPage.getPara2Text(),aboutUsPage.getFontSizeOfPara2(),aboutUsPage.getFontColorOfPara2(),aboutUsPage.getFontFamilyOfPara2(),"Para2");
			log.info("Verified the paragraph2 on About Us Page");

			log.info("Verifying the paragraph3 on About Us Page");
			verifyGivenParaValues(inputs,aboutUsPage.getPara3Text(),aboutUsPage.getFontSizeOfPara3(),aboutUsPage.getFontColorOfPara3(),aboutUsPage.getFontFamilyOfPara3(),"Para3");
			log.info("Verified the paragraph3 on About Us Page");

			log.info("Verifying the paragraph4 on About Us Page");
			verifyGivenParaValues(inputs,aboutUsPage.getPara4Text(),aboutUsPage.getFontSizeOfPara4(),aboutUsPage.getFontColorOfPara4(),aboutUsPage.getFontFamilyOfPara4(),"Para4");
			log.info("Verified the paragraph4 on About Us Page");
			
		} catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyParagraphsOnAboutUsPage failed -------------<"+ e.getMessage());
		} catch (PageException e) {
			log.error(">--------------Test case verifyParagraphsOnAboutUsPage failed -------------<"
					+ e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(">--------------Test case verifyParagraphsOnAboutUsPage failed -------------<"
					+ e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally {
			SoftAssertor.displayAssertErrors();
		}
	}

	//This test verify the Image on Aboutus page.
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 3,enabled=true, groups={"Regression"}, description="Verifies image on About Us page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "AboutUs", dataKey = "AboutUsPage_Table")
	public void verifyImageOnAboutUsPage(TestParameters inputs) {

		log.info("Verifying Image on About Us Page");

		try {
			String bgImage = aboutUsPage.verifyImageOnAboutUsPage();
			if(bgImage.length()!=0)
				bgImage=bgImage.replace("url(", "").replace(")", "").replaceAll("\\\"", "");
			SoftAssertor.assertEquals(bgImage, inputs.getParamMap().get("Image"), "About Us Background Image not matched");

			log.info("Verified Image on AboutUs Page");

		} catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyImageOnAboutUsPage failed -------------<"+ e.getMessage());
		} catch (PageException e) {
			log.error(">--------------Test case verifyImageOnAboutUsPage failed -------------<"
					+ e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(">--------------Test case verifyImageOnAboutUsPage failed -------------<"
					+ e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally {
			SoftAssertor.displayAssertErrors();
		}
	}
	
	public void verifyGivenParaValues(TestParameters inputs,String paraText,String fontSize,String fontColor,String fontFamily,String expParaText){
		SoftAssertor.assertEquals(paraText,inputs.getParamMap().get(expParaText),"Paragraph Texts are Not matched");
		SoftAssertor.assertEquals(fontSize,inputs.getParamMap().get("ParagraphFontSize"),"Paragraph Font Size are Not matched");
		SoftAssertor.assertEquals(fontColor,inputs.getParamMap().get("ParagraphFontColor"),"Paragraph Font Color are Not matched");
		SoftAssertor.assertEquals(getFontFamily(fontFamily),inputs.getParamMap().get("ParagraphFontFamily"),"Paragraph Font Family Not matched");
	}
	
	public void verifyGivenHeaderValues(TestParameters inputs,String paraText,String fontSize,String fontColor,String fontFamily,String expParaText){
		SoftAssertor.assertEquals(paraText,inputs.getParamMap().get(expParaText),"Header Texts are Not matched");
		SoftAssertor.assertEquals(fontSize,inputs.getParamMap().get("HeaderFontSize"),"Header Font Size are Not matched");
		SoftAssertor.assertEquals(fontColor,inputs.getParamMap().get("HeaderFontColor"),"Header Font Color are Not matched");
		SoftAssertor.assertEquals(getFontFamily(fontFamily),inputs.getParamMap().get("HeaderFontFamily"),"Header Font Family Not matched");
	}
	
	public void logDefectOnTestFailure(TestParameters inputs,AssertionError e){
		Jira.setDefectInformation(inputs.getParamMap().get("summary"),inputs.getParamMap().get("description"));
		aboutUsPage.logAndCreateADefect( TestBedManager.INSTANCE.getDefect(),url3,  issueUrl, username,  password, keys);
		SoftAssertor.addVerificationFailure(e.getMessage());
	}

}

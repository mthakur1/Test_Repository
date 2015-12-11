package com.etouch.net.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.etouch.net.common.BaseTest;
import com.etouch.net.locators.ServicePageLocators;
import com.etouch.net.pages.ServicesPage;
import com.etouch.taf.core.TestBedManager;
import com.etouch.taf.core.config.TestBedManagerConfiguration;
import com.etouch.taf.core.datamanager.excel.TafExcelDataProvider;
import com.etouch.taf.core.datamanager.excel.TestParameters;
import com.etouch.taf.core.datamanager.excel.annotations.IExcelDataFiles;
import com.etouch.taf.core.datamanager.excel.annotations.ITafExcelDataProviderInputs;
import com.etouch.taf.core.exception.PageException;
import com.etouch.taf.tools.jira.Jira;
import com.etouch.taf.util.CommonUtil;
import com.etouch.taf.util.LogUtil;
import com.etouch.taf.util.SoftAssertor;
import com.etouch.taf.webui.selenium.WebPage;

@IExcelDataFiles(excelDataFiles = { "file1=src/test/resources/testdata/eTouchTestData.xls" })
public class ServicesPageTest extends BaseTest {
	private WebPage webPage;
	static Log log = LogUtil.getLog(ServicesPageTest.class);
	private ServicesPage servicePage;
	String ServiceUrl="/services/index.html";
	private String url1 = TestBedManagerConfiguration.getInstance().getWebConfig().getURL();
	private String url=url1+ServiceUrl;
	private static Map<String,ArrayList<String>> headerOnWheels;
	private static Map<String,ArrayList<String>> headerOnSlides;
	private static List<String> backGroundofSlidesOnPage=null;
	private static int headerOnSlides_index=0;
	private static int BulletPointOnSlides_index=0;
	private static int backGroundColor_index=0;
	private static Map<String,ArrayList<String>> bulletPointOnSlide;
	private static int textOnWheel_index=0;
	private static int headerOnDigitalMobileOverlay_index=0;
	private static Map<String, ArrayList<String>> headerOnDigitalMobileOverlay;
	private static int titleOnDigitalMobileOverlay_index=0;
	private static Map<String, ArrayList<String>> titleOnDigitalMobileOverlay;
	private static int img_index = 0;
	List<String> imgSourceDetails = null;
	private static int headerOnAnalyticsOverlay_index=0;
	private static Map<String, ArrayList<String>> headerOnAnalyticsOverlay;
	private static int titleOnAnalyticsOverlay_index=0;
	private static Map<String, ArrayList<String>> titleOnAnalyticsOverlay;
	private static int imgIndexAnalyticsOverlay = 0;
	List<String> imgSourceDetailsAnalyticsOverlay = null;

	@BeforeClass(alwaysRun = true)
	public void prepareBeforeClass() throws Exception {
		try {
			webPage = new WebPage();			
			servicePage = new ServicesPage(url, webPage);			
		}
		catch (Exception e) {
			log.info("Error is:" + e);
			SoftAssertor.addVerificationFailure(e.getMessage());
		}
	}

	//This method verify current url and title of page.

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 1,enabled = true , groups={"Regression","Smoke"}, description="Verifies landing on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "LandingPage")
	public void testVerifyLandingPage(TestParameters inputs){
		try {
			CommonUtil.sop("Verifying the pageUrl & pageTitle of Page");
			SoftAssertor.assertEquals(webPage.getCurrentUrl(), inputs.getParamMap().get("URL"), "ServicePage URL not matched");
			SoftAssertor.assertEquals(webPage.getDriver().getTitle(), inputs.getParamMap().get("Title"), "ServicePage Title not matched");
			CommonUtil.sop("Verified the pageUrl & pageTitle of Page");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case ServicePage LandingPage failed -------------<"+e.getMessage());
		}catch(Exception e){
			log.error(">--------------Test case ServicePage LandingPage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}

	// This method verify the paragraph below the Image on page load.
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 2,enabled = true , groups={"Regression"}, description="Verifies Text below homepage image on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "ParagraphsBelowHomePageImage")
	public void verifyParagraphTextBelowHomepageImagOnServicsPage(TestParameters inputs){
		try {
			CommonUtil.sop("Verifying the paragrapshs on services page");
			webPage.getDriver().get("http://www.etouch.net/services/index.html#skills");
			Thread.sleep(3000);
			log.info("Verifying content of paragraph1 below Image on service Page.");
			String paragraph1Text = servicePage.getParaText();
			SoftAssertor.assertEquals(paragraph1Text,inputs.getParamMap().get("Para1"),"paragraph1 Texts are Not matched");
			String paragraph1FontSize = servicePage.getFontSizeOfPara();
			SoftAssertor.assertEquals(paragraph1FontSize,inputs.getParamMap().get("Para1FontSize"),"paragraph1 Font Size are Not matched");
			String paragraph1FontColor = servicePage.getFontColorOfPara();
			SoftAssertor.assertEquals(paragraph1FontColor,inputs.getParamMap().get("Para1FontColor"),"paragraph1 Font Color are Not matched");
			String paragraph1FontFamily = servicePage.getFontFamilyOfPara();
			SoftAssertor.assertEquals(getFontFamily(paragraph1FontFamily),inputs.getParamMap().get("family"),"paragraph1 Font Family are Not matched");
			log.info("Verified paragraph1 below Image on service Page.");

			log.info("Verifying content of paragraph2 below Image on service Page.");
			String paragraph2Text = servicePage.getPara2Text();
			SoftAssertor.assertEquals(paragraph2Text,inputs.getParamMap().get("Para2"),"paragraph2 Texts are Not matched");
			String paragraph2FontSize = servicePage.getFontSizeOfPara2();
			SoftAssertor.assertEquals(paragraph2FontSize,inputs.getParamMap().get("Para2FontSize"),"paragraph2 Font Size are Not matched");
			String paragraph2FontColor = servicePage.getFontColorOfPara2();
			SoftAssertor.assertEquals(paragraph2FontColor,inputs.getParamMap().get("Para2FontColor"),"paragraph2 Font Color are Not matched");
			String paragraph2FontFamily = servicePage.getFontFamilyOfPara2();
			SoftAssertor.assertEquals(getFontFamily(paragraph2FontFamily),inputs.getParamMap().get("family"),"paragraph2 Font Family are Not matched");
			log.info("Verified paragraph2 below Image on service Page.");

			log.info("Verifying paragraph above rotating wheels Page.");
			webPage.getDriver().get("http://www.etouch.net/services/index.html#approach");
			Thread.sleep(5000);
			String servicePortPara = servicePage.getServiceportParaText();
			SoftAssertor.assertEquals(servicePortPara,inputs.getParamMap().get("ParaoverRotatingWheels"),"servicePortParaTexts are Not matched");
			String servicePortParaFontSize = servicePage.getFontSizeOfServiceportPara();
			SoftAssertor.assertEquals(servicePortParaFontSize,inputs.getParamMap().get("ParaFontSize"),"paragraph above rotating wheels Font Size are Not matched");
			String servicePortParaFontColor = servicePage. getFontColorOfServiceportPara();
			SoftAssertor.assertEquals(servicePortParaFontColor,inputs.getParamMap().get("ParaFontColor"),"paragraph above rotating wheels Font Color are Not matched");
			String servicePortParaFontFamily = servicePage.getFontFamilyOfServiceportPara();
			SoftAssertor.assertEquals(getFontFamily(servicePortParaFontFamily),inputs.getParamMap().get("family"),"paragraph above rotating wheels Font Family are Not matched");
			log.info("Verified paragraph above rotating wheels on Page.");

		}
		catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyParagraphTextBelowHomepageImagOnServicsPage failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyParagraphTextBelowHomepageImagOnServicsPage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyParagraphTextBelowHomepageImagOnServicsPage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}


	//This method verify header on carosual /slides on page. Total number of slides are 5.

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =3,enabled = true , groups={"Regression"}, description="Verifies slide header on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "Carosual_Header")
	public void verifyHeaderOnSlideOnpage(TestParameters inputs){
		try {
			CommonUtil.sop("Verifying Header on slides. Total no of slides:5");
			if(headerOnSlides_index==0){
				headerOnSlides=null;
				webPage.getDriver().get("http://www.etouch.net/services/index.html#skills");
				Thread.sleep(3000);
				headerOnSlides=servicePage.getTextHeadingPropertiesOfHeader(ServicePageLocators.h2HeaderOnSlides);
			}
			verifyGivenValues(headerOnSlides_index, headerOnSlides, inputs,"Heading on Slides ");
			headerOnSlides_index++;
			CommonUtil.sop("Verified Header on slides. Total no of slides:5");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyHeaderOnSlideOnpage failed -------------<"+e.getMessage());
		}catch(Exception e){
			log.error(">--------------Test case verifyHeaderOnSlideOnpage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally{
			SoftAssertor.displayAssertErrors();
		}
	}

	//This method verify bullet Points on slides on service page.

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =4,enabled = true , groups={"Regression"}, description="Verifies slide bullet points on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "PointsOnSlide")
	public void verifyBulletPointOnSlideOnpage(TestParameters inputs){
		try {
			CommonUtil.sop("Verifying Bullet points on each slide. Total No of Slides : 5");
			if(BulletPointOnSlides_index==0){
				bulletPointOnSlide=null;
				bulletPointOnSlide=servicePage.getTextHeadingPropertiesOfHeader(ServicePageLocators.slideContainer);
				Thread.sleep(3000);
			}
			verifyGivenValues(BulletPointOnSlides_index, bulletPointOnSlide, inputs, "Bullet Points On ");
			BulletPointOnSlides_index++;
			CommonUtil.sop("Verified Bullet points on each slide. Total No of Slides : 5");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyBulletPointOnSlideOnpage failed -------------<"+e.getMessage());
		}catch(Exception e){
			log.error(">--------------Test case verifyBulletPointOnSlideOnpage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally{
			SoftAssertor.displayAssertErrors();
		}
	}


	//This method verify Background Color of each slide
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =5,enabled = true , groups={"Regression"}, description="Verifies slide background color on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "SlideBackgroundColor")
	public void verifyBackGroundColorOfSlidesOnServicesPage(TestParameters inputs){
		try {
			CommonUtil.sop("Verifying Background color for each slide. Total No of Slides : 5");
			if(backGroundColor_index==0){
				backGroundofSlidesOnPage=null;

				backGroundofSlidesOnPage=servicePage.getBackGroundColorOfFluidBoxWrapper();
			}
			SoftAssertor.assertEquals(backGroundofSlidesOnPage.get(backGroundColor_index),
					inputs.getParamMap().get("backGroundColor"),"header are Not matched");
			backGroundColor_index++;
			CommonUtil.sop("Verified Background color for each slide. Total No of Slides : 5");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyBackGroundColorOfSlidesOnServicesPage failed -------------<"+e.getMessage());
		}catch(Exception e){
			log.error(">--------------Test case verifyBackGroundColorOfSlidesOnServicesPage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally{
			SoftAssertor.displayAssertErrors();
		}
	}

	// To verify the text on rotating wheels.

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =6,enabled = true , groups={"Regression"}, description="Verifies header on wheels on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "TextOnRotatingWheels")
	public void verifyHeaderOnWheelsOnpage(TestParameters inputs){
		try {
			CommonUtil.sop("Verifying text on Rotating wheels below slides");
			if(textOnWheel_index==0){
				headerOnWheels=null;
				webPage.getDriver().get("http://www.etouch.net/services/index.html#approach");
				Thread.sleep(3000);
				headerOnWheels=servicePage.getTextHeadingPropertiesOfHeader(ServicePageLocators.rotatingWheels);
			}
			verifyGivenValues(textOnWheel_index, headerOnWheels, inputs, "Heading on Rotating wheels");
			textOnWheel_index++;
			CommonUtil.sop("Verified text on Rotating wheels below slides");

		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyHeaderOnWheelsOnpage failed -------------<"+e.getMessage());
		}catch(Exception e){
			log.error(">--------------Test case verifyHeaderOnWheelsOnpage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally{
			SoftAssertor.displayAssertErrors();
		}
	}

	// To verify digital and web section content.

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =7,enabled = true , groups={"Regression"}, description="Verifies Digital Web Mobile Enterprise Section on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "DigitalWebMobileEnterpriseSection")
	public void verifyDigitalWebMobileEnterpriseSection(TestParameters inputs) {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#webAndMobile");
			log.info("Verifying the Header on DigitalWebMobileEnterpriseSection");
			String headerTextOnservicePage = servicePage.getDigitalWebMobileHeaderText();
			SoftAssertor.assertEquals(headerTextOnservicePage,inputs.getParamMap().get("Header"),"Header Texts are Not matched");
			String headerFontSizeOnservicePage = servicePage.getDigitalWebMobileFontSizeOfHeader();
			SoftAssertor.assertEquals(headerFontSizeOnservicePage,inputs.getParamMap().get("HeaderFontSize"),"Header Font Size are Not matched");
			String headerFontColorOnservicePage = servicePage.getDigitalWebMobileFontColorOfHeader();
			SoftAssertor.assertEquals(headerFontColorOnservicePage,inputs.getParamMap().get("HeaderFontColor"),"Header Font Color are Not matched");
			String headerFontFamilyOnservicePage = servicePage.getDigitalWebMobileFontFamilyOfHeader();
			SoftAssertor.assertEquals(getFontFamily(headerFontFamilyOnservicePage),inputs.getParamMap().get("family"),"Header Font Family are Not matched");
			log.info("Verified the Header on DigitalWebMobileEnterpriseSection");

			log.info("Verifying the paragraph1 on DigitalWebMobileEnterpriseSection");
			String paragraph1TextOnservicePage = servicePage.getDigitalWebMobilePara1Text();
			SoftAssertor.assertEquals(paragraph1TextOnservicePage,inputs.getParamMap().get("Para1"),"paragraph1 Texts are Not matched");
			String paragraph1FontSizeOnservicePage = servicePage.getDigitalWebMobileFontSizeOfPara1();
			SoftAssertor.assertEquals(paragraph1FontSizeOnservicePage,inputs.getParamMap().get("Para1FontSize"),"paragraph1 Font Size are Not matched");
			String paragraph1FontColorOnservicePage = servicePage.getDigitalWebMobileFontColorOfPara1();
			SoftAssertor.assertEquals(paragraph1FontColorOnservicePage,inputs.getParamMap().get("Para1FontColor"),"paragraph1 Font Color are Not matched");
			String paragraph1FontFamilyOnservicePage = servicePage.getDigitalWebMobileFontFamilyOfPara1();
			SoftAssertor.assertEquals(getFontFamily(paragraph1FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph1 Font Family are Not matched");
			log.info("Verified the paragraph1 on DigitalWebMobileEnterpriseSection");

			log.info("Verifying the paragraph2 on DigitalWebMobileEnterpriseSection");
			String paragraph2TextOnservicePage = servicePage.getDigitalWebMobilePara2Text();
			SoftAssertor.assertEquals(paragraph2TextOnservicePage,inputs.getParamMap().get("Para2"),"paragraph2 Texts are Not matched");
			String paragraph2FontSizeOnservicePage = servicePage.getDigitalWebMobileFontSizeOfPara2();
			SoftAssertor.assertEquals(paragraph2FontSizeOnservicePage,inputs.getParamMap().get("Para2FontSize"),"paragraph2 Font Size are Not matched");
			String paragraph2FontColorOnservicePage = servicePage.getDigitalWebMobileFontColorOfPara2();
			SoftAssertor.assertEquals(paragraph2FontColorOnservicePage,inputs.getParamMap().get("Para2FontColor"),"paragraph2 Font Color are Not matched");
			String paragraph2FontFamilyOnservicePage = servicePage.getDigitalWebMobileFontFamilyOfPara2();
			SoftAssertor.assertEquals(getFontFamily(paragraph2FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph2 Font Family are Not matched");
			log.info("Verified the paragraph2 on DigitalWebMobileEnterpriseSection");
		}
		catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseSection failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	
	

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =8,enabled = true , groups={"Regression"}, description="Verifies Digital Egineering Section on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "DigitalTestEngineeringSection")
	public void verifyDigitalEgineeringSection(TestParameters inputs) {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#testEngineering");
			log.info("Verifying the Header on Digital Engineering section");
			String headerTextOnservicePage = servicePage.getDigitalEngineeringSection();
			SoftAssertor.assertEquals(headerTextOnservicePage,inputs.getParamMap().get("Header"),"Header Texts are Not matched");
			String headerFontSizeOnservicePage = servicePage.getFontSizeOfHeader();
			SoftAssertor.assertEquals(headerFontSizeOnservicePage,inputs.getParamMap().get("HeaderFontSize"),"Header Font Size are Not matched");
			String headerFontColorOnservicePage = servicePage.getFontColorOfHeader();
			SoftAssertor.assertEquals(headerFontColorOnservicePage,inputs.getParamMap().get("HeaderFontColor"),"Header Font Color are Not matched");
			String headerFontFamilyOnservicePage = servicePage.getFontFamilyOfHeader();
			SoftAssertor.assertEquals(getFontFamily(headerFontFamilyOnservicePage),inputs.getParamMap().get("family"),"Header Font Family are Not matched");
			log.info("Verified the Header on Digital Engineering section");

			log.info("Verifying the paragraph1 on DigitalEngineeringSection");
			String paragraph1TextOnservicePage = servicePage.getDigitalEngineeringPara1Text();
			SoftAssertor.assertEquals(paragraph1TextOnservicePage,inputs.getParamMap().get("Para1"),"paragraph1 Texts are Not matched");
			String paragraph1FontSizeOnservicePage = servicePage.getDigitalEngineeringFontSizeOfPara1();
			SoftAssertor.assertEquals(paragraph1FontSizeOnservicePage,inputs.getParamMap().get("Para1FontSize"),"paragraph1 Font Size are Not matched");
			String paragraph1FontColorOnservicePage = servicePage.getDigitalEngineeringFontColorOfPara1();
			SoftAssertor.assertEquals(paragraph1FontColorOnservicePage,inputs.getParamMap().get("Para1FontColor"),"paragraph1 Font Color are Not matched");
			String paragraph1FontFamilyOnservicePage = servicePage.getDigitalEngineeringFontFamilyOfPara1();
			SoftAssertor.assertEquals(getFontFamily(paragraph1FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph1 Font Family are Not matched");
			log.info("Verified the paragraph1 on DigitalEngineering Section");

			log.info("Verifying the paragraph2 on DigitalEngineeringSection");
			String paragraph2TextOnservicePage = servicePage.getDigitalEngineeringPara2Text();
			SoftAssertor.assertEquals(paragraph2TextOnservicePage,inputs.getParamMap().get("Para2"),"paragraph2 Texts are Not matched");
			String paragraph2FontSizeOnservicePage = servicePage.getDigitalEngineeringFontSizeOfPara2();
			SoftAssertor.assertEquals(paragraph2FontSizeOnservicePage,inputs.getParamMap().get("Para2FontSize"),"paragraph2 Font Size are Not matched");
			String paragraph2FontColorOnservicePage = servicePage.getDigitalEngineeringFontColorOfPara2();
			SoftAssertor.assertEquals(paragraph2FontColorOnservicePage,inputs.getParamMap().get("Para2FontColor"),"paragraph2 Font Color are Not matched");
			String paragraph2FontFamilyOnservicePage = servicePage.getDigitalEngineeringFontFamilyOfPara2();
			SoftAssertor.assertEquals(getFontFamily(paragraph2FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph2 Font Family are Not matched");
			log.info("Verified the paragraph2 on DigitalEngineering Section");

			log.info("Verifying the paragraph3 on DigitalEngineeringSection");
			String paragraph3TextOnservicePage = servicePage.getDigitalEngineeringPara3Text();
			SoftAssertor.assertEquals(paragraph3TextOnservicePage,inputs.getParamMap().get("Para3"),"paragraph3 Texts are Not matched");
			String paragraph3FontSizeOnservicePage = servicePage.getDigitalEngineeringFontSizeOfPara3();
			SoftAssertor.assertEquals(paragraph3FontSizeOnservicePage,inputs.getParamMap().get("Para3FontSize"),"paragraph3 Font Size are Not matched");
			String paragraph3FontColorOnservicePage = servicePage.getDigitalEngineeringFontColorOfPara3();
			SoftAssertor.assertEquals(paragraph3FontColorOnservicePage,inputs.getParamMap().get("Para3FontColor"),"paragraph3 Font Color are Not matched");
			String paragraph3FontFamilyOnservicePage = servicePage.getDigitalEngineeringFontFamilyOfPara3();
			SoftAssertor.assertEquals(getFontFamily(paragraph3FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph3 Font Family are Not matched");
			log.info("Verified the paragraph3 on DigitalEngineering Section");

		}
		catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyDigitalEgineeringSection failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyDigitalEgineeringSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyDigitalEgineeringSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =9,enabled = true , groups={"Regression"}, description="Verifies Analytics Section on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "AnalyticsSection")
	public void verifyAnalyticsSection(TestParameters inputs) {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#analytics");
			log.info("Verifying the Header on Analytics page");
			String headerTextOnservicePage = servicePage.getAnalyticsHeaderText();
			SoftAssertor.assertEquals(headerTextOnservicePage,inputs.getParamMap().get("Header"),"Header Texts are Not matched");
			String headerFontSizeOnservicePage = servicePage.getAnalyticsFontSizeOfHeader();
			SoftAssertor.assertEquals(headerFontSizeOnservicePage,inputs.getParamMap().get("HeaderFontSize"),"Header Font Size are Not matched");
			String headerFontColorOnservicePage = servicePage.getAnalyticsFontColorOfHeader();
			SoftAssertor.assertEquals(headerFontColorOnservicePage,inputs.getParamMap().get("HeaderFontColor"),"Header Font Color are Not matched");
			String headerFontFamilyOnservicePage = servicePage.getAnalyticsFontFamilyOfHeader();
			SoftAssertor.assertEquals(getFontFamily(headerFontFamilyOnservicePage),inputs.getParamMap().get("family"),"Header Font Family are Not matched");
			log.info("Verified the Header on Analytics page");

			log.info("Verifying the paragraph on Analytics page");
			String paragraphTextOnservicePage = servicePage.getAnalyticsParaText();
			SoftAssertor.assertEquals(paragraphTextOnservicePage,inputs.getParamMap().get("Para"),"paragraph Texts are Not matched");
			String paragraphFontSizeOnservicePage = servicePage.getAnalyticsFontSizeOfPara();
			SoftAssertor.assertEquals(paragraphFontSizeOnservicePage,inputs.getParamMap().get("ParaFontSize"),"paragraph Font Size are Not matched");
			String paragraphFontColorOnservicePage = servicePage.getAnalyticsFontColorOfPara();
			SoftAssertor.assertEquals(paragraphFontColorOnservicePage,inputs.getParamMap().get("ParaFontColor"),"paragraph Font Color are Not matched");
			String paragraphFontFamilyOnservicePage = servicePage.getAnalyticsFontFamilyOfPara();
			SoftAssertor.assertEquals(getFontFamily(paragraphFontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph Font Family are Not matched");
			log.info("Verified the paragraph on Analytics page");

		}
		catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyAnalyticsSection failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyAnalyticsSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyAnalyticsSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	//To verify DevOps and Cloud Enablement section

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =10,enabled = true , groups={"Regression"}, description="Verifies DevOps Cloud Enablement Section on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "DevOpsCloudEnablementSection")
	public void verifyDevOpsCloudEnablementSection(TestParameters inputs) {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#DevOps");
			log.info("Verifying the Header on DevOps and Cloud Enablement section");
			String headerTextOnservicePage = servicePage.getDevOpsCloudHeaderText();
			SoftAssertor.assertEquals(headerTextOnservicePage,inputs.getParamMap().get("Header"),"Header Texts are Not matched");
			String headerFontSizeOnservicePage = servicePage.getDevOpsCloudFontSizeOfHeader();
			SoftAssertor.assertEquals(headerFontSizeOnservicePage,inputs.getParamMap().get("HeaderFontSize"),"Header Font Size are Not matched");
			String headerFontColorOnservicePage = servicePage.getDevOpsCloudFontColorOfHeader();
			SoftAssertor.assertEquals(headerFontColorOnservicePage,inputs.getParamMap().get("HeaderFontColor"),"Header Font Color are Not matched");
			String headerFontFamilyOnservicePage = servicePage.getDevOpsCloudFontFamilyOfHeader();
			SoftAssertor.assertEquals(getFontFamily(headerFontFamilyOnservicePage),inputs.getParamMap().get("family"),"Header Font Family are Not matched");
			log.info("Verified the Header on DevOps and Cloud Enablement section");

			log.info("Verifying the paragraph1 on DevOps and Cloud Enablement section");
			String paragraph1TextOnservicePage = servicePage.getDevOpsCloudPara1Text();
			SoftAssertor.assertEquals(paragraph1TextOnservicePage,inputs.getParamMap().get("Para1"),"paragraph1 Texts are Not matched");
			String paragraph1FontSizeOnservicePage = servicePage.getDevOpsCloudFontSizeOfPara1();
			SoftAssertor.assertEquals(paragraph1FontSizeOnservicePage,inputs.getParamMap().get("Para1FontSize"),"paragraph1 Font Size are Not matched");
			String paragraph1FontColorOnservicePage = servicePage.getDevOpsCloudFontColorOfPara1();
			SoftAssertor.assertEquals(paragraph1FontColorOnservicePage,inputs.getParamMap().get("Para1FontColor"),"paragraph1 Font Color are Not matched");
			String paragraph1FontFamilyOnservicePage = servicePage.getDevOpsCloudFontFamilyOfPara1();
			SoftAssertor.assertEquals(getFontFamily(paragraph1FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph1 Font Family are Not matched");
			log.info("Verified the paragraph1 on DevOps and Cloud Enablement section");

			log.info("Verifying the paragraph2 on DevOps and Cloud Enablement section");
			String paragraph2TextOnservicePage = servicePage.getDevOpsCloudPara2Text();
			SoftAssertor.assertEquals(paragraph2TextOnservicePage,inputs.getParamMap().get("Para2"),"paragraph2 Texts are Not matched");
			String paragraph2FontSizeOnservicePage = servicePage.getDevOpsCloudFontSizeOfPara2();
			SoftAssertor.assertEquals(paragraph2FontSizeOnservicePage,inputs.getParamMap().get("Para2FontSize"),"paragraph2 Font Size are Not matched");
			String paragraph2FontColorOnservicePage = servicePage.getDevOpsCloudFontColorOfPara2();
			SoftAssertor.assertEquals(paragraph2FontColorOnservicePage,inputs.getParamMap().get("Para2FontColor"),"paragraph2 Font Color are Not matched");
			String paragraph2FontFamilyOnservicePage = servicePage.getDevOpsCloudFontFamilyOfPara2();
			SoftAssertor.assertEquals(getFontFamily(paragraph2FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph2 Font Family are Not matched");
			log.info("Verified the paragraph2 on DevOps and Cloud Enablement section");

			log.info("Verifying the paragraph3 on DevOps and Cloud Enablement section");
			String paragraph3TextOnservicePage = servicePage.getDevOpsCloudPara3Text();
			SoftAssertor.assertEquals(paragraph3TextOnservicePage,inputs.getParamMap().get("Para3"),"paragraph3 Texts are Not matched");
			String paragraph3FontSizeOnservicePage = servicePage.getDevOpsCloudFontSizeOfPara3();
			SoftAssertor.assertEquals(paragraph3FontSizeOnservicePage,inputs.getParamMap().get("Para3FontSize"),"paragraph3 Font Size are Not matched");
			String paragraph3FontColorOnservicePage = servicePage.getDevOpsCloudFontColorOfPara3();
			SoftAssertor.assertEquals(paragraph3FontColorOnservicePage,inputs.getParamMap().get("Para3FontColor"),"paragraph3 Font Color are Not matched");
			String paragraph3FontFamilyOnservicePage = servicePage.getDevOpsCloudFontFamilyOfPara3();
			SoftAssertor.assertEquals(getFontFamily(paragraph3FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph3 Font Family are Not matched");
			log.info("Verified the paragraph3 on DevOps and Cloud Enablement section");
		}
		catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyDevOpsCloudEnablementSection failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyDevOpsCloudEnablementSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyDevOpsCloudEnablementSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	//To verify Security for Digital Enterprise section.
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class,priority =11,enabled = true , groups={"Regression"}, description="Verifies Security Digital Enterprise Section on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "SecurityForDigitalEnterpriseSection")
	public void verifySecurityDigitalEnterpriseSection(TestParameters inputs) {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#security");
			log.info("Verifying the Header on Security for Digital Enterprise Section");
			String headerTextOnservicePage = servicePage.getSecurityDigitalEnterpriseHeaderText();
			SoftAssertor.assertEquals(headerTextOnservicePage,inputs.getParamMap().get("Header"),"Header Texts are Not matched");
			String headerFontColorOnservicePage = servicePage.getSecurityDigitalEnterpriseFontColorOfHeader();
			SoftAssertor.assertEquals(headerFontColorOnservicePage,inputs.getParamMap().get("HeaderFontColor"),"Header Font Color are Not matched");
			String headerFontFamilyOnservicePage = servicePage.getSecurityDigitalEnterpriseFontFamilyOfHeader();
			SoftAssertor.assertEquals(getFontFamily(headerFontFamilyOnservicePage),inputs.getParamMap().get("family"),"Header Font Family are Not matched");
			log.info("Verified the Header on Security for Digital Enterprise Section");

			log.info("Verifying the paragraph1 on Security for Digital Enterprise Section");
			String paragraph1TextOnservicePage = servicePage.getSecurityDigitalEnterprisePara1Text();
			SoftAssertor.assertEquals(paragraph1TextOnservicePage,inputs.getParamMap().get("Para1"),"paragraph1 Texts are Not matched");
			String paragraph1FontColorOnservicePage = servicePage.getSecurityDigitalEnterpriseFontColorOfPara1();
			SoftAssertor.assertEquals(paragraph1FontColorOnservicePage,inputs.getParamMap().get("Para1FontColor"),"paragraph1 Font Color are Not matched");
			String paragraph1FontFamilyOnservicePage = servicePage.getSecurityDigitalEnterpriseFontFamilyOfPara1();
			SoftAssertor.assertEquals(getFontFamily(paragraph1FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph1 Font Family are Not matched");
			log.info("Verified the paragraph1 on Security for Digital Enterprise Section");

			log.info("Verifying the paragraph2 on Security for Digital Enterprise Section");
			String paragraph2TextOnservicePage = servicePage.getSecurityDigitalEnterprisePara2Text();
			SoftAssertor.assertEquals(paragraph2TextOnservicePage,inputs.getParamMap().get("Para2"),"paragraph2 Texts are Not matched");
			String paragraph2FontColorOnservicePage = servicePage.getSecurityDigitalEnterpriseFontColorOfPara2();
			SoftAssertor.assertEquals(paragraph2FontColorOnservicePage,inputs.getParamMap().get("Para2FontColor"),"paragraph2 Font Color are Not matched");
			String paragraph2FontFamilyOnservicePage = servicePage.getSecurityDigitalEnterpriseFontFamilyOfPara2();
			SoftAssertor.assertEquals(getFontFamily(paragraph2FontFamilyOnservicePage),inputs.getParamMap().get("family"),"paragraph2 Font Family are Not matched");
			log.info("Verified the paragraph2 on Security for Digital Enterprise Section");
		}
		catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifySecurityDigitalEnterpriseSection failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifySecurityDigitalEnterpriseSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifySecurityDigitalEnterpriseSection failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =12,enabled = true , groups={"Regression"}, description="Verifies Digital Web Mobile Enterprise Overlay Headers on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "DigitalWebMobileEnterpriseOverlayHeaders")
	public void verifyDigitalWebMobileEnterpriseOverlaySectionHeaders(TestParameters inputs) {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#webAndMobile");
			log.info("Verifying the Headers on DigitalWebMobileEnterprise Overlay");
			if(headerOnDigitalMobileOverlay_index==0){
				headerOnDigitalMobileOverlay = null;
				Thread.sleep(3000);
				headerOnDigitalMobileOverlay = servicePage.verifyDigitalWebMobileOverlayHeaders();				
			}
			if(headerOnDigitalMobileOverlay_index==2)
				headerOnDigitalMobileOverlay_index++;
			verifyGivenValues(headerOnDigitalMobileOverlay_index, headerOnDigitalMobileOverlay, inputs,"Heading on Overlay");
			headerOnDigitalMobileOverlay_index++;
			//SoftAssertor.assertEquals(headerTextOnservicePage,inputs.getParamMap().get("Header"),"Header Texts are Not matched");
			log.info("Verified the Headers on DigitalWebMobileEnterprise Overlay");
		}
		catch (AssertionError e) {
			//logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionHeaders failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionHeaders failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionHeaders failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =13,enabled = true , groups={"Regression"}, description="Verifies Digital Web Mobile Enterprise Overlay Images Titles")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "DigitalWebMobileEnterpriseOverlayTitles")
	public void verifyDigitalWebMobileEnterpriseOverlaySectionTitles(TestParameters inputs) {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#webAndMobile");
			log.info("Verifying the Titles on DigitalWebMobileEnterprise Overlay");
			if(titleOnDigitalMobileOverlay_index==0){
				titleOnDigitalMobileOverlay = null;
				Thread.sleep(3000);
				titleOnDigitalMobileOverlay = servicePage.verifyDigitalWebMobileOverlayTitles();				
			}
			
			verifyGivenValues(titleOnDigitalMobileOverlay_index, titleOnDigitalMobileOverlay, inputs,"Titles on Overlay");
			titleOnDigitalMobileOverlay_index++;
			//SoftAssertor.assertEquals(headerTextOnservicePage,inputs.getParamMap().get("Header"),"Header Texts are Not matched");
			log.info("Verified the the Titles on DigitalWebMobileEnterprise Overlay");
		}
		catch (AssertionError e) {
			//logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionTitles failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionTitles failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionTitles failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 14, enabled = true ,groups={"Regression"}, description="Verifies Digital Web Mobile Enterprise Overlay Images")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "DigitalWebMobileEnterpriseOverlayImages")
	public void verifyDigitalWebMobileEnterpriseOverlaySectionImages(TestParameters inputs)throws PageException {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#webAndMobile");
			log.info("VerifyingImage source in DigitalWebMobileEnterpriseOverlaySection");
			if(img_index==0){
				Thread.sleep(3000);
				imgSourceDetails = servicePage.getImageSrcOnDigitalWebMobileEnterprizeOverlay();
			}
			log.info(imgSourceDetails.get(img_index).replace("url(", "").replace(")", "").replaceAll("\\\"", ""));
			log.info(inputs.getParamMap().get("imageSource"));
			SoftAssertor.assertEquals(imgSourceDetails.get(img_index).replace("url(", "").replace(")", "").replaceAll("\\\"", ""), inputs.getParamMap().get("imageSource"));
			img_index++;			
			/*if(img_index==32)
				servicePage.closeDigitalWebMobileEnterprizeOverlay();*/
			log.info("Verified image source in DigitalWebMobileEnterpriseOverlaySection");
		} catch (AssertionError e) {
			//logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionImages failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionImages failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyDigitalWebMobileEnterpriseOverlaySectionImages failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =15,enabled = true , groups={"Regression"}, description="Verifies Analytics Overlay Headers on Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "AnalyticsOverlayHeaders")
	public void verifyAnalyticsOverlaySectionHeaders(TestParameters inputs) {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#analytics");
			log.info("Verifying the Headers on Analytics Overlay");
			if(headerOnAnalyticsOverlay_index==0){
				headerOnAnalyticsOverlay = null;
				Thread.sleep(3000);
				headerOnAnalyticsOverlay = servicePage.verifyAnalyticsOverlayHeaders();				
			}
			
			verifyGivenValues(headerOnAnalyticsOverlay_index, headerOnAnalyticsOverlay, inputs,"Heading on Activity Overlay");
			headerOnAnalyticsOverlay_index++;
			//SoftAssertor.assertEquals(headerTextOnservicePage,inputs.getParamMap().get("Header"),"Header Texts are Not matched");
			log.info("Verified the Headers on Analytics Overlay");
		}
		catch (AssertionError e) {
			//logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyAnalyticsOverlaySectionHeaders failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyAnalyticsOverlaySectionHeaders failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyAnalyticsOverlaySectionHeaders failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority =16,enabled = true , groups={"Regression"}, description="Verifies Image Titles on Analytics Overlay of Services page")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "AnalyticsOverlayTitles")
	public void verifyAnalyticsOverlaySectionTitles(TestParameters inputs) {
		try {			
			webPage.getDriver().get("http://www.etouch.net/services/index.html#analytics");
			log.info("Verifying the Titles on Analytics Overlay");
			if(titleOnAnalyticsOverlay_index==0){
				titleOnAnalyticsOverlay = null;
				Thread.sleep(3000);
				titleOnAnalyticsOverlay = servicePage.verifyAnalyticsOverlayTitles();				
			}
			
			verifyGivenValues(titleOnAnalyticsOverlay_index, titleOnAnalyticsOverlay, inputs,"Titles on Overlay");
			titleOnAnalyticsOverlay_index++;
			log.info("Verified the the Titles on Analytics Overlay");
		}
		catch (AssertionError e) {
			//logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyAnalyticsOverlaySectionTitles failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyAnalyticsOverlaySectionTitles failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyAnalyticsOverlaySectionTitles failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}
	
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 17, enabled = true ,groups={"Regression"}, description="Verifies Analytics Overlay Images")
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "ServicePage", dataKey = "AnalyticsOverlayImages")
	public void verifyAnalyticsOverlaySectionImages(TestParameters inputs)throws PageException {
		try {
			webPage.getDriver().get("http://www.etouch.net/services/index.html#analytics");
			log.info("Verifying Image source in Analytics Overlay Section");
			if(imgIndexAnalyticsOverlay==0){
				Thread.sleep(3000);
				imgSourceDetailsAnalyticsOverlay = servicePage.getImageSrcOnAnalyticsOverlay();
			}
			log.info(imgSourceDetailsAnalyticsOverlay.get(imgIndexAnalyticsOverlay).replace("url(", "").replace(")", "").replaceAll("\\\"", ""));
			log.info(inputs.getParamMap().get("imageSource"));
			SoftAssertor.assertEquals(imgSourceDetailsAnalyticsOverlay.get(imgIndexAnalyticsOverlay).replace("url(", "").replace(")", "").replaceAll("\\\"", ""), inputs.getParamMap().get("imageSource"));
			imgIndexAnalyticsOverlay++;			
			/*if(imgIndexAnalyticsOverlay==41)
				servicePage.closeAnalyticsOverlay();*/
			log.info("Verified image source in Analytics Overlay Section");
		} catch (AssertionError e) {
			//logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case verifyAnalyticsOverlaySectionImages failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case verifyAnalyticsOverlaySectionImages failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case verifyAnalyticsOverlaySectionImages failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}

	public void verifyGivenValues(int index, Map<String,ArrayList<String>> actualValues,TestParameters inputs,String msg){
		log.info(actualValues.get("text").get(index).replaceAll("\\r\\n|\\r|\\n", ""));
		log.info(inputs.getParamMap().get("header"));
		SoftAssertor.assertEquals(actualValues.get("text").get(index).trim().replaceAll("\\r\\n|\\r|\\n", ""), inputs.getParamMap().get("header"),msg+" text header not matched.");
		SoftAssertor.assertEquals(actualValues.get("color").get(index), inputs.getParamMap().get("color"),msg+" text color not matched.");
		SoftAssertor.assertEquals(actualValues.get("size").get(index), inputs.getParamMap().get("size"),msg+" text size not matched.");
		//SoftAssertor.assertEquals(getFontFamily(actualValues.get("family").get(index)),inputs.getParamMap().get("family").replaceAll("\\s",""),msg+"font family not matched.");
		SoftAssertor.assertEquals(getFontFamily(actualValues.get("family").get(index)),inputs.getParamMap().get("family"),msg+"font family not matched.");
	}
	public void logDefectOnTestFailure(TestParameters inputs,AssertionError e){
		Jira.setDefectInformation(inputs.getParamMap().get("summary"),inputs.getParamMap().get("description"));
		servicePage.logAndCreateADefect( TestBedManager.INSTANCE.getDefect(),url3,  issueUrl, username,  password, keys);
		SoftAssertor.addVerificationFailure(e.getMessage());
	}
}
package com.etouch.net.tests;

import java.util.List;

import org.apache.commons.logging.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.etouch.net.common.BaseTest;
import com.etouch.net.pages.CareersPage;
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

/**
 * 
 * @author mkanchetty
 *
 */
@IExcelDataFiles(excelDataFiles = { "file1=src/test/resources/testdata/eTouchTestData.xls" })
public class CareersPageTest extends BaseTest{

	/** The web page. */
	private WebPage webPage;

	/** The main page. */
	private CareersPage careersPage;

	private String baseURL= TestBedManagerConfiguration.getInstance().getWebConfig().getURL();
	private String subURL="/careers/index.html";


	static Log log = LogUtil.getLog(CareersPageTest.class);
	/**
	 * Prepare before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass(alwaysRun=true)
	public void prepareBeforeClass() throws Exception {
		try {

			webPage = new WebPage();
			careersPage = new CareersPage(baseURL+subURL, webPage);
		}

		catch (Exception e) {
			log.info("Error is:" + e);
			SoftAssertor.addVerificationFailure(e.getMessage());
		}
	}


	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 1 , enabled=true,groups={"Regression","Smoke","Sanity"})
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "CareersPage", dataKey = "LandingPage")
	public void verifyLandingPageTest(TestParameters inputs){
		try{
			log.info("Verifying Careers Page LandingPage");
			SoftAssertor.assertEquals(webPage.getCurrentUrl(), inputs.getParamMap().get("URL"), "Careers Page URL not matched");
			SoftAssertor.assertEquals(webPage.getDriver().getTitle(), inputs.getParamMap().get("Title"), "Careers Page Title not matched");
			log.info("Verified Careers Page LandingPage");
		}catch(AssertionError e){
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case Careers LandingPage failed -------------<"+e.getMessage());
		}catch(Exception e){
			log.error(">--------------Test case Careers  LandingPage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} 
	}

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 2 , enabled=true,groups={"Regression","Sanity"})
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "CareersPage", dataKey = "CareersParaText")
	public void testVerifyCareersParaText(TestParameters inputs){
		try{
			log.info("Verifying Careers Page Career Header Text");
			List<String> headerText = careersPage.verifyCareersHeaderText();
			verifyGivenValues(headerText, inputs, "CareersText", "color", "size", "Careers Page Career Header text");
			log.info("Verified Careers Page Career Header Text");

			log.info("Verifying Careers Page Career Paragraph Text");
			List<String> paraText = careersPage.verifyCareersParaText();
			verifyGivenValues(paraText, inputs, "ParaText", "para_color", "para_size","Careers Page Paragraph text ");

			log.info("Verified Careers Page Career Paragraph Text");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case Careers Paragraph text -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case Careers Paragraph text failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case Careers Paragraph text failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}

	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 3 , enabled=true ,groups={"Regression"})
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "CareersPage", dataKey = "Address")
	public void testVerifyWhereToApplyText(TestParameters inputs){
		try {
			log.info("Verifying Careers Page Where to apply Header Text");
			List<String> whereToApply = careersPage.verifywhereToApplyHeaderText();
			verifyGivenValues(whereToApply, inputs, "headerText", "color", "size", "Where To Apply text ");
			log.info("Verified Careers Page Where to apply Header Text");

			log.info("Verifying Careers Page US Address Text");
			List<String> addressUS = careersPage.verifyAddress();
			verifyGivenValues(addressUS, inputs, "US", "color_address", "size_address",  "US Address text ");
			log.info("Verified Careers Page US Address Text");

			log.info("Verifying Careers Page India Address Text");
			List<String> addressIndia = careersPage.verifyAddress1();
			verifyGivenValues(addressIndia, inputs, "India", "color_address", "size_address", "US Address text ");
			log.info("Verified Careers Page India Address Text");

			String flagUS = careersPage.getUSFlagImageSRC();
			String flagIndia = careersPage.getIndiaFlagImageSRC();

			log.info("Verifying Careers Page US & India Logos ");
			SoftAssertor.assertEquals(flagUS, inputs.getParamMap().get("USFlag"), "US Flag not matched");
			SoftAssertor.assertEquals(flagIndia, inputs.getParamMap().get("IndiaFlag"), "India Flag not matched");
			log.info("Verified Careers Page US & India Logos");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case WhereToApply text -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case WhereToApply text failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case WhereToApply text failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}


	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 4 , enabled=true ,groups={"Regression","Sanity"})
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "CareersPage", dataKey = "CarrerImage")
	public void testVerifyCareerBackGroundImage(TestParameters inputs){
		try {
			log.info("Verifying Careers Page Background Image");
			String bgImage = careersPage.verifyCareerBackGroundImage();
			log.info("bgImage:"+bgImage);
			String bgCareerImage=null;
			if(bgImage.length()!=0)
				bgCareerImage = bgImage.replace("url(","").replace(")","").replaceAll("\\\"", "");
			SoftAssertor.assertEquals(bgCareerImage, inputs.getParamMap().get("Image"), "Career Background Image not matched");

			log.info("Verified Careers Page Background Image");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case CareerBackGroundImage failed -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case CareerBackGroundImage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case CareerBackGroundImage failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}


	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 5, enabled=true ,groups={"Regression"})
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "CareersPage", dataKey = "Opportunity")
	public void testVerifyOpportunity(TestParameters inputs){
		try {
			log.info("Verifying Careers Page Opportunity header");
			List<String> oppText = careersPage.getOpportunityText();
			verifyGivenValues(oppText, inputs, "Header", "color", "size", "Opportunity text ");

			List<String> headerText = careersPage.getopportunityHeaderText();
			verifyGivenValues(headerText, inputs, "TableHeader", "color_header", "size_header",  "Opportunity Listing Headers text ");
			log.info("Verified Careers Page Opportunity header");
		}catch (AssertionError e) { 
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case Opportunity text -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case Opportunity text failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case Opportunity text failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}


	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 6, enabled=true ,groups={"Regression"})
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "CareersPage", dataKey = "OpportunityDesc")
	public void testVerifyOpportunityDescription(TestParameters inputs){
		try {
			log.info("Verifying Careers Page Opportunity Listing link");
			String  vfontFamily = null;
			List<String> descLink = careersPage.getOpportunityDescription();
			vfontFamily = getFontFamily(descLink.get(3));
			SoftAssertor.assertEquals(descLink.get(1), inputs.getParamMap().get("color"),"Opportunity Listing link font color is Not matched");
			SoftAssertor.assertEquals(descLink.get(2), inputs.getParamMap().get("size"),"Opportunity Listing link font size is Not matched");
			SoftAssertor.assertEquals(vfontFamily, inputs.getParamMap().get("family"), "Opportunity Listing link font family is Not matched");
			log.info("Verified Careers Page Opportunity Listing link");

			log.info("Verifying Opportunity Description Right Panel text");
			List<String> jobContactText = careersPage.getJobContactDetails();
			verifyGivenValues(jobContactText, inputs, "ContactText", "color_cText", "size_cText", "Opportunity Description Right Panel text ");
			log.info("Verified Opportunity Description Right Panel text");

			log.info("Verifying Opportunity Description Right Panel Back To Positions text");
			List<String> backToPositions = careersPage.getBackToPositionsLink();
			verifyGivenValues(backToPositions, inputs, "BackToPositions", "color_cText", "size_cText", "Opportunity Description Right Panel back To Positions text ");
			SoftAssertor.assertEquals(backToPositions.get(4), inputs.getParamMap().get("BackToPositions_Header")," Opportunity Description Right Panel back To Positions Link text Not matched");
			log.info("Verified Opportunity Description Right Panel Back To Positions text");

			log.info("Verifying Opportunity Description Right Panel More Job Positions text");
			List<String> moreJobPositions = careersPage.getMoreJobPositionsLink();
			verifyGivenValues(moreJobPositions, inputs, "MoreJobPositions", "color_cText", "size_cText",  "Opportunity Description Right Panel more Job Positions text ");
			SoftAssertor.assertEquals(moreJobPositions.get(4), inputs.getParamMap().get("MoreJobPositions_Header")," Opportunity Description Right Panel More Job Positions Link text Not matched");

			log.info("Verified Opportunity Description Right Panel More Job Positions text");
		}catch (AssertionError e) {
			logDefectOnTestFailure(inputs,e);
			log.error(">--------------Test case OpportunityDescription text -------------<"+e.getMessage());
		}catch (PageException e) {
			log.error(">--------------Test case OpportunityDescription text failed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			log.error(">--------------Test case OpportunityDescription tfailed -------------<"+e.getMessage());
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		} finally
		{
			SoftAssertor.displayAssertErrors();
		}
	}

	public void verifyGivenValues(List<String> actualValues,TestParameters inputs,String header,String color,String size,String msg){
		String  fontFamily = null;
		fontFamily = getFontFamily(actualValues.get(3));
		SoftAssertor.assertEquals(actualValues.get(0), inputs.getParamMap().get(header), msg+"Not matched");
		SoftAssertor.assertEquals(actualValues.get(1), inputs.getParamMap().get(color), msg+" font color is Not matched");
		SoftAssertor.assertEquals(actualValues.get(2), inputs.getParamMap().get(size), msg+" font size is Not matched");
		SoftAssertor.assertEquals(fontFamily, inputs.getParamMap().get("family"), msg+" font family is Not matched");
	}

	public void logDefectOnTestFailure(TestParameters inputs,AssertionError e){
		Jira.setDefectInformation(inputs.getParamMap().get("summary"),inputs.getParamMap().get("description"));
		careersPage.logAndCreateADefect( TestBedManager.INSTANCE.getDefect(),url3,  issueUrl, username,  password, keys);
		SoftAssertor.addVerificationFailure(e.getMessage());
	}

}

package utilities;

// this is a listeners class used to generate the extent report

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    public void onStart(ITestContext testContext){

        String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date()); // time Stamp
        String repName ="Test-Report-"+timeStamp+".html";
        // to be able to generate the report.html in intelliJ , we will have to add an entry to our project
        // we will click on run located the top menu bar , then click on Edit Configurations , then select our project's testNG.xml file
        // then click on listeners , hit the plus button on the bottom then add this entry "org.testng.reporters.TestHTMLReporter" , then hit apply and okay
        // After executing our program from the testNG.xml , the report will be created

        htmlReporter= new ExtentHtmlReporter("/Users/billalyahiaoui/IdeaProjects/inetBankingV1_FrameWork/test-output"+repName); // in here we specified the location of our report
        htmlReporter.loadXMLConfig("/Users/billalyahiaoui/IdeaProjects/inetBankingV1_FrameWork/test-output/extent-config.xml");
        //i created the "extent-config.xml" file in my project , in order for the file to be found , but the file came out empty , i copied a template from google and pasted it in it
        // if the "extent-config.xml" then our program wont be executed

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user","Billal");

        htmlReporter.config().setDocumentTitle("InetBanking Test Project");// title of the report
        htmlReporter.config().setReportName("Functional Test Automation Report");// name of the report
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
        htmlReporter.config().setTheme(Theme.DARK);

    }

    public void onTestSuccess(ITestResult tr){
        logger=extent.createTest(tr.getName());// create new entry in the report
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));// send the passed information
    }

    public void onTestFailure(ITestResult tr){
        logger=extent.createTest(tr.getName());
        logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));

        String ScreenShotPath = "/Users/billalyahiaoui/IdeaProjects/inetBankingV1_FrameWork/ScreenShots"+tr.getName()+".png";
        File f = new File(ScreenShotPath);

        if (f.exists()){
            try {
                logger.fail("ScreenShot is below:"+logger.addScreenCaptureFromPath(ScreenShotPath));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public void onTestSkipped(ITestResult tr){
        logger= extent.createTest(tr.getName());// create new entry in the report
        logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext){
        extent.flush();
    }

    ///Users/billalyahiaoui/IdeaProjects/inetBankingV1_FrameWork/test-output

}

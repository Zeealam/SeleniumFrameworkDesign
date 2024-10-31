package zeeshan.SeleniumFrameworkDesign.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count =0;
	int maxTry = 1;
	@Override
	public boolean retry(ITestResult result) {//when this method return true then this method return test again
		if(count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}

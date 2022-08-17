package retryConfig;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestFailed implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount = 2;
	
	public boolean retry (ITestResult Result) {
		if(retryCount < maxRetryCount) {
			System.out.println("Retry testname: " + Result.getName() + " with " + (retryCount + 1) + " times.");
			retryCount++;
			return true;
		}
		return false;
	}
}
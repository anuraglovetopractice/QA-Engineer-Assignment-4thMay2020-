package qaEnginnerPaytmInsider.qaEngineerPaytmInsider;

import org.testng.annotations.BeforeMethod;
import httpRequests.HTTPBase;

public class TestBase {
	
	@BeforeMethod
	void setup() {
		HTTPBase.setBaseUri();
	}

}

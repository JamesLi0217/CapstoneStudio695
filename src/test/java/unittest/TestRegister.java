package unittest;

import rpc.Register;

import org.junit.Assert;
//import static org.junit.Assert.assertEquals;
//
//import java.io.IOException;
//import java.net.URI;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.http.Header;
//import org.apache.http.HeaderIterator;
//import org.apache.http.HttpRequest;
//import org.apache.http.ProtocolVersion;
//import org.apache.http.RequestLine;
//import org.apache.http.params.HttpParams;
import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;


public class TestRegister {
	private static final String WEBSITE_URL = "http://localhost:8080/Jobs";
	WebTester webTester;
	
	
	@Before
    public void start() {
        webTester = new WebTester();
        webTester.setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        webTester.getTestContext().setBaseUrl(WEBSITE_URL);
        System.out.println(1);
    }
	
	@Test
    public void validRegisterSucceeds() throws Exception {
		System.out.println(2);
		webTester.setScriptingEnabled(false);
		System.out.println(3);
        webTester.beginAt("/index.html");
        System.out.println(4);
        webTester.clickButton("register-form-btn");
//        System.out.println(webTester.getTestContext().getPassword());
        webTester.setTextField("user_id","Tom");
        System.out.println(41);
        webTester.setTextField("password","Tom666!");

        webTester.setTextField("first_name", "Tom");
        webTester.setTextField("last_name", "Lee");
        System.out.println(5);
        System.out.println(webTester.getTestContext());
        webTester.submit();
//        webTester.notify();
        System.out.println(webTester.getTestContext());
        webTester.assertTextPresent("OK");
//        Assert.assertTrue(webTester.getServerResponse().contains("OK"));
    }
}

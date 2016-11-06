package tests.environment;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;

import static tests.environment.BrowserDriver.close;

/**
 * Created by sritej583 on 5/11/16.
 */
public class BrowserDriver {
    private static WebDriver mDriver;

    public synchronized static WebDriver getCurrentDriver() {
        if (mDriver == null) {
            try {
                mDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), (Capabilities) new FirefoxProfile());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
        }
        return mDriver;
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            close();
        }
    }

    public static void close() {
        try {
            getCurrentDriver().quit();
            mDriver = null;
        } catch (UnreachableBrowserException e) {
            System.out.println(e.getMessage());
        }
    }
}

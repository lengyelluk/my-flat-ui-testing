package com.lengyel.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserCapabilities {

    public DesiredCapabilities caps;

    public DesiredCapabilities buildDesiredCapabilities() {
        caps = new DesiredCapabilities();
        //for local testing
        caps.setCapability("browserstack.local", "true");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "74.0");
        caps.setCapability("resolution", "1280x800");
        caps.setCapability("project", "New My Flat App");
        caps.setCapability("build", "Smoke Tests");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "3.10.0");
        return caps;
    }

}
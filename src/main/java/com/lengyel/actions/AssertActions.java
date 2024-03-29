package com.lengyel.actions;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class AssertActions {

    private static final Logger logger = LogManager.getLogger(AssertActions.class);

    private List<Error> verificationErrors;
    public static SoftAssert verifications;
    private static List<String> checkPoints;

    public AssertActions() {
        if(verifications == null) {
            verifications = new SoftAssert();
        }
        checkPoints = new ArrayList<String>();
        setVerificationErrors(new ArrayList<Error>());
    }

    public List<Error> getVerificationErrors() {
        return verificationErrors;
    }

    public void setVerificationErrors(List<Error> verificationErrors) {
        this.verificationErrors = verificationErrors;
    }

    public void assertEquals(Object currentValue, Object expectedValue, String message) {
        logger.info("Assert for Equality: Current:" + currentValue + " against expected: " + expectedValue);
        Assert.assertEquals(currentValue, expectedValue, message);
        checkPoints.add("[OK] " + message);
    }

    public void assertEquals(Object currentValue, Object expectedValue) {
        assertEquals(currentValue, expectedValue, "");
    }

    public void assertEquals(String currentValue, String expectedValue, String message) {
        logger.info("Assert for Equality: Current:" + currentValue + " against expected: " + expectedValue);
        Assert.assertEquals(currentValue, expectedValue, message);
        checkPoints.add("[OK] " + message);
    }

    public void assertEquals(String currentValue, String expectedValue) {
        assertEquals(currentValue, expectedValue, "");
    }

    public void assertEquals(int currentValue, int expectedValue, String message) {
        logger.info("Assert for Equality: Current:" + currentValue + " against expected: " + expectedValue);
        Assert.assertEquals(currentValue, expectedValue, message);
    }

    public void assertEquals(int currentValue, int expectedValue) {
        assertEquals(currentValue, expectedValue, "");
    }

    public void verifyEquals(String currentValue, String expectedValue) {
        verifyEquals(currentValue, expectedValue, "");
    }

    public void verifyEquals(String currentValue, String expectedValue, String message) {
        logger.info("Verify for Equality: Current: " + currentValue + " against expected: " + expectedValue);
        //boolean and go to existing one
        boolean condition = currentValue.equals(expectedValue);
        verifyTrue(condition, "Verify for Equality: Current: " + currentValue + " against expected: " + expectedValue);
    }

    public void verifyEquals(int currentValue, int expectedValue, String message) {
        logger.info(message + " Verify for Equality: Current: " + currentValue + " against expected: " + expectedValue);
        //boolean and go to existing one
        boolean condition = currentValue == expectedValue;
        verifyTrue(condition, message + " Verify for Equality: Current: " + currentValue + " against expected: " + expectedValue);
    }

    public static void checkForVerificationErrors(SessionId sessionId) throws Exception {
        URI uri = new URI("https://lukaslengyel1:NuSvcbafD6XJubzUBZbW@api.browserstack.com/automate/sessions/" + sessionId + ".json");
        HttpPut putRequest = new HttpPut(uri);
      
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        
        nameValuePairs.add((new BasicNameValuePair("reason", "")));
        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
      
        HttpClientBuilder.create().build().execute(putRequest);
        
        if (verifications != null) {
            try {
                verifications.assertAll();
                nameValuePairs.add((new BasicNameValuePair("status", "passed")));
            } catch (Error e) {
                nameValuePairs.add((new BasicNameValuePair("status", "failed")));
                throw e;
            } finally {
                putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
              
                HttpClientBuilder.create().build().execute(putRequest);
                verifications = new SoftAssert();
            }
        }
    }

    public static void mark() throws URISyntaxException, UnsupportedEncodingException, IOException {
        URI uri = new URI("https://lukaslengyel1:NuSvcbafD6XJubzUBZbW@api.browserstack.com/automate/sessions/<session-id>.json");
        HttpPut putRequest = new HttpPut(uri);
      
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add((new BasicNameValuePair("status", "completed")));
        nameValuePairs.add((new BasicNameValuePair("reason", "")));
        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
      
        HttpClientBuilder.create().build().execute(putRequest);
      }



    public static String checkForVerificationErrorMessages(SessionId sessionId) throws Exception {
        try {
            checkForVerificationErrors(sessionId);
        } catch (Error e) {
            return e.getMessage().replace("[ERROR] The following asserts failed:\n", "");
        }
        return "";
    }

    public void assertTrue(boolean checkCondition) {
        assertTrue(checkCondition, "");
    }

    public void assertFalse(boolean checkCondition) {
        assertFalse(checkCondition, "");
    }

    public void assertTrue(boolean checkCondition, String message) {
        logger.info("Assert for True: Current:" + checkCondition);
        logger.info(message);
        Assert.assertTrue(checkCondition, message);
        checkPoints.add("[OK] " + message);
    }

    public void assertFalse(boolean checkCondition, String message) {
        Assert.assertFalse(checkCondition, message);
        checkPoints.add("[OK] " + message);
    }

    public void assertNotNull(Object currentValue, String message) {
        Assert.assertNotNull(currentValue, message);
        checkPoints.add("[OK] " + message);
    }

    public void assertNotNull(Object currentValue) {
        assertNotNull(currentValue, "");
    }

    public void verifyTrue(boolean checkCondition) {
        verifyTrue(checkCondition, "");
    }

    public void verifyTrue(boolean checkCondition, String message) {
        try {
            if (checkCondition) {
                checkPoints.add("[OK] " + message);
            } else {
                checkPoints.add("[NOK] " + message);
                logger.error("[ERROR] " + message + ". Expected: true. Actual: false");
            }
            verifications.assertTrue(checkCondition, message + "\n");
        } catch (Error e) {
            System.err.println("Failed");
            verificationErrors.add(e);
            checkPoints.add("[ERROR] " + message);
        }
    }

    public void verifyEquals(BigDecimal currentValue, BigDecimal expectedValue, String message) {
        logger.info("Verify for Equality: Current:" + currentValue + " against expected: " + expectedValue);
        try {
            verifications.assertEquals(currentValue, expectedValue, message + "\n");
            checkPoints.add("[OK] " + message);
        } catch (Error e) {
            System.err.println("Failed");
            verificationErrors.add(e);
            checkPoints.add("[ERROR] " + message);
        }
    }

    public List<String> getCheckPoints() {
        return checkPoints;
    }

    public static String getCheckPointsString() {
        StringBuilder sb = new StringBuilder();
        for (String cp : checkPoints) {
            sb.append(cp + "\n");
        }
        checkPoints = new ArrayList<String>();
        return sb.toString();
    }

}

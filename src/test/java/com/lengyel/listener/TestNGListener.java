package com.lengyel.listener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    
    ArrayList<NameValuePair> nameValuePairs;    

    public void mark(ITestResult iTestResult) throws URISyntaxException, UnsupportedEncodingException, IOException {
        String sessionId = iTestResult.getTestContext().getAttribute("sessionId").toString();
        URI uri = new URI("https://lukaslengyel1:NuSvcbafD6XJubzUBZbW@api.browserstack.com/automate/sessions/" + sessionId + ".json");
        HttpPut putRequest = new HttpPut(uri);
        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpClientBuilder.create().build().execute(putRequest);
      }
    
    @Override
    public void onTestStart(ITestResult iTestResult) {
        nameValuePairs = new ArrayList<NameValuePair>();
        System.out.println("*******Test started*********  " + iTestResult.getName());

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        nameValuePairs.add((new BasicNameValuePair("status", "passed")));
        try { 
            mark(iTestResult);
        } catch (Exception e) {

        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        nameValuePairs.add((new BasicNameValuePair("status", "failed")));
        System.out.println("this one test failed: " + iTestResult.getName());
        try { 
            mark(iTestResult);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}

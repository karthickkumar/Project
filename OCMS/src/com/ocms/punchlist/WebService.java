package com.ocms.punchlist;

import java.io.InputStream;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.xml.handler.XMLHandler;

import android.util.Log;
 
public class WebService {
    //Namespace of the Webservice - can be found in WSDL
    private static String NAMESPACE = "http://tempuri.org/";
    //Webservice URL - WSDL File location    
    private static String URL = "http://mmhemobileservices.iscistech.com/UserAuthentication.svc";//Make sure you changed IP address
    //SOAP Action URI again Namespace + Web method name
    private static String SOAP_ACTION = "http://tempuri.org/IUserAuthentication/IsValidUser";
    
    
    public static void invoke(){
         String response = null;
         try {
             HttpClient httpClient = new DefaultHttpClient();
             HttpPost httpPost = new HttpPost(URL);
             String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                     "<SOAP-ENV:Envelope \n"+
                     "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n"+
                     "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n"+
                     "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" \n"+
                     "SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" \n"+
                     "xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"> \n"+
                     "<SOAP-ENV:Body> \n"+
                     "<IsValidUser xmlns=\"http://tempuri.org/\">"+
                     "<username>admin</username><pwd>aaa</pwd>"+
                     "</IsValidUser> \n"+
                     "</SOAP-ENV:Body> \n"+
                     "</SOAP-ENV:Envelope>"; 
             
             String message1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                     "<SOAP-ENV:Envelope \n"+
                     "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n"+
                     "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n"+
                     "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" \n"+
                     "SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" \n"+
                     "xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"> \n"+
                     "<SOAP-ENV:Body> \n"+
                     "<GetProjectsByLoginProjectID xmlns=\"http://tempuri.org/\">"+
                     "<logiProjectID>57f49751-a274-4627-b553-87510601ff85</logiProjectID>"+
                     "</GetProjectsByLoginProjectID> \n"+
                     "</SOAP-ENV:Body> \n"+
                     "</SOAP-ENV:Envelope>"; 

             StringEntity se = new StringEntity(message1, HTTP.UTF_8);
             se.setContentType("text/xml");
             httpPost.addHeader("SOAPAction", "http://tempuri.org/IUserAuthentication/GetProjectsByLoginProjectID");
             httpPost.setEntity(se);

             HttpResponse httpResponse = httpClient.execute(httpPost);
             HttpEntity resEntity = httpResponse.getEntity();
             response = EntityUtils.toString(resEntity);
          // receive response as inputStream
             InputStream inputStream = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
             
             XMLHandler obj = new XMLHandler(inputStream, XMLHandler.viewType.LOGIN);
             obj.fetchXML();
       
             Log.d("Response :", response.toString());
             
         } catch (Exception e) {
             //Assign Error Status true in static variable 'errored'
             //CheckLoginActivity.errored = true;
             e.printStackTrace();
         } 
    }
    
    									
    public static boolean invokeLoginWS(String userName,String passWord, String webMethName) {
        boolean loginStatus = false;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo unamePI = new PropertyInfo();
        PropertyInfo passPI = new PropertyInfo();
        // Set Username
        unamePI.setName("username");
        // Set Value
        unamePI.setValue(userName);
        // Set dataType
        unamePI.setType(String.class);
        // Add the property to request object
        request.addProperty(unamePI);
        //Set Password
        passPI.setName("password");
        //Set dataType
        passPI.setValue(passWord);
        //Set dataType
        passPI.setType(String.class);
        //Add the property to request object
        request.addProperty(passPI);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
 
        try {
        	String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                             "<SOAP-ENV:Envelope \n"+
                             "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n"+
                             "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n"+
                             "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" \n"+
                             "SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" \n"+
                             "xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"> \n"+
                             "<SOAP-ENV:Body> \n"+
                             "<IsValidUser xmlns=\"http://tempuri.org/\">"+
                             "<username>admin</username><pwd>aaa</pwd>"+
                             "</IsValidUser> \n"+
                             "</SOAP-ENV:Body> \n"+
                             "</SOAP-ENV:Envelope>"; 
        	
        	androidHttpTransport.setXmlVersionTag(message);
        	
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to  boolean variable variable
            loginStatus = Boolean.parseBoolean(response.toString());
 
        } catch (Exception e) {
            //Assign Error Status true in static variable 'errored'
            //CheckLoginActivity.errored = true;
            e.printStackTrace();
        } 
        //Return booleam to calling object
        return loginStatus;
    }
}
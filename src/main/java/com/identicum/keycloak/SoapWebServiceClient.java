package com.identicum.keycloak;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;


public class SoapWebServiceClient {

    //This method sends a SOAP request and prints response, and parses and prints texts of all <balance> elements in the response
    private static final Logger log = Logger.getLogger(SoapWebServiceClient.class);
    public static String postSOAPXML() {
        String resp = null;
        try {

            String Body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Body>\n" +
                    "    <InvertStringCase xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                    "      <sAString>AbCdE</sAString>\n" +
                    "    </InvertStringCase>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";

            HttpClient httpclient = new DefaultHttpClient();
            // You can get below parameters from SoapUI's Raw request if you are using that tool
            StringEntity strEntity = new StringEntity(Body, "text/xml", "UTF-8");
            // URL of request
            HttpPost post = new HttpPost("https://www.dataaccess.com/webservicesserver/TextCasing.wso");
            post.setHeader("SOAPAction", "InvertStringCase");
            post.setEntity(strEntity);

            // Execute request
            HttpResponse response = httpclient.execute(post);
            HttpEntity respEntity = response.getEntity();

            if (respEntity != null) {
                resp = EntityUtils.toString(respEntity);
                Object s = resp.toString();
                log.info(s);

            } else {
                log.error("No Response");
            }

        } catch (Exception e) {
            log.error("WebService SOAP exception = " + e.toString());
        }

        return resp;
    }

}













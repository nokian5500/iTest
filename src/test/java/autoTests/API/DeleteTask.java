package autoTests.API;

import autoTests.ConfigurationVariables;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Privat24 on 15.11.2016.
 */
public class DeleteTask {

    private static String Url;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();

    public  HttpClient createHttpClient_AcceptsUntrustedCerts() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpClientBuilder b = HttpClientBuilder.create();

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                return true;
            }
        }).build();
        b.setSslcontext( sslContext);

        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, (X509HostnameVerifier) hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();

        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager( socketFactoryRegistry);
        b.setConnectionManager( connMgr);

        HttpClient client = b.build();
        return client;
    }


    public void  methodDelete( String ID) throws Exception {

        switch (configVariables.baseUrl) {
            case "https://alpha.test.igov.org.ua":
                Url ="https://alpha.test.region.igov.org.ua";
                break;

            case "https://alpha.test.region.igov.org.ua":
                Url ="https://beta.test.region.igov.org.ua";
                break;

        case "https://beta-old.test.igov.org.ua":
                Url ="https://beta-old.test.region.igov.org.ua";
                break;

        case "https://delta.test.igov.org.ua":
                Url ="https://delta.test.region.igov.org.ua";
                break;

            default:
                throw new Exception("ERROR URL ");
    }


        String patch = "/wf/service/action/task/delete-process?nID_Order=";
        String IdOrder = ID;
        String urlDelete = Url+patch+IdOrder;

        HttpClient client = createHttpClient_AcceptsUntrustedCerts();
        HttpDelete httpDelete = new HttpDelete(urlDelete);

        httpDelete.addHeader("Content-Type", "application/json");
        httpDelete.addHeader("Authorization","Basic a2VybWl0Omtlcm1pdA==");

        HttpResponse response = client.execute(httpDelete);
//        HttpResponse response = Request.Post(url).stringBody("request_body","content_type").execute().returnResponse();
//        JSONObject jsonResponse = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
//        JSONArray jsonResponse = new JSONArray(IOUtils.toString(response.getEntity().getContent()));
//        JSONObject jsonResponse = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
//
//        System.out.println("Response Code : " +
//                response.getStatusLine().getStatusCode());
//
//        System.out.print("RESP Body :"+ jsonResponse);

        System.out.println("\nSending 'delete' request to URL : " + urlDelete);

        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }

    public void  deleteAllOrderId() throws Exception {
        for (String object: configVariables.orderId) {
            methodDelete(object);
            System.out.println(object);
        }
    }



}





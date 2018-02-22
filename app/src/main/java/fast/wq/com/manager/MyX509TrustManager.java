package fast.wq.com.manager;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * https 证书弱校验
 * 自定义MyX509TrustManager实现安全校验
 */

public class MyX509TrustManager implements X509TrustManager {
    private static final String TAG = "MyX509TrustManager";
    private Certificate mCa;

    public MyX509TrustManager(Certificate ca) {
        this.mCa = ca;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        //默认接收任意的客户端证书
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String authTYpe) throws CertificateException {
        //默认接收任意的服务端证书
        if (null == x509Certificates || 0 == x509Certificates.length) {
            Log.i(TAG, "Certificate chain is invalide: 证书链是无效的");
        } else if (null == authTYpe || 0 == authTYpe.length()) {
            Log.i(TAG, "Authentication type is invalid 认证类型无效");
        } else {

            for (X509Certificate cert : x509Certificates) {
                //检验证书有效期
                cert.checkValidity();
                //校验证书的签名
                try {
                    cert.verify(mCa.getPublicKey());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                } catch (SignatureException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    //提供与服务器对应证书，并且校验
    public static void init(Context mContext) throws CertificateException, IOException, NoSuchProviderException, NoSuchAlgorithmException, KeyManagementException {

        //Certificate证书工厂
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream caInput = new BufferedInputStream(mContext.getAssets().open("test.crt"));//assets目录中
        final Certificate ca;

        try {
            ca = cf.generateCertificate(caInput);
        } finally {
            caInput.close();
        }
        MyX509TrustManager trustManager = new MyX509TrustManager(ca);
        SSLContext sslContext = SSLContext.getInstance("TLSv1", "AndroidOpenSSL");
        sslContext.init(null, new TrustManager[]{trustManager}, null);
    }

    //自定义 HostnameVerifier Verifier验证者
    //ssl握手期间，如果对应的主机名和服务器标示的主机名不匹配，会调用HostnameVerifier。verify
    HostnameVerifier hv = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession sslSession) {
            //return true 表示接受任意域名服务器

            if (true)//实现自定义过滤
            {
                return true;
            } else {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify(hostname, sslSession);
            }

        }
    };

    public void set() {
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    public void setSocketFactory(){
        SSLSocketFactory sf = new MySSLSocketFactory();


    }
    public class MySSLSocketFactory extends SSLSocketFactory{

        @Override
        public String[] getDefaultCipherSuites() {
            return new String[0];
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return new String[0];
        }

        @Override
        public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException {
            return null;
        }

        @Override
        public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
            return null;
        }

        @Override
        public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
            return null;
        }

        @Override
        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return null;
        }

        @Override
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
            return null;
        }
    }

}

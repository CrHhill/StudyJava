import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class GetTuicoolHotArticles {
    public static void main(String[] args) throws ClientProtocolException,IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpclient实例
        HttpGet httpGet = new HttpGet("https://www.tuicool.com/");//创建httpget实例
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");

        CloseableHttpResponse response = httpClient.execute(httpGet);//执行httpget请求
        HttpEntity entity = response.getEntity();//获取返回实体
        System.out.println("网页内容：" + EntityUtils.toString(entity,"UTF-8"));
        response.close();
        httpClient.close();

    }
}

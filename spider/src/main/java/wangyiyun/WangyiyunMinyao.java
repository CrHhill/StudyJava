package wangyiyun;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WangyiyunMinyao {

    public static ArrayList<Wangyiyun> Crawl(String url_str, String charset)throws ClientProtocolException, IOException {
// 获取
        CloseableHttpClient hc=HttpClients.createDefault();
        //HttpClient hc = new DefaultHttpClient();
        HttpGet hg = new HttpGet(url_str);
        HttpResponse response = hc.execute(hg);
        HttpEntity entity = response.getEntity();

        InputStream htm_in = null;
        ArrayList<Wangyiyun> arrayList = new ArrayList<Wangyiyun>();
        if (entity != null) {
            htm_in = entity.getContent();
            String htm_str = InputStream2String(htm_in, charset);
            Document doc = Jsoup.parse(htm_str);
            Elements links = doc.select("div[class=g-bd]").select("div[class=g-wrap p-pl f-pr]")
                    .select("ul[class=m-cvrlst f-cb]").select("div[class=u-cover u-cover-1");
            for (Element link : links) {
                Elements lin = link.select("a");
                // 歌曲描述
                String description = lin.attr("title");
                // 歌曲链接地址
                String href = lin.attr("href");
                href = "http://music.163.com" + href;
				/*
				 * System.out.print(re_title + "       ");
				 * System.out.print(re_url + "       "); int nums =
				 * GetTheNumberOfPlays(re_url, charset);
				 */
                int nums =GetTheNumberOfPlays(href, charset);
                Wangyiyun wangyiyun = new Wangyiyun(description, href, nums);
                arrayList.add(wangyiyun);
            }
        }
        return arrayList;
    }

    public static int GetTheNumberOfPlays(String url_str, String charset) throws ClientProtocolException, IOException {
        CloseableHttpClient hc= HttpClients.createDefault();
        HttpGet hg = new HttpGet(url_str);
        HttpResponse response = hc.execute(hg);
        HttpEntity entity = response.getEntity();

        InputStream htm_in = null;
        int nums = 0;
        if (entity != null) {
            htm_in = entity.getContent();
            String htm_str = InputStream2String(htm_in, charset);
            Document doc = Jsoup.parse(htm_str);
            String links = doc.select("div[class=u-title u-title-1 f-cb]").select("div[class=more s-fc3]")
                    .select("strong").text();
            nums = Integer.parseInt(links);
        }
        return nums;
    }

    public static String InputStream2String(InputStream in_st, String charset) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while ((line = buff.readLine()) != null) {
            res.append(line);
        }
        return res.toString();
    }
}

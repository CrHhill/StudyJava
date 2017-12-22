import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TuicoolHotArticles {
    public static void main(String[] args) throws IOException{
        String url = "https://www.tuicool.com/";
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

        // #id: 通过ID查找元素，比如：#logo
        Elements items = document.select(".list_article_item ");
        System.out.println(items.size());
        for (Element item : items) {
            // 标题。（类名title下的 a标签的title属性获取）
            String title = item.select(".title a").attr("title");
            // 标题图片地址（类名article_thumb_image下的 img标签的src属性获取）
            String picture_href = item.select(".article_thumb_image img").attr("src");
            // 时间 。(类名tip下的最后一个span标签的文字获取)
            String date = item.select(".tip span").last().text();
            // 作者 。(类名tip下的第一个span标签的文字获取)
            String author = item.select(".tip span").first().text();

            System.out.println("标题：               " + title);
            System.out.println("标题图片地址：" + picture_href);
            System.out.println("发布日期           " + date);
            System.out.println("作者                  " + author);
            System.out.println();
            System.out.println();
        }
    }

}

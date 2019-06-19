package com.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HtmlParser {
    public static Document getDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(50000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void parseWdzj(String url) {
        Document document = getDocument(url);
        Elements elements = document.select(".zllist li");
        for (Element element : elements) {
            String imgUrl = "http://admin.wdzj.com" + element.select(".img a img").attr("src");
            System.out.println("img url: " + imgUrl);

            String detailUrl = element.select(".text h3 a").attr("href");
            Document detailDocument = getDocument("http:" + detailUrl);
            Elements detailElements = detailDocument.select(".page-cen");
            Element detailElement = detailElements.get(0);
            String title = detailElement.select(".page-title").text();
            String author = detailElement.select(".page-time span:contains(作者) em").text();
            String publishTime = detailElement.select(".page-time span").last().text();
            String content = detailElement.select(".page-content").html();
            System.out.println("title: " + title);
            System.out.println("author: " + author);
            System.out.println("publishTime: " + publishTime + " " + detailElement.select(".page-time span").size());
        }
    }

    public static void main(String[] args) {
        String wdzjUrl = "https://www.wdzj.com/news/hydongtai/";
        parseWdzj(wdzjUrl);
    }
}

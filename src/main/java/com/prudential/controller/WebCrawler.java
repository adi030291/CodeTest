/**
 * 
 */
package com.prudential.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Aditya Rathore
 *
 */
public class WebCrawler {

    private static HashSet<String> linkSet;
    private static String applicationURL;

    public static void main(String[] args) {

        System.out.println("process started..");
        linkSet = new HashSet<String>();
        PrintWriter pw;
        try {
            // creating output file having all the URLs
            Properties prop = new Properties();
            prop.load(WebCrawler.class.getClassLoader().getResourceAsStream("application.properties"));
            applicationURL = prop.getProperty("application.url");
            pw = new PrintWriter(new FileWriter(prop.getProperty("output.file.path")));
            new WebCrawler().getLinks(applicationURL, pw);
            pw.close();
            System.out.println("process completed");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getLinks(final String stringURL, final PrintWriter pw) {

        if (!linkSet.contains(stringURL)) {
            try {
                if (linkSet.add(stringURL)) {
                    pw.write(stringURL + "\n");
                }

                Document document = Jsoup.connect(stringURL).get();
                Elements redirectLinks = document.select("a[href]");
                Elements images = document.select("img[src]");

                // printing unique image URLS
                images.forEach(i -> {
                    if (!linkSet.contains(i.attr("abs:src"))) {
                        pw.write(i.attr("abs:src") + "\n");
                        linkSet.add(i.attr("abs:src"));
                    }
                });

                redirectLinks.forEach(p -> {
                    if (!p.attr("abs:href").contains(applicationURL.substring(9))) {
                        if (linkSet.add(p.attr("abs:href"))) {
                            pw.write(p.attr("abs:href") + "\n");
                        }
                    } else {
                        getLinks(p.attr("abs:href"), pw);
                    }
                });
            } catch (IOException e) {
                System.err.println(stringURL + "': " + e.getMessage());
            }
        }
    }
}

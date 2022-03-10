package com.devsubho.project.service.impl;

import com.devsubho.project.model.Site;
import com.devsubho.project.service.SiteCrawler;

// Import JSoup classes
import com.devsubho.project.utils.SiteCrawlerConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;

import com.devsubho.project.model.Loader;
import com.devsubho.project.model.Printer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Set;

/**
 * Class to crawl links.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 11:30
 * @user Subhajit
 *
 */
public class SiteCrawlerImpl implements SiteCrawler {

    Set<String> links;
    Set<String> visitedPages;
    Set<String> pagesToVisit;
    Set<String> unloadedElements;
    SiteDownloaderImpl siteLoadImpl = new SiteDownloaderImpl();

    /**
     * This function loops through Site object.
     *
     * @param site
     * @return null
     */
    @Override
    public void crawlAllLinks(Site site) {
        System.out.println("CollectAllLinks started");
        System.out.println("Thread name: " + Thread.currentThread().getName());
        links = site.getLinks();
        unloadedElements = site.getUnloadedElements();
        visitedPages = site.getVisitedPages();
        pagesToVisit = site.getPagesToVisit();
        while (!site.getUrl().isEmpty()) {
            String currentUrl;
            if (pagesToVisit.isEmpty()) {
                currentUrl = site.getUrl();
                visitedPages.add(currentUrl);
            } else {
                currentUrl = nextUrl(site.getUrl());
            }
            if (Jsoup.isValid(currentUrl, Safelist.basicWithImages())) {
                crawling(currentUrl);
            }
            pagesToVisit.addAll(getLinks());
            if (!unloadedElements.isEmpty()) {
                pagesToVisit.addAll(getListOfAllUnloadedElements());
            }
        }
        System.out.println("CollectAllLinks done: " + getLinks().size());
    }

    /**
     * This function crawls through url.
     *
     * @param url
     * @return null
     */
    @Override
    public void crawling(String url) {
        if (isValidURL(url)) {
            if (links.contains(url)) {
                return;
            }
            Document document;
            try {
                System.out.println("Thread name: " + Thread.currentThread().getName());
                document = Jsoup.connect(url).get();
                if (document.connection().response().statusCode() == 200) {
                    links.add(url);
                    System.out.println("\n**Visiting** received web page at " + url);

                    Elements medias = document.select(SiteCrawlerConstants.mediaElement);
                    Elements imports = document.select(SiteCrawlerConstants.importElement);
                    Elements links = document.select(SiteCrawlerConstants.linkElement);

                    // Creating directory and downloading resources for each type of elements
                    createDirectoryAndDownload(medias);
                    createDirectoryAndDownload(imports);

                    // Creating directory and downloading resources for each links
                    for (Element link : links) {
                        String href = link.attr(SiteCrawlerConstants.absoluteLink);
                        System.out.println(link.attr(SiteCrawlerConstants.absoluteLink));
                        siteLoadImpl.downloadHTMLFile(new Loader(link.attr(SiteCrawlerConstants.absoluteLink), getFileName(link.attr(SiteCrawlerConstants.absoluteLink))));
                        Printer printer = new Printer(Thread.currentThread().getName(), link.attr(SiteCrawlerConstants.absoluteSource), Paths.get("").toAbsolutePath() + "/" + link.attr(SiteCrawlerConstants.absoluteSource));
                        System.out.println(printer);
                        URL hrefUrl = new URL(href);
                        if (hrefUrl.getHost().equals(new URL(url).getHost())) { // here check we the host name is the same as we had
                            crawling(href);
                        }
                    }
                }
            } catch (MalformedURLException e) {
                System.err.println("Unsupported protocol for URL: " + url + " Error: " + e);
                saveUnloadedElement(url); // Save all failed URLs with Unsupported protocol
            } catch (IOException e) {
                System.err.println("Timeout fetching URL: " + url + " Error: " + e);
                saveUnloadedElement(url); // Save all failed URLs due to timeout
            }
        }
    }

    public String getFileName(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }

    /**
     * This function is used to create directory and download sources.
     *
     * @param elements
     * @return null
     */
    private void createDirectoryAndDownload(Elements elements) {
        for (Element element : elements) {
            System.out.println(element.attr(SiteCrawlerConstants.absoluteSource));
            siteLoadImpl.createDirAndDownloadSrc(new Loader(element.attr(SiteCrawlerConstants.absoluteSource), getFileName(element.attr(SiteCrawlerConstants.absoluteSource))));
            Printer printer = new Printer(Thread.currentThread().getName(), element.attr(SiteCrawlerConstants.absoluteSource), Paths.get("").toAbsolutePath() + "/" + element.attr(SiteCrawlerConstants.absoluteSource));
            System.out.println(printer);
        }
    }

    /**
     * This function is used to save unloaded URLs.
     *
     * @param url
     * @return null
     */
    private void saveUnloadedElement(String url) {
        if (!unloadedElements.contains(url)) {
            unloadedElements.add(url);
        }
        System.out.println("Page is unloaded!");
    }

    private Set<String> getLinks() {
        return links;
    }

    private Set<String> getListOfAllUnloadedElements() {
        return unloadedElements;
    }

    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return true;
    }

    private String nextUrl(String url) {
        if (pagesToVisit.contains(url)) {
            pagesToVisit.remove(url);
            visitedPages.add(url);
        }
        return url;
    }

}

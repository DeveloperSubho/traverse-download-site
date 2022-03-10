package com.devsubho.project;

import com.devsubho.project.service.impl.SiteDownloaderImpl;
import com.devsubho.project.utils.SiteCrawlerConstants;
import org.junit.Assert;
import org.junit.Test;
import com.devsubho.project.model.Loader;

public class SiteDownloaderImplTest {

    SiteDownloaderImpl siteDownloader = new SiteDownloaderImpl();

    @Test
    public void createDirectory() {
    }

    @Test
    public void downloadHTMLFile() {
    }

    @Test
    public void createDirAndDownloadSrc() {
    }

    @Test
    public void createDirAndDownloadHtml() {
    }

    @Test
    public void isFileHtml() {
        boolean isHtmlFile = siteDownloader.isFileHtml(new Loader(SiteCrawlerConstants.TRETTON37_URL_HTML_FILE, "michal-lusiak.html"));
        Assert.assertTrue(isHtmlFile);
    }

    @Test
    public void isIndexHtml() {
        boolean isIndexHtml = siteDownloader.isIndexHtml(new Loader(SiteCrawlerConstants.TRETTON37, ""));
        Assert.assertTrue(isIndexHtml);
    }


    @Test
    public void getOnlyUrlText() {
        String resultUrl = siteDownloader.getOnlyUrlText(SiteCrawlerConstants.expectedURL);
        Assert.assertEquals(resultUrl, SiteCrawlerConstants.expectedOnlyURLWithoutFileName);
    }
}

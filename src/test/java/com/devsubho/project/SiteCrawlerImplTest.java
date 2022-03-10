package com.devsubho.project;

import com.devsubho.project.service.impl.SiteCrawlerImpl;
import com.devsubho.project.utils.SiteCrawlerConstants;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author subha
 * @project traverse-download-site
 * @created 08/03/2022 - 13:20
 * @user subha
 */
public class SiteCrawlerImplTest {

    SiteCrawlerImpl siteCrawler = new SiteCrawlerImpl();

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
    public void getFileName() {
        String resultFileName = siteCrawler.getFileName(SiteCrawlerConstants.expectedURL);
        Assert.assertEquals(resultFileName, SiteCrawlerConstants.expectedFileName);
    }

}

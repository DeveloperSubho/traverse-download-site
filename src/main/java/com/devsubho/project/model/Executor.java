package com.devsubho.project.model;

import com.devsubho.project.service.impl.SiteCrawlerImpl;
import com.devsubho.project.utils.SiteCrawlerConstants;
import lombok.Getter;

/**
 * This class provides site and siteCrawler object.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 11:30
 * @user Subhajit
 *
 */
@Getter
public class Executor {

    private final Site site;
    private final SiteCrawlerImpl siteCrawler;

    public Executor() {
        this.site = new Site(SiteCrawlerConstants.TRETTON37);
        this.siteCrawler = new SiteCrawlerImpl();
    }
}

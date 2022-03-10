package com.devsubho.project.service;

import com.devsubho.project.model.Site;

public interface SiteCrawler {

    void crawlAllLinks(Site site);

    void crawling(String url);
}

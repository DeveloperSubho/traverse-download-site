package com.devsubho.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Model class to store site related information.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 11:30
 * @user Subhajit
 */
@Getter
@Setter
public class Site {

    private Set<String> visitedPages;
    private Set<String> pagesToVisit;
    private Set<String> unloadedElements;
    private String url;
    private Set<String> links;

    /**
     * When creating Crawler we just take the url (Creates ONCE) and the lists are created automatically
     *
     * @param url
     */
    public Site(String url) {
        this.visitedPages = new HashSet<>();
        this.pagesToVisit = new HashSet<>();
        this.unloadedElements = new HashSet<>();
        this.url = url;
        this.links = new HashSet<>();
    }

}

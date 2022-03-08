package com.devsubho.project.utils;

/**
 * Class to store constant variables. Class is final to prevent it from being extended.
 * Additionally, we've defined a private constructor so it can't be instantiated.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 14:09
 * @user Subhajit
 */
public final class SiteCrawlerConstants {

    public static final String TRETTON37 = "https://tretton37.com/";
    public static final String TRETTON37_URL_HTML_FILE = "https/tretton37.com/meet/michal-lusiak.html";
    public static final String expectedFileName = "polyfills.js";
    public static final String expectedURL = "https://tretton37.com/assets/js/lib/polyfills.js";
    public static final String expectedOnlyURLWithoutFileName = "https://tretton37.com/assets/js/lib";
    public static final String linkElement = "a[href]";
    public static final String mediaElement = "[src]";
    public static final String importElement = "link[href]";
    public static final String absoluteSource = "abs:src";
    public static final String absoluteLink = "abs:href";
    public static final String htmlExtension = ".html";

    private SiteCrawlerConstants() {

    }

}

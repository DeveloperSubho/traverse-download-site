package com.devsubho.project.service.impl;

import com.devsubho.project.model.Loader;
import com.devsubho.project.service.SiteDownloader;
import com.devsubho.project.utils.SiteCrawlerConstants;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to download site. Creating directories and saving files.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 11:30
 * @user Subhajit
 *
 */
public class SiteDownloaderImpl implements SiteDownloader {

    String currentWorkDir = Paths.get("").toAbsolutePath().toString();

    @Override
    public Path createDirectory(String path) throws IOException {
        Path directory = Paths.get(currentWorkDir, path.replaceAll(":", ""));
        if (!path.isEmpty()) {
            if (Files.notExists(directory)) {
                System.out.println("Creating directory " + directory);
                Files.createDirectories(directory);
                System.out.println("Directory is created successfully: " + directory);
            }
        } else {
            System.out.println("Directory with empty path " + path + " is not created!");
        }
        return directory;
    }

    @Override
    public void downloadHTMLFile(Loader loader) {
        if (isIndexHtml(loader)) {
            loader.setFileName("index.html");
            createDirAndDownloadHtml(loader);
        }
        if (isFileHtml(loader)) {
            createDirAndDownloadHtml(loader);
        }
    }

    @Override
    public void createDirAndDownloadSrc(Loader loader) {
        try {
            Path directory = createDirectory(getOnlyUrlText(loader.getUrl()));
            if (isUriAbsolute(loader.getUrl())) {
                try (BufferedInputStream in = new BufferedInputStream(new URL(loader.getUrl()).openStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(directory.toString() + "/" + loader.getFileName())) {
                    byte[] dataBuffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                        fileOutputStream.write(dataBuffer, 0, bytesRead);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("createDirAndDownloadSrc: " + e.getMessage() + e);
        }
    }

    @Override
    public void createDirAndDownloadHtml(Loader loader) {
        try {
            Path directory = createDirectory(getOnlyUrlText(loader.getUrl()));
            URL url = new URL(loader.getUrl());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(directory.toString() + "/" + loader.getFileName()));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
            System.out.println("HTML Page downloaded: " + directory + "/" + loader.getFileName());
        } catch (IOException e) {
            System.out.println(e.getMessage() + e);
        }
    }

    public boolean isFileHtml(Loader loader) {
        return loader.getUrl().contains(SiteCrawlerConstants.htmlExtension);
    }

    public boolean isIndexHtml(Loader loader) {
        return loader.getUrl().equals(SiteCrawlerConstants.TRETTON37);
    }

    public String getOnlyUrlText(String urlText) {
        if (null != urlText && urlText.length() > 0) {
            int endIndex = urlText.lastIndexOf("/");
            if (endIndex != -1) {
                return urlText.substring(0, endIndex);
            }
        }
        return urlText;
    }

    private boolean isUriAbsolute(String url) {
        try {
            URI uri = new URI(url);
            return uri.isAbsolute();
        } catch (URISyntaxException e) {
            return false;
        }
    }
}

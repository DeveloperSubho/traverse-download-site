package com.devsubho.project.service;

import com.devsubho.project.model.Loader;

import java.io.IOException;
import java.nio.file.Path;

public interface SiteDownloader {

    void createDirAndDownloadSrc(Loader loader);

    void createDirAndDownloadHtml(Loader loader);

    Path createDirectory(String path) throws IOException;

    void downloadHTMLFile(Loader loader);
}

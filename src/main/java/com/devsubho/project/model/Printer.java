package com.devsubho.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class to print the progress in console.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 11:30
 * @user Subhajit
 */

@AllArgsConstructor
@Getter
@Setter
public class Printer {
    private String currentThreadName;
    private String urlOfLoadedPage;
    private String directoryOfSavedFile;

    @Override
    public String toString() {
        return String.format("currentThreadName:%s, urlOfLoadedPage:%s, directoryOfSavedFile:%s", currentThreadName, urlOfLoadedPage, directoryOfSavedFile);
    }
}

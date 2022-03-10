package com.devsubho.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class for loader.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 11:30
 * @user Subhajit
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class Loader {

    private String url;
    private String fileName;

}

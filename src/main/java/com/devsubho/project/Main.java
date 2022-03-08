package com.devsubho.project;

import com.devsubho.project.model.Executor;
import com.devsubho.project.model.Task;

/**
 * This is the starting point for traverse and download site project.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 11:30
 * @user Subhajit
 *
 */
public class Main {

    public static void main(String[] args) {

        Executor executor = new Executor();

        Task task = new Task(executor);
        for (int i = 0; i < task.getAvailableCPU(); i++) {
            task.getService().execute(task);
        }
    }
}

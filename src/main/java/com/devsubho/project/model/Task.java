package com.devsubho.project.model;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that creates threads and that should have Crawler as a parameter in itself.
 *
 * @author Subhajit
 * @project traverse-download-site
 * @created 08/03/2022 - 11:30
 * @user Subhajit
 *
 */
public class Task implements Runnable {

    Executor executor;

    /**
     * Run the TaskThreadsHandle itself
     */
    ExecutorService service;

    /**
     * Indicates the number of CPUs
     */
    int availableCPU;

    public Task(Executor executor) {
        this.service = Executors.newFixedThreadPool(getAvailableCPU());
        this.availableCPU = getAvailableCPU();
        this.executor = executor;
    }

    public ExecutorService getService() {
        return service;
    }

    /**
     * Function to get the number of available CPUs.
     */
    public int getAvailableCPU() {
        return Runtime.getRuntime().availableProcessors(); //
    }

    /**
     * Function to execute threads.
     */
    @Override
    public void run() {
        try {
            System.out.println("Thread name: " + Thread.currentThread().getName());
            executor.getSiteCrawler().crawlAllLinks(executor.getSite());

            // Allow other threads to work
            Thread.sleep(Duration.ofSeconds(2).toMillis());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage() + e);
            Thread.currentThread().interrupt();
        }
    }
}

package com.gfa.p2pchatapp.services;

public interface LogService {
    void log(String path, String method, String requestData);
    void logError(String path, String method, String requestData);
}

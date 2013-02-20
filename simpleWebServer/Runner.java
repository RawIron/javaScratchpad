/*
 * 
 */

package simpleWebServer;
 
import java.io.*;
import java.net.*;
import java.util.*;

import simpleWebServer.Config;
import simpleWebServer.Logger;
import simpleWebServer.SocketServer;
import simpleWebServer.HttpRequestWorkerPool;


class Runner {
    public static void main(String[] args) throws Exception {
        Config commandLine = commandLineOptionsIntoConfig(args);

        Logger logger = new SimpleLogger();
        ConfigDefaults defaults = new ConfigDefaults();

        Config config = new Config(defaults, logger);
        config.load();
        config.list();

        WorkerPool pool = new HttpRequestWorkerPool(config);
        pool.init();

        SocketServer webServer = new SocketServer(pool, config);
        webServer.start();
    }

    protected static Config commandLineOptionsIntoConfig(String[] args) {
        Config commandLineOptions = null;
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        return commandLineOptions;
    }
}


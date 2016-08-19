package com.weasley.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// rest-demo is the "Context Root", 
// which by default  is the basename of the WAR file which is the name of the project
// http://myserver.com:8080/rest-demo/rest/
@ApplicationPath("/rest")
public class WeasleyRESTApp extends Application {

}

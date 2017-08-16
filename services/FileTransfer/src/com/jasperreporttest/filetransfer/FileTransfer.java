/*Copyright (c) 2016-2017 medicalwizard.com.au All Rights Reserved.
 This software is the confidential and proprietary information of medicalwizard.com.au You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with medicalwizard.com.au*/
package com.jasperreporttest.filetransfer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;


import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import com.wavemaker.runtime.server.ParamName;


//import com.jasperreporttest.filetransfer.model.*;

/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 */
@ExposeToClient
public class FileTransfer {

    private static final Logger logger = LoggerFactory.getLogger(FileTransfer.class);

    @Autowired
    private SecurityService securityService;

    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
    
    
    /** UPLOADS stores the location of files on disk. Windows users
     * must update this; Mac & Linux should be fine. */
     
        private File UPLOADS = new File("/tmp/uploads");
        public void doUpload(@ParamName(name="file") MultipartFile file) throws IOException {
            File outputFile = new File(UPLOADS, file.getOriginalFilename());
            System.out.println("writing the content of uploaded file to: "+outputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
            IOUtils.copy(file.getInputStream(), fos);
            file.getInputStream().close();
            fos.close();
        }
    

}

package com.my.dubrovnyi.shop.servlets.services.imagesService;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

class UserImageCreator {
    private static final String REPOSITORY_PATH_VALUE = "repositoryPath";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext sc;

    UserImageCreator(HttpServletRequest request, HttpServletResponse response,
                     ServletContext sc) {
        this.request = request;
        this.response = response;
        this.sc = sc;
    }

    void drawImage() throws IOException {
        String userLogin = (String) request.getSession()
                .getAttribute(USER_LOGIN_VALUE);
        response.setContentType(CONTENT_TYPE);
        String repositoryPath = getRepositoryPath();
        FileInputStream fin = null;
        try (ServletOutputStream out = response.getOutputStream()) {
            fin = new FileInputStream(repositoryPath + userLogin
                    + IMAGE_FORMAT_NAME_WITH_DOT);
            try (BufferedInputStream bin = new BufferedInputStream(fin)) {
                BufferedOutputStream bout = new BufferedOutputStream(out);
                int ch;
                while ((ch = bin.read()) != -1) {
                    bout.write(ch);
                }
                bout.close();
            }
        } finally {
            close(fin);
        }
    }

    private void close(FileInputStream fin) throws IOException {
        if (fin != null) {
            fin.close();
        }
    }

    private String getRepositoryPath() {
        String captchaStoringMode = sc.getInitParameter(REPOSITORY_PATH_VALUE);
        return captchaStoringMode + SLASH_VALUE;
    }
}

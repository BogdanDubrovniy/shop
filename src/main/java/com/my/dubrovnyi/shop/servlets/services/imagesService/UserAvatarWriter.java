package com.my.dubrovnyi.shop.servlets.services.imagesService;

import com.my.dubrovnyi.shop.constants.ConstantsClass;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class UserAvatarWriter {
    private static final String DEFAULT_IMAGE_NAME = "default.png";
    private static final String INPUT_FILE_VALUE = "default.png";
    private static final String REPOSITORY_PATH_VALUE = "repositoryPath";

    private HttpServletRequest request;
    private ServletContext sc;
    private String name;

    public UserAvatarWriter(HttpServletRequest request, ServletContext sc, String name) {
        this.request = request;
        this.sc = sc;
        this.name = name;
    }

    public void writeUserAvatar() throws IOException, ServletException {
        Part filePart = request.getPart(INPUT_FILE_VALUE);
        String repositoryPath = getRepositoryPath();
        if (filePart != null) {
            filePart.write(repositoryPath + name + ConstantsClass.IMAGE_FORMAT_NAME_WITH_DOT);
        } else {
            Files.copy(new File(repositoryPath, DEFAULT_IMAGE_NAME).toPath(),
                    new File(repositoryPath, name
                            + ConstantsClass.IMAGE_FORMAT_NAME_WITH_DOT)
                            .toPath());
        }
    }

    private String getRepositoryPath() {
        String captchaStoringMode = sc.getInitParameter(REPOSITORY_PATH_VALUE);
        return captchaStoringMode + ConstantsClass.SLASH_VALUE;
    }
}
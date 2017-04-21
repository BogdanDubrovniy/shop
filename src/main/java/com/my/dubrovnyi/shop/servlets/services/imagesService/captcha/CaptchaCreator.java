package com.my.dubrovnyi.shop.servlets.services.imagesService.captcha;

import com.my.dubrovnyi.shop.constants.ConstantsClass;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

/**
 * Creation captcha class
 */
public class CaptchaCreator {
    private HttpServletRequest request;
    private HttpServletResponse response;

    private static final int WIDTH = 150;
    private static final int HEIGHT = 50;
    private static final long CAPTCHA_TIMEOUT = 1000000;

    private static final int FONT_SIZE = 18;
    private static final String FONT_NAME = "Georgia";

    private static final int SECOND_Y_GRADIENT_VALUE = 25;

    private static final int RED_COLOR_VALUE = 255;
    private static final int GREEN_COLOR_VALUE = 153;

    private static final int INDEX_MODULE = 5;
    private static final int X_MODULE = 15;
    private static final int Y_MODULE = 20;
    private static final int CHAR_LENGTH = 1;

    private static final char DATA[][] = {
            {'1', '0', '2', '3', '5', '3'},
            {'8', '3', '1', '1', '7'},
            {'3', '2', '9', '4', '3', '4'},
            {'9', '8', '8', '3', '4', '1'},
            {'2', '5', '3'}
    };

    public CaptchaCreator(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void processRequest() throws ServletException, IOException {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();

        Font font = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
        g2d.setFont(font);

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        GradientPaint gp = new GradientPaint(ConstantsClass.ZERO, ConstantsClass.ZERO, Color.red,
                ConstantsClass.ZERO, SECOND_Y_GRADIENT_VALUE, Color.black, true);

        g2d.setPaint(gp);
        g2d.fillRect(ConstantsClass.ZERO, ConstantsClass.ZERO, WIDTH, HEIGHT);

        g2d.setColor(new Color(RED_COLOR_VALUE, GREEN_COLOR_VALUE, ConstantsClass.ZERO));

        Random r = new Random();
        int index = Math.abs(r.nextInt()) % INDEX_MODULE;

        String captcha = String.copyValueOf(DATA[index]);

        //saves captcha value in session attribute:
        request.getSession().setAttribute(ConstantsClass.USER_CAPTCHA_VALUE, captcha);

        long currentTime = new Date().getTime();

        request.getSession().setAttribute(ConstantsClass.TIMEOUT_ATTRIBUTE_VALUE,
                CAPTCHA_TIMEOUT);
        request.getSession().setAttribute(ConstantsClass.CURRENT_TIME_ATTRIBUTE_VALUE,
                currentTime);

        int x = ConstantsClass.ZERO;
        int y;
        for (int i = 0; i < DATA[index].length; i++) {
            x += 10 + (Math.abs(r.nextInt()) % X_MODULE);
            y = 20 + Math.abs(r.nextInt()) % Y_MODULE;
            g2d.drawChars(DATA[index], i, CHAR_LENGTH, x, y);
        }

        g2d.dispose();

        response.setContentType(ConstantsClass.CONTENT_TYPE);
        OutputStream os = response.getOutputStream();
        ImageIO.write(bufferedImage, ConstantsClass.IMAGE_FORMAT_NAME, os);
        os.close();
    }
}
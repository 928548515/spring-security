package com.imooc.security.core.code;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author pengyu
 * @Date 2019/4/25
 */

@RestController
public class ValidateCodeController {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int width = 67;
//        int height = 23;
        int width = ServletRequestUtils.getIntParameter(request,"width",securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request,"height",securityProperties.getCode().getImage().getHeight());
        String verifyCode = VerifyCodeUtils.outputVerifyImage(width, height, response.getOutputStream(), securityProperties.getCode().getImage().getLength());
        ImageCode imageCode = new ImageCode(null, verifyCode, securityProperties.getCode().getImage().getExpireIn());
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
    }

}

package com.imooc.security.core.code;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
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

    private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode = createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JEPG", response.getOutputStream());

    }

    class VerifyUtil{
        /** 验证码字符集 */
        private  final char[] chars = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        /**
         * 字符数量
         */
        private static final int SIZE = 4;

        /**
         * 干扰线数量
         */
        private static final int LINES = 5;
        /**
         * 宽度
         */
        private static final int WIDTH = 80;
        /**
         * 高度
         */
        private static final int HEIGHT = 40;
        /**
         * 字体大小
         */
        private static final int FONT_SIZE = 30;

        /**
         * 生成随机验证码及图片
         * Object[0]：验证码字符串；
         * Object[1]：验证码图片。
         */
        public  Object[] createImage() {
            StringBuffer sb = new StringBuffer();
            // 1.创建空白图片
            BufferedImage image = new BufferedImage(
                    WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            // 2.获取图片画笔
            Graphics graphic = image.getGraphics();
            // 3.设置画笔颜色
            graphic.setColor(Color.LIGHT_GRAY);
            // 4.绘制矩形背景
            graphic.fillRect(0, 0, WIDTH, HEIGHT);
            // 5.画随机字符
            Random ran = new Random();
            for (int i = 0; i < SIZE; i++) {
                // 取随机字符索引
                int n = ran.nextInt(chars.length);
                // 设置随机颜色
                graphic.setColor(getRandomColor());
                // 设置字体大小
                graphic.setFont(new Font(
                        null, Font.BOLD + Font.ITALIC, FONT_SIZE));
                // 画字符
                graphic.drawString(
                        chars[n] + "", i * WIDTH / SIZE, HEIGHT * 2 / 3);
                // 记录字符
                sb.append(chars[n]);
            }
            // 6.画干扰线
            for (int i = 0; i < LINES; i++) {
                // 设置随机颜色
                graphic.setColor(getRandomColor());
                // 随机画线
                graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
                        ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "jpeg", baos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Object[]{sb.toString(), baos.toByteArray()};

        }

        /**
         * 随机取色
         */
        public  Color getRandomColor() {
            Random ran = new Random();
            Color color = new Color(ran.nextInt(256),
                    ran.nextInt(256), ran.nextInt(256));
            return color;
        }
    }

    public ImageCode createImageCode(HttpServletRequest request) {
        int width = 67;
        int height = 23;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //随机操作对象
        Random random = new Random();
        g.fillRect(0,0,width,height);

        return new ImageCode();

    }


    /**
     * 生成随机验证码
     * @param type  类型
     * @param length   长度
     * @param exChars  排除的字符
     * @return
     */
    public static String getRandomCode(int type,int length,String exChars) {
        Random random = new Random();
        int i = 0;
        StringBuffer sb = new StringBuffer();
            while (i < length) {
                int t = random.nextInt(123);
                if ((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0)) {
                    sb.append((char) t);
                    i++;
                }
            }
            return sb.toString();
    }


}

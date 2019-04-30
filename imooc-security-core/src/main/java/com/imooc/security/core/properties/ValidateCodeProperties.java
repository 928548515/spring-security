package com.imooc.security.core.properties;

/**
 * 由于可能会有其他类型验证码，讲ImageCodeProperties进行封装
 * @author pengyu
 * @Date 2019/4/30
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}

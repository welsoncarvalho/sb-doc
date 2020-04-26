package com.test.sbdocker.model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Monster")
public class Monster implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id;
    private byte[] image;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

}
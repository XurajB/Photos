package com.iandrobot.example.photos.data;

/**
 * Created by surajbhattarai on 10/15/15.
 */
public class Photo {
    private String mTitle;
    private String mImageUrl;
    private String mFarmId;
    private String mServerId;
    private String mImageId;
    private String mSecret;

    private final String IMAGE_URL = "https://farm%s.staticflickr.com/%s/%s_%s_s.jpg";

    //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_s.jpg"

    public Photo(){}

    public Photo(String title, String farmId, String serverId, String imageId, String secret) {
        this.mTitle = title;
        this.mFarmId = farmId;
        this.mServerId = serverId;
        this.mImageId = imageId;
        this.mSecret = secret;

        this.mImageUrl = String.format(IMAGE_URL, farmId, serverId, imageId, secret);
    }

    public void setFarmId(String farmId) {
        this.mFarmId = farmId;
    }

    public void setServerId(String serverId) {
        this.mServerId = serverId;
    }

    public void setImageId(String imageId) {
        this.mImageId = imageId;
    }

    public void setSecret(String secret) {
        this.mSecret = secret;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}

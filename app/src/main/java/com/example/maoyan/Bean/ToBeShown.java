package com.example.maoyan.Bean;

import com.google.gson.Gson;


public class ToBeShown {
    /**
     * id : 1475137
     * haspromotionTag : false
     * img : https://p0.pipi.cn/mmdb/fb7386718ea5bf3ba3ddd2619594259dac1e3.jpg?imageMogr2/thumbnail/2500x2500%3E
     * version : v2d imax
     * nm : 长安三万里
     * preShow : false
     * sc : 9.5
     * globalReleased : false
     * wish : 320747
     * star : 杨天翔,凌振赫,吴俊全
     * rt : 2023-07-08
     * showInfo : 2023-07-08 本周六上映
     * showst : 4
     * wishst : 0
     * comingTitle : 7月8日 周六
     * showStateButton : {"color":"#3C9FE6","content":"预售","onlyPreShow":false,"shadowColor":"rgba(60,159,230, 0.15)"}
     */

    private String id;
    private String haspromotionTag;
    private String img;
    private String version;
    private String nm;
    private String preShow;
    private String sc;
    private String globalReleased;
    private String wish;
    private String star;
    private String rt;
    private String showInfo;
    private String showst;
    private String wishst;
    private String comingTitle;
    private ShowStateButtonBean showStateButton;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHaspromotionTag() {
        return haspromotionTag;
    }

    public void setHaspromotionTag(String haspromotionTag) {
        this.haspromotionTag = haspromotionTag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getPreShow() {
        return preShow;
    }

    public void setPreShow(String preShow) {
        this.preShow = preShow;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getGlobalReleased() {
        return globalReleased;
    }

    public void setGlobalReleased(String globalReleased) {
        this.globalReleased = globalReleased;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(String showInfo) {
        this.showInfo = showInfo;
    }

    public String getShowst() {
        return showst;
    }

    public void setShowst(String showst) {
        this.showst = showst;
    }

    public String getWishst() {
        return wishst;
    }

    public void setWishst(String wishst) {
        this.wishst = wishst;
    }

    public String getComingTitle() {
        return comingTitle;
    }

    public void setComingTitle(String comingTitle) {
        this.comingTitle = comingTitle;
    }

    public ShowStateButtonBean getShowStateButton() {
        return showStateButton;
    }

    public void setShowStateButton(ShowStateButtonBean showStateButton) {
        this.showStateButton = showStateButton;
    }

    public static class ShowStateButtonBean {
        /**
         * color : #3C9FE6
         * content : 预售
         * onlyPreShow : false
         * shadowColor : rgba(60,159,230, 0.15)
         */

        private String color;
        private String content;
        private String onlyPreShow;
        private String shadowColor;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOnlyPreShow() {
            return onlyPreShow;
        }

        public void setOnlyPreShow(String onlyPreShow) {
            this.onlyPreShow = onlyPreShow;
        }

        public String getShadowColor() {
            return shadowColor;
        }

        public void setShadowColor(String shadowColor) {
            this.shadowColor = shadowColor;
        }
    }
}

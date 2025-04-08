package com.example.maoyan.Bean;


import com.google.gson.Gson;

public class Hot {

    /**
     * bingeWatch : 0
     * boxInfo : 上映1天，累计票房48092万
     * cat : 剧情
     * civilPubSt : 0
     * comingTitle : 7月6日 周四
     * desc : 主演:王宝强,陈永胜,史彭元
     * dir : 王宝强
     * dur : 117
     * effectShowNum : 0
     * followst : 0
     * globalReleased : true
     * haspromotionTag : false
     * headLineShow : false
     * id : 1413252
     * img : https://p0.pipi.cn/mmdb/fb73867106d8079257f2aa6c2aa3f22e9365a.jpg?imageMogr2/thumbnail/2500x2500%3E
     * isRevival : false
     * late : false
     * localPubSt : 0
     * mark : false
     * mk : 9.5
     * movieType : 0
     * nm : 八角笼中
     * pn : 227
     * preSale : 0
     * preShow : false
     * proScore : 0
     * proScoreNum : 0
     * pubDate : 1688572800000
     * pubDesc : 2023-07-06 09:00中国大陆上映
     * pubShowNum : 0
     * recentShowDate : 1688572800000
     * recentShowNum : 0
     * rt : 2023-07-06
     * sc : 9.5
     * scm : 使劲活！王宝强新作冲破命运枷锁
     * scoreLabel : 猫眼购票评分
     * showCinemaNum : 236
     * showInfo : 今天236家影院放映2292场
     * showNum : 2292
     * showStateButton : {"color":"#F03D37","content":"购票","onlyPreShow":false}
     * showTimeInfo : 2023-07-06 本周四上映
     * showst : 3
     * snum : 154209
     * star : 王宝强,陈永胜,史彭元
     * totalShowNum : 5876
     * ver : IMAX 2D/杜比影院 2D/中国巨幕2D/CINITY 2D/2D/4DX
     * videoId : 498630
     * videoName : 王宝强新片《八角笼中》曝终极预告 冲破命运牢笼为出路“野蛮生长”
     * videourl : https://vod.pipi.cn/fec9203cvodtransbj1251246104/7e4de4763270835009929188887/v.f42905.mp4
     * vnum : 8
     * vodPlay : false
     * wish : 451954
     * wishst : 0
     */

    private String price;
    private String bingeWatch;
    private String boxInfo;
    private String cat;
    private String civilPubSt;
    private String comingTitle;
    private String desc;
    private String dir;
    private String dur;
    private String effectShowNum;
    private String followst;
    private boolean globalReleased;
    private boolean haspromotionTag;
    private boolean headLineShow;
    private String id;
    private String img;
    private boolean isRevival;
    private boolean late;
    private String localPubSt;
    private boolean mark;
    private double mk;
    private String movieType;
    private String nm;
    private String pn;
    private String preSale;
    private boolean preShow;
    private String proScore;
    private String proScoreNum;
    private long pubDate;
    private String pubDesc;
    private int pubShowNum;
    private long recentShowDate;
    private int recentShowNum;
    private String rt;
    private String sc;
    private String scm;
    private String scoreLabel;
    private String showCinemaNum;
    private String showInfo;
    private String showNum;
    private ShowStateButtonBean showStateButton;
    private String showTimeInfo;
    private String showst;
    private String snum;
    private String star;
    private int totalShowNum;
    private String ver;
    private int videoId;
    private String videoName;
    private String videourl;
    private int vnum;
    private boolean vodPlay;
    private String wish;
    private String wishst;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isRevival() {
        return isRevival;
    }

    public void setRevival(boolean revival) {
        isRevival = revival;
    }

    public String getBingeWatch() {
        return bingeWatch;
    }

    public void setBingeWatch(String bingeWatch) {
        this.bingeWatch = bingeWatch;
    }

    public String getBoxInfo() {
        return boxInfo;
    }

    public void setBoxInfo(String boxInfo) {
        this.boxInfo = boxInfo;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCivilPubSt() {
        return civilPubSt;
    }

    public void setCivilPubSt(String civilPubSt) {
        this.civilPubSt = civilPubSt;
    }

    public String getComingTitle() {
        return comingTitle;
    }

    public void setComingTitle(String comingTitle) {
        this.comingTitle = comingTitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDur() {
        return dur;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public String getEffectShowNum() {
        return effectShowNum;
    }

    public void setEffectShowNum(String effectShowNum) {
        this.effectShowNum = effectShowNum;
    }

    public String getFollowst() {
        return followst;
    }

    public void setFollowst(String followst) {
        this.followst = followst;
    }

    public boolean isGlobalReleased() {
        return globalReleased;
    }

    public void setGlobalReleased(boolean globalReleased) {
        this.globalReleased = globalReleased;
    }

    public boolean isHaspromotionTag() {
        return haspromotionTag;
    }

    public void setHaspromotionTag(boolean haspromotionTag) {
        this.haspromotionTag = haspromotionTag;
    }

    public boolean isHeadLineShow() {
        return headLineShow;
    }

    public void setHeadLineShow(boolean headLineShow) {
        this.headLineShow = headLineShow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isIsRevival() {
        return isRevival;
    }

    public void setIsRevival(boolean isRevival) {
        this.isRevival = isRevival;
    }

    public boolean isLate() {
        return late;
    }

    public void setLate(boolean late) {
        this.late = late;
    }

    public String getLocalPubSt() {
        return localPubSt;
    }

    public void setLocalPubSt(String localPubSt) {
        this.localPubSt = localPubSt;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public double getMk() {
        return mk;
    }

    public void setMk(double mk) {
        this.mk = mk;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getPreSale() {
        return preSale;
    }

    public void setPreSale(String preSale) {
        this.preSale = preSale;
    }

    public boolean isPreShow() {
        return preShow;
    }

    public void setPreShow(boolean preShow) {
        this.preShow = preShow;
    }

    public String getProScore() {
        return proScore;
    }

    public void setProScore(String proScore) {
        this.proScore = proScore;
    }

    public String getProScoreNum() {
        return proScoreNum;
    }

    public void setProScoreNum(String proScoreNum) {
        this.proScoreNum = proScoreNum;
    }

    public long getPubDate() {
        return pubDate;
    }

    public void setPubDate(long pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDesc() {
        return pubDesc;
    }

    public void setPubDesc(String pubDesc) {
        this.pubDesc = pubDesc;
    }

    public int getPubShowNum() {
        return pubShowNum;
    }

    public void setPubShowNum(int pubShowNum) {
        this.pubShowNum = pubShowNum;
    }

    public long getRecentShowDate() {
        return recentShowDate;
    }

    public void setRecentShowDate(long recentShowDate) {
        this.recentShowDate = recentShowDate;
    }

    public int getRecentShowNum() {
        return recentShowNum;
    }

    public void setRecentShowNum(int recentShowNum) {
        this.recentShowNum = recentShowNum;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getScm() {
        return scm;
    }

    public void setScm(String scm) {
        this.scm = scm;
    }

    public String getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(String scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public String getShowCinemaNum() {
        return showCinemaNum;
    }

    public void setShowCinemaNum(String showCinemaNum) {
        this.showCinemaNum = showCinemaNum;
    }

    public String getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(String showInfo) {
        this.showInfo = showInfo;
    }

    public String getShowNum() {
        return showNum;
    }

    public void setShowNum(String showNum) {
        this.showNum = showNum;
    }

    public ShowStateButtonBean getShowStateButton() {
        return showStateButton;
    }

    public void setShowStateButton(ShowStateButtonBean showStateButton) {
        this.showStateButton = showStateButton;
    }

    public String getShowTimeInfo() {
        return showTimeInfo;
    }

    public void setShowTimeInfo(String showTimeInfo) {
        this.showTimeInfo = showTimeInfo;
    }

    public String getShowst() {
        return showst;
    }

    public void setShowst(String showst) {
        this.showst = showst;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public int getTotalShowNum() {
        return totalShowNum;
    }

    public void setTotalShowNum(int totalShowNum) {
        this.totalShowNum = totalShowNum;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public int getVnum() {
        return vnum;
    }

    public void setVnum(int vnum) {
        this.vnum = vnum;
    }

    public boolean isVodPlay() {
        return vodPlay;
    }

    public void setVodPlay(boolean vodPlay) {
        this.vodPlay = vodPlay;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getWishst() {
        return wishst;
    }

    public void setWishst(String wishst) {
        this.wishst = wishst;
    }


    public static class ShowStateButtonBean {
        /**
         * color : #F03D37
         * content : 购票
         * onlyPreShow : false
         */

        private String color;
        private String content;
        private boolean onlyPreShow;

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

        public boolean isOnlyPreShow() {
            return onlyPreShow;
        }

        public void setOnlyPreShow(boolean onlyPreShow) {
            this.onlyPreShow = onlyPreShow;
        }
    }
}

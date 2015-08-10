package cn.edu.scau.hometown.library.com.javis.mylistview;

/**
 * Created by laisixiang on 2015/8/10.
 * 为了Adapter_ListView_hmtF.java而产生
 */
public class ItemBean {
    private String titile;
    private String time;
    private String owner;
    private String commmentNum;
    private String brief;
    private String anthor;

    public ItemBean(){
        this.titile = "默认标题";
        this.owner = "默认主题";
        this.time = "2015-07-10";
        this.commmentNum = "回复 100";
        this.anthor = "风一样的驴子";
        this.brief = "我和草原有个约定想也是凉快点积分绿卡的减肥了 拉伸到机房拉开当数据分开啊，奥斯卡的减肥了卡。ID经费卢卡斯的路口附近啊流口水将对方离开就按到了空间发卡机的离开房间爱思考就";
    }

    /**
     *
     * @param titile
     * @param time
     * @param owner
     * @param commmentNum
     * @param brief
     */
    public ItemBean(String titile, String time, String owner, String commmentNum, String brief) {
        this.titile = titile;
        this.time = time;
        this.owner = owner;
        this.commmentNum = "回复 "+commmentNum;
        this.brief = brief;
    }

    public ItemBean(String titile, String owner, String time, String commmentNum, String anthor, String brief) {
        this.titile = titile;
        this.owner = owner;
        this.time = time;
        this.commmentNum = "回复 "+commmentNum;
        this.anthor = anthor;
        this.brief = brief;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCommmentNum(String commmentNum) {
        this.commmentNum = commmentNum;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setAnthor(String anthor) {
        this.anthor = anthor;
    }

    public String getTitile() {
        return titile;
    }

    public String getTime() {
        return time;
    }

    public String getCommmentNum() {
        return commmentNum;
    }

    public String getOwner() {
        return owner;
    }

    public String getBrief() {
        return brief;
    }

    public String getAnthor() {
        return anthor;
    }

}

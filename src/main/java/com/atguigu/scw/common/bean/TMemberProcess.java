package com.atguigu.scw.common.bean;

public class TMemberProcess {
    private Integer id;

    private Integer memberid;

    private String prosinstid;

    private String createtime;

    private String protype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getProsinstid() {
        return prosinstid;
    }

    public void setProsinstid(String prosinstid) {
        this.prosinstid = prosinstid == null ? null : prosinstid.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype == null ? null : protype.trim();
    }
}
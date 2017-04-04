package com.bawei.demo_yuekaoa.bean;

/**
 * 作    者：云凯文
 * 时    间：2017/4/4
 * 描    述：
 * 修改时间：
 */

public class RowsBean {


    private String fang_type;
    private InfoBean info;

    public String getFang_type() {
        return fang_type;
    }

    public void setFang_type(String fang_type) {
        this.fang_type = fang_type;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "RowsBean{" +
                "fang_type='" + fang_type + '\'' +
                ", info=" + info +
                '}';
    }
}

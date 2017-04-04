package com.bawei.demo_yuekaoa.bean;

import java.util.List;

/**
 * 作    者：云凯文
 * 时    间：2017/4/4
 * 描    述：
 * 修改时间：
 */

public class ResultBean {

    private String title;
    private String total;
    private List<RowsBean> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "title='" + title + '\'' +
                ", total='" + total + '\'' +
                ", rows=" + rows +
                '}';
    }
}

package com.baseinfo.collect.beans;

/**
 * 搜索命中结果
 */
public class TotalHits {

    private Long total;

    public TotalHits(long total){
        this.total = total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }
}

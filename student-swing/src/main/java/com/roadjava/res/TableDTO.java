package com.roadjava.res;

import java.util.Vector;

/**
 * @author zhaodaowen
 * @see <a href="wer">java</a>
 */
public class TableDTO {
    private Vector<Vector<Object>> data;
    private int totalCount;

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}

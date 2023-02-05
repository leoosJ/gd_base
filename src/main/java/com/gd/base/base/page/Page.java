package com.gd.base.base.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页组件
 *
 * @author JLP
 * @param <T>
 * @date 2022-12-18
 */
public class Page<T> extends PageInfo<T> {

    private static final long serialVersionUID = 1L;

    public Page() {
        PageHelper.startPage(1, 10);
    }

    public Page(int pageNum, int pageSize) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
    }

    @Override
    public void setList(List<T> list) {
        if (list instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<T> page = (com.github.pagehelper.Page<T>) list;
            this.setPageNum(page.getPageNum());
            this.setPageSize(page.getPageSize());
            this.setPages(page.getPages());
            super.setList(list);
            this.setSize(page.size());
            this.setTotal(page.getTotal());
            // 由于结果集是>startRow的，所以实际的需要+1
            if (this.getSize() == 0) {
                this.setStartRow(0);
                this.setEndRow(0);
            } else {
                this.setStartRow(page.getStartRow() + 1);
                // 计算实际的endRow（最后一页的时候特殊）
                this.setEndRow(this.getStartRow() - 1 + this.getSize());
            }
        } else {
            super.setList(list);
        }
    }

}

package com.gd.base.base.crud;

import com.gd.base.base.entity.BaseEntity;
import com.gd.base.base.page.Page;
import com.gd.base.common.utils.IdGenUtil;
import com.gd.base.common.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 基础Service封装
 *
 * @author JLP
 * @param <D>
 * @param <T>
 * @date 2022-12-18
 */
public abstract class CrudService<D extends CrudMapper<T>, T extends BaseEntity> {

    /**
     * 持久层对象
     */
    @Autowired(required = false)
    protected D mapper;

    /**
     * 根据ID获取单条数据
     * @param id
     * @return
     */
    public T get(String id) {
        return this.mapper.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return this.mapper.get(entity);
    }

    /**
     * 获取数据列表
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return this.mapper.findList(entity);
    }

    /**
     * 获取数据分页列表
     * @param page
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        page.setList(this.mapper.findList(entity));
        return page;
    }

    /**
     * 保存数据
     * @param entity
     */
    public void save(T entity) {
        if (entity.isNewRecord()) {
            if (StringUtils.isEmpty(entity.getId())) {
                entity.setId(IdGenUtil.uuid());
            }
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());

            if (StringUtils.isEmpty(entity.getCreateId())) {
                entity.setCreateId(UserUtil.getUserId());
            }
            if (StringUtils.isEmpty(entity.getUpdateId())) {
                entity.setUpdateId(UserUtil.getUserId());
            }
            this.mapper.insert(entity);
        } else {
            entity.setUpdateDate(new Date());
            entity.setUpdateId(UserUtil.getUserId());
            this.mapper.update(entity);
        }
    }

    /**
     * 物理删除数据
     * @param id
     */
    public void delete(String id) {
        this.mapper.delete(id);
    }

    /**
     * 逻辑删除数据
     * @param id
     */
    public void deleteByLogic(String id) {
        this.mapper.deleteByLogic(id);
    }

}

package com.gd.base.base.crud;

import java.util.List;

/**
 * 基础Mapper封装
 *
 * @author JLP
 * @param <T>
 * @date 2022-12-18
 */
public interface CrudMapper<T> {

    /**
     * 根据ID获取单条数据
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity);

    /**
     * 获取数据列表
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * 删除数据（物理删除）
     * @param id
     * @return
     */
    public int delete(String id);

    /**
     * 删除数据（逻辑删除）
     * @param id
     * @return
     */
    public int deleteByLogic(String id);

}

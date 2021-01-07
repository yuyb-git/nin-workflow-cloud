package cn.netinnet.cloudcommon.base;


import java.util.List;


/**
 * @param <T>
 * @author admin
 * @date 2017/12/11
 */
public interface Service<T> {

    /**
     * 根据唯一主键查询
     *
     * @param id
     * @return
     */
    T selectById(long id);

    /**
     * 根据唯一主键，逻辑删除
     *
     * @param id
     * @param userId
     * @return
     */
    // int deleteLogical(int id, long userId);

    /**
     * 根据唯一主键，物理删除
     *
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 通过主键更新
     *
     * @param t
     * @return
     */
    int updateByIdSelective(T t);

    /**
     * 新增插入
     *
     * @param t
     * @param userId
     * @return
     */
    int insertSelective(T t, long userId);

    /**
     * 物理查询
     *
     * @param t
     * @return
     */
    List<T> getList(T t);

    /**
     * 批量插入，全字段
     *
     * @param list
     * @return
     */
    int batchInsert(List<T> list);

    /**
     * 批量插入，部分字段
     *
     * @param list
     * @return
     */
    int batchInsertSelective(List<T> list);

    /**
     * 批量删除，通过列表
     *
     * @param list
     * @return
     */
    int batchDeleteByList(List<Long> list);

    /**
     * 批量删除，通过数组
     *
     * @param ids
     * @return
     */
    int batchDeleteByArray(long[] ids);

    /**
     * 批量更新，通过主键
     *
     * @param list
     * @return
     */
    int batchUpdateById(List<T> list);

    /**
     * @return
     */
    Class getClazz();


}
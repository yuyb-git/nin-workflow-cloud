package cn.netinnet.cloudcommon.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Liyq [Liyq@netinnet.cn]
 * @date 2017/12/4 13:40
 */
public abstract class BaseService<T> implements Service<T> {

    @Autowired
    protected BaseMapper<T> mapper;

    /**
     * 根据唯一主键查询
     *
     * @param id
     * @return
     */
    @Override
    public T selectById(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据唯一主键，物理删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过主键更新
     *
     * @param t
     * @return
     */
    @Override
    public int updateByIdSelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 新增插入
     *
     * @param t
     * @param userId
     * @return
     */
    @Override
    public int insertSelective(T t, long userId) {
        return mapper.insertSelective(t);
    }

    /**
     * 物理查询
     *
     * @param t
     * @return
     */
    @Override
    public List<T> getList(T t) {
        return mapper.getList(t);
    }

    /**
     * 批量插入，全字段
     *
     * @param list
     * @return
     */
    @Override
    public int batchInsert(List<T> list) {
        if (null != list && list.size() > 0) {
            return mapper.batchInsert(list);
        }
        return 0;
    }

    /**
     * 批量插入，部分字段
     *
     * @param list
     * @return
     */
    @Override
    public int batchInsertSelective(List<T> list) {
        if (null != list && list.size() > 0) {
            return mapper.batchInsertSelective(list);
        }
        return 0;
    }

    /**
     * 批量删除，通过ID列表
     *
     * @param list
     * @return
     */
    @Override
    public int batchDeleteByList(List<Long> list) {
        return mapper.batchDeleteByList(list);
    }

    /**
     * 批量删除，通过ID数组
     *
     * @param ids
     * @return
     */
    @Override
    public int batchDeleteByArray(long[] ids) {
        return mapper.batchDeleteByArray(ids);
    }

    /**
     * 批量更新，通过主键
     *
     * @param list
     * @return
     */
    @Override
    public int batchUpdateById(List<T> list) {
        return mapper.batchUpdateByPrimaryKey(list);
    }
}

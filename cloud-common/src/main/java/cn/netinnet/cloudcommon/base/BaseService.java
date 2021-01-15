package cn.netinnet.cloudcommon.base;

import cn.netinnet.cloudcommon.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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
     * 物理查询
     *
     * @param fieldNames  以,隔开的字段名称
     * @param fieldValues 字段名称对应的查询的条件
     *                    反射注入一个bean对象然后进行查询 避免每次都去new一个新的对象
     * @author zhangwy
     */
    @Override
    public List<T> getList(String fieldNames, Object... fieldValues) {
        return getListByArray(fieldNames, fieldValues);
    }

    private List<T> getListByArray(String fieldNames, Object[] fieldValues) {
        Class clazz = this.getClazz();
        try {
            T instance = (T) clazz.newInstance();
            String[] fieldNamesList = fieldNames.split(",");
            if (fieldNamesList.length != fieldValues.length) {
                throw new IllegalArgumentException("参数不对");
            }
            for (int index = 0; index < fieldNamesList.length; index++) {

                String fieldName = fieldNamesList[index];
                String field = StringUtil.firstCharToUpperCase(fieldName.trim());

                Method method = BeanUtils.findDeclaredMethodWithMinimalParameters(clazz, "set" + field);
                method.invoke(instance, fieldValues[index]);
            }
            return getList(instance);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 逻辑查询
     *
     * @param fieldNames
     * @param filedValues
     * @return
     */
    @Override
    public List<T> getLogicalList(String fieldNames, Object... filedValues) {
        List<Object> list = new ArrayList<>(Arrays.asList(filedValues));
        return getListByArray(fieldNames + ", del", list.toArray());
    }

    /**
     * 逻辑查询，查询出一个实体
     *
     * @param fieldNames
     * @param filedValues
     * @return
     */
    @Override
    public T getLogicalSingle(String fieldNames, Object... filedValues) {
        List<T> list = getLogicalList(fieldNames, filedValues);
        if (list.size() >= 1) {
            return list.get(0);
        }
        return null;
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

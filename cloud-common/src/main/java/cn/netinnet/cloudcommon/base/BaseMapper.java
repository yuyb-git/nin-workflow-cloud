package cn.netinnet.cloudcommon.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 欧阳宣来
 * @date 2017/12/8
 */
public interface BaseMapper<T> {
    T selectByPrimaryKey(long var1);

    int deleteByPrimaryKey(long var1);

    int updateByPrimaryKeySelective(T var1);

    int insertSelective(T var1);

    List<T> getList(@Param("item") T var1);

    int batchInsert(List<T> var1);

    int batchInsertSelective(List<T> var1);

    int batchDeleteByList(List<Long> var1);

    int batchDeleteByArray(long[] var1);

    int batchUpdateByPrimaryKey(List<T> var1);

}
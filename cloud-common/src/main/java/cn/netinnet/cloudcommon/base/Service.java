package cn.netinnet.cloudcommon.base;

import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

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
     * 物理查询
     *
     * @param fieldNames
     * @param filedValues
     * @return
     */
    List<T> getList(String fieldNames, Object... filedValues);

    /**
     * 逻辑查询
     *
     * @param fieldNames
     * @param filedValues
     * @return
     */
    List<T> getLogicalList(String fieldNames, Object... filedValues);

    /**
     * 逻辑查询，查询出一个实体
     *
     * @param fieldNames
     * @param filedValues
     * @return
     */
    T getLogicalSingle(String fieldNames, Object... filedValues);

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

    /**
     * @param pageFun
     * @param pageInfo pageHelper
     * @return
     * @Author liyq liyq@netinnet.cn
     * @date 2017/12/8 9:40
     * @description EJS模板分页返回数据封装
     */
    default HttpResultEntry returnPage2EJS(String pageFun, PageInfo pageInfo) {
        return returnPage2EJS(pageInfo.getPageNum(), pageInfo.getPageSize(), pageFun, pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @param page
     * @param pageCount
     * @param pageFun
     * @param total
     * @param list
     * @return
     * @Author liyq liyq@netinnet.cn
     * @date 2017/12/8 9:41
     * @description EJS模板分页返回数据
     */
    default HttpResultEntry returnPage2EJS(Integer page, Integer pageCount, String pageFun, Long total, List list) {
        JSONObject data = new JSONObject();
        data.put("page", page);
        data.put("pageCount", pageCount);
        data.put("pageFun", pageFun);
        data.put("total", total);
        data.put("list", list);
        return HttpResultEntry.customize(ResultEnum.R_SUCC, data);
    }
}
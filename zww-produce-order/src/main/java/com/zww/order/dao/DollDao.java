package com.zww.order.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.zww.api.model.Doll;
import com.zww.api.model.DollTopic;
import com.zww.api.model.TopicInfo;
import com.zww.api.model.vo.DollAndAddress;


public interface DollDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Doll record);

    int insertSelective(Doll record);

    Doll selectByPrimaryKey(Integer id);

    List<Doll> selectDollHistory(Integer memberId);

    //清除状态
    int updateClean(Doll record);

    int updateByPrimaryKeySelective(Doll record);

    int updateByPrimaryKey(Doll record);

    /**
     * 获取娃娃机主题列表
     *
     * @return
     */
    List<DollTopic> getDollTopics();

    /**
     * 查询所有娃娃机
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return
     */
    List<Doll> getDollList(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询所有娃娃机
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return
     */
    List<Doll> getH5DollList(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 加载主题房间
     *
     * @param offset
     * @param limit
     * @param dollTopic
     * @return
     */
    List<Doll> getDollTopicList(@Param("offset") int offset, @Param("limit") int limit, @Param("dollTopic") int dollTopic);

    /**
     * 加载H5主题房间
     *
     * @return
     */
    List<Doll> getH5DollTopicList();

    //获取娃娃机数据总数量
    int getTotalCount();

    //分页查询
    List<DollAndAddress> dollList(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("name") String name, @Param("machineCode") String machineCode, @Param("machineStatus") String machineStatus);

    /**
     * 根据id删除娃娃机
     */
    int dollDel(Doll doll);

    /**
     * 根据id查询娃娃机
     */
    Doll selectById(Doll doll);

    int totalCount(@Param("name") String name, @Param("machineCode") String machineCode, @Param("machineStatus") String machineStatus);

    List<Doll> allDollList();

    Doll getDollByName(@Param("name") String name);

    //删除状态
    Integer updateDeleteStatus(Integer id);

    List<TopicInfo> getTopicInfo();

    Integer selectTypeById(Integer dollId);

    List<Integer> selectDollId();

    Doll getDollByDollCode(String dollCode);

    List<Doll> selectSpareRoom();

}
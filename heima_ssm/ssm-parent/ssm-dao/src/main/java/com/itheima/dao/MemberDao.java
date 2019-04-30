package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: YangRunTao
 * @Description: 会员持久层
 * @Date: 2019/04/26 16:44
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface MemberDao {
    /**
     * @param memberId
     * @description: 根据订单查询会员
     * @return: com.itheima.domain.Member
     * @author: YangRunTao
     * @date: 2019/04/26 16:50
     * @throws:
     **/
    @Select("select * from member where id = #{memberId}")
    Member findById(String memberId);
}

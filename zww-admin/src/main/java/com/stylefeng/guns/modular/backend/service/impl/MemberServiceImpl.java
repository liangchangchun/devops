package com.stylefeng.guns.modular.backend.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.dao.AccountMapper;
import com.stylefeng.guns.common.persistence.dao.TMemberTokenMapper;
import com.stylefeng.guns.common.persistence.dao.TModifyRecordMapper;
import com.stylefeng.guns.common.persistence.model.Account;
import com.stylefeng.guns.common.persistence.model.Member;
import com.stylefeng.guns.common.persistence.dao.MemberMapper;
import com.stylefeng.guns.common.persistence.model.TModifyRecord;
import com.stylefeng.guns.core.util.RedisKeyGenerator;
import com.stylefeng.guns.modular.backend.service.IMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangchangchun
 * @since 2018-01-02
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private TMemberTokenMapper memberTokenMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TModifyRecordMapper tModifyRecordMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;


    @Override
    public List<Map<String, Object>> selectMember(Page<Member> page,Integer id, String userId, String userName, String beginTime, String lastLoginFrom) {
        return memberMapper.selectMember(page,id,userId,userName,beginTime,lastLoginFrom);
    }

    @Override
    public List<Map<String, Object>> selectChannel(Page<Member> page, String channelNum,String userId, String userName, String beginTime,String endTime, String lastLoginFrom) {
        return memberMapper.selectChannel(page,channelNum,userId,userName,beginTime,endTime,lastLoginFrom);
    }

    @Override
    public List<Map<String, Object>> xiaoyaojingSelectChannel(Page<Member> page, String channelNum, String userId, String userName, String beginTime, String endTime, String lastLoginFrom) {
        return memberMapper.xiaoyaojingSelectChannel(page,channelNum,userId,userName,beginTime,endTime,lastLoginFrom);
    }

    @Override
    public Member selectIdByMemberId(String memberId) {
        return memberMapper.selectIdByMemberId(memberId);
    }

    @Override
    public Integer updateById(Account account) {
        Account ac = accountMapper.selectById(account.getId());
        if(ac.getCoins()!=account.getCoins()) {
            TModifyRecord tModifyRecord = new TModifyRecord();
            tModifyRecord.setMemberId(account.getId());
            tModifyRecord.setCoins(account.getCoins()-ac.getCoins());
            tModifyRecord.setSuperTicket(account.getSuperTicket()-ac.getSuperTicket());
            tModifyRecord.setModifiedDate(new Date());
            tModifyRecordMapper.insertSelective(tModifyRecord);
        }
        return accountMapper.updateByKeyId(account);
    }

    @Override
    public Integer closeMember(Integer memberId) {

        List<String> tokens = memberTokenMapper.selectTokenById(memberId);
        String rtoken = valOpsStr.get(String.valueOf(memberId));
        if (rtoken != null) {
            tokens.add(rtoken);
        }
        if (tokens != null && tokens.size() > 0) {
            stringRedisTemplate.delete(RedisKeyGenerator.getMemberInfoKey(memberId));
            //删除数据库 token
            memberTokenMapper.deleteByWhere(memberId);
            for (String token : tokens) {
                //删除Redis token
                stringRedisTemplate.delete(token);
            }
        }
        //删除Redis token
        stringRedisTemplate.delete(memberId.toString());

        Member member = new Member();
        member.setId(memberId);
        member.setActiveFlg(false);
        return memberMapper.updateById(member);

    }


    @Override
    public Integer openMember(Integer memberId) {
        Member member = new Member();
        member.setId(memberId);
        member.setActiveFlg(true);
        return memberMapper.updateById(member);
    }
}

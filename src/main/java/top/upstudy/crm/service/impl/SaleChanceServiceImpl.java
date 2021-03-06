package top.upstudy.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.upstudy.crm.enums.DevResult;
import top.upstudy.crm.enums.StateStatus;
import top.upstudy.crm.pojo.SaleChance;
import top.upstudy.crm.mapper.SaleChanceMapper;
import top.upstudy.crm.query.SaleChanceQuery;
import top.upstudy.crm.service.SaleChanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.upstudy.crm.utils.AssertUtil;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SaleChanceServiceImpl extends ServiceImpl<SaleChanceMapper, SaleChance> implements SaleChanceService {

    @Resource
    private SaleChanceMapper saleChanceMapper;

    /**
     * 查询操作
     * @param saleChanceQuery
     * @return
     */
    //通过传入参数查询营销机会,多条件分页查询营销机会
    public Map<String,Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery){

        //把查询值放在map中
        Map<String,Object> map = new HashMap<String, Object>();

        //获取分页状态
        PageHelper.startPage(saleChanceQuery.getPage(),saleChanceQuery.getLimit());
        //使用了pageHelper查询结果并分页
        PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceMapper.selectByParams(saleChanceQuery));
        //将获取到的分页信息放入map中，然后返回
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        //返回结果集
        return map;
    }


    /**
     * 添加营销机会
     * @param saleChance
     */
    public void saveSaleChance(SaleChance saleChance){
        /**
         *      设置相关参数默认值，创建人为当前登陆用户
         *       state 默认未分配   如果选择分配人  state 为已分配状态
         *       assignTime 默认空   如果选择分配人  分配时间为系统当前时间
         *       devResult  默认未开发  如果选择分配人 devResult 为开发中 0-未开发  1-开发中 2-开发成功 3-开发失败
         *       isValid  默认有效(1-有效  0-无效)
         *       createDate  updateDate:默认系统当前时间
         */
        //写入状态（默认）
        saleChance.setState(StateStatus.UNSTATE.getType());
        saleChance.setDevResult(DevResult.UNDEV.getStatus());
        //如果分配人不为空
        if(StringUtils.isNotBlank(saleChance.getAssignMan())){
            //写入开发状态，枚举对象取值
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
            saleChance.setAssignTime(new Date());
        }
        //逻辑存在
        saleChance.setIsValid(1);
        //写入创建和更新时间
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        //插入数据
        AssertUtil.isTrue(saleChanceMapper.insert(saleChance) < 1,"机会数据添加失败!");
    }


    /**
     * 更新营销机会记录
     */

    public void updateSaleChance(SaleChance saleChance){

        //先查询将要更新的记录在不在，获取对象
        SaleChance temp = saleChanceMapper.selectById(saleChance.getId());
        AssertUtil.isTrue(null == temp,"待更新记录不存在!");

        //写入当前记录的更新时间
        saleChance.setUpdateDate(new Date());
        /**
         *       未分配 修改后
         *       已分配(分配人是否存在)
         *          state   0--->1
         *          assignTime   设置分配时间 系统时间
         *          devResult  0--->1
         *
         *       原始记录  已分配
         *       修改后  未分配
         *         state 1-->0
         *         assignTime  null
         *         devResult 1-->0
         *
         *         原始记录  已分配
         *          修改后  已分配
         *          assignTime   设置分配时间 系统时间
         */
        //如果数据库中记录的分配人为空，并且传入的对象的分配人不为空（为记录进行分配操作）
        if(StringUtils.isBlank(temp.getAssignMan()) && StringUtils.isNotBlank(saleChance.getAssignMan()) ){
            //修改为已分配
            saleChance.setState(StateStatus.STATED.getType());
            //写入分配时间
            saleChance.setAssignTime(new Date());
            //写入开发状态
            saleChance.setDevResult(DevResult.DEVING.getStatus());
        }else if(StringUtils.isNotBlank(temp.getAssignMan()) && StringUtils.isBlank(saleChance.getAssignMan())){
            //修改为未分配状态
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setAssignTime(null);
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
            saleChance.setAssignMan("");

        }else if(StringUtils.isNotBlank(temp.getAssignMan()) && StringUtils.isNotBlank(saleChance.getAssignMan())){
            //如果前后分配人不是同一个
            if(!saleChance.getAssignMan().equals(temp.getAssignMan())){
               saleChance.setAssignTime(new Date());
            }
            //如果前后分配人是同一个,指派时间不变
            else{
                saleChance.setAssignTime(temp.getAssignTime());
            }
        }

        //查看更新结果
        AssertUtil.isTrue(saleChanceMapper.updateById(saleChance) < 1,"机会数据更新失败!");
    }


    /**
     * 批量删除
     */

    public void deleteSaleChance(Integer[] ids){
        AssertUtil.isTrue(null == ids || ids.length == 0,"请选择待删除记录!");
        AssertUtil.isTrue(saleChanceMapper.deleteBatchIds(Arrays.asList(ids)) != ids.length,"记录删除失败!");
    }


    //更新开发结果
    public void updateSaleChanceDevResult(Integer id, Integer devResult){
        AssertUtil.isTrue(null == id,"待更新记录不存在!");
        SaleChance temp =saleChanceMapper.selectById(id);
        AssertUtil.isTrue(null == temp,"待更新记录不存在!");
        temp.setDevResult(devResult);
        AssertUtil.isTrue(saleChanceMapper.updateById(temp) < 1,"机会数据状态更新失败!");
    }

}

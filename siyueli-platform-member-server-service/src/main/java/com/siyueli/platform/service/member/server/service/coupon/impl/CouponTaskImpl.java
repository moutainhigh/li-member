package com.siyueli.platform.service.member.server.service.coupon.impl;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.constants.coupon.CouponTaskStatus;
import com.siyueli.platform.member.constants.coupon.CouponTaskType;
import com.siyueli.platform.member.constants.coupon.ScopeType;
import com.siyueli.platform.member.pojo.coupon.Coupon;
import com.siyueli.platform.member.pojo.coupon.CouponTask;
import com.siyueli.platform.member.pojo.coupon.CouponTaskChannel;
import com.siyueli.platform.member.pojo.coupon.CouponTaskGift;
import com.siyueli.platform.member.pojo.coupon.CouponTaskSku;
import com.siyueli.platform.member.request.coupon.CouponTaskListRequest;
import com.siyueli.platform.member.request.coupon.CouponTaskRequest;
import com.siyueli.platform.member.request.coupon.GetCouponTaskListRequest;
import com.siyueli.platform.member.response.coupon.CouponTaskResponse;
import com.siyueli.platform.member.response.coupontask.GetCouponTaskListResponse;
import com.siyueli.platform.member.response.coupontask.GetCouponTaskResponse;
import com.siyueli.platform.service.member.server.mapper.coupon.CouponTaskMapper;
import com.siyueli.platform.service.member.server.service.coupon.CouponServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskChannelServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskGiftServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskSkuServiceContract;
import com.siyueli.platform.service.member.server.util.ConvertUtil;
import com.siyueli.platform.service.member.server.util.JsonTokenUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.exceptions.exception.RequestException;

/**
 * <p>
 * 卡券任务 服务实现类
 * </p>
 */
@Service
public class CouponTaskImpl extends ServiceImpl<CouponTaskMapper, CouponTask> implements CouponTaskServiceContract {

    private CouponTaskGiftServiceContract taskGiftService;

    private CouponTaskSkuServiceContract taskSkuService;

    private CouponServiceContract couponService;

    private CouponTaskChannelServiceContract couponTaskChannelService;

    private JsonTokenUtil jsonTokenUtil;

    @Autowired
    public CouponTaskImpl(
            CouponTaskGiftServiceContract taskGiftService, CouponTaskSkuServiceContract taskSkuService,
            CouponServiceContract couponService,
            CouponTaskChannelServiceContract couponTaskChannelService,
            JsonTokenUtil jsonTokenUtil
    ) {
        this.taskGiftService = taskGiftService;
        this.taskSkuService = taskSkuService;
        this.couponService = couponService;
        this.couponTaskChannelService = couponTaskChannelService;
        this.jsonTokenUtil = jsonTokenUtil;
    }

    @Override
    public Page<CouponTaskResponse> selectTaskPage(Page<CouponTaskResponse> page, CouponTaskListRequest request) {

        List<CouponTask> couponTasks = baseMapper.selectTaskPage(page.getLimit(), page.getOffset(), request);
        List<CouponTaskResponse> couponTaskVos = new ArrayList<>(couponTasks.size());
        if (couponTasks.size() > 0) {
            couponTasks.forEach(task -> {
                CouponTaskResponse couponTaskResponse = new CouponTaskResponse();
                BeanUtils.copyProperties(task, couponTaskResponse);
                couponTaskVos.add(couponTaskResponse);

                page.setRecords(couponTaskVos);

                Integer total = (baseMapper.selectTaskPage(null, null, request)).size();
                page.setTotal(total);
            });

            return page;
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CouponTask createEntity(CouponTaskRequest request) {
        CouponTask task = new CouponTask();
        BeanUtils.copyProperties(request, task);

        Long creatorId = jsonTokenUtil.getCurrentUserId();

        if (Objects.isNull(creatorId)) {
            throw new RequestException(ResponseBackCode.ERROR_USER_NOT_EXIST.getValue(), "用户ID不存在");
        }

        // 设置创建者
        task.setCreatorId(creatorId);
        task.setStatusId(CouponTaskStatus.NOT_ENABLE.getValue());
        baseMapper.insert(task);

        request.getChannels().forEach(it -> {
            CouponTaskChannel channel = new CouponTaskChannel();
            channel.setCouponTaskId(task.getId());
            channel.setChannelId(it);
            couponTaskChannelService.insert(channel);
        });


        insertGiftOrSkus(task.getId(), request);

        return task;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CouponTask updateEntity(CouponTask currentTask, CouponTaskRequest request) {
        CouponTask updateTask = new CouponTask();
        BeanUtils.copyProperties(request, updateTask);
        updateTask.setId(currentTask.getId());
        baseMapper.updateById(updateTask);

        if (CouponTaskType.GIFT.getValue().equals(currentTask.getTypeId())) {
            taskGiftService.delete(new EntityWrapper<>(new CouponTaskGift(currentTask.getId())));
        } else {
            taskSkuService.delete(new EntityWrapper<>(new CouponTaskSku(currentTask.getId())));
        }

        List<CouponTaskChannel> channelList = couponTaskChannelService.selectList(new EntityWrapper<>(new CouponTaskChannel(updateTask.getId())));

        List<Integer> stringChannels = channelList.stream().map(it -> it.getChannelId()).collect(Collectors.toList());
        if (!equalLists(request.getChannels(), stringChannels)) {
            couponTaskChannelService.delete(new EntityWrapper<>(new CouponTaskChannel(updateTask.getId())));

            List<CouponTaskChannel> insertChannels = new ArrayList<>(request.getChannels().size());
            request.getChannels().forEach(it -> {
                CouponTaskChannel channel = new CouponTaskChannel();
                channel.setCouponTaskId(updateTask.getId());
                channel.setChannelId(it);
                insertChannels.add(channel);
            });
            couponTaskChannelService.insertBatch(insertChannels);
        }

        insertGiftOrSkus(updateTask.getId(), request);
        return updateTask;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean startProcess(CouponTask task) {

        Boolean isSuccess = baseMapper.updateByStatus(task.getId(), CouponTaskStatus.NOT_ENABLE.getValue(), CouponTaskStatus.ENABLE.getValue());
        if (isSuccess) {
            couponService.batchCoupon(task, task.getStockQty());
        }

        return true;
    }

    @Override
    public Boolean updateStatus(Long taskId, Integer currentStatus, Integer updateStatus) {
        return baseMapper.updateByStatus(taskId, currentStatus, updateStatus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public Boolean increaseStock(CouponTask currentTask, Integer updateQty) {
        CouponTask tempTask = this.baseMapper.selectById(currentTask.getId());

        Boolean isSuccess = this.baseMapper.updateByStockQty(tempTask.getId(), tempTask.getStockQty(), updateQty);

        if (!isSuccess) {
            throw new RequestException(
                    ResponseBackCode.ERROR_UPDATE_FAIL.getValue(),
                    ResponseBackCode.ERROR_UPDATE_FAIL.getMessage()
            );
        }

        // 增加的库存数
        Integer increaseQty = updateQty - tempTask.getStockQty();
        if (tempTask.getStatusId().equals(CouponTaskStatus.ENABLE.getValue())) {
            couponService.batchCoupon(tempTask, increaseQty);

            int couponCounts = couponService.selectCount(new EntityWrapper<>(new Coupon(tempTask.getId())));
            if (!updateQty.equals(couponCounts)) {
                throw new RequestException(ResponseBackCode.ERROR_UPDATE_FAIL.getValue(), "库存数量与生成优惠券数量不一致");
            }
        }

        return true;
    }

    @Override
    public ResponseData<PageResponse<GetCouponTaskListResponse>> getCouponTaskList(GetCouponTaskListRequest requestParam) {
        EntityWrapper<CouponTask> entityWrapper = new EntityWrapper<CouponTask>();
        Page<CouponTask> page = new Page<CouponTask>(requestParam.getPage(), requestParam.getSize());
        Page<CouponTask> pageResult = selectPage(page, entityWrapper);

        try {
            PageResponse<GetCouponTaskListResponse> pageResp = ConvertUtil.getPageResponse(pageResult, GetCouponTaskListResponse.class);
            return ResponseUtil.success(pageResp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseUtil.fail();
    }

    @Override
    public ResponseData<GetCouponTaskResponse> getCouponTask(Long id) {
        CouponTask couponTask = selectById(id);
        GetCouponTaskResponse couponTaskVo = null;
        if (couponTask != null) {
            couponTaskVo = new GetCouponTaskResponse();
            BeanUtils.copyProperties(couponTask, couponTaskVo);
        }
        return ResponseUtil.success(couponTaskVo);
    }

    @Override
    public ResponseData<GetCouponTaskResponse> getCouponTaskByCardId(String cardId) {
        EntityWrapper<CouponTask> entityWrapper = new EntityWrapper<CouponTask>();
        entityWrapper.eq("card_id", cardId);
        CouponTask entity = selectOne(entityWrapper);

        GetCouponTaskResponse couponTaskVo = null;
        if (entity != null) {
            couponTaskVo = new GetCouponTaskResponse();
            BeanUtils.copyProperties(entity, couponTaskVo);
        }
        return ResponseUtil.success(couponTaskVo);
    }

    /**
     * 插入卡券赠品 或 卡券商品(分可用或不可用)
     */
    private void insertGiftOrSkus(Long taskId, CouponTaskRequest request) {
        // 赠品券
        if (CouponTaskType.GIFT.getValue().equals(request.getTypeId())) {

            request.getScopeGifts().forEach(it -> {

                CouponTaskGift gift = new CouponTaskGift();
                gift.setTaskId(taskId)
                        .setSkuNo(it.getSkuNo())
                        .setQty(it.getQty());
                taskGiftService.insert(gift);
            });
        } else {
            if (!request.getScope().equals(ScopeType.ALL.getValue())) {
                // 代金券、打折券
                request.getScopeSkus().forEach(it -> {
                    CouponTaskSku sku = new CouponTaskSku();
                    sku.setTaskId(taskId)
                            .setSkuNo(it);
                    taskSkuService.insert(sku);
                });
            }
        }
    }

    private static boolean equalLists(List<Integer> one, List<Integer> two) {

        if (one == null && two == null) {
            return true;
        }

        if ((one == null && two != null) || (one != null && two == null) || one.size() != two.size()) {
            return false;
        }

        one = new ArrayList<Integer>(one);
        two = new ArrayList<Integer>(two);

        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }
}

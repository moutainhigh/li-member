package com.siyueli.platform.service.member.server.service.activity.impl;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.activity.ActivityInfo;
import com.siyueli.platform.member.pojo.activity.ActivityRoleInfo;
import com.siyueli.platform.member.pojo.activity.ActivityUserCoupon;
import com.siyueli.platform.member.pojo.coupon.CouponTask;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.member.request.activity.addactivity.AddActRoleInfoVo;
import com.siyueli.platform.member.request.activity.addactivity.AddActUserCouponVo;
import com.siyueli.platform.member.request.activity.addactivity.AddActivityRequest;
import com.siyueli.platform.member.request.activity.common.CommonActRoleInfoVo;
import com.siyueli.platform.member.request.activity.common.CommonActUserCouponVo;
import com.siyueli.platform.member.request.activity.common.CommonActivityRequest;
import com.siyueli.platform.member.request.activity.getactivityinfo.GetActivityInfoRequest;
import com.siyueli.platform.member.request.activity.getactivitylist.GetActivityListRequest;
import com.siyueli.platform.member.request.activity.getcouponlist.GetCouponListRequest;
import com.siyueli.platform.member.request.activity.getuserlist.GetUserListRequest;
import com.siyueli.platform.member.request.activity.updateactivity.UpdateActRoleInfoVo;
import com.siyueli.platform.member.request.activity.updateactivity.UpdateActUserCouponVo;
import com.siyueli.platform.member.request.activity.updateactivity.UpdateActivityRequest;
import com.siyueli.platform.member.response.activity.getactivityinfo.ActivityInfoVo;
import com.siyueli.platform.member.response.activity.getactivityinfo.ActivityRoleInfoVo;
import com.siyueli.platform.member.response.activity.getactivityinfo.ActivityUserCouponVo;
import com.siyueli.platform.member.response.activity.getactivityinfo.GetActivityInfoResponse;
import com.siyueli.platform.member.response.activity.getcouponlist.ActCouponTaskResponse;
import com.siyueli.platform.member.response.activity.getuserlist.ActMemberUserResponse;
import com.siyueli.platform.service.member.server.service.activity.ActivityInfoServiceContract;
import com.siyueli.platform.service.member.server.service.activity.ActivityRoleInfoServiceContract;
import com.siyueli.platform.service.member.server.service.activity.ActivityService;
import com.siyueli.platform.service.member.server.service.activity.ActivityUserCouponServiceContract;
import com.siyueli.platform.service.member.server.service.coupon.CouponTaskServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberUserServiceContract;
import com.siyueli.platform.service.member.server.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityInfoServiceContract activityInfoServiceContract;

    @Autowired
    private ActivityRoleInfoServiceContract activityRoleInfoServiceContract;

    @Autowired
    private ActivityUserCouponServiceContract activityUserCouponServiceContract;

    @Autowired
    private CouponTaskServiceContract couponTaskServiceContract;

    @Autowired
    private MemberUserServiceContract memberUserServiceContract;

    @Override
    public ResponseData addActivity(AddActivityRequest addActivityRequest) {
        if (addActivityRequest != null) {
            // 转换成update对象
            CommonActivityRequest commonActivityRequest = new CommonActivityRequest();
            UpdateActivityRequest updateActivityRequest = new UpdateActivityRequest();
            copyProps(addActivityRequest, commonActivityRequest, updateActivityRequest);

            if (addActivityRequest.getActRoleInfoList() != null && addActivityRequest.getActRoleInfoList().size() > 0) {
                List<UpdateActRoleInfoVo> updateAriList = new ArrayList<UpdateActRoleInfoVo>();
                for (AddActRoleInfoVo addAri : addActivityRequest.getActRoleInfoList()) {
                    CommonActRoleInfoVo commonAri = new CommonActRoleInfoVo();
                    UpdateActRoleInfoVo updateAri = new UpdateActRoleInfoVo();
                    copyProps(addAri, commonAri, updateAri);
                    updateAriList.add(updateAri);

                    if (addAri.getAucList() != null && addAri.getAucList().size() > 0) {
                        List<UpdateActUserCouponVo> updateAucList = new ArrayList<UpdateActUserCouponVo>();
                        for (AddActUserCouponVo addAuc : addAri.getAucList()) {
                            CommonActUserCouponVo commonAuc = new CommonActUserCouponVo();
                            UpdateActUserCouponVo updateAuc = new UpdateActUserCouponVo();
                            copyProps(addAuc, commonAuc, updateAuc);
                            updateAucList.add(updateAuc);
                        }
                        updateAri.setAucList(updateAucList);
                    }
                }
                updateActivityRequest.setActRoleInfoList(updateAriList);
            }

            return saveActivity(updateActivityRequest);
        }

        return ResponseData.build(ResponseBackCode.ERROR_CREATE_FAIL.getValue(), ResponseBackCode.ERROR_CREATE_FAIL.getMessage());
    }

    private void copyProps(Object addObj, Object commonObj, Object updateObj) {
        BeanUtils.copyProperties(addObj, commonObj);
        BeanUtils.copyProperties(commonObj, updateObj);
    }

    @Override
    public ResponseData updateActivity(UpdateActivityRequest updateActivityRequest) {
        return saveActivity(updateActivityRequest);
    }

    @Override
    public ResponseData updateStatus(Long id, Integer status) {
        ActivityInfo activityInfo = activityInfoServiceContract.selectById(id);
        if (activityInfo != null) {
            activityInfo.setStatus(status);
            activityInfoServiceContract.updateAllColumnById(activityInfo);

            return ResponseUtil.success();
        }
        return ResponseUtil.fail();
    }


    @Transactional(rollbackFor = Exception.class)
    public ResponseData saveActivity(UpdateActivityRequest updateActivityRequest) {
        LocalDateTime now = LocalDateTime.now();

        // 活动信息
        ActivityInfo activityInfo = null;
        if (updateActivityRequest.getId() != null && updateActivityRequest.getId() != 0) {
            activityInfo = activityInfoServiceContract.selectById(updateActivityRequest.getId());
        }
        if (activityInfo == null) {
            activityInfo = new ActivityInfo();
            activityInfo.setStatus(1);
            activityInfo.setCreateAt(now);
        }
        BeanUtils.copyProperties(updateActivityRequest, activityInfo);

        //保存活动信息

        activityInfoServiceContract.insertOrUpdateAllColumn(activityInfo);
        Long activityId = activityInfo.getId();

        List<UpdateActUserCouponVo> allUpdateAucList = new ArrayList<UpdateActUserCouponVo>();
        if (updateActivityRequest != null && updateActivityRequest.getActRoleInfoList() != null && updateActivityRequest.getActRoleInfoList().size() > 0) {
            for (UpdateActRoleInfoVo updateAri : updateActivityRequest.getActRoleInfoList()) {
                if (updateAri.getAucList() != null && updateAri.getAucList().size() > 0) {
                    for (UpdateActUserCouponVo updateAuc : updateAri.getAucList()) {
                        allUpdateAucList.add(updateAuc);
                    }
                }
            }
        }

        List<ActivityRoleInfo> activityRoleInfoList = activityRoleInfoServiceContract.selectList(new EntityWrapper<ActivityRoleInfo>().eq("activity_id", activityId));
        List<Long> roleIds = new ArrayList<Long>();
        if (activityRoleInfoList != null && activityRoleInfoList.size() > 0) {
            for (ActivityRoleInfo ari : activityRoleInfoList) {
                roleIds.add(ari.getId());
            }
        }

        // 删除
        List<ActivityRoleInfo> ariDeleteList = getActRoleInfoDeleteList(activityRoleInfoList, updateActivityRequest.getActRoleInfoList());
        EntityWrapper<ActivityUserCoupon> aucEntityWrapper = new EntityWrapper<ActivityUserCoupon>();
        aucEntityWrapper.in("role_id", roleIds);
        List<ActivityUserCoupon> allAucList = activityUserCouponServiceContract.selectList(aucEntityWrapper);
        List<ActivityUserCoupon> aucDeleteList = getActUserCouponDeleteList(allAucList, allUpdateAucList);
        List<Long> delRoleIds = new ArrayList<Long>();
        List<Long> delAucIds = new ArrayList<Long>();

        if (aucDeleteList != null && aucDeleteList.size() > 0) {
            for (ActivityUserCoupon auc : aucDeleteList) {
                delAucIds.add(auc.getId());
            }
            activityUserCouponServiceContract.deleteBatchIds(delAucIds);
        }

        if (ariDeleteList != null && ariDeleteList.size() > 0) {
            for (ActivityRoleInfo ari : ariDeleteList) {
                delRoleIds.add(ari.getId());
            }
            activityRoleInfoServiceContract.deleteBatchIds(delRoleIds);
        }


        if (updateActivityRequest.getActRoleInfoList() != null && updateActivityRequest.getActRoleInfoList().size() > 0) {

            for (UpdateActRoleInfoVo updateAri : updateActivityRequest.getActRoleInfoList()) {
                ActivityRoleInfo ari = findActRoleInfo(activityRoleInfoList, updateAri);
                if (ari == null) {
                    ari = new ActivityRoleInfo();
                    ari.setActivityId(activityId);
                    ari.setCreateAt(now);
                }
                //设置属性
                // 设置派发卡券信息
                if (updateActivityRequest.getType() == 1) {
                    ari.setDistributeUserType(updateAri.getDistributeUserType());
                    ari.setCouponId(updateAri.getCouponId());
                }

                // 设置积分，注册和充值
                if (updateActivityRequest.getType() == 2 || updateActivityRequest.getType() == 3) {
                    ari.setPoints(updateAri.getPoints());
                }

                // 设置充值信息
                if (updateActivityRequest.getType() == 3) {
                    ari.setRechargeAmout(updateAri.getRechargeAmout());
                    ari.setDonateBalance(updateAri.getDonateBalance());
                }

                // 设置促销信息
                if (updateActivityRequest.getType() == 4) {
                    ari.setPromoteType(updateAri.getPromoteType());
                }


                activityRoleInfoServiceContract.insertOrUpdateAllColumn(ari);

                if (updateAri.getAucList() != null && updateAri.getAucList().size() > 0) {
                    for (UpdateActUserCouponVo updateAuc : updateAri.getAucList()) {

                        ActivityUserCoupon auc = findActivityUserCoupon(allAucList, updateAuc);
                        if (auc == null) {
                            auc = new ActivityUserCoupon();
                            auc.setRoleId(ari.getId());
                            auc.setCreateAt(now);
                        }
                        // 派发卡券活动
                        if (updateActivityRequest.getType() == 1) {
                            auc.setUserId(updateAuc.getUserId());
                        }

                        // 注册或充值活动
                        if (updateActivityRequest.getType() == 2 || updateActivityRequest.getType() == 3) {
                            auc.setCouponId(updateAuc.getCouponId());
                        }


                        activityUserCouponServiceContract.insertOrUpdateAllColumn(auc);
                    }
                }
            }
        }




        return ResponseUtil.success();
    }





    private ActivityRoleInfo findActRoleInfo(List<ActivityRoleInfo> activityRoleInfoList, UpdateActRoleInfoVo updateAri) {
        if (activityRoleInfoList != null && activityRoleInfoList.size() > 0) {
            if (updateAri != null && updateAri.getRoleId() != null && updateAri.getRoleId() != 0) {
                for (ActivityRoleInfo ari : activityRoleInfoList) {
                    if (updateAri.getRoleId().equals(ari.getId())) {
                        return ari;
                    }
                }
            }
        }
        return null;
    }

    private List<ActivityRoleInfo> getActRoleInfoDeleteList(List<ActivityRoleInfo> activityRoleInfoList, List<UpdateActRoleInfoVo> updateAriList) {
        if (activityRoleInfoList != null && activityRoleInfoList.size() > 0) {
            List<ActivityRoleInfo> deleteList = new ArrayList<ActivityRoleInfo>();
            for (ActivityRoleInfo ari : activityRoleInfoList) {
                boolean foundFlag = false;
                if (updateAriList != null && updateAriList.size() > 0) {
                    for (UpdateActRoleInfoVo updateAri : updateAriList) {
                        if (ari.getId().equals(updateAri.getRoleId())) {
                            foundFlag = true;
                            break;
                        }
                    }

                }

                if (!foundFlag) {
                    deleteList.add(ari);
                }
            }
            return deleteList;
        }
        return null;
    }

    private List<ActivityUserCoupon> getActUserCouponDeleteList(List<ActivityUserCoupon> actUserCouponList, List<UpdateActUserCouponVo> updateAucList) {
        if (actUserCouponList != null && actUserCouponList.size() > 0) {
            List<ActivityUserCoupon> deleteList = new ArrayList<ActivityUserCoupon>();
            for (ActivityUserCoupon auc : actUserCouponList) {
                boolean foundFlag = false;
                if (updateAucList != null && updateAucList.size() > 0) {
                    for (UpdateActUserCouponVo updateAuc : updateAucList) {
                        if (auc.getId().equals(updateAuc.getAucId())) {
                            foundFlag = true;
                            break;
                        }
                    }

                }

                if (!foundFlag) {
                    deleteList.add(auc);
                }
            }
            return deleteList;
        }
        return null;
    }



    private ActivityUserCoupon findActivityUserCoupon(List<ActivityUserCoupon> list, UpdateActUserCouponVo user) {
        if (list != null && list.size() > 0) {
            if (user != null && user.getAucId() != null && user.getAucId() != 0) {
                for (ActivityUserCoupon auc : list) {
                    if (user.getAucId().equals(auc.getId()))
                        return auc;
                }
            }
        }
        return null;
    }



    @Override
    public ResponseData getCouponList(GetCouponListRequest getCouponListRequest) {
        EntityWrapper<CouponTask> entityWrapper = new EntityWrapper<CouponTask>();
        Page<CouponTask> page = new Page<CouponTask>(getCouponListRequest.getPage(), getCouponListRequest.getSize());
        Page<CouponTask> pageResult = couponTaskServiceContract.selectPage(page, entityWrapper);
        List<CouponTask> list = pageResult.getRecords();
        List<ActCouponTaskResponse> resultList = null;
        try {
            resultList = ConvertUtil.convertList(ActCouponTaskResponse.class, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageResponse<ActCouponTaskResponse> pageResponse = getPageResponse(pageResult, resultList);
        ResponseData data = ResponseUtil.success(pageResponse);
        return data;
    }

    private <T> PageResponse<T> getPageResponse(Page pageResult, List<T> list) {
        if (pageResult != null) {
            PageResponse<T> resp = new PageResponse<T>();
            resp.setCurrent(pageResult.getCurrent());
            resp.setPages(pageResult.getPages());
            resp.setSize(pageResult.getSize());
            resp.setTotal(pageResult.getTotal());
            resp.setRecords(list);
            return resp;
        }
        return null;
    }



    @Override
    public ResponseData getUserList(GetUserListRequest getUserListRequest) {
        EntityWrapper<MemberUser> entityWrapper = new EntityWrapper<MemberUser>();
        Page<MemberUser> page = new Page<MemberUser>(getUserListRequest.getPage(), getUserListRequest.getSize());
        Page<MemberUser> pageResult = memberUserServiceContract.selectPage(page, entityWrapper);
        List<MemberUser> list = pageResult.getRecords();
        List<ActMemberUserResponse> resultList = null;
        try {
            resultList = ConvertUtil.convertList(ActMemberUserResponse.class, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageResponse<ActMemberUserResponse> pageResponse = getPageResponse(pageResult, resultList);
        ResponseData data = ResponseUtil.success(pageResponse);
        return data;
    }

    @Override
    public ResponseData getActivityList(GetActivityListRequest getActivityListRequest) {
        EntityWrapper<ActivityInfo> entityWrapper = new EntityWrapper<ActivityInfo>();
        Page<ActivityInfo> page = new Page<ActivityInfo>(getActivityListRequest.getPage(), getActivityListRequest.getSize());
        Page<ActivityInfo> pageResult = activityInfoServiceContract.selectPage(page, entityWrapper);
        List<ActivityInfo> list = pageResult.getRecords();
        List<ActivityInfoVo> aiList = new ArrayList<ActivityInfoVo>();
        if (list != null && list.size() > 0) {
            List<Long> ids = new ArrayList<Long>();
            for (ActivityInfo ai : list) {
                ids.add(ai.getId());
            }
            EntityWrapper<ActivityRoleInfo> ariEntityWrapper = new EntityWrapper<ActivityRoleInfo>();
            ariEntityWrapper.in("activity_id", ids);
            List<ActivityRoleInfo> ariList = activityRoleInfoServiceContract.selectList(ariEntityWrapper);
            List<ActivityUserCoupon> aucList = null;
            if (ariList != null && ariList.size() > 0) {
                List<Long> ariIds = new ArrayList<Long>();
                for (ActivityRoleInfo ari : ariList) {
                    ariIds.add(ari.getId());
                }
                EntityWrapper<ActivityUserCoupon> aucEntityWrapper = new EntityWrapper<ActivityUserCoupon>();
                aucEntityWrapper.in("role_id", ariIds);
                aucList = activityUserCouponServiceContract.selectList(aucEntityWrapper);

            }

            //组装列表
            Map<Long, List<ActivityRoleInfoVo>> ariListMap = sortActivityRoleInfo(ariList);
            Map<Long, List<ActivityUserCouponVo>> aucListMap = sortActivityUserCoupon(aucList);

            for (ActivityInfo ai : list) {
                ActivityInfoVo aiVo = new ActivityInfoVo();
                BeanUtils.copyProperties(ai, aiVo);
                aiList.add(aiVo);
                if (ariListMap == null)
                    continue;
                List<ActivityRoleInfoVo> oneAriList = ariListMap.get(ai.getId());
                if (oneAriList != null && oneAriList.size() > 0) {
                    aiVo.setAriList(oneAriList);
                    for (ActivityRoleInfoVo ari : oneAriList) {
                        if (aucListMap == null)
                            continue;
                        List<ActivityUserCouponVo> oneAucList = aucListMap.get(ari.getId());
                        ari.setActivityUserCouponList(oneAucList);
                    }
                }
            }

        }

        PageResponse<ActivityInfoVo> pageResponse = getPageResponse(pageResult, aiList);
        ResponseData data = ResponseUtil.success(pageResponse);
        return data;
    }


    private Map<Long, List<ActivityRoleInfoVo>> sortActivityRoleInfo(List<ActivityRoleInfo> ariList) {
        if (ariList != null && ariList.size() > 0) {
            Map<Long, List<ActivityRoleInfoVo>> ariListMap = new HashMap<Long, List<ActivityRoleInfoVo>>();
            for (ActivityRoleInfo ari : ariList) {
                List<ActivityRoleInfoVo> oneAriList = ariListMap.get(ari.getActivityId());
                if (oneAriList == null) {
                    oneAriList = new ArrayList<ActivityRoleInfoVo>();
                    ariListMap.put(ari.getActivityId(), oneAriList);
                }
                ActivityRoleInfoVo ariVo = new ActivityRoleInfoVo();
                BeanUtils.copyProperties(ari, ariVo);
                oneAriList.add(ariVo);
            }
            return ariListMap;
        }
        return null;
    }

    private Map<Long, List<ActivityUserCouponVo>> sortActivityUserCoupon(List<ActivityUserCoupon> aucList) {
        if (aucList != null && aucList.size() > 0) {
            Map<Long, List<ActivityUserCouponVo>> voListMap = new HashMap<Long, List<ActivityUserCouponVo>>();
            for (ActivityUserCoupon auc : aucList) {
                List<ActivityUserCouponVo> voList = voListMap.get(auc.getRoleId());
                if (voList == null) {
                    voList = new ArrayList<ActivityUserCouponVo>();
                    voListMap.put(auc.getRoleId(), voList);
                }
                ActivityUserCouponVo aucVo = new ActivityUserCouponVo();
                BeanUtils.copyProperties(auc, aucVo);
                voList.add(aucVo);
            }
            return voListMap;
        }
        return null;
    }



    private List<ActivityInfoVo> convertActivityList(List<Map> list) {
        try {
            if (list != null && list.size() > 0) {
                Map<Long, ActivityInfoVo> aiMap = new HashMap<Long, ActivityInfoVo>();
                Map<Long, ActivityRoleInfoVo> ariMap = new HashMap<Long, ActivityRoleInfoVo>();

                List<ActivityInfoVo> aiList = new ArrayList<ActivityInfoVo>();
                Map<Long, List<ActivityRoleInfoVo>> ariListMap = new HashMap<Long, List<ActivityRoleInfoVo>>();
                List<ActivityRoleInfoVo> ariList = null;
                Map<Long, List<ActivityUserCouponVo>> aucListMap = new HashMap<Long, List<ActivityUserCouponVo>>();
                List<ActivityUserCouponVo> aucList = null;
                for (Map map : list) {
                    Long id = (Long)map.get("id");
                    ActivityInfoVo ai = aiMap.get(id);
                    if (ai == null) {
                        ai = ConvertUtil.mapToObject(map, ActivityInfoVo.class);
                        aiMap.put(ai.getId(), ai);
                        aiList.add(ai);
                    }
                    Long role_id = (Long)map.get("role_id");
                    if (role_id != null) {
                        ariList = ariListMap.get(id);
                        if (ariList == null) {
                            ariList = new ArrayList<ActivityRoleInfoVo>();
                            ariListMap.put(id, ariList);
                        }
                        ActivityRoleInfoVo ari = ariMap.get(role_id);
                        if (ari == null) {
                            ari = new ActivityRoleInfoVo();
                            ari.setId(role_id);
                            ari.setActivityId(id);
                            ari.setCouponId((Long)map.get("ari_coupon_id"));
                            ari.setDistributeUserType((Integer)map.get("distribute_user_type"));
                            //ari.setPoints(new BigDecimal(map.get("points")));
                            //ari.setRechargeAmout();
                            //ari.setDonateBalance();
                            //ari.setPromoteType();
                            ariMap.put(role_id, ari);
                            ariList.add(ari);
                        }


                        Long auc_id = (Long)map.get("auc_id");
                        if (auc_id != null) {
                            aucList = aucListMap.get(role_id);
                            if (aucList == null) {
                                aucList = new ArrayList<ActivityUserCouponVo>();
                                aucListMap.put(role_id, aucList);
                            }
                            ActivityUserCouponVo auc = new ActivityUserCouponVo();
                            auc.setId(auc_id);
                            auc.setCouponId((Long)map.get("auc_coupon_id"));
                            auc.setUserId((Long)map.get("user_id"));

                            aucList.add(auc);
                        }

                    }



                }
                //组装列表
                for (ActivityInfoVo ai: aiList) {
                    ariList = ariListMap.get(ai.getId());
                    if (ariList != null && ariList.size() > 0) {
                        ai.setAriList(ariList);
                        for (ActivityRoleInfoVo ari : ariList) {
                            aucList = aucListMap.get(ari.getId());
                            if (aucList != null && aucList.size() > 0) {
                                ari.setActivityUserCouponList(aucList);
                            }
                        }
                    }
                }
                return aiList;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ResponseData getActivityInfo(GetActivityInfoRequest getActivityInfoRequest) {
        ResponseData data = ResponseUtil.success();

        ActivityInfo activityInfo = activityInfoServiceContract.selectById(getActivityInfoRequest.getId());
        if (activityInfo != null) {
            GetActivityInfoResponse resp = new GetActivityInfoResponse();
            data.setData(resp);

            ActivityInfoVo activityInfoVo = new ActivityInfoVo();
            BeanUtils.copyProperties(activityInfo, activityInfoVo);
            resp.setActivityInfo(activityInfoVo);

            List<ActivityRoleInfo> actRoleInfoList = activityRoleInfoServiceContract.selectList(new EntityWrapper<ActivityRoleInfo>().eq("activity_id", activityInfo.getId()));
            if (actRoleInfoList != null && actRoleInfoList.size() > 0) {
                List<ActivityRoleInfoVo> ariList = new ArrayList<ActivityRoleInfoVo>();
                for (ActivityRoleInfo ari : actRoleInfoList) {
                    ActivityRoleInfoVo activityRoleInfoVo = new ActivityRoleInfoVo();
                    BeanUtils.copyProperties(ari, activityRoleInfoVo);
                    ariList.add(activityRoleInfoVo);


                    List<ActivityUserCoupon> actUserCouponList = activityUserCouponServiceContract.selectList(new EntityWrapper<ActivityUserCoupon>().eq("role_id", ari.getId()));
                    if (actUserCouponList != null && actUserCouponList.size() > 0) {
                        List<ActivityUserCouponVo> activityUserCouponVoList = new ArrayList<ActivityUserCouponVo>();
                        for (ActivityUserCoupon auc : actUserCouponList) {
                            ActivityUserCouponVo activityUserCouponVo = new ActivityUserCouponVo();
                            BeanUtils.copyProperties(auc, activityUserCouponVo);
                            activityUserCouponVoList.add(activityUserCouponVo);
                        }

                        activityRoleInfoVo.setActivityUserCouponList(activityUserCouponVoList);
                    }

                }
                activityInfoVo.setAriList(ariList);



            }


        }

        return data;
    }




}

package com.siyueli.platform.service.member.server.controller.authority;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.exceptions.exception.PermissionDenyException;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.request.permissionlogin.PermissionLoginSearchRequest;
import com.siyueli.platform.member.response.PermissionActionVo;
import com.siyueli.platform.member.response.permissionlogin.PermissionLoginVo;
import com.siyueli.platform.service.member.server.constant.MemberServiceConstant;
import com.siyueli.platform.service.member.server.controller.BaseController;
import com.siyueli.platform.service.member.server.service.member.MemberUserLoginTokenService;
import com.siyueli.platform.service.member.server.service.permission.PermissionServiceContract;
import com.siyueli.platform.service.member.server.service.permissionlogin.PermissionLoginServiceContract;
import com.siyueli.platform.service.member.server.service.redis.PermissionLoginRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "是否需要登录权限控制")
@RestController
@RequestMapping("/authority")
public class AuthorityController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityController.class);

    @Autowired
    private PermissionLoginServiceContract permissionLoginServiceContract;

    @Autowired
    private MemberUserLoginTokenService memberUserLoginTokenService;

    @Autowired
    private PermissionLoginRedisService permissionLoginRedisService;

    @Autowired
    private PermissionServiceContract permissionServiceContract;

    @ApiOperation(nickname = "judgetAuthority",value = "是否需要登录权限控制")
    @LogAnnotation
    @GetMapping("/judgetAuthority")
    public ResponseData judgetAuthority(@RequestParam(value = "uri") String uri) {
        boolean flag = checkBackendToken();
        if (flag) {
            PermissionActionVo permissionActionVo = matchBackendUri(uri);
            if (permissionActionVo != null) {
                return ResponseUtil.success();
            }
        } else {
            PermissionLoginVo vo = matchUri(uri);
            if (vo == null) {
                return ResponseUtil.fail("uri的是否需要登录权限没有配置");
            } else {
                if (vo.getNeedLogin() == 0) {
                    return ResponseUtil.success();
                }

                flag = checkToken();
                if (flag) {
                    return ResponseUtil.success();
                } else {
                    return ResponseUtil.build(ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(), ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage());
                }

            }
        }

        return ResponseUtil.fail();

    }

    private PermissionLoginVo matchUri(String uri) {
        List<PermissionLoginVo> list = permissionLoginRedisService.getList();

        if (list == null || list.isEmpty()) {
            PermissionLoginSearchRequest requestParam = new PermissionLoginSearchRequest();
            requestParam.setPage(MemberServiceConstant.PAGE_ONE);
            requestParam.setSize(MemberServiceConstant.PAGE_SIZE_ALL);
            ResponseData<PageResponse<PermissionLoginVo>> respData = permissionLoginServiceContract.search(requestParam);
            if (respData != null && ResponseBackCode.SUCCESS.getValue() == respData.getCode()) {
                PageResponse<PermissionLoginVo> pageResp = respData.getData();
                if (pageResp != null) {
                    list = pageResp.getRecords();
                    if (list != null && list.size() > 0) {
                        permissionLoginRedisService.saveList(list);
                    }
                }
            }

        }

        if (list != null && list.size() > 0) {
            for (PermissionLoginVo vo : list) {
                boolean matchResult = match(uri, vo.getUri());
                if (matchResult) {
                    return vo;
                }
            }
        }

        return null;

    }

    private PermissionActionVo matchBackendUri(String uri) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String userId = memberUserLoginTokenService.getUserIdByBackendToken(token);
        List<PermissionActionVo> voList = permissionServiceContract.getPermissionListByUserId(new Long(userId));
        if (voList != null && voList.size() > 0) {
            for (PermissionActionVo act : voList) {
                boolean matchResult = match(uri, act.getActionUrl());
                if (matchResult) {
                    return act;
                }
            }
        }

        return null;

    }

    private boolean match(String uri, String uri2) {
        String perUri = uri2;
        Pattern pattern = Pattern.compile("\\{[\\s\\S]+\\}");
        Matcher matcher = pattern.matcher(perUri);
        StringBuffer sb = new StringBuffer();
        Integer preEnd = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            sb.append(perUri.substring(preEnd, start));
            sb.append("[\\s\\S]+");

            preEnd = end;

        }
        if (preEnd < perUri.length()) {
            sb.append(perUri.substring(preEnd));
        }

        boolean matchResult = Pattern.matches("^" + sb.toString() + "$", uri);
        return matchResult;
    }

    private boolean checkToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (token == null || "".equals(token)) {
            return false;

        }
        //验证token
        ResponseData responseData = memberUserLoginTokenService.checkToken(token);
        if (!responseData.getCode().equals(ResponseBackCode.SUCCESS.getValue())) {
            return false;
        }
        return true;
    }

    private boolean checkBackendToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (token == null || "".equals(token)) {
            return false;

        }
        //验证token
        ResponseData responseData = memberUserLoginTokenService.checkBackendToken(token);
        if (!responseData.getCode().equals(ResponseBackCode.SUCCESS.getValue())) {
            return false;

        }
        return true;
    }
}

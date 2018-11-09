package com.siyueli.platform.service.member.server.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.siyueli.platform.service.member.server.util.FileReaderUtil;
import com.siyueli.platform.service.member.server.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SwaggerJsonParserTest {


    @Test
    public void testParse() {
        String content = FileReaderUtil.readFile("/swagger/swagger.json");
        try {
            JsonNode jsonNode = JsonUtil.parseJson(content);
            JsonNode pathsNode = jsonNode.path("paths");
            String basePath = jsonNode.path("basePath").asText();
            basePath = basePath.substring(0, basePath.length() - 1);
            Iterator<Map.Entry<String, JsonNode>> iterator = pathsNode.fields();
            while (iterator.hasNext()) {
                printPermissionLogin(iterator, basePath);
                //printPermissionAction(iterator, basePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printPermissionAction(Iterator<Map.Entry<String, JsonNode>> iterator, String basePath) {
        Map.Entry<String, JsonNode> entry = iterator.next();
        String uri = entry.getKey();
        JsonNode uriNode = entry.getValue();
        Iterator<Map.Entry<String, JsonNode>> uriIterator = uriNode.fields();
        JsonNode requestMethodNode = uriIterator.next().getValue();
        ArrayNode tagsNode = (ArrayNode)requestMethodNode.path("tags");
        String uriCategory = tagsNode.get(0).asText();
        String uriName = requestMethodNode.path("summary").asText();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = sdf.format(now);

        List<String> uriList = getUriList();

        if (uriList != null && uriList.size() > 0) {
            for (String str : uriList) {
                if (uri.startsWith(str)) {
                    System.out.println(String.format("%s,%s,%s,%s", basePath + uri, uriName, 1, nowStr));
                }
            }
        } else {
            System.out.println(String.format("%s,%s,%s,%s", basePath + uri, uriName, 1, nowStr));
        }
    }

    private void printPermissionLogin(Iterator<Map.Entry<String, JsonNode>> iterator, String basePath) {
        Map.Entry<String, JsonNode> entry = iterator.next();
        String uri = entry.getKey();
        JsonNode uriNode = entry.getValue();
        Iterator<Map.Entry<String, JsonNode>> uriIterator = uriNode.fields();
        JsonNode requestMethodNode = uriIterator.next().getValue();
        ArrayNode tagsNode = (ArrayNode)requestMethodNode.path("tags");
        String uriCategory = tagsNode.get(0).asText();
        String uriName = requestMethodNode.path("summary").asText();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = sdf.format(now);

        List<String> uriList = getUriList();

        if (uriList != null && uriList.size() > 0) {
            for (String str : uriList) {
                if (uri.startsWith(str)) {
                    System.out.println(String.format("%s,%d,%s,%s,%s,%s", basePath + uri, 1, uriName, uriCategory, nowStr, nowStr));
                }
            }
        } else {
            System.out.println(String.format("%s,%d,%s,%s,%s,%s", basePath + uri, 1, uriName, uriCategory, nowStr, nowStr));
        }
    }

    private List<String> getUriList() {
        List<String> list = new ArrayList<String>();
        list.add("/sportActivityCategoryFront/");
        list.add("/sportActivityFront/");
        list.add("/sportActivitySignupFront/");
        list.add("/sportActivityViewFront/");
        list.add("/sportInfomationFront/");
        list.add("/sportInvitationLetterFront/");
        list.add("/sportInvitedUserViewFront/");


        return list;
    }
}

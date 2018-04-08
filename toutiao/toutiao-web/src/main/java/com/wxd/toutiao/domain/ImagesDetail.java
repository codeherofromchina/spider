package com.wxd.toutiao.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxd.toutiao.util.TouTiaoUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 商品新闻详情信息实体
 * Created by wangxiaodan on 2018/3/28.
 */
public class ImagesDetail {
    private String title;
    /**
     * 图片总数量
     */
    private int count = 0;
    /**
     * 详情图片
     */
    private List<Image> images;

    /**
     * 根据详情推荐内容
     */
    private List<ImageNews> recommend;


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {
        if (this.images == null) {
            this.images = new ArrayList<Image>();
        }
        this.images.add(image);
        count++;
    }

    public List<ImageNews> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<ImageNews> recommend) {
        this.recommend = recommend;
    }


    public void addRecommend(ImageNews imageNews) {
        if (this.recommend == null) {
            this.recommend = new ArrayList<ImageNews>();
        }
        this.recommend.add(imageNews);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static void main(String[] args) throws IOException {
        String str = TouTiaoUtils.UnicodeTochinese("\\u4e0d\\u662f\\u88c5\\u51fa\\u6765\\u7684");
        System.out.println(str);
        File file = new File("/Users/wangxiaodan/tmp/test.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuffer content = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        System.out.println(content);

//        content.setLength(0);
//        content.append("JSON.parseabjdefadfdfd");

        //JSON.parse\("(.*)"\),\s*siblingList
        //siblingList: (.*)\s*publish_time:

        Pattern pattern = Pattern.compile("JSON\\.parse\\(\"(.*)\"\\),\\s*siblingList");
        Matcher matcher = pattern.matcher(content);
        boolean b = matcher.find();
        if (b) {
            String group = matcher.group(1);
            group = group.replace("\\","");
            System.out.println(group);

            JSONObject jsonObject = JSONObject.parseObject(group);

            JSONArray sub_abstracts = jsonObject.getJSONArray("sub_abstracts");
            JSONArray subAbstracts = new JSONArray();
            for (int i = 0;i<sub_abstracts.size();i++) {
                String string = sub_abstracts.getString(i);
                if (StringUtils.isNotBlank(string)) {
                    string = TouTiaoUtils.UnicodeTochinese(string.replace("u", "\\u"));
                }
                System.out.println(string);
                subAbstracts.add(string);
            }
            jsonObject.put("sub_abstracts",subAbstracts);


            System.out.println(jsonObject.toString());
        }

        System.out.println(b);

    }
}

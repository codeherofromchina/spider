package com.wxd.toutiao.domain;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 图片新闻
 * 
 * @author Administrator
 *
 */
public class ImageNews {
	private String uuid;
	/**
	 * 标题
	 */
	private String title;
	// 封面主图片
	private String image_url;
	// 封面图片集合
	private List<Map<String, String>> image_list;
	private String middle_image;
	// 内容图片数量
	private Integer gallary_image_count;
	private String source_url; // 图片新闻内容地址
	// 标签
	private String tag;
	private String chinese_tag;
	private List<String> label;
	// 来源
	private String source;
	private int group_source;
	// 详情内容是否存在图册 true:图册  false:整片文章
	private boolean has_gallery;
	// 用户首页地址
	private String media_url;
	// 用户头像地址
	private String media_avatar_url;
	// 新闻时间戳
	private long behot_time;
	// 评论数量
	private int comments_count;
	// 图片相册和文章的区别，图册：false  文章：true
	private boolean single_mode;
	private boolean middle_mode;
	private boolean more_mode;
	private String tag_url;
	private String article_genre;
	private boolean is_feed_ad;
	private String item_id;
	private String group_id;
	private int gallary_flag;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public List<Map<String, String>> getImage_list() {
		return image_list;
	}
	public void setImage_list(List<Map<String, String>> image_list) {
		this.image_list = image_list;
	}
	public String getMiddle_image() {
		return middle_image;
	}
	public void setMiddle_image(String middle_image) {
		this.middle_image = middle_image;
	}
	public Integer getGallary_image_count() {
		return gallary_image_count;
	}
	public void setGallary_image_count(Integer gallary_image_count) {
		this.gallary_image_count = gallary_image_count;
	}
	public String getSource_url() {
		return source_url;
	}
	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getChinese_tag() {
		return chinese_tag;
	}
	public void setChinese_tag(String chinese_tag) {
		this.chinese_tag = chinese_tag;
	}
	public List<String> getLabel() {
		return label;
	}
	public void setLabel(List<String> label) {
		this.label = label;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getGroup_source() {
		return group_source;
	}
	public void setGroup_source(int group_source) {
		this.group_source = group_source;
	}
	public boolean isHas_gallery() {
		return has_gallery;
	}
	public void setHas_gallery(boolean has_gallery) {
		this.has_gallery = has_gallery;
	}
	public String getMedia_url() {
		return media_url;
	}
	public void setMedia_url(String media_url) {
		this.media_url = media_url;
	}
	public String getMedia_avatar_url() {
		return media_avatar_url;
	}
	public void setMedia_avatar_url(String media_avatar_url) {
		this.media_avatar_url = media_avatar_url;
	}
	public long getBehot_time() {
		return behot_time;
	}
	public void setBehot_time(long behot_time) {
		this.behot_time = behot_time;
	}
	public int getComments_count() {
		return comments_count;
	}
	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}
	public boolean isSingle_mode() {
		return single_mode;
	}
	public void setSingle_mode(boolean single_mode) {
		this.single_mode = single_mode;
	}
	public boolean isMiddle_mode() {
		return middle_mode;
	}
	public void setMiddle_mode(boolean middle_mode) {
		this.middle_mode = middle_mode;
	}
	public boolean isMore_mode() {
		return more_mode;
	}
	public void setMore_mode(boolean more_mode) {
		this.more_mode = more_mode;
	}
	public String getTag_url() {
		return tag_url;
	}
	public void setTag_url(String tag_url) {
		this.tag_url = tag_url;
	}
	public String getArticle_genre() {
		return article_genre;
	}
	public void setArticle_genre(String article_genre) {
		this.article_genre = article_genre;
	}
	public boolean isIs_feed_ad() {
		return is_feed_ad;
	}
	public void setIs_feed_ad(boolean is_feed_ad) {
		this.is_feed_ad = is_feed_ad;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
		setUuid(group_id);
	}
	public int getGallary_flag() {
		return gallary_flag;
	}
	public void setGallary_flag(int gallary_flag) {
		this.gallary_flag = gallary_flag;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this).toString();
	}
	
}

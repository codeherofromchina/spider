package com.wxd.toutiao.dao.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxd.toutiao.domain.ImagesDetail;

import org.apache.http.impl.execchain.MainClientExec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxd.toutiao.dao.ImageNewsDao;
import com.wxd.toutiao.domain.Image;
import com.wxd.toutiao.domain.ImageNews;
import com.wxd.toutiao.exception.ToutiaoException;
import com.wxd.toutiao.util.TouTiaoUtils;

@Repository("httpImageNewsDao")
public class HttpImageNewsDao extends AbstractTouTiaoDao implements ImageNewsDao {
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpImageNewsDao.class);
	private final static String SUCCESS = "success";
	ObjectMapper om = new ObjectMapper();

	@Override
	public List<ImageNews> findImageNews(String category, long maxBehotTime) throws ToutiaoException {
		Map<String, String> header = getHeader();
		String signature = getSignature(String.valueOf(maxBehotTime));

		String content = TouTiaoUtils.fetchImage(category, String.valueOf(maxBehotTime), signature, header);
		LOGGER.info("获取结果", content, category, maxBehotTime);

		JSONObject jsonObject = JSON.parseObject(content);

		if (SUCCESS.equals(jsonObject.getString("message"))) {
			JSONArray data = jsonObject.getJSONArray("data");
			String js = JSONObject.toJSONString(data);
			List<ImageNews> list = JSONObject.parseArray(js, ImageNews.class);
			return list;
		}
		return null;
	}

	/**
	 * 获取图片详情信息并赋值到实体信息中
	 */
	@Override
	public ImagesDetail findImageNewsDetails(String uuid) throws ToutiaoException {
		Map<String, String> header = getHeader();
		// 获取详情信息json数据
		JSONObject contentObject = TouTiaoUtils.fetchImageDetail(uuid, header);
		if (contentObject == null) {
		    return null;
        }
		// 新建结果集
		ImagesDetail detail = new ImagesDetail();
		JSONObject imageDetailJson = contentObject.getJSONObject("detail");
		// 完善图片详情内容
		detail.setCount(imageDetailJson.getIntValue("count"));
		detail.setMaxImgWidth(imageDetailJson.getIntValue("max_img_width"));
		JSONArray subTitles = imageDetailJson.getJSONArray("sub_titles");
		if (subTitles != null && subTitles.size() > 0) {
			detail.setTitle(subTitles.getString(0));
		}
		JSONArray subImages = imageDetailJson.getJSONArray("sub_images");
		JSONArray subAbstracts = imageDetailJson.getJSONArray("sub_abstracts");
		for (int i = 0; i < subImages.size(); i++) {
			JSONObject imageObject = subImages.getJSONObject(i);
			Image image = new Image();
			image.setUrl(imageObject.getString("url"));
			image.setWidth(imageObject.getIntValue("width"));
			image.setHeight(imageObject.getIntValue("height"));
			if (subAbstracts.size() > i) {
				image.setDescribe(subAbstracts.getString(i));
			}
			detail.addImage(image);
		}
		// 完善图片相关推荐内容
		JSONArray recommendArray = contentObject.getJSONArray("recommend");
		if (recommendArray != null && recommendArray.size() > 0) {
			String js = JSONObject.toJSONString(recommendArray);
			List<ImageNews> list = JSONObject.parseArray(js, ImageNews.class);
			detail.setRecommend(list);
		}
		return detail;
	}

	public static void main(String[] args) {
		String str = "[{'comments_count':317,'media_avatar_url':'//p2.pstatp.com/large/ef7000410ecfe28db39','is_feed_ad':false,'is_diversion_page':false,'title':'农妇与丈夫斗气出走20多年，回家发现丈夫已故，女儿出嫁儿子入狱','single_mode':true,'gallary_image_count':8,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6530231289960727054/','source':'闻舞视界','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/6c370002456dd33b4475','group_id':'6530231289960727054','is_related':true,'media_url':'/c/user/50696374564/'},{'comments_count':3460,'media_avatar_url':'//p3.pstatp.com/large/6edb00037e1dd6df9a8e','is_feed_ad':false,'is_diversion_page':false,'title':'新婚丈夫被撞瘫痪，她选择原谅肇事女司机，不离不弃照顾丈夫12年','single_mode':true,'gallary_image_count':11,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6518182319210103310/','source':'北疆记录者','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p9.pstatp.com/list/300x170/5e7f0001b64476126421','group_id':'6518182319210103310','is_related':true,'media_url':'/c/user/51216992998/'},{'comments_count':690,'media_avatar_url':'//p3.pstatp.com/large/1bf600011d8ba38871da','is_feed_ad':false,'is_diversion_page':false,'title':'年近九旬的袖珍老人高调征婚，娶到漂亮的51岁第5任新娘','single_mode':true,'gallary_image_count':7,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6525602393340510733/','source':'博雅物语','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p1.pstatp.com/list/300x170/66a9000437d6b89b41ea','group_id':'6525602393340510733','is_related':true,'media_url':'/c/user/59033947857/'},{'comments_count':353,'media_avatar_url':'//p3.pstatp.com/large/6cb002996a55fb5396c','is_feed_ad':false,'is_diversion_page':false,'title':'85岁老汉身高98厘米，先后娶4个妻子今又征婚，吸引30名女子上门','single_mode':true,'gallary_image_count':12,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6538742646528016899/','source':'日照新图','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':1,'image_url':'//p1.pstatp.com/list/300x170/pgc-image/15224165792454790643c95','group_id':'6538742646528016899','is_related':true,'media_url':'/c/user/6771716696/'},{'comments_count':5205,'media_avatar_url':'//p5a.pstatp.com/large/2c6500137a098bc80db6','is_feed_ad':false,'is_diversion_page':false,'title':'13岁瘦小少年为救同学牺牲，岸边50人无动于衷，数千人送别小英雄','single_mode':true,'gallary_image_count':8,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6520139576843960839/','source':'风云变幻的大地','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':1,'image_url':'//p3.pstatp.com/list/300x170/615f00058817d8d29209','group_id':'6520139576843960839','is_related':true,'media_url':'/c/user/51902550532/'},{'comments_count':2537,'media_avatar_url':'//p2.pstatp.com/large/434a00002fbee3f8ab43','is_feed_ad':false,'is_diversion_page':false,'title':'22岁瘫痪女孩一天学没上，自学达初中水平，渴望开网店和获得爱情','single_mode':true,'gallary_image_count':13,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6537872069298749959/','source':'五度图文','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p1.pstatp.com/list/300x170/pgc-image/1522214530829a99bb2c127','group_id':'6537872069298749959','is_related':true,'media_url':'/c/user/59107433937/'},{'comments_count':629,'media_avatar_url':'//p6.pstatp.com/large/3e7f000228f0e81de5ba','is_feed_ad':false,'is_diversion_page':false,'title':'孩子跟狗同锁在笼子里，1家8口蹬三轮车流浪，网友说太心酸','single_mode':true,'gallary_image_count':6,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6541124456167768584/','source':'图释天下','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p9.pstatp.com/list/300x170/pgc-image/1522972296286a941b16866','group_id':'6541124456167768584','is_related':true,'media_url':'/c/user/62031706180/'},{'comments_count':823,'media_avatar_url':'//p9.pstatp.com/large/470b00050734bdb9ccee','is_feed_ad':false,'is_diversion_page':false,'title':'19岁小伙帮堂兄拆屋砸成高位截瘫，一躺24年，该不该找堂兄索赔？','single_mode':true,'gallary_image_count':8,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6538728207498084867/','source':'图文直击','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/pgc-image/1522414271524699b83a89c','group_id':'6538728207498084867','is_related':true,'media_url':'/c/user/77380440059/'},{'comments_count':130,'media_avatar_url':'//p9.pstatp.com/large/470b00050734bdb9ccee','is_feed_ad':false,'is_diversion_page':false,'title':'老天不公人生多难啊，11岁儿子救人牺牲，这个家庭又发生两起悲剧','single_mode':true,'gallary_image_count':9,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6541119373745062403/','source':'图文直击','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/pgc-image/1522969712653e3bb5cb589','group_id':'6541119373745062403','is_related':true,'media_url':'/c/user/77380440059/'},{'comments_count':30,'media_avatar_url':'//p4.pstatp.com/large/2c68000006582322ad80','is_feed_ad':false,'is_diversion_page':true,'title':'堵锁眼酿悲剧 男子回家不成坠楼身亡','single_mode':true,'gallary_image_count':13,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6531952980197900808/','source':'光明网','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/65c70010713d7306060f','group_id':'6531952980197900808','is_related':true,'media_url':'/c/user/5806115967/'},{'comments_count':11487,'media_avatar_url':'//p3.pstatp.com/large/97e0011fb31149222a5','is_feed_ad':false,'is_diversion_page':false,'title':'21岁大学生为救人被水冲走去世，被救者冷漠称：是自己游上岸的！','single_mode':true,'gallary_image_count':4,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6525382792350532109/','source':'诗人风采','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/66a900034b7f12167772','group_id':'6525382792350532109','is_related':true,'media_url':'/c/user/6990399041/'},{'comments_count':5577,'media_avatar_url':'//p5a.pstatp.com/large/6edf0003d4d8dae26533','is_feed_ad':false,'is_diversion_page':false,'title':'一场车祸打破了平静的家庭，妻子离家出走，他带着儿子流浪','single_mode':true,'gallary_image_count':11,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6540580816139846147/','source':'唯图','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/pgc-image/1522843652377c28466a680','group_id':'6540580816139846147','is_related':true,'media_url':'/c/user/76224852774/'},{'comments_count':79,'media_avatar_url':'//p2.pstatp.com/large/434a00002fbee3f8ab43','is_feed_ad':false,'is_diversion_page':false,'title':'一笔医药费引发家庭悲剧，丈夫残障妻子离家，被房东赶走父子流浪','single_mode':true,'gallary_image_count':10,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6541242864045654535/','source':'五度图文','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/pgc-image/15229959436768344620449','group_id':'6541242864045654535','is_related':true,'media_url':'/c/user/59107433937/'},{'comments_count':538,'media_avatar_url':'//p1.pstatp.com/large/1233000bbe745d9a0b59','is_feed_ad':false,'is_diversion_page':false,'title':'50岁农民老来得子，三胞胎男娃愁坏了他，流落街头','single_mode':true,'gallary_image_count':7,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6540855803782365709/','source':'小鱼儿游世界','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p1.pstatp.com/list/300x170/pgc-image/1522909981383ccbfb3ab76','group_id':'6540855803782365709','is_related':true,'media_url':'/c/user/52407240362/'},{'comments_count':10,'media_avatar_url':'//p6.pstatp.com/large/6edc0001d25bf9c42478','is_feed_ad':false,'is_diversion_page':false,'title':'农村女孩双腿残疾，在网上卖苹果自食其力，一年卖10万斤','single_mode':true,'gallary_image_count':8,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6513205628263989774/','source':'图鱼','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p9.pstatp.com/list/300x170/5b400001158d0ae9ac30','group_id':'6513205628263989774','is_related':true,'media_url':'/c/user/71111401384/'},{'comments_count':43,'media_avatar_url':'//p6.pstatp.com/large/53ee0001e562e7804e28','is_feed_ad':false,'is_diversion_page':false,'title':'河南男子因工地事故致终生残疾 获助后坚持捐献器官回报社会','single_mode':true,'gallary_image_count':8,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6536042254732100110/','source':'黑土影像','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p1.pstatp.com/list/300x170/pgc-image/1521790489678d479ce5fc2','group_id':'6536042254732100110','is_related':true,'media_url':'/c/user/3522827171/'},{'comments_count':71,'media_avatar_url':'//p1.pstatp.com/large/616d00055fde46e74378','is_feed_ad':false,'is_diversion_page':false,'title':'男子打工10年，5万元的小车都买不起！相亲姑娘说，活该你打光棍','single_mode':true,'gallary_image_count':6,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6532636760554340872/','source':'职场反黑第一人','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/pgc-image/1520990443541a560ac2b0c','group_id':'6532636760554340872','is_related':true,'media_url':'/c/user/50084950116/'},{'comments_count':303,'media_avatar_url':'//p4.pstatp.com/large/2c68000006582322ad80','is_feed_ad':false,'is_diversion_page':true,'title':'女儿遭父亲殴打 绑在摩托车上拖行100米浑身是伤！','single_mode':true,'gallary_image_count':4,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6528180106223944196/','source':'光明网','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p1.pstatp.com/list/300x170/65b90016d6fc7e448d43','group_id':'6528180106223944196','is_related':true,'media_url':'/c/user/5806115967/'},{'comments_count':56,'media_avatar_url':'//p4.pstatp.com/large/2c68000006582322ad80','is_feed_ad':false,'is_diversion_page':true,'title':'女孩郑州租房刚交完房租就被赶 随后的事让她无语','single_mode':true,'gallary_image_count':16,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6535206810633126403/','source':'光明网','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':0,'image_url':'//p3.pstatp.com/list/300x170/71f20001c8f62937f8ce','group_id':'6535206810633126403','is_related':true,'media_url':'/c/user/5806115967/'},{'comments_count':459,'media_avatar_url':'//p5a.pstatp.com/large/6edf0003d4d8dae26533','is_feed_ad':false,'is_diversion_page':false,'title':'女儿远嫁儿子外出打工，六旬老人眼睛看不见独自在家，做豆腐靠听','single_mode':true,'gallary_image_count':8,'middle_mode':false,'has_video':false,'video_duration_str':null,'source_url':'https://www.toutiao.com/group/6540530296297619971/','source':'唯图','more_mode':null,'article_genre':'gallery','has_gallery':false,'video_play_count':1,'image_url':'//p3.pstatp.com/list/300x170/pgc-image/1522834223599b56adba935','group_id':'6540530296297619971','is_related':true,'media_url':'/c/user/76224852774/'}]";
		JSONArray data = JSONArray.parseArray(str);

		String js = JSONObject.toJSONString(data);
		List<ImageNews> list = JSONObject.parseArray(js, ImageNews.class);
		System.out.println(list.size());

		for (ImageNews news : list) {
			System.out.println(news);
		}

	}
}

#!/usr/bin/env python
# -*- coding:utf-8 -*-
from redis import Redis
from selenium import webdriver

print('准备python服务器')
##打开浏览器
firefox = webdriver.Firefox()
firefox.get('https://www.toutiao.com/ch/news_image/')
##获取cookie
dictCookies = firefox.get_cookies()
cookieHeader = ''
for cookie in dictCookies:
	cookieHeader += cookie['name'] + '=' + cookie['value'] + '; '

##获取redis的连接
conn = Redis(host='127.0.0.1',port=6379,db=0)
##设置请求头信息到 redis
conn.hset("headerKey","Accept",'text/javascript, text/html, application/xml, text/xml, */*')
conn.hset("headerKey","Accept-Encoding",'gzip, deflate, br')
conn.hset("headerKey","Accept-Language",'zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2')
conn.hset("headerKey","Connection",'keep-alive')
conn.hset("headerKey","Content-Type",'application/x-www-form-urlencoded')
conn.hset("headerKey","Cookie",cookieHeader)
conn.hset("headerKey","Host",'www.toutiao.com')
conn.hset("headerKey","User-Agent",'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0')
conn.hset("headerKey","X-Requested-With",'XMLHttpRequest')

pubChannel = 'toutiaolistener'
subChannel = 'toutiaopush'
##监听通知
pub = conn.pubsub()
pub.subscribe(subChannel)
pub.parse_response()
print('python服务器准备完成')
while True:
	##获取签名并发送信息
	max_behot_time= pub.parse_response()
	print('max_behot_time:' + str(max_behot_time))
	sinature = firefox.execute_script('return TAC.sign(' + str(max_behot_time) + ')')
	conn.publish(pubChannel,max_behot_time + ':' + sinature)
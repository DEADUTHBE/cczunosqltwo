# from server.src.Spider.SpiderDependence import WeiboSpider, ZhiHuSpider, BilibiliSpider
from SpiderDependence import WeiboSpider, ZhiHuSpider, BilibiliSpider
import time

"""
:Author:    iWorld
:Create:    2021/12/13 11:34
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  在此定时地调用所有的爬虫
"""
if __name__ == "__main__":
    while True:
        try:
            weiboSpider = WeiboSpider()
            weiboSpider.getWeiboHot()
        except Exception as e:
            print(e)
            continue

        try:
            zhiHuSpider = ZhiHuSpider()
            zhiHuSpider.getZhiHuHot()
        except Exception as e:
            print(e)
            continue

        try:
            bilibiliSpider = BilibiliSpider()
            bilibiliSpider.getBilibiliHot()
        except Exception as e:
            print(e)
            continue
        time.sleep(60)

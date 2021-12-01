from SpiderDependence import Bilibili
from SpiderDependence import Weibo
from SpiderDependence import ZhiHu
"""
:Author:    iWorld
:Create:    2021/11/19 15:30
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  在此调用所有的爬虫
"""

if __name__ == "__main__":
    weiboSpider = Weibo.WeiboSpider()
    weiboSpider.getWeiboHot()
    zhiHuSpider = ZhiHu.ZhiHuSpider()
    zhiHuSpider.getZhiHuHot()
    bilibiliSpider = Bilibili.BilibiliSpider()
    bilibiliSpider.getBilibiliHot()
import WeiboSpider
import ZhiHuSpider
import BilibiliSpider

"""

:Author:    iWorld
:Create:    2021/11/19 15:30
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  在此调用所有的爬虫
"""
if __name__ == "__main__":
    weiboSpider = WeiboSpider()
    weiboSpider.getWeiboHot()
    zhiHuSpider = ZhiHuSpider()
    zhiHuSpider.getZhiHuHot()
    bilibiliSpider = BilibiliSpider()
    bilibiliSpider.getBilibiliHot()

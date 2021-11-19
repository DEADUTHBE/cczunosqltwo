class weiboSpider:
    def __init__(self):
        self.myclient = pymongo.MongoClient("mongodb://localhost:27017/")
        self.mydb = self.myclient["hot"]
        self.mycol = self.mydb["weibo"]
        self.mycol.drop()
        self.url = "https://weibo.com/ajax/side/hotSearch"
        self.headers = {
            'User-Agent': "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36"
        }

    def getHotSearch(self):
        req = requests.get(self.url)
        print(req.text)
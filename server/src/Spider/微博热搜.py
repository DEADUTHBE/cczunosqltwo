import pymongo
import requests
from lxml import etree

myclient = pymongo.MongoClient("mongodb://localhost:27017/")
mydb = myclient["hot"]
mycol = mydb["weibo"]
mycol.drop()

url='https://tophub.today/'
headers={
    'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36',
    'Cookie':'UM_distinctid=17cda85f7978-031628076c1214-57b193e-e1000-17cda85f798b46; CNZZDATA1276310587=989304809-1635751351-%7C1636951333; Hm_lvt_3b1e939f6e789219d8629de8a519eab9=1635753786,1636901586,1636902051,1636954220; Hm_lpvt_3b1e939f6e789219d8629de8a519eab9=1636954220',
    'Referer':'https://www.baidu.com/link?url=KOxaTe_gSJOMNBmuB7i1CNe0u8S6tx9mwZgyG3EyFj_&wd=&eqid=c6d4c65c000a6cc0000000056191f067'
}
response = requests.get(url, headers=headers,verify=False)
#print(response.text) 
html=etree.HTML(response.text)
#datas=html.xpath('//*[@id="pl_top_realtimehot"]/table/tbody/tr')
datas=html.xpath('//*[@id="node-1"]/div/div[2]/div[1]/a/div')

mylist=[]
for i in range(50):
    dicty = {'_id': 'a','热搜排名': 'b', '热搜标题': 'q', '热搜点击量': 'w', '热搜地址': 'e'}
    mylist.append(dicty)

for i,data in enumerate(datas): #循环多次提取
    data_rank=''.join(data.xpath('span[1]/text()')) #热搜标题
    data_title=''.join(data.xpath('span[2]/text()')) #热搜排名
    data_num=''.join(data.xpath('span[3]/text()')) #热搜点击量
    data_url=''.join(data.xpath('//*[@id="node-1"]/div/div[2]/div[1]/a[4]/@href'))
    mylist[i]['_id']=i+1
    mylist[i]['热搜排名']=data_rank
    mylist[i]['热搜标题']=data_title
    mylist[i]['热搜点击量']=data_num
    mylist[i]['热搜地址']=data_url
    
    print(data_rank,data_title,data_num,data_url)


myclient = pymongo.MongoClient("mongodb://localhost:27017/")
mydb = myclient["hot"] #数据库名
mycol = mydb["weibo"] #集合名
x = mycol.insert_many(mylist)
# 输出插入的所有文档对应的 _id 值
#print(x.inserted_ids)  
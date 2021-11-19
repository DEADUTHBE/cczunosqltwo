from pymongo import MongoClient
import json


class Json2Mongo(object):
    def __init__(self):
        self.host = '0.0.0.0'
        self.port = 27017
        # 创建mongodb客户端
        self.client = MongoClient(self.host, self.port)
        # 创建数据库dialog
        self.db = self.client.dialog
        # 创建集合scene
        self.collection = self.db.scene

    # 写入数据库
    def write_database(self):
        with open(r'../temp/zhiHuUsersInfo.json', 'r', encoding="utf-8") as f:
            # 转换为dict
            json_data = json.load(f)
        data = {
            "name": "zhiHuUsersInfo",
            "content": json_data
        }
        try:
            myquery = {"name": "zhiHuUsersInfo"}  # 查询条件
            self.collection.update(myquery, data, upsert=True)  # upsert=True不存在则插入，存在则更新
            # self.collection.insert(data)
            print('写入成功')
        except Exception as e:
            print(e)

    # 从数据库读取
    def read_datebase(self):
        try:
            myquery = {"name": "zhiHuUsersInfo"}  # 查询条件
            scene_flow = self.collection.find(myquery)
            print(type(scene_flow))
            for x in scene_flow:
                print(type(x))
                print(x)
            print('读取成功')
        except Exception as e:
            print(e)


if __name__ == '__main__':
    jm = Json2Mongo()
    jm.write_database()
    jm.read_datebase()

def constructPicUrl(picUrl):
    picUrl = "https://wx4.sinaimg.cn/orj360/['6ab9a2f6ly1gxdhue1mbbj20zl0u0q8i', '6ab9a2f6ly1gxdhue8umaj20xa0u00ye', '6ab9a2f6ly1gxdhueig4qj21530u0te6', '6ab9a2f6ly1gxdhuepv8ij20oa10bwhg', '6ab9a2f6ly1gxdhudduocj20t707h0t6', '6ab9a2f6ly1gxdhuew6eaj20tz05uwf3', '6ab9a2f6ly1gxdhuf4budj20ti0483yu', '6ab9a2f6ly1gxdhufbiv8j20tl04fgm0', '6ab9a2f6ly1gxdhufhk9gj20tl044t92'].jpg"
    if "[" in picUrl:
        print("true")
    picUrl = picUrl.split('[')
    tempStr = picUrl[1].split(',')
    url = picUrl[0] + tempStr[0].replace("'", "")

    while "[" in url:
        url = url.replace("[", "")
    while "]" in url:
        url = url.replace("]", "")
    while "'" in url:
        url = url.replace("'", "")
    while "\\" in url:
        url = url.replace("\\", "")
    return url


hotDict = {}
# %%
hotDict["picUrl"] = "https://wx4.sinaimg.cn/orj360/['001R0E0aly1gxdieafsfrj60rs0rsdjj02'].jpg"
# %%
if "[" in hotDict["picUrl"]:
    hotDict["picUrl"] = constructPicUrl(hotDict["picUrl"])
    print(hotDict["picUrl"])
// miniprogram/pages/HotSearch/HotSearch.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        hotData: {},
        pageNum: 1,
        hotIndex: ["微博热榜", "哔哩热榜", "知乎热榜"],
        currentTab: 0
    },

    getHotData: function () {
        let that = this;
        wx.request({
            url: "https://iworld.fun/springboot/hotdata",
            fail(res) {
                console.log(res.errMsg)
            },
            success(res) {
                console.log(res.data["0"])
                that.setData({
                    hotData: res.data["0"]
                })
            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.getHotData();
    },
    navbarTap: function (e) {
        this.setData({
            currentTab: e.currentTarget.dataset.idx
        })
    },
})
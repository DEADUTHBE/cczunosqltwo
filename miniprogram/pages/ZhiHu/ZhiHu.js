// miniprogram/pages/HotSearch/HotSearch.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        hotData: {},
        pageNum: 1
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

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})
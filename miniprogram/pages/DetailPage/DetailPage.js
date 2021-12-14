// miniprogram/pages/HomePage/DetailPage/DetailPage.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        usersInfo: {}
    },
    getData: function (word) {
        console.log(word);
        let that = this;
        wx.request({
            url: "https://iworld.fun/springboot/s?keyword=" + word,
            success(res) {
                console.log(res.data)
                that.setData({
                    usersInfo: res.data
                })
            }
        });
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (word) {
        // console.log(word.word);
        // this.getData(word.word)
        this.getData("计算机");
    }
})
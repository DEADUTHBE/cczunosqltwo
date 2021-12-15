const app = getApp();
const db = wx.cloud.database({
    env: 'woodpecker-demo-5gd5u8sl03d34a50'
})
Page({
    /**
     * 页面的初始数据
     */
    data: {
        usersInfo: {},
        word: "",
        zhiZheUsersInfo: {},
        showPageContainer: false
    },
    getInput: function (e) {
        let word = e.detail.value;
        this.setData({
            word: word
        })
    },
    doSearchFromIcon: function () {
        wx.navigateTo({
            url: "DetailPage/DetailPage?word=" + this.data.word
        })
    },
    doSearch: function (event) {
        let word = event.detail.value;
        wx.navigateTo({
            url: "DetailPage/DetailPage?word=" + word
        })
    },
    getTime: function () {
        let time = new Date().getTime();
        let date = new Date(time + 8 * 3600 * 1000); // 增加8小时
        console.log(date);
        return date.toJSON().substr(0, 19).replace('T', ' ');
    },
    getUserProfile(e) {
        let that = this;
        wx.getUserProfile({
            desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
            success: (res) => {
                that.setData({
                    userInfo: res.userInfo,
                    hasUserInfo: true
                });
                db.collection("ZhiZheUsersInfo").add({
                    data: {
                        time: that.getTime(),
                        gender: that.data.userInfo.gender ? "女" : "男",
                        language: that.data.userInfo.language,
                        nickName: that.data.userInfo.nickName,
                        province: that.data.userInfo.province,
                        avatarUrl: that.data.userInfo.avatarUrl,
                        city: that.data.userInfo.city,
                        country: that.data.userInfo.country
                    },
                    success: function (res) {
                        // res 是一个对象，其中有 _id 字段标记刚创建的记录的 id
                        console.log(res)
                    }
                })
            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        if (wx.getUserProfile) {
            this.setData({
                canIUseGetUserProfile: true
            })
        }

        let that = this;
        wx.getUserInfo({
            success: function (res) {
                var userInfo = res.userInfo
                that.setData({
                    userInfo: res.userInfo,
                    nickName: userInfo.nickName,
                    avatarUrl: userInfo.avatarUrl,
                    gender: userInfo.gender, //性别 0：未知、1：男、2：女
                    province: userInfo.province,
                    city: userInfo.city,
                    country: userInfo.country,
                })
            }
        })
    },
    popup(e) {
        const position = e.currentTarget.dataset.position
        console.log("position: ", position);
        this.setData({
            show: true,
        })
    },
    exit() {
        this.setData({
            show: false
        })
    }
})
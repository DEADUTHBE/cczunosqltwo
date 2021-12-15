Page({

    /**
     * 页面的初始数据
     */
    data: {
        usersInfo: {},
        show: false,
        currentUser: {}
    },
    getData: function (word) {
        console.log(word);
        let that = this;
        wx.request({
            url: "https://iworld.fun/springboot/s?keyword=" + word,
            success(res) {
                that.setData({
                    usersInfo: res.data
                })
            }
        });
    },

    onLoad: function (word) {
        // this.getData(word.word);
        this.getData("计算机");
    },

    // 弹出详细页面
    popup(e) {
        const position = e.currentTarget.dataset.position;
        let userIndex = e.currentTarget.dataset.id;
        let currentUser = this.data.usersInfo[userIndex * 1];
        console.log(userIndex);
        this.setData({
            show: true,
            currentUser: currentUser
        });
    },
    exit() {
        this.setData({
            show: false
        })
    }
})
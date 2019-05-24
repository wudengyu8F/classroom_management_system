<template>
<div class="a">
  <header>
    <div id="title">首页</div>
  </header>
  <a><div class="tologin" @click="login">{{username}}</div>
    <div class="quit" @click="quit">退出</div>
  </a>
  <img class="welcome" src="../assets/welcome.jpg"/>
  <div class="footer">
    <el-row>
      <el-button type="primary" plain style="margin:1rem" @click="getRoom">教室查询</el-button>
      <el-button type="warning" plain style="margin:1rem" @click="tomine">我的预约</el-button>
      <el-button type="info" plain style="margin:1rem" @click="getRoomDetailByIdAndDate">教室审批</el-button>

    </el-row>
  </div>
  <AlertTip :alertText="alertText" v-show="alertShow" @closeTip="closeTip"/>
</div>
</template>

<script>
  import foowrap from "./foowrap"
  import AlertTip from './AlertTip.vue'
  import { setCookie,getCookie,delCookie } from '../assets/cookie.js'
  import axios from "axios"
    export default {
        name: "home",
      data(){
         return{
           ol:'登录注册',
           username:'',
           userId:this.userId,
           username:'',
           foowrap:foowrap,
           alertShow: false ,// 是否显示提示框
           alertText: '', // 提示文本
         }
      },
      components: {
        foowrap: foowrap,
        AlertTip
      },
      mounted(){
        /*页面挂载获取保存的cookie值，渲染到页面上*/
        let uname = getCookie('userId')
        let userName = getCookie('userName')
        console.log(userName)
        this.username = userName
        /*如果cookie不存在，则跳转到登录userId页*/
        if(userName === ""){
          this.username = '登录/注册'
        }
      },
      methods:{
         //显示提示框
       showAlert (alertText) {
            this.alertShow = true
            this.alertText = alertText
        },
        //关闭提示框
        closeTip () {
            this.alertShow = false
            this.alertText = ''
        },
        //TODO 需要调用后端接口退出     /api/login/logout
        quit(){
          let self = this
          /*删除cookie*/
          delCookie('userId')
          delCookie('userName')
          // location. reload()
          // this.$router.go(0)
           axios.get( '/api/login/logout')
              .then(function (response) {
                console.log(response)
                if(response.data.code == 1){
                  self.showAlert('退出成功')
                  // window.location.reload(); 
                }
              })
              .catch(function (err) {
                console.log(err);
              })
        },
        login:function () {
          let uname = getCookie('userId')
          console.log(uname)
          this.userId = uname
          /*如果cookie不存在，则跳转到登录userId页*/
          if(uname === ""){
          this.$router.push({path:'./login'})
        }
        },
        tomine:function(){
            this.$router.push({path:'./mine',query:{'userId': this.userId,'username':this.username}})
        },
        getRoom:function () {
          this.$router.push({path:'./getRoom',query:{'userId': this.userId}})
        },
        insertInfor:function(){
          this.$router.push({path:'./insertInfor',query:{'userId': this.userId}})
        },
        getRoomDetailByIdAndDate:function () {
          this.$router.push({path:'./getApprovalDetail',query:{'userId': this.userId}})
        },
        gin:function(){
          let data1 = ({
            "userId": this.userId,
            "password": this.password,
            "rememberMe":this.rememberMe
          })
          console.log(data1)
          axios({
            method: 'get',
            url: '/api/login/logout',
            data: data1
          }).then((response) => {
            console.log(response.data.code)
            if (response.data.code === 1) {
              this.$router.push({path: '/#', query: {'uid': this.uId}})
            } else if (response.data === 0) {
            }
          }).catch(error => function () {
          })
        }
      }
    }
</script>

<style scoped>
  header #title{
    float: left;
    text-align: center;
    font-size: 1.2rem;
    width: 100%;
    height: 1.58rem;
    line-height: 1.68rem;
    margin-bottom: 1rem;
    margin-top: 10px;
  }
  .a{
    width: 100vw;
    height: 100vh;
    background-image: url("../assets/home.jpg");
    background-size: cover;
    position: relative;
  }
  .tologin{
    text-align: right;
    margin-top: 10px;
    margin-left: 13rem;
    float: left;
  }
  .quit{
    text-align: right;
    margin-top: 10px;
    margin-right: 20px;
    float: right;
  }
.welcome{
  width: 90%;
  margin: 85px 5% 0 5%;
}
  .footer{

    width: 100%;
    position: absolute;
    bottom: 60px;
  }
</style>

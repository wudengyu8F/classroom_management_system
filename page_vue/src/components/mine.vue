<template>
<div>
  <header>
    <div id="title">我的预约</div>
    <i class="el-icon-arrow-left" @click="tohome"></i>
  </header>

   <div class="block">
    <!-- <el-date-picker
      v-model="value1"
      type="date"
      placeholder="选择日期"
      :picker-options="pickerOptions1" @change="selectList(value1)">
    </el-date-picker> -->
  </div>
  <div class="crumbs">
    <div class="list-title">
      <div>教学楼</div>
      <div>教室号</div>
      <div>日期</div>
      <div>时间</div>
      <div>状态</div>
      <div>操作</div>
    </div>
    <div v-if="tableData.length == 0" class="wu">无数据</div>
    <div class="cantainer" v-for="(item,index) in tableData" :key="index">
      <div>{{item.teachingBuilding}}</div>
      <div>{{item.roomNumber}}</div>
      <div>{{item.date}}</div>
      <div>{{item.time}}</div>
      <div>{{item.isPass}}</div>
      <div @click="cacel(item.isPass,item.id)"> 取消申请</div>
    </div>
  </div>
  <AlertTip :alertText="alertText" v-show="alertShow" @closeTip="closeTip"/>
  <Alert :alertText1="alertText1" v-show="alertShow1" @closeTip1="closeTip1"/>
</div>
</template>

<script>
  import axios from 'axios'
  import AlertTip from './AlertTip.vue'
  import Alert from './Alert.vue'
  import { setCookie,getCookie,delCookie } from '../assets/cookie.js'
    export default {
        name: "mine",
      data(){
          return{
            date:'',
            userId:'',
            roomId:'',
            roomNumber:'',
            teachingBuilding:'',
            time:'',
            user:'点击登录',
            tableData:[],
            pickerOptions2: {
              shortcuts: [{
                text: '最近一周',
                onClick(picker) {
                  const end = new Date();
                  const start = new Date();
                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                  picker.$emit('pick', [start, end]);
                }
              }]
            },
            value6: '',
            value7: '',
            alertShow: false ,// 是否显示提示框
            alertText: '', // 提示文本
            alertShow1:false,
            alertText1:''
          }
      },
      created () {

      },
      components:{
        AlertTip,
        Alert
      },
      mounted(){
        let uname = getCookie('userId')
        console.log(uname)
        this.userId = uname
        console.log(this.userId)
        /*如果cookie不存在，则跳转到登录页*/
        // if(uname ===""){
        //   this.$router.push('/')
        // }
        this.getmsge()
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
         //显示提示框
       showAlert1 (alertText1) {
            this.alertShow1 = true
            this.alertText1 = alertText1
        },
        //关闭提示框
        closeTip1 () {
            this.alertShow1 = false
            this.alertText1 = ''
        },
        selectList(date){
          var str = new Date(date)
          let urlDate
          if((str.getMonth() + 1)<10){
            urlDate = String(str.getFullYear()) + '0' + String((str.getMonth() + 1)) + String(str.getDate())
          }else{
            urlDate = String(str.getFullYear()) + String((str.getMonth() + 1)) + String(str.getDate())
          }
          this.applyDate = urlDate
        },
        cacel(ispass,id){
          let self = this
          if(ispass == '未通过'){
            axios.get( '/api/rsv/cancelApplication', {
            params: {
              'id':id
            }
            })
              .then(function (response) {
                console.log(response)
                if(response.data.code == 1){
                  self.showAlert('取消成功')
                  // window.location.reload(); 
                }
              })
              .catch(function (err) {
                console.log(err);
              })
          }else{
            this.showAlert('取消失败')
          }
          
          },
        getmsge:function(){
          let self = this
          axios.get( '/api/rsv/getStudentRSVById', {
            params: {
              'uid':this.userId
            }
          })
            .then(function (response) {
              console.log(response)
              if(response.data.code == 0){
                console.log('mine')
                console.log(response.data.message)
               self.showAlert1(response.data.message)
              }else{
                 let tableData=[]
                self.tableData = eval(response.data.data)
                console.log(self.tableData)
              }
             
            })
            .catch(function (err) {
              console.log(err);
            })
        },
        tohome:function () {
          this.$router.push({path:'./#',query:{'userId': "this.userId"}})
        },
        login:function () {
          this.$router.push({path:'./login'})
        },
        handleSizeChange(val) {
          console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
          console.log(`当前页: ${val}`);
        }
      }
    }
</script>

<style scoped>
  header{
    width: 100%;
    height:1.58rem;
    background-color: #fff;
    padding-top: 10px;
  }
  .el-icon-arrow-left{
    font-size: 1.45rem;
    position: absolute;
    left: 1.5rem;
    top: 0.65rem;
  }
  .el-icon-circle-close{
    font-size: 1.15rem;
    position: absolute;
    right: 2.5rem;
    top: 5.65rem;
  }
  header #title{
    float: left;
    text-align: center;
    font-size: 1.2rem;
    width: 100%;
    height: 1.58rem;
    line-height: 1.68rem;
    margin-bottom: 1rem;
  }
  .login{
    text-align: left;
    margin-left: 50px;
  }
  .list-title{
    width: 100vw;
    display: flex;
    padding-top: 10px;
    justify-content: space-around;
    font-weight: 600;
    /* color: #cccccc; */
  }
  .list-title div{
    width: 62px;
  }
  .crumbs{
    width: 100vw;
  }
  .cantainer{
    display: flex;
    justify-content: space-around;
   }
  .cantainer div{
    width: 62px;
    display: inline-block;
    margin-bottom: 5px;
  }
  .cantainer div:last-child{
    font-size: 12px;
    text-align: center;
    line-height: 20px;
    margin-right: 2px;
    border: 1px solid #eeeeee
  }
  .cantainer div:nth-child(4){
    padding-left: 5px;
  }
  .wu{
    font-size:25px;
    font-weight: 500;
  }
</style>

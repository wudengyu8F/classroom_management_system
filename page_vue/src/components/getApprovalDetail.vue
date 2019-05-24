<template>
<div>
  <header>
    <div id="title">审批</div>
    <i class="el-icon-arrow-left" @click="tohome"></i>
  </header>
<!-- <div class="block">
    <el-date-picker
      v-model="value1"
      type="date"
      placeholder="选择日期"
      :picker-options="pickerOptions1" @change="selectList(value1)">
    </el-date-picker>
  </div> -->
  <div class="content">

      <div class="crumbs">
        <div>教学楼</div>
        <div>教室号</div>
        <div>日期</div>
        <div>时间</div>
        <div>申请人</div>
        <div>描述</div>
        <div>操作</div>
      </div>
      <div v-if="tableData.length == 0" class="wu">无数据</div>
      <div v-else>
         <div class="cantainer" v-for="(item,index) in tableData" :key="index">
         <div>{{item.teachingBuilding}}</div>
         <div>{{item.roomNumber}}</div>
         <div>{{item.date}}</div>
         <div>{{item.time}}</div>
         <div>{{item.userName}}</div>
         <div>{{item.reservationDesc}}</div>
         <div>
           <span @click="pass(item.id)">同意</span>
           <span @click="open3(item.id)">驳回</span>
         </div>
        </div>
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
  import { MessageBox } from 'mint-ui'
import { readlink } from 'fs';
  export default {
    data() {
      return {
        tableData:[],
        date:'',
        reservationDesc:'',
        roomNumber:'',
        teachingBuilding:'',
        userName:'',
        time:'',
        pickerOptions1: {
            disabledDate(time) {
              return time.getTime() < Date.now() || Date.now()<time.getTime()-3600*1000*24*7;
            },
          },
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
        value1:'',
        id:'',
        alertShow: false ,// 是否显示提示框
          alertText: '', // 提示文本
          alertShow1:false,
            alertText1:''
      }},
      components:{
        AlertTip,
        Alert
      },
    created () {
      let userId = this.$route.query.userId
      let id = this.$route.query.id
      this.userId = userId
      this.id = id
      console.log(this.userId)
      console.log(this.id)
      this.getmsg()
    },
    mounted(){
      /*如果cookie不存在，则跳转到登录页*/
      // if(uname ===""){
      //   this.$router.push('/')
      // }
    },
    methods: {
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
          let self = this
          var str = new Date(date)
          let urlDate
          if((str.getMonth() + 1)<10){
            urlDate = String(str.getFullYear()) + '0' + String((str.getMonth() + 1)) + String(str.getDate())
          }else{
            urlDate = String(str.getFullYear()) + String((str.getMonth() + 1)) + String(str.getDate())
          }
          this.applyDate = urlDate
          console.log(urlDate)
          axios.get('/api/rsv/high/getApprovalDetail', {
          params: {
            "date": urlDate
          }
        }).then((response) => {
          console.log(response.data.data)
          if(response.data.code == 0){
            self.showAlert1(response.data.message)
          }else{
              let tableData=[]
              let data =eval(response.data.data)
              let roomdata = data.classroom
              let self = this
              console.log(roomdata);
              self.tableData = data.status
              console.log(tableData)
              self.seatsNumber= roomdata.seatsNumber
              console.log(self.seatsNumber)
              self.roomLocal=roomdata.roomLocal
              self.roomNumber = roomdata.roomNumber
              self.teachingBuilding=roomdata.teachingBuilding
              self.multimediaEquipment=roomdata.multimediaEquipment
          }
         
        })
          .catch(function (err) {
            console.log(err);
          })
        },
      pass(id){
        let self = this
         axios.get( '/api/rsv/high/approvalOperation', {
            params: {
              'id':id,
              'ope':1
            }
            })
              .then(function (response) {
                console.log(response)
                if(response.data.code == 1){
                  self.showAlert('同意申请')
                  // window.location.reload(); 
                }
              })
              .catch(function (err) {
                console.log(err);
              })
      },
      npass(){

      },
      open3(id) {
        let self = this
        MessageBox.prompt('请输入理由').then(({ value, action }) => {
          console.log(action)
          if(action == 'confirm'){
            console.log(111)
            axios.get( '/api/rsv/high/approvalOperation', {
            params: {
              'id':id,
              'ope':3,
              'desc':value
            }
            })
              .then(function (response) {
                console.log(response)
                if(response.data.code == 1){
                  self.showAlert('驳回成功')
                  
                }
              })
              .catch(function (err) {
                console.log(err);
              })
          }
        //   this.$message({
        //     type: 'success',
        //     message: '理由: ' + value
        //   });
        // }).catch(() => {
        //   this.$message({
        //     type: 'info',
        //     message: '取消输入'
        //   });
        });
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      tohome:function () {
        this.$router.push({path:'./#'})
      },
      getmsg:function(){
        let self = this
        let data1 = ({

        })
        console.log(data1)
        axios({
          method: 'get',
          url: '/api/rsv/high/getApprovalDetail',
          data: data1
        }).then((response) => {
           console.log(response.data.data)
          if(response.data.code == 0){
            console.log('getApp')
            self.showAlert1(response.data.message)
          }else{
          console.log(response)
          let tableData=[]
          console.log(eval(response.data.data))
          this.tableData = eval(response.data.data)
          console.log(self.tableData)
          }

        }).catch(error => function () {
        })
      }
    }
  }
</script>

<style scoped>
  header{
    width: 100vw;
    height:1.58rem;
    background-color: #fff;
    padding-top: 10px;
    position: fixed;
    top: 0;
    left: 0;
  }
  .el-icon-arrow-left{
    font-size: 1.45rem;
    position: fixed;
    left: 1.5rem;
    top: 0.65rem;
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
  .block{
    margin-top: 45px;
  }
  .content{
    margin-top: 45px;
  }
  .crumbs{
    width: 100vw;
    font-weight: 600;
    display: flex;
    justify-content: space-around;

  }
  .crumbs div:nth-child(1){
    width: 10%;
    font-size: 14px;
  }
  .crumbs div:nth-child(2){
    width: 10%;
    font-size: 14px;
  }
  .crumbs div:nth-child(3){
    width: 14%;
  }
  .crumbs div:nth-child(4){
    width:14%;
  }
  .crumbs div:nth-child(5){
    width: 15%;
  }
  .crumbs div:nth-child(6){
    width: 15%;
  }
  .wu{
    font-size:25px;
    font-weight: 500;
  }
  .cantainer{
    width: 100vw;
    display: flex;
    justify-content: space-around;
  }
  .cantainer div{
    width: 32px;
  }
  .cantainer div:nth-child(3){
    width: 20%;
    font-size: 12px;
    line-height: 41px;
  }
  .cantainer div:nth-child(1){
    width: 10%;
    line-height: 41px;
  }
  .cantainer div:nth-child(2){
    width: 10%;
    line-height: 41px;
  }
  .cantainer div:nth-child(4){
    width: 15%;
    font-size: 12px;
    line-height: 41px;
  }
  .cantainer div:nth-child(5){
    width: 20%;
    font-size: 12px;
    line-height: 41px;
    text-overflow: ellipsis;
  }
  .cantainer div:nth-child(6){
    width: 15%;
    line-height: 41px;
    text-overflow: ellipsis;
    padding-right: 3px;
  }
</style>

<template>
  <div>
    <header>
      <div id="title" >教室预约</div>
      <i class="el-icon-arrow-left" @click="tohome"></i>
    </header>
    <p>教室详情</p>
    <el-row :gutter="16">
      <el-col :span="8">教学楼号：</el-col>
      <el-col :span="6">{{teachingBuilding}}</el-col>
    </el-row>
    <el-row :gutter="16">
      <el-col :span="8">教室号：</el-col>
      <el-col :span="6">{{roomNumber}}</el-col>
    </el-row>
    <el-row :gutter="16">
      <el-col :span="8">教室位置：</el-col>
      <el-col :span="6">{{roomLocal}}</el-col>
    </el-row>
    <el-row :gutter="16">
      <el-col :span="8">座位数：</el-col>
      <el-col :span="6">{{seatsNumber}}</el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8">教室器材：</el-col>
      <el-col :span="15" style="text-align:left">{{multimediaEquipment}}</el-col>
    </el-row><br>
    <div class="block">
    <el-date-picker
      v-model="value1"
      type="date"
      placeholder="选择日期"
      :picker-options="pickerOptions1" @change="selectList(value1)">
    </el-date-picker>
  </div>
    <div class="item">
      <div>时间</div>
      <div>状态</div>
      <div>申请描述</div>
      <div>操作</div>
    </div>
    <div v-for="(item,index) in tableData" :key="index" class="item-list">
      <div>{{item.time}}</div>
      <div>{{item.status}}</div>
      <div @click="open3(index)">理由</div>
      <div @click="toinserfor(item.status,item.timeId)">{{isTrue}}</div>
    </div>
    <AlertTip :alertText="alertText" v-show="alertShow" @closeTip="closeTip"/>
    <!-- <div>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column
            prop="time"
            label="时间"
            width="80">
          </el-table-column>
          <el-table-column
            prop="status"
            label="状态"
            width="100">
          </el-table-column>
          <el-table-column
            prop="address"
            label="申请描述">
            <span @click="open3">理由</span>
          </el-table-column>
          <el-table-column
            prop="address"
            label="操作">
            <span @click="toinserfor(timeId)">预约</span>
          </el-table-column>
        </el-table>
      </div> -->
  </div>
</template>

<script>
  import 'mint-ui/lib/style.min.css'
  import AlertTip from './AlertTip.vue'
  import axios from 'axios'
  import { Toast } from 'mint-ui'
  import { MessageBox } from 'mint-ui'
  import { setCookie,getCookie,delCookie } from '../assets/cookie.js'
  import { DatetimePicker } from 'mint-ui'
  /*Vue.component(DatetimePicker.name, DatetimePicker)*/
  import Bus from '../assets/Bus.js'
    export default {
        name: "insertInfor",
      data(){
        return {
          tableData:[],
          userId: this.userId,
          username:'',
          teachingBuilding:this.teachingBuilding,
          roomNumber:'',
          seatsNumber:'',
          roomLocal:'',
          id:this.id,
          status:'',
          st:'',
          time:'',
          isTrue:'预约',
          textarea: '',
          applyDate:'',
          applyValue:'',
          multimediaEquipment:'',
          // pickerOptions2: {
          //   shortcuts: [{
          //     text: '最近一周',
          //     onClick(picker) {
          //       const end = new Date();
          //       const start = new Date();
          //       start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
          //       picker.$emit('pick', [start, end]);
          //     }
          //   }]
          // },
          pickerOptions1: {
            disabledDate(time) {
              return time.getTime() < Date.now() || Date.now()<time.getTime()-3600*1000*24*7;
            },
          },
          value6: '',
          value7: '',
          value1:'',
          date:'',
          alertShow: false ,// 是否显示提示框
          alertText: '', // 提示文本
        }
      },
      components:{
        AlertTip
      },
      created() {
        this.userId = this.$route.query.userId
        this.id = this.$route.query.id
        console.log(this.userId,this.id)
        axios.get('/api/cls/getRoomDetailByIdAndDate', {
          params: {
            'id': this.id
            // 'id': '1',
            // "date": "20190521"
          }
        }).then((response) => {
          console.log(response.data.data)
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
        })
          .catch(function (err) {
            console.log(err);
          })
      },
      mounted(){
        /*页面挂载获取保存的cookie值，渲染到页面上*/
        let uname = getCookie('userId')
        let userName = getCookie('userName')
        console.log(userName)
        console.log(uname)
        this.userId = uname
        this.username = userName
       
        if(uname === ""){
          this.userId = '登录/注册'
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
        selectList(date){
          var str = new Date(date)
          let urlDate
          if((str.getMonth() + 1)<10){
            urlDate = String(str.getFullYear()) + '0' + String((str.getMonth() + 1)) + String(str.getDate())
          }else{
            urlDate = String(str.getFullYear()) + String((str.getMonth() + 1)) + String(str.getDate())
          }
          this.applyDate = urlDate
          console.log(urlDate)
          axios.get('/api/cls/getRoomDetailByIdAndDate', {
          params: {
            'id': this.id,
            "date": urlDate
          }
        }).then((response) => {
          console.log(response.data.data)
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
        })
          .catch(function (err) {
            console.log(err);
          })
        },
        selectData () { // 打开时间选择器

          if (this.selectedValue) {
            this.dateVal = this.selectedValue
          } else {
            this.dateVal = new Date()
          }
          this.$refs['datePicker'].open()
        },
        dateConfirm () { // 时间选择器确定按钮，并把时间转换成我们需要的时间格式
          this.selectedValue = formatDateMin(this.dateVal)
      },
        openPicker() {
          this.$refs.picker.open();
        },
        open3(index) {
          MessageBox.prompt('请输入理由').then(({ value, action }) => {
            this.tableData[index].bec = value
            var node = document.getElementsByClassName('item-list')[index]
            node.getElementsByTagName('div')[2].innerHTML = value
            console.log(node.getElementsByTagName('div')[2].innerHTML)
            this.applyValue = value
            console.log(value)
            console.log(this.tableData)
            this.$message({
              type: 'success',
              message: '理由: ' + value
              
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });
          // console.log(this.$message)
      },
        open1() {
          Toast({
            message: '预约成功',
            iconClass: 'icon icon-success'
          });
        },
        open2() {
        Toast({
        message: '预约失败',
        iconClass: 'icon icon-success'
        });

        },
        toinserfor:function(status,timeId){
          if(this.userId == '登录/注册'){
            this.showAlert('请登录')
          }else{
             if(status == '空闲' || status == '排队'){
            console.log(timeId)
            let self = this
            if(this.applyValue == ''){
              this.showAlert('请填写理由')
            }else{
              let param = {
                "roomId": this.$route.query.id,
                "date": this.applyDate,
                "timeId": timeId+1,
                "userId": this.userId,
                "userName": this.username,
                "reservationDesc": this.applyValue
              }
                  axios.post( '/api/rsv/insertInfo', param)
                    .then(function (response) {
                      console.log(response.data.message)
                      if(response.data.code===1){
                        // self.open1()
                        self.showAlert(response.data.message)
                        this.isTrue = '已预约'
                      }else{
                        // self.open2()
                      }
                    })
                    .catch(function (err) {
                      console.log(err);
                    })
              }
          }else{
                  this.showAlert('预约失败')
                }
          }
          
        },
        tohome:function () {
          this.$router.push({path:'./getRoom'})
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
  header #title{
    float: left;
    text-align: center;
    font-size: 1.2rem;
    width: 100%;
    height: 1.58rem;
    line-height: 1.68rem;
    margin-bottom: 1rem;
  }
  .block .sele{
    background-color: #ccc;
    display: inline-block;
    height: 30px;
    line-height: 30px;
    width: 50px;
    text-align: center;
    border-radius: 5px;
  }
  p{
    font-weight: 600;
  }
  .item,.item-list{
    display: flex;
    justify-content: space-around;
    margin-bottom: 3px;
  }
  .item-list div:nth-child(3){
    /* width: 30px; */
    height: 4opx;
    border: 1px solid #cccccc;
    overflow: hidden;
    border-radius: 3px;
  }
  .item div{
    width: 25%;
  } 
  .item-list div{
    width: 25%;
  }
</style>

<template>
<div>
  <header>
    <div id="title">我的预约</div>
    <i class="el-icon-arrow-left" @click="tohome"></i>
  </header>
  <p>教室详情</p>
  <el-row :gutter="16">
    <el-col :span="8">教学楼号：</el-col>
    <el-col :span="6">教一</el-col>
  </el-row>
  <el-row :gutter="16">
    <el-col :span="8">教室号：</el-col>
    <el-col :span="6">430</el-col>
  </el-row>
  <el-row :gutter="16">
    <el-col :span="8">教室位置：</el-col>
    <el-col :span="6">东南角</el-col>

  </el-row>
  <el-row :gutter="16">
    <el-col :span="8">座位数：</el-col>
    <el-col :span="6">120</el-col>

  </el-row>
  <el-row :gutter="16">
    <el-col :span="8">教室器材：</el-col>
    <el-col :span="6">投影仪</el-col>

  </el-row><br>
  <span>请选择日期</span>
  <div>
    <div class="block">
      <span class="demonstration">起始日期</span>
      <el-date-picker
        v-model="value7"
        size="mini"
        type="daterange"
        align="right"
        unlink-panels
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :picker-options="pickerOptions2">
      </el-date-picker>
    </div>
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column
          prop="date"
          label="时间"
          width="80">
        </el-table-column>
        <el-table-column
          prop="name"
          label="状态"
          width="100">
        </el-table-column>
        <el-table-column
          prop="address"
          label="操作">
        </el-table-column>
      </el-table>
    </div>
  </div>

</div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }],
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
          value7: ''
        }
      },
    created () {
      let userId = this.$route.query.userId
      this.userId = userId
      console.log(this.userId)
    },
    methods:{
      tohome:function () {
        this.$router.push({path:'./#'})
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
</style>

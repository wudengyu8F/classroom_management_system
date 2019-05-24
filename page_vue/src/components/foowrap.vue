<template>
  <div class="footer-warp">
    <router-link :to="{path: '/#', query: {'uid': this.uId}}">
      <i ref="home" class="el-icon-menu"></i>
      <span>主页面</span>
    </router-link>
    <router-link :to="{path: '/getRoom', query: {'uid': this.uId}}" @click="getRoom">
      <div @click="getRoom">
        <i class="el-icon-date"></i>
        <span>教室查询</span>
      </div>
    </router-link>
    <router-link :to="{path: '/choice', query: {'uid': this.uId}}">
      <i class="el-icon-edit-outline"></i>
      <span>预约/审批</span>
    </router-link>
    <router-link :to="{path: '/mine', query: {'uid': this.uId}}">
      <i class="el-icon-star-off"></i>
      <span>我的</span>
    </router-link>
  </div>
</template>

<script>
  export default {
    name: "footer",
    data(){
      return{

      }
    },
    methods:{

    }
  }
</script>

<style scoped>
  .router-link-active{
    transform-style: preserve-3d;
    transform: rotateX(30deg);
    transform-origin: center center;
  }
  .footer-warp{
    display: flex;
    position: fixed;
    width: 100%;
    /*overflow: hidden;*/
    height: 50px;
    bottom: 65px;
    background-color: #fff;
    background-image: url("../assets/home.jpg");
  }
  .footer-warp a{
    display: block;
    flex: 1;
    text-align: center;
    padding-top: 0.16rem;
    font-size: 1.5rem;
  }
  .footer-warp>a i{
    margin-bottom: 5px;
    color: rgb(64,158,255);
  }
  .footer-warp>a span{
    font-size: 16px;
    display: block;
    color: rgb(64,158,255);
  }
</style>

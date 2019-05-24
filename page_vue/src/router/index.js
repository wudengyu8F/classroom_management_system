import Vue from 'vue'
import Router from 'vue-router'
import getApprovalDetail from '../components/getApprovalDetail'
import getRoom from '../components/getRoom'
import getRoomDetailByIdAndDate from '../components/getRoomDetailByIdAndDate'
import home from '../components/home'
import insertInfor from '../components/insertInfor'
import login from '../components/login'
import register from '../components/register'
import foowrap from  '../components/foowrap'
import mine from '../components/mine'
import father from '../components/father'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/getApprovalDetail',
      name: 'getApprovalDetail',
      component: getApprovalDetail
    },
    {
      path: '/father',
      name: 'father',
      component: father
    },
    {
      path: '/getRoom',
      name: 'getRoom',
      component: getRoom
    },
    {
      path: '/getRoomDetailByIdAndDate',
      name: 'getRoomDetailByIdAndDate',
      component:getRoomDetailByIdAndDate
    },
    {
      path: '/',
      name: 'home',
      component: home
    },
    {
      path: '/insertInfor',
      name: 'insertInfor',
      component: insertInfor
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path:'/foowrap',
      name:'/foowrap',
      component: foowrap
    },
    {
      path:'/mine',
      name:'/mine',
      component:mine
    }

  ]
})

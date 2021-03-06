import UserApi from '@/apis/UserApi'
import SNSApi from '@/apis/SNSApi'
import router from '../main'
const axios = require('axios').default
 /* eslint-disable no-unused-vars */
// initial state
const state = {
  JWT : localStorage.getItem('JWT'), // 새로고침해도 토큰값유지하기위함
  userInfo : {}, // user 프로필 사진, 이름, 닉네임 등 
  config : {},
  seq: 0,
  togosite:'',
}

// getters
const getters = {
  isLogin: function(state){
    var JWT = state.JWT
    if(JWT==null){
      return false
    }else{
      return true
    }
  }
}

// actions
const actions = {
  login({ commit },data){
    return UserApi.requestLogin(data,res=>{
      if(res.data.state==="ok"){
        commit('loginSuccess', res.data.JWT)
      }else{
        console.log(`로그인 실패 : ${res.data.message}`)
        // alert(res.data)
      }
    },error=>{
      console.log(`로그인 오류 ${error}`)
    })
  },

  logout({ commit }){
    axios.defaults.headers.common['Authorization'] = undefined
    axios.interceptors.request.eject();
    commit('logoutSuccess')
  },
  getUserInfo({ commit }){
    UserApi.requestUserInFo(res=>{
      if (res.data.state === 'ok'){
        commit('setUserInfo', res.data.message)
      }else{
        console.log(`유저 정보 조회 실패 : ${res.data.message}`)
      }
    },error=>{
      // 백엔드가 꺼져있을 경우:
      alert(`현재 사이트 접속이 불가합니다. 관리자에게 문의해주세요`)
      console.log(error)
    })
  },
  SNSLogin({ commit }, data){
    // console.log('데이터 잘 왔다',data.code)
    SNSApi[`${data.togosite}`]({code:data.code},
      res=>{
        if(res.data.state ==='ok'){
        // 로그인 된 경우
        commit('loginSuccess',res.data.JWT)
        }else{
          if (res.data.seq !== undefined){
          //코드와 togosite를 저장함
          commit('setSNSseq', {togosite:data.togosite, seq:res.data.seq})
          }else{
            //코드가 틀림
            console.log(res.data)
          }  
        }
      },err=>{
        console.log(err)
      })
    // 데이터가 토큰인 경우 loginSuccess로 
  },
  Join({ commit },data){
    UserApi.requestSignup(data,
      res=>{
        if(res.data.state === 'ok'){
          alert('회원가입에 성공하셨습니다.')
          router.replace({name:'Login'})
        }else{
          // 중복 ID인경우 알람 띄우기
          alert(res.data.message)
          console.log(res)
        }
      },err=>{
        console.log(err)
      })
  },
  SNSJoin({commit}, data){
    data['seq'] = state.seq
    SNSApi.requestSNSJoin(data,
      res=>{
        if(res.data.state === 'ok'){
          router.push({name:'Login'})
        }else{
          console.log(res.data)
        }
      },err=>{
        console.log(err)
      })
  }

}
// mutations
const mutations = {
  loginSuccess(state, JWT){
    state.JWT = JWT
    localStorage.JWT = JWT
    // const accessToken= function(){
    //   const {JWT} = localStorage.JWT
    //   if (!JWT) return
    //   axios.defaults.headers.common['Authorization'] = `Bearer ${JWT}`;
    // }
    // accessToken()
    router.push({name:'Home'})
  },
  logoutSuccess(state){
    state.JWT = null
    delete localStorage.JWT;
  },
  setUserInfo(state, data){
    state.userInfo = data
    // console.log('성공', state.userInfo)
  },
  setSNSseq(state, data){
    state.seq = data.seq
    state.togosite = data.togosite
    console.log(`시퀀스 : ${data.seq} togosite 저장`)
    router.push({name:'Join'})
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
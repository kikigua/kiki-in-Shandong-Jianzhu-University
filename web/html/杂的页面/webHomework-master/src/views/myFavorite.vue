<template>
  <div>
    <!--    头部导航部分-->
    <header-nav></header-nav>
    <!--    头部轮播部分-->
    <head-banner></head-banner>
    <!--    主要的区域-->
    <div class="main-container">
      <div class="nav-left">
        <ul>
          <li class="active">
            <a class="nav-header"><i class="iconfont icon-ali-clould" @click="goToHome()"></i>综合资源</a>
            <div style="margin-top: 20px">
              <el-button type="info" @click="goToFavorite()">我的模块</el-button>

            </div>
            <div style="margin-top: 20px">
              <el-button type="success" @click="addItem()">创建模块</el-button>
            </div>
          </li>
        </ul>
      </div>
      <div class="nav-phone-menu iconfont icon-menu"></div>
      <div class="nav-phone-bg"></div>
      <div class="nav-right" data-url="/visit-nav/">
        <div id="custom-nav-container">
        </div>
        <div class="nav-list">
          <h2 id="近期更新" style="color: orange">收藏模块</h2>
          <div v-for="(item,index) in articleList" :key="index">
            <myBox :item="item"></myBox>
          </div>
        </div>
        <div class="nav-list">
          <h2 id="资源搜索">自定义模块</h2>
          <div v-for="(item,index) in ownCategoryList" :key="index">
            <favorite-box :items="item"></favorite-box>
          </div>
        </div>
      </div>
    </div>
    <!--    底部的区域-->
    <myFoots></myFoots>
    <tools></tools>
    <boottom-tips></boottom-tips>
    <!--    更新与修改的模块-->
    <class-add></class-add>
    <class-update></class-update>
  </div>
</template>

<script>
import myBox from "@/components/box/myBox";
import myFoots from "@/components/home/myFoots";
import tools from "@/components/home/tools";
import boottomTips from "@/components/home/boottomTips";
import headBanner from "@/components/home/headBanner";
import headerNav from "@/components/home/headerNav";
import {getAllFavorites, getOwnCategory, setFavorite} from "@/api/serveArticle";
import ClassAdd from "@/components/article/classAdd";
import FavoriteBox from "@/components/box/favoriteBox";
import ClassUpdate from "@/components/article/classUpdate";

export default {
  name: "myMain",
  components: {ClassUpdate, FavoriteBox, ClassAdd, myBox, myFoots, tools, boottomTips, headBanner, headerNav},
  data() {
    return {
      articleList: [],
      ownCategoryList: []
    }
  },
  methods: {
    goToFavorite() {
      //页面的跳转
      this.$router.push('/myFavorite')
    },
    goToHome() {
      this.$router.push('/')
    },
    addItem() {
      //事件的触发
      this.$bus.$emit('classAddOpen')//0代表的普通模块
    }
  },
  beforeCreate() {
    getAllFavorites().then(res => {
      this.articleList = res.data.data
    })
    getOwnCategory().then(res => {
      this.ownCategoryList = res.data.data
    })
  }
}
</script>
<style>
@font-face {
  font-family: "iconfont"; /* Project id 1671397 */
  src: url('//at.alicdn.com/t/font_1671397_uamnv3a12b.woff2?t=1621159532939') format('woff2'),
  url('//at.alicdn.com/t/font_1671397_uamnv3a12b.woff?t=1621159532939') format('woff'),
  url('//at.alicdn.com/t/font_1671397_uamnv3a12b.ttf?t=1621159532939') format('truetype');
}

.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-collection:before {
  content: "\e79f";
}

.icon-huojian:before {
  content: "\e68b";
}

.icon-ali-clould:before {
  content: "\e748";
}

.icon-Top:before {
  content: "\e769";
}

.icon-menu:before {
  content: "\e7f4";
}

.icon-close:before {
  content: "\e747";
}

.icon-arrow-down:before {
  content: "\e7b2";
}

.icon-comments:before {
  content: "\e7b4";
}

.icon-search:before {
  content: "\e7b5";
}

select:focus {
  outline: 0
}

button {
  outline: 0
}

li, ul {
  list-style: none;
  padding: 0;
  margin: 0;
  box-sizing: border-box
}

a {
  text-decoration: none
}

input {
  color: #000;
  box-shadow: none !important;
  outline: 0 !important;
  border: 0;
  -webkit-appearance: none
}

input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
  color: #6c7e9a !important
}

input:-moz-placeholder, textarea:-moz-placeholder {
  color: #6c7e9a !important
}

input::-moz-placeholder, textarea::-moz-placeholder {
  color: #6c7e9a !important
}

input:-ms-input-placeholder, textarea:-ms-input-placeholder {
  color: #6c7e9a !important
}

input::-ms-clear, input::-ms-reveal {
  display: none
}

* {
  -webkit-tap-highlight-color: transparent;
  -webkit-appearance: none
}

h1, h2, h3, h4, h5, h6 {
  font-weight: 400;
  margin: 0
}

p {
  margin: 0
}

.global-tips {
  z-index: 666666;
  position: fixed;
  top: 88px;
  left: 50%;
  transform: translateX(-50%);
  box-sizing: border-box;
  width: 92%;
  max-width: 600px;
  min-height: 98px;
  padding: 16px 40px 26px 76px;
  color: #fff;
  font-size: 18px;
  line-height: 1.5;
  border-radius: 4px;
  overflow: hidden
}

.global-tips-icon {
  position: absolute;
  font-size: 56px !important;
  line-height: 100%;
  top: calc(50% - 33px);
  left: 12px;
  color: rgba(255, 255, 255, .9)
}

.global-tips-warning .global-tips-icon {
  transform: rotate(-180deg)
}

.global-tips-close {
  position: absolute;
  font-size: 24px !important;
  top: 2px;
  right: 8px;
  color: rgba(255, 255, 255, .8);
  cursor: pointer
}

.global-tips-des {
  min-height: 56px;
  display: flex;
  align-items: center;
  word-break: break-all
}

.global-tips-process, .global-tips-process-body {
  height: 10px;
  width: 100%;
  background-color: rgba(255, 255, 255, .4);
  position: absolute;
  bottom: 0;
  left: 0
}

.global-tips-process {
  z-index: 666668;
  animation: global-tips-process 6s infinite;
  -webkit-animation: global-tips-process 6s infinite
}

@keyframes global-tips-process {
  from {
    left: 100%
  }
  to {
    left: 0
  }
}

@-webkit-keyframes global-tips-process {
  from {
    left: 100%
  }
  to {
    left: 0
  }
}

.global-tips-info, .global-tips-info .global-tips-process {
  background-color: #1d74f5
}

.bottom-fixed-tips {
  position: fixed;
  box-sizing: border-box;
  width: 100%;
  bottom: 0;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  z-index: 3
}

.bottom-fixed-tips-container {
  width: 92%;
  max-width: 1300px;
  margin: 0 auto;
  text-align: center;
  font-size: 0;
  padding: 12px 0;
  overflow: hidden;
  position: relative
}

.bottom-fixed-tips .icon-huojian {
  color: #fff;
  font-size: 24px;
  display: inline-block;
  height: 32px;
  line-height: 32px;
  vertical-align: middle
}

.bottom-fixed-tips .bottom-fixed-tips-text {
  display: inline-block;
  color: #fff;
  font-size: 18px;
  font-weight: 700;
  vertical-align: middle;
  margin-left: 8px
}

.bottom-fixed-tips .bottom-fixed-tips-btn {
  display: inline-block;
  height: 32px;
  width: 90px;
  background-color: #1d74f5;
  color: #fff;
  vertical-align: middle;
  margin-left: 16px;
  border-radius: 4px;
  line-height: 32px;
  text-align: center;
  font-size: 16px
}

.bottom-fixed-tips .bottom-fixed-tips-close {
  position: absolute;
  right: 0;
  top: 12px;
  font-size: 20px;
  color: rgba(255, 255, 255, .7);
  cursor: pointer
}

@media screen and (max-width: 440px) {
  .bottom-fixed-tips .bottom-fixed-tips-btn {
    margin: 12px 0 12px 0
  }
}

header {
  z-index: 10;
  position: fixed;
  top: 0;
  width: 100%
}

nav {
  width: 92%;
  max-width: 1300px;
  margin: auto;
  height: 72px
}

.logo {
  float: left;
  height: 40px;
  margin-top: 16px
}

.logo img {
  height: 100%
}

.site-name {
  float: left;
  margin: 20px 48px 0 8px
}

.site-name-en {
  margin: 0;
  line-height: 16px;
  color: #fff;
  font-size: 16px;
  font-weight: 700;
  letter-spacing: 1px
}

.site-name-zh {
  margin: 6px 0 0 0;
  line-height: 12px;
  color: rgba(255, 255, 255, .6);
  font-size: 12px;
  font-weight: lighter
}

.nav-item {
  float: left;
  position: relative
}

.nav-user {
  float: right
}

.nav-item > a {
  display: block;
  line-height: 72px;
  padding: 0 16px;
  color: #fff;
  cursor: pointer
}

.nav-register a {
  box-sizing: border-box;
  border-radius: 20px;
  line-height: 32px;
  margin-top: 20px;
  background-color: rgba(255, 255, 255, .3);
  text-align: center
}

.nav-drop {
  display: none;
  position: absolute;
  top: 52px;
  width: 160px;
  padding: 0 16px;
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 0 8px rgba(10, 22, 41, .12)
}

.nav-drop a {
  display: block;
  line-height: 48px;
  color: #0a1629;
  cursor: pointer;
  position: relative
}

.nav-line {
  border-top: 1px solid #dde4f0
}

.nav-item a:hover {
  color: #1d74f5
}

.nav-register a:hover {
  background-color: #1d74f5;
  color: #fff !important
}

.user-head > a {
  box-sizing: border-box;
  padding: 20px 0;
  height: 72px;
  line-height: 32px;
  position: relative
}

.user-head > a > img {
  height: 100%;
  border-radius: 50%
}

.user-head span {
  display: block;
  width: 12px;
  height: 12px;
  border-radius: 50%;
  background-color: #ff5050;
  position: absolute;
  top: 20px;
  right: 0
}

nav .user-head:hover .nav-drop {
  right: 0;
  top: 60px
}

.nav-item:hover .nav-drop {
  display: block
}


.active-header a, .active-header h2, .white-header .nav-item > a, .white-header h2 {
  color: #0a1629
}

.white-header .nav-register > a {
  border: 2px solid #1d74f5;
  color: #1d74f5;
  margin-top: 18px
}

.white-header .nav-register > a:hover {
  color: #fff
}

.white-header a:hover {
  color: #1d74f5
}

nav > .icon-menu {
  display: none;
  font-size: 26px;
  color: #fff;
  line-height: 72px;
  float: right;
  cursor: pointer
}

.white-header .icon-menu {
  color: #0a1629
}

.active-header nav {
  background-color: #fff;
  width: 100%;
  height: auto
}

.active-header ul {
  display: block
}

.active-header nav > .icon-menu {
  display: none
}

.active-header .nav-item {
  float: none
}

.active-header .nav-item > a {
  padding: 0 4%;
  line-height: 56px
}

.active-header .nav-register > a {
  padding: 0 !important;
  width: 92%;
  line-height: 40px;
  margin: 0 auto;
  border: 1px solid #1d74f5;
  color: #1d74f5
}

.active-header .nav-user > a {
  text-align: center
}

.active-header .user-head span {
  top: 0;
  right: calc(50% - 36px)
}

.active-header .nav-drop {
  display: none;
  position: static;
  width: 100%;
  padding: 0 6%;
  border-radius: 0;
  box-shadow: none
}

.phone-nav {
  display: none
}

.active-header > nav > a {
  display: none
}

.active-header .phone-nav {
  display: block;
  position: fixed;
  left: 0;
  top: 0;
  box-sizing: border-box;
  width: 100%;
  padding: 0 4%;
  background-color: #fff;
  box-shadow: 0 4px 16px rgba(10, 22, 41, .16)
}

.active-header .nav-item:hover .nav-drop {
  display: none
}

.phone-nav .icon-close {
  font-size: 32px;
  color: #0a1629;
  line-height: 72px;
  float: right;
  cursor: pointer
}

.big-banner {
  height: 400px;
  text-align: center;
  box-sizing: border-box;
  padding-top: 160px;
  position: relative;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center
}

.big-banner::before {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .3);
  left: 0;
  top: 0;
  z-index: 1
}

.big-banner h1 {
  font-weight: 600;
  font-size: 48px;
  color: #fff;
  line-height: 1.5;
  letter-spacing: 0;
  position: relative;
  z-index: 2;
  margin: 0 0 20px
}

.big-banner p {
  font-size: 20px;
  color: #fff;
  line-height: 1.5;
  letter-spacing: 0;
  position: relative;
  z-index: 2;
  font-weight: 400;
  margin-top: 0
}

.main-container {
  max-width: 1300px;
  margin: 63px auto 80px;
  width: 90%;
  overflow: hidden;
  box-sizing: border-box
}

.article-search button {
  float: right;
  display: block;
  border-radius: 4px;
  font-size: 20px;
  line-height: 38px;
  text-align: center;
  width: 48px;
  background-color: #1d74f5;
  cursor: pointer;
  transition: .3s;
  color: #fff;
  border: none
}

.select-box i {
  float: right
}

.select-list a {
  display: block;
  line-height: 48px;
  color: #0a1629
}

.select-list a:hover {
  color: #1d74f5
}

.article-sort:hover .select-list {
  display: block
}

.article-recommend h4 {
  font-size: 18px;
  line-height: 135%;
  overflow: hidden;
  letter-spacing: 1px;
  margin: 0 0 20px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2
}

.article-recommend .recommend-list > * + * {
  margin-top: 32px
}

.recommend-item div {
  border-radius: 4px;
  overflow: hidden;
  width: 80px;
  height: 80px;
  float: left;
  margin-right: 16px
}

.recommend-item img {
  width: 100%;
  height: 100%;
  object-fit: cover
}

.recommend-item h5 {
  display: block;
  font-size: 16px;
  line-height: 150%;
  color: #0a1629;
  margin-bottom: 8px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  height: 48px
}

.recommend-item p {
  color: #6c7e9a;
  font-size: 12px;
  line-height: 19px
}

.recommend-item:hover h5 {
  color: #1d74f5
}

.recommend-item div:hover img {
  transform: scale(1.2);
  transition: all .6s ease 0s;
}

.wechat-recommend img {
  display: block;
  width: 100%
}

.wechat-recommend span {
  line-height: 135%;
  letter-spacing: 1px;
  display: block;
  margin-top: 16px
}

.wechat-recommend p {
  font-size: 14px;
  color: #6c7e9a;
  line-height: 175%;
  margin-top: 12px
}

.search-title b {
  color: #1d74f5
}

.article-main-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition-duration: .4s
}

.article-des h2 {
  font-size: 20px;
  color: #0a1629
}

.article-des p {
  color: #6c7e9a;
  margin: 20px 0 30px;
  min-height: 163px;
  line-height: 24px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 7;
  -webkit-box-orient: vertical
}

.article-item:nth-of-type(1) .article-des p {
  font-size: 14px;
  line-height: 24px;
  min-height: 120px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  overflow: hidden;
  text-overflow: ellipsis;
  margin: 8px 0 16px
}

.article-main-img:hover img {
  transform: scale(1.08);
  transition: all .6s ease 0s;
}

.pagination li {
  display: inline-block;
  margin: 0 10px
}

.pagination a {
  display: block;
  line-height: 38px;
  width: 38px;
  border: 1px solid #d8e0eb;
  border-radius: 4px;
  color: #8c8889;
  font-size: 18px;
  transition: .3s
}

.pagination a:hover {
  color: #1d74f5;
  border-color: #1d74f5
}

.footer {
  padding: 56px 0 40px;
  background-color: #f3f5f8
}

.footer-content {
  width: 92%;
  max-width: 1300px;
  text-align: left;
  margin: auto
}

.left-footer, .right-footer {
  font-size: 14px;
  color: #6c7e9a;
  display: inline-block;
  text-align: left;
  vertical-align: top;
  box-sizing: border-box
}

.left-footer {
  width: 32%
}

.right-footer {
  width: 64%
}

.right-footer h6 {
  font-size: 20px;
  font-weight: 400;
  color: #0a1629
}

.right-footer p {
  margin-top: 16px
}

.left-footer a, .right-footer a {
  color: #6c7e9a
}

.left-footer span {
  display: block;
  margin-top: 16px
}

.side-tool {
  position: fixed;
  z-index: 10;
  right: 16px;
  bottom: 60px
}

.side-item {
  cursor: pointer;
  width: 40px;
  height: 40px;
  margin-top: 8px;
  background-color: #fff;
  border-radius: 4px;
  text-align: center;
  box-shadow: 0 0 8px rgba(10, 22, 41, .12);
  position: relative
}

.side-item .iconfont {
  line-height: 40px;
  margin: 0 auto;
  font-size: 24px;
  color: #6c7e9a
}

.side-tool-tooltips {
  display: none;
  position: absolute;
  top: 0;
  right: 56px;
  width: 200px;
  padding: 12px;
  background-color: #0a1629;
  color: #fff;
  border-radius: 4px
}

.side-tool-tooltips::before {
  content: ' ';
  display: block;
  border-right: 12px solid transparent;
  border-left: 12px solid #0a1629;
  border-top: 12px solid transparent;
  border-bottom: 12px solid transparent;
  width: 0;
  height: 0;
  position: absolute;
  top: 8px;
  right: -24px;
  z-index: 3
}

.side-tool-contact {
  display: none;
  position: absolute;
  bottom: 0;
  right: 40px;
  width: 280px;
  padding-right: 16px;
  cursor: default
}

.side-tool-contact-box {
  padding: 16px;
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 0 8px rgba(10, 22, 41, .12);
  overflow: hidden
}

.side-tool-contact-title {
  text-align: center;
  font-size: 18px;
  font-weight: 700
}

.side-tool-contact-qrcode {
  display: block;
  width: 180px;
  margin: 12px auto 12px
}

.side-tool-contact-des {
  margin-bottom: 20px
}

.side-tool-contact-des span {
  color: #1d74f5
}

.side-tool-contact-contact {
  border-top: 1px solid #dde4f0;
  padding: 16px 0 0;
  text-align: left;
  line-height: 2;
  font-size: 14px
}

.side-item:hover {
  background-color: #1d74f5
}

.side-item:hover .iconfont {
  color: #fff
}

.side-item:hover .side-tool-contact, .side-item:hover .side-tool-tooltips {
  display: block
}

#article-content p {
  font-size: 16px !important;
  line-height: 2.25 !important;
  color: #0a1629 !important;
  margin: 20px 0 !important
}

#article-content div {
  font-size: 16px
}

#article-content h1, .article-detail > h1 {
  font-size: 36px;
  font-weight: 700 !important;
  line-height: 150%
}

#article-content h2 {
  font-size: 28px;
  line-height: 1.5;
  font-weight: 700 !important;
  margin: 46px 0 1em !important
}

#article-content h3 {
  font-size: 20px;
  font-weight: 700 !important;
  margin: 30px 0 0 0 !important;
  line-height: 1.5
}

#article-content h4 {
  font-size: 16px;
  margin: 30px 0 10px 0 !important;
  font-weight: 600 !important;
  line-height: 1.5
}

#article-content h5, #article-content h6 {
  line-height: 1.5;
  margin: 0 0 1.6em
}

#article-content ul {
  padding-left: 57px
}

#article-content li {
  font-size: 16px;
  line-height: 30px;
  margin: 10px 0 10px;
  list-style: disc
}

#article-content ol {
  list-style: decimal
}

#article-content ol li {
  display: list-item !important;
  list-style: decimal;
  list-style-position: outside
}

#article-content img {
  display: block;
  max-width: 100% !important;
  height: auto !important;
  margin: 30px auto 50px !important
}

#article-content table {
  word-break: break-all
}

#article-content hr {
  background-color: #d8e0eb;
  height: 1px;
  border: none;
  margin: 56px 0
}

#article-content a {
  color: #1d74f5;
  transition: .3s
}

#article-content a:hover, .big-window .window-text a:hover {
  text-decoration: underline
}

#article-content video {
  display: block;
  max-width: 100% !important;
  height: auto !important;
  margin: 30px auto 50px !important
}

#article-content table {
  box-sizing: border-box;
  width: 100%;
  border-collapse: collapse
}

#article-content td, #article-content tr {
  box-sizing: border-box;
  border: 1px solid #d8e0eb;
  white-space: normal
}

#article-content td {
  padding: 12px 12px
}

#article-content tr:nth-of-type(2n+1) {
  background-color: #f3f5f8
}

#resource span {
  font-size: 20px;
  font-weight: 700 !important;
  margin: 30px 0 0 0 !important;
  line-height: 1.5
}

#resource p {
  font-size: 16px !important;
  line-height: 1.8 !important;
  color: #0a1629 !important;
  margin: 20px 0 !important
}

.nav-left {
  float: left;
  width: 190px;
  overflow-y: auto;
  padding: 0 30px 33px 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box
}

.nav-left > ul {
  padding: 0 0 32px;
  border-bottom: 1px solid #dde4f0
}

.nav-header {
  display: block;
  height: 40px;
  line-height: 38px;
  color: #0a1629;
  box-sizing: border-box;
  padding: 0 20px;
  border-radius: 4px;
  cursor: pointer;
  overflow: hidden
}

.nav-header i {
  font-size: 20px;
  margin-right: 10px
}

.active > a {
  background: #e8f1fe;
  color: #1d74f5
}

.nav-body {
  display: none
}

.nav-left .active .nav-body {
  display: block
}

.nav-body li {
  overflow: hidden
}

.nav-body a {
  display: block;
  height: 24px;
  margin-top: 20px;
  box-sizing: border-box;
  padding-left: 30px;
  font-size: 14px;
  color: #0a1629;
  line-height: 24px
}

.nav-body a:hover {
  color: #1d74f5
}

.nav-left > ul > li + li {
  margin-top: 16px
}

.main-container .we-share {
  display: block;
  box-sizing: border-box;
  width: 100px;
  border-radius: 4px;
  background-color: #1d74f5;
  color: #fff;
  line-height: 40px;
  margin: 24px auto 0;
  text-align: center;
  font-size: 14px
}

.nav-phone-menu {
  display: none;
  position: fixed;
  z-index: 4;
  right: 10px;
  bottom: 10px;
  -webkit-transition: .3s;
  -o-transition: .3s;
  transition: .3s;
  width: 48px;
  height: 48px;
  border-radius: 100%;
  background-color: #1d74f5;
  color: #fff;
  line-height: 48px;
  text-align: center;
  font-size: 64px;
  box-shadow: 0 0 8px rgba(10, 22, 41, .12);
  cursor: pointer
}

.nav-phone-bg {
  display: none;
  width: 100%;
  z-index: 12;
  background: rgba(0, 0, 0, .5);
  position: fixed;
  left: 0;
  top: 0;
  height: 100%
}

.nav-right {
  position: relative;
  z-index: 2;
  float: right;
  width: 80%;
  max-width: 1000px
}

.nav-list {
  overflow: hidden;
  margin-bottom: 30px
}

.nav-list h2 {
  position: relative;
  font-size: 18px;
  line-height: 28px;
  padding-left: 16px;
  margin-bottom: 24px
}

.nav-list h2::before {
  position: absolute;
  width: 3px;
  height: 16px;
  background: #1d74f5;
  content: '';
  left: 0;
  top: 50%;
  margin-top: -8px
}

.nav-box {
  float: left;
  width: 31.136%;
  position: relative;
  box-sizing: border-box;
  overflow: hidden;
  margin: 0 2% 34px 0;
  border: 1px solid #f0f0f0;
  border-radius: 4px;
  padding: 16px;
  -webkit-transition: .3s;
  -o-transition: .3s;
  transition: .3s
}

/*.nav-box:nth-child(3n) {*/
/*  margin-right: 0*/
/*}*/
.nav-box img {
  display: block;
  float: left;
  width: 48px;
  border-radius: 50%
}

.nav-des {
  float: left;
  width: calc(100% - 64px);
  margin-left: 16px
}

.custom-nav-box .nav-des, .nav-box h3 {
  font-size: 18px;
  font-weight: 600;
  height: 24px;
  line-height: 24px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden
}

.nav-box p {
  margin: 4px 0 8px 0;
  font-size: 14px;
  color: #6c7e9a;
  height: 60px;
  line-height: 20px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden
}

.nav-skill, .nav-visit {
  position: relative;
  float: right;
  width: 86px;
  height: 30px;
  font-size: 14px;
  text-align: center;
  line-height: 30px;
  border: 1px solid #dde4f0;
  border-radius: 4px;
  color: #0a1629;
  cursor: pointer;
  margin-left: 16px
}

.nav-visit i {
  position: absolute;
  top: -8px;
  right: 1px;
  font-size: 20px;
  color: #6c7e9a
}

.nav-box:hover {
  transform: translateY(-8px);
  -webkit-transform: translateY(-8px);
  -moz-transform: translateY(-8px);
  box-shadow: 0 16px 32px rgba(10, 22, 41, .12);
  -webkit-box-shadow: 0 16px 32px rgba(10, 22, 41, .12);
  -moz-box-shadow: 0 16px 32px rgba(10, 22, 41, .12);
  -webkit-transition: all .3s ease;
  -moz-transition: all .3s ease;
  -o-transition: all .3s ease;
  transition: all .3s ease;
  cursor: default
}

.nav-box:hover .nav-visit {
  border-color: #1d74f5;
  color: #1d74f5
}

.nav-box:hover .nav-skill {
  background-color: #1d74f5;
  color: #fff
}

.custom-nav-box img {
  width: 28px !important;
  height: 28px !important
}

.custom-nav-box .nav-des {
  color: #0a1629;
  height: 28px;
  line-height: 28px;
  width: calc(100% - 40px);
  margin-left: 12px
}

.site-icon img {
  max-width: 100%;
  height: auto;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate3d(-50%, -50%, 0);
  -webkit-transform: translate3d(-50%, -50%, 0)
}

.site-delete-tips a {
  color: #1d74f5
}

.site-delete-tips span {
  color: #ff5050;
  font-weight: 700
}

.more-site h2::before {
  height: 24px;
  width: 4px;
  margin-top: -12px
}

.more-site li {
  width: 48%;
  margin: 0 4% 34px 0 !important
}

.more-site li:nth-child(2n) {
  margin-left: 0 !important;
  margin-right: 0 !important
}

.window-title .icon-close {
  float: right;
  font-size: 24px;
  line-height: 48px;
  margin-right: 12px;
  cursor: pointer
}

.window-select .select-list li {
  display: block;
  line-height: 48px;
  cursor: pointer
}

.window-check input {
  -webkit-appearance: checkbox;
  text-align: center;
  margin-right: 6px;
  cursor: pointer
}

.window-check input:after {
  width: 100%;
  height: 100%;
  line-height: 14px;
  top: 0;
  content: " ";
  background-color: #fff;
  display: inline-block;
  visibility: visible;
  border-radius: 2px;
  border: 1px solid #0a1629
}

.window-check input:checked:after {
  content: "\2713";
  font-size: 12px;
  font-weight: 700;
  color: #fff;
  background-color: #1d74f5;
  border: 1px solid #1d74f5
}

.big-window .window-title .icon-close {
  line-height: 60px;
  color: rgba(255, 255, 255, .5)
}

.big-window .window-text img {
  width: 100%
}

.big-window .window-text a {
  color: #1d74f5
}

input:focus {
  border-color: #1d74f5
}

.login-register a, .login-tips a {
  color: #1d74f5
}

@keyframes ldio-53faqx9iix9-o {
  0% {
    opacity: 1;
    transform: translate(0 0)
  }
  49.99% {
    opacity: 1;
    transform: translate(40px, 0)
  }
  50% {
    opacity: 0;
    transform: translate(40px, 0)
  }
  100% {
    opacity: 0;
    transform: translate(0, 0)
  }
}

@keyframes ldio-53faqx9iix9 {
  0% {
    transform: translate(0, 0)
  }
  50% {
    transform: translate(40px, 0)
  }
  100% {
    transform: translate(0, 0)
  }
}

.ldio-53faqx9iix9 div {
  position: absolute;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  top: 30px;
  left: 10px
}

.ldio-53faqx9iix9 div:nth-child(1) {
  background: #1d74f5;
  animation: ldio-53faqx9iix9 1s linear infinite;
  animation-delay: -.5s
}

.ldio-53faqx9iix9 div:nth-child(2) {
  background: #ff5050;
  animation: ldio-53faqx9iix9 1s linear infinite;
  animation-delay: 0s
}

.ldio-53faqx9iix9 div:nth-child(3) {
  background: #1d74f5;
  animation: ldio-53faqx9iix9-o 1s linear infinite;
  animation-delay: -.5s
}

.ldio-53faqx9iix9 div {
  box-sizing: content-box
}

.parse-thumbnail img {
  width: 100%;
  height: auto
}

.douyin-music-des a {
  color: #1d74f5
}

.douyin-music-list th {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis
}

.douyin-music-list thead tr {
  background-color: #f3f5f8
}

.douyin-music-list thead tr th {
  padding: 8px 0
}

.douyin-music-list tbody th {
  box-sizing: border-box;
  padding: 16px 16px;
  font-weight: 400;
  border-bottom: 1px solid #d8e0eb
}

.douyin-music-list .music-name img {
  display: inline-block;
  width: 64px;
  border-radius: 8px;
  overflow: hidden;
  margin-right: 16px
}

.douyin-music-list .music-name div {
  display: inline-block;
  line-height: 64px;
  vertical-align: top
}

.douyin-music-list .douyin-video img {
  width: 48px;
  border-radius: 8px;
  overflow: hidden
}

.douyin-music-list .douyin-video a {
  margin-right: 8px
}

.douyin-music-list .douyin-video a:last-child {
  margin-right: 0
}

.super-search-container h1 {
  font-weight: 600;
  font-size: 48px;
  line-height: 1.5;
  margin: 0 0 20px;
  text-align: center
}

.super-search-container p {
  font-size: 20px;
  line-height: 1.5;
  font-weight: 400;
  text-align: center
}

.super-search-hot-box a {
  color: #fff;
  cursor: pointer
}

.super-search-hot-box a:hover {
  color: #1d74f5;
  text-decoration: underline
}

.super-search-hot-box .iconfont {
  color: #ff5050
}

.super-search-source span {
  font-size: 16px;
  display: inline-block;
  margin-right: 8px
}

.super-search-source a {
  color: #0a1629;
  line-height: 48px;
  display: inline-block;
  font-size: 16px;
  margin-right: 32px
}

.super-search-source a:hover {
  color: #1d74f5;
  text-decoration: none
}

.free-movie-guide-item img {
  display: block;
  max-width: 100%;
  margin: 0 auto
}

.free-movie-guide-item div {
  margin: 24px auto 0
}

.free-movie-site a {
  display: inline-block;
  vertical-align: top;
  width: 160px;
  height: 56px;
  line-height: 56px;
  text-align: center;
  margin-left: 2%
}

@media (max-width: 1168px) {
  .douyin-music-list .douyin-video img {
    width: 40px
  }
}

@media (max-width: 950px) {
  .douyin-music-list .music-name img {
    width: 40px
  }

  .douyin-music-list .music-name div {
    line-height: 40px
  }
}

@media (max-width: 920px) {
  .more-site li {
    width: 100%;
    margin: 0 0 34px 0 !important
  }
}

@media (max-width: 1434px) {
  .article-item:nth-of-type(1) p {
    margin-bottom: 56px !important
  }
}

@media (max-width: 1199px) {
  .nav-right {
    width: 75%
  }

  .nav-box {
    width: 48%;
    margin: 0 3.296% 34px 0 !important
  }

  .nav-box:nth-of-type(2n) {
    margin-right: 0 !important
  }
}

@media (max-width: 1080px) {
  .big-banner {
    height: 320px;
    padding-top: 106px
  }

  .big-banner h1 {
    font-size: 28px
  }
}

@media (max-width: 999px) {
  nav ul {
    display: none
  }

  nav > .icon-menu {
    display: block
  }

  .main-container {
    margin-top: 50px
  }

  .article-search button {
    line-height: 28px
  }

  .select-list a {
    font-size: 14px;
    line-height: 40px
  }

  .wechat-recommend p {
    font-size: 12px
  }

  .article-recommend h4 {
    font-size: 16px
  }

  .recommend-item div {
    width: 40px;
    height: 40px
  }

  .recommend-item h5 {
    font-size: 14px;
    -webkit-line-clamp: 1;
    height: 21px;
    margin-bottom: 2px
  }

  .nav-right {
    width: 70%
  }

  .side-tool {
    display: none
  }

  .super-search-container h1 {
    font-size: 28px;
    margin-bottom: 0
  }

  .super-search-container p {
    font-size: 16px
  }

  .super-search-type-item-mobile .iconfont {
    font-weight: 700;
    margin-left: 4px
  }

  .super-search-logo-2 .logo {
    margin-top: 8px;
    height: 24px
  }

  .super-search-logo-2 .site-name {
    margin-top: 12px;
    margin-right: 0
  }

  .super-search-logo-2 .site-name-zh {
    display: none
  }
}

@media (max-width: 824px) {
  .nav-box {
    width: 100%;
    margin-right: 0 !important
  }
}

@media (max-width: 808px) {
  .pagination li {
    margin: 0 2px
  }

  .pagination a {
    width: 30px;
    height: 30px;
    line-height: 28px;
    font-size: 14px
  }
}

@media (max-width: 760px) {
  .left-footer, .right-footer {
    display: block;
    width: 100%
  }

  .left-footer {
    margin-bottom: 32px
  }

  .left-footer span {
    display: inline-block;
    margin: 0
  }

  .nav-left {
    position: fixed;
    height: 100%;
    z-index: 4;
    top: 0;
    left: -100%
  }

  .nav-right {
    width: 100%
  }

  .nav-box {
    width: 48%
  }

  .nav-box:nth-child(2n) {
    margin: 0 0 34px 3.296% !important
  }

  .nav-phone-menu {
    display: block
  }

  .nav-left > ul {
    margin-top: 32px
  }

  #article-content td {
    padding: 8px 6px
  }

  .free-movie-site a {
    display: inline-block;
    width: 50%;
    margin-left: 0;
    margin-top: 16px
  }
}

@media screen and (max-width: 599px) {
  .nav-right .nav-list .nav-box {
    width: 100%;
    margin: 0 0 16px 0 !important
  }

  .parse-banner .parse-content p {
    font-size: 16px
  }
}

@media (prefers-color-scheme: dark) {
  .active-header a, .active-header h2, .nav-drop a, .white-header .nav-item > a, .white-header h2 {
    color: #fff
  }

  .site-name-zh {
    color: #9bacc3 !important
  }

  .nav-drop {
    box-shadow: 0 0 8px rgba(0, 0, 0, .6)
  }

  .white-header .icon-menu {
    color: #fff
  }

  .big-banner::before {
    background-color: rgba(0, 0, 0, .4)
  }

  .big-banner h1, .big-banner p {
    color: rgba(255, 255, 255, .8)
  }

  .active > a, .nav-register a, .side-item, .side-tool-tooltips {
    background-color: #2e3f59;
    color: #fff
  }

  .side-tool-tooltips::before {
    border-left: 12px solid #2e3f59
  }

  .side-tool-contact-box {
    background-color: #2e3f59;
    color: #9bacc3
  }

  .side-tool-contact-contact {
    border-top: 1px solid rgba(255, 255, 255, .16)
  }

  .side-tool-contact-des, .side-tool-contact-title {
    color: #fff
  }

  #article-content p, #article-content table, #article-content ul, #resource p, #resource ul, .left-footer, .left-footer a, .more-site-blank-tips, .nav-box p, .right-footer, .right-footer a, .site-des {
    color: #6c7e9a !important
  }

  .nav-skill, .nav-visit, .pagination a {
    color: #9bacc3;
    border: 1px solid #354763
  }

  #article-content tr:nth-of-type(2n+1), .footer, .nav-drop, .window-check input:after {
    background-color: #121f33
  }

  #article-content hr {
    background-color: #354763
  }

  .side-item {
    box-shadow: 0 0 8px rgba(0, 0, 0, .6)
  }

  .white-header .nav-register > a {
    background-color: #1d74f5
  }

  .nav-register a:hover {
    background-color: #1d74f5 !important;
    color: #fff !important
  }

  .big-window .window-text img {
    margin-top: 12px
  }
}
</style>

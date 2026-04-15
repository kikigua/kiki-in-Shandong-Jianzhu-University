<template>
  <div class="about">
    <ul>
      <li class="nav-box" style="margin-left: 0">
        <img :src="'https://imyshare.com/'+item.img" alt="">
        <div class="nav-des">
          <h3>{{ item.title }}</h3>
          <p>{{ item.content }}</p>
        </div>
        <el-button @click="setFavorite()" type="warning" round v-if="item.isFavorite==0">收藏</el-button>
        <el-button type="warning" icon="el-icon-star-off" circle @click="showWarnings()" v-else></el-button>
        <a data-url="aHR0cHM6Ly93d3cuZnk2Yi5jb20v" data-id="976" data-pre="False" class="nav-visit">访问</a>
      </li>
    </ul>
  </div>
</template>
<script>

import {setFavorite} from "@/api/serveArticle";

export default {
  name: "myBox",
  props: ["item"],
  methods: {
    setFavorite() {
      setFavorite(this.item).then(res => {
        this.$alert('收藏成功', '标题名称', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${action}`
            });
          },
        }).then(() => {

        });
      })
    },
    //获取到列表的数据
    showWarnings() {
      this.$alert('你已经收藏请勿重复收藏', '标题名称', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${action}`
          });
        }
      });
    }
  },
  data() {
    return {}
  },


}
</script>


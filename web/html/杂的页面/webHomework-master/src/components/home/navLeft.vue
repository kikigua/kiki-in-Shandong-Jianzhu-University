<template>

  <div class="nav-left">
    <ul>
      <li class="active">
        <a class="nav-header"><i class="iconfont icon-ali-clould" @click="goToHome()"></i>综合资源</a>
        <div style="margin-top: 20px">
          <el-button type="info" @click="goToFavorite()">我的模块</el-button>
        </div>
      </li>
      <li>
        <a class="nav-header"><i class="iconfont icon-clock"></i>实用工具</a>
        <ul class="nav-body">
          <li><a href="#Win实用工具">Win实用工具</a></li>

          <li><a href="#Mac实用工具">Mac实用工具</a></li>

          <li><a href="#安卓实用工具">安卓实用工具</a></li>

          <li><a href="#iOS实用工具">iOS实用工具</a></li>

          <li><a href="#在线实用工具">在线实用工具</a></li>
          <li><a href="#实用插件">实用插件</a></li>
          <li><a href="#其他实用工具">其他实用工具</a></li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<script>
// import "src/static/css/imyshare.css"
export default {
  name: "navLeft",
  methods: {
    goToFavorite() {
      this.$router.push('/myFavorite')
    },
    goToHome() {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
</style>
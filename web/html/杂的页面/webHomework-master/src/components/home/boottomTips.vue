<template>
  <div class="bottom-fixed-tips">
    <div class="bottom-fixed-tips-container">
      <i class="iconfont icon-huojian"></i>
      <div class="bottom-fixed-tips-text">一键登录，海量资源快人一步！</div>
      <button class="bottom-fixed-tips-btn bottom-fixed-tips-login" @click="goToLogin()">立即登录</button>
      <i class="iconfont icon-close bottom-fixed-tips-close"></i>
    </div>
  </div>
</template>

<script>
export default {
  name: "boottomTips",
  methods: {
    goToLogin() {
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>
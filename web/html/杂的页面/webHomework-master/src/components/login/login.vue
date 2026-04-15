<template>
  <div>
    <section class="w3l-workinghny-form">
      <!-- /form -->
      <div class="workinghny-form-grid">
        <div class="wrapper">
          <div class="logo">
            <h1><a class="brand-logo" href="index.html"><span>Working</span> Sign In</a></h1>
          </div>
          <div class="workinghny-block-grid">
            <div class="workinghny-left-img align-end">
              <!--              如果出现问题的话我们的就换一个路径书写即可-->
              <img src="./images/2.png" class="img-responsive" alt="img"/>
            </div>
            <div class="form-right-inf">

              <div class="login-form-content">
                <h2>Where to?</h2>
                <div class="signin-form">
                  <div class="one-frm">

                    <label>Name</label>
                    <input type="text" name="Name" placeholder="" required="" v-model="user.name">
                  </div>
                  <div class="one-frm">
                    <label>Password</label>
                    <input type="password" name="Password" placeholder="" required="" v-model="user.password">
                  </div>
                  <label class="check-remaind">
                    <input type="checkbox">
                    <span class="checkmark"></span>
                    <p class="remember">Remember Me</p>
                  </label>
                  <button class="btn btn-style mt-3" @click="login()">Login In</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="copyright text-center">
        <div class="wrapper">
          <p class="copy-footer-29">© 2022 Working Sign In. All rights reserved | Design by </p>
        </div>
      </div>
    </section>
  </div>
</template>

<script>

export default {
  name: "login",
  data() {
    return {
      user: {
        name: "",
        password: ""
      }
    }
  },
  methods: {
    login() {
      console.log(this.user.name)
      if (this.user.name === "admin" && this.user.password === "123") {
        this.$router.push('/')
      } else {
        this.$alert('用户名或密码错误', '错误', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${action}`
            });
          }
        });

      }
    }
  },
  created() {
    this.user.name=''
    this.user.password=''
  }
}
</script>

<style scoped>
@import "css/style.css";
</style>
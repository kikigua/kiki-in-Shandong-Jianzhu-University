<template>
  <el-dialog :visible.sync="show" title="文章类别修改" width="30%">
    <div>
      <el-form ref="form" :model="updateForm" label="left" label-width="80px">
        <el-form-item label="标题">
          <el-input v-model="updateForm.title"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="updateForm.content"></el-input>
        </el-form-item>
        <el-form-item label="图片地址">
          <el-input v-model="updateForm.img"></el-input>
        </el-form-item>
        <el-form-item label="网站地址">
          <el-input v-model="updateForm.url"></el-input>
        </el-form-item>
        <el-form-item label="类别">
          <el-input v-model="updateForm.category"></el-input>
        </el-form-item>
      </el-form>
    </div>
    <span slot="footer" class="dialog-footer">
			<el-button @click="show = false">取 消</el-button>
			<el-button type="primary" v-on:click="updateTrueButton">确 定</el-button>
		</span>
  </el-dialog>
</template>

<script>
import {updateArticle} from "@/api/serveArticle";

export default {
  name: "classUpdate",
  data() {
    return {
      show: false,
      // 修改-表单
      updateForm: {
        title: '',
        content: '',
        img: '',
        url: '',
        category: '',
      },
      msg: {
        id: '',
        title: '',
        type: '',
      },
    }
  },
  mounted() {
    //监听我们的属性
    this.$bus.$on('classUpdateOpen', this.classUpdateOpen)
    this.$bus.$on('classUpdateClose', this.classUpdateClose)
  },
  beforeCreate() {
    this.$bus.$off('classUpdateOpen')
    this.$bus.$off('classUpdateClose')
  },
  methods: {
    classUpdateOpen(msg) {
      this.updateForm = msg
      this.show = true
    },
    classUpdateClose() {
      this.show = false
    },
    //修改弹出框---确认按钮 修改完成只会将我们的代码进行隐藏
    updateTrueButton() {
      updateArticle(this.updateForm).then(response => {
        console.log(response)
      })
      this.show = false
    },
  },
}
</script>

<style scoped>

</style>

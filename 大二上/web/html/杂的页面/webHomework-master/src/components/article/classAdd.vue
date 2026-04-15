<template>
  <el-dialog :visible.sync="show" title="添加新用户" width="30%">
    <el-form ref="form" :model="addForm" label="left" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="addForm.title"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="addForm.content"></el-input>
      </el-form-item>
      <el-form-item label="图片地址">
        <el-input v-model="addForm.img"></el-input>
      </el-form-item>
      <el-form-item label="网站地址">
        <el-input v-model="addForm.url"></el-input>
      </el-form-item>
      <el-form-item label="类别">
        <el-input v-model="addForm.category"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
			<el-button type="button" @click="show = false">取 消</el-button>
      <el-button type="primary" @click="submitButton()">确 定</el-button>
		</span>
  </el-dialog>
</template>
<script>
import {insertArticle} from "@/api/serveArticle";

export default {
  name: "classAdd",
  data() {
    return {
      show: false,
      // 添加-表单(对应) 与后台的字段进行对弈
      addForm: {
        title: '',
        content: '',
        img: '',
        url: '',
        category: '',
      },
    }
  },
  mounted() {
    //这里处于监听的状态 如果调用的话 就会触发弹出框的打开
    this.$bus.$on('classAddOpen', this.classAddOpen)
    this.$bus.$on('classAddClose', this.classAddClose)
  },
  beforeCreate() {
    this.$bus.$off('classAddOpen')
    this.$bus.$off('classAddClose')
  },
  methods: {
    classAddOpen() {
      this.show = true
    },
    classAddClose() {
      this.show = false
    },
    submitButton() {
      insertArticle(this.addForm
      ).then(response => {
        console.log(response)
        location.reload()
      })
      this.show = false
    },
  }
}
</script>

<style scoped>

</style>

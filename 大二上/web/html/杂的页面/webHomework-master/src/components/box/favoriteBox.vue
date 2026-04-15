<template>
  <div class="about">
    <ul>
      <li class="nav-box" style="margin-left: 0">
        <img :src="items.img" alt="">
        <div class="nav-des">
          <h3>{{ items.title }}</h3>
          <p>{{ items.content }}</p>
        </div>
        <div>
          <el-button type="primary" icon="el-icon-edit" circle @click="toUpdate()">修改模块信息</el-button>
        </div>
        <a :href="items.url" data-id="976" data-pre="False" class="nav-visit">访问</a>
      </li>
    </ul>
  </div>
</template>

<script>

import ClassUpdate from "@/components/article/classUpdate";

export default {
  name: "favoriteBox",
  components: {ClassUpdate},
  props: ["items"],
  methods: {
    toUpdate() {
      this.$bus.$emit('classUpdateOpen', this.items)
    }
  },
  data() {
    return {}
  },


}


</script>
<template>
<el-dropdown @command="handleCommand">
    <el-input v-model="a" id="aaa"></el-input>
  <el-dropdown-menu slot="dropdown">
    <el-dropdown-item command="黄金糕">黄金糕</el-dropdown-item>
    <el-dropdown-item command="b">狮子头</el-dropdown-item>
    <el-dropdown-item command="c">螺蛳粉</el-dropdown-item>
    <el-dropdown-item command="d" disabled>双皮奶</el-dropdown-item>
    <el-dropdown-item command="e" divided>蚵仔煎</el-dropdown-item>
  </el-dropdown-menu>
</el-dropdown>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
//import引入的组件需要注入到对象中才能使用
components: {},
props: {},
data() {
//这里存放数据
return {
    a: ""
};
},
//计算属性 类似于data概念
computed: {},
//监控data中的数据变化
watch: {},
//方法集合
methods: {
    handleCommand(command) {
        this.a=command;
        this.$message('click on item ' + command+" input="+this.a);
      }
},
//声明周期 - 创建完成（可以访问当前this实例）
created() {},
//声明周期 - 挂载完成（可以访问DOM元素）
mounted() {},
beforeCreate() {}, //生命周期 - 创建之前
beforeMount() {}, //生命周期 - 挂载之前
beforeUpdate() {}, //生命周期 - 更新之前
updated() {}, //生命周期 - 更新之后
beforeDestroy() {}, //生命周期 - 销毁之前
destroyed() {}, //生命周期 - 销毁完成
activated() {} //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
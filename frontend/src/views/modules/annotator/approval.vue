<template>
  <div>
    <el-row>
      <el-button type="primary">ConfirmAll</el-button>
    </el-row>

    <el-input
      v-model="input"
      placeholder="Search"
      suffix-icon="el-icon-search"
    ></el-input>
    <div>
      <el-table
        ref="multipleTable"
        :data="
          tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)
        "
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="id" label="ID" width="300"> </el-table-column>
        <el-table-column prop="files" label="Files" width="300"> </el-table-column>
        <el-table-column prop="annotated_time" label="Annotated time" width="300"> </el-table-column>
        <el-table-column prop="status" label="Status" width="300"> </el-table-column>
        <el-table-column label="Action">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >修改
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="text-align: center;margin-top: 30px;">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          @current-change="current_change"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "dataList",
  components: {},
  props: {},
  data() {
    return {
      tableData: [],
      multipleSelection: [],
      total: 0,
      pagesize: 10,
      currentPage: 1
    };
  },

  computed: {},

  watch: {},

  methods: {
    addUser() {
      this.$http({
        method: "GET",
        url: "http://127.0.0.1:8080/api/users"
      })
        .then(res => {
          console.log(res);
          if (!res.data.errno) {
            this.tableData = res.data.data.users;
            this.total = res.data.totalnum;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },

    current_change: function(currentPage) {
      this.currentPage = currentPage;
    },
    mounted: function() {
      this.addUser();
    }
  },

  created() {},

  mounted() {},
  beforeCreate() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  activated() {}
};
</script>
<style scoped></style>

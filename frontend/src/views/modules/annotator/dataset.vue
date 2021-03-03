<template>
  <div>
    <div>
      <el-row>
        <upload v-model="fileUrl"></upload>
        <el-button type="primary" @click.native="testMethod">Test</el-button>
        <el-button type="primary">Export Dataset</el-button>
        <el-button type="danger" disabled>Delete</el-button>
      </el-row>
    </div>
    <el-input
      v-model="input"
      placeholder="Search"
      suffix-icon="el-icon-search"
    ></el-input>
    <div>
      <el-table
        :data="dataList"
        border
        v-loading="dataListLoading"
        @selection-change="selectionChangeHandle"
        style="width: 100%;"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="text" label="Text" width="400">
        </el-table-column>
        <el-table-column prop="metadata" label="Metadata" width="400">
        </el-table-column>
        <el-table-column label="Action">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >修改
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="sizeChangeHandle"
        @current-change="currentChangeHandle"
        :current-page="pageIndex"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageSize"
        :total="totalPage"
        layout="total, sizes, prev, pager, next, jumper"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
import upload from "@/components/upload/upload";
export default {
  name: "dataList",
  components: { upload },
  props: {},
  data() {
    return {
      tableData: [],
      multipleSelection: [],
      total: 0,
      pagesize: 10,
      currentPage: 1,
      fileUrl: "",
      input: ""
    };
  },

  computed: {
    userId: {
        get () { return this.$store.state.user.id },
        set (val) { this.$store.commit('user/updateId', val) }
      }
  },

  watch: {
    fileUrl: function(val, oldVal) {
      if(val!=null){
      this.processDataSet(val);
      }
    }
  },

  methods: {
    getUserInfo() {
      this.$http({
        url: this.$http.adornUrl("/sys/user/info"),
        method: "get",
        params: this.$http.adornParams()
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.userId = data.user.userId;
          console.log("userId=",this.userId)
        }
      });
    },
    processDataSet(val) {
      this.getUserInfo();
      console.log("processing userId",this.userId)
      this.$http({
        url: this.$http.adornUrl("/annotator/srcdoc/process"),
        method: "post",
        params: this.$http.adornParams({
          filePath: val,
          userId: this.userId
        })
      }).then(res => {
        console.log("---", res);
      });
      console.log("new file path:", val);
    },
    testMethod() {
      alert("test");
      this.$http({
        url: this.$http.adornUrl("/annotator/doc/list"),
        method: "get",
        data: this.$http.adornData({})
      }).then(res => {
        console.log(res);
      });
    },
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

<template>
  <div>
    <div>
      <el-row>
        <el-button
          type="primary"
          @click="batchApprove()"
          :disabled="dataListSelections.length <= 0"
          >Approve Selected</el-button
        >
        <el-button
          type="danger"
          @click="deleteHandle()"
          :disabled="dataListSelections.length <= 0"
          >Delete</el-button
        >
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
        <el-table-column
          type="selection"
          header-align="center"
          align="center"
          width="50"
        >
        </el-table-column>
        <el-table-column
          prop="recordId"
          header-align="center"
          align="center"
          width="50"
          label="recordId"
        ></el-table-column>
        <el-table-column
          prop="docId"
          header-align="center"
          align="center"
          width="200"
          label="DocId"
        >
        </el-table-column>
        <el-table-column
          prop="docContent"
          header-align="center"
          align="center"
          width="400"
          label="Text"
        >
        </el-table-column>
        <el-table-column label="img" align="center" height="10px" prop="docContent" width="300">
          <template slot-scope="scope">
            <el-popover placement="right" title="" trigger="hover">
              <img :src="scope.row.docContent" />
              <img
                slot="reference"
                :src="scope.row.docContent"
                :alt="scope.row.docContent"
                style="max-height: 180;max-width: 320px"
              />
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          prop="oldLabelNames"
          header-align="center"
          align="center"
          width="100"
          label="oldLabel"
        >
        </el-table-column>
        <el-table-column
          prop="updatedLabelNames"
          header-align="center"
          align="center"
          width="100"
          label="newLabel"
        >
        </el-table-column>
        <el-table-column
          prop="createTime"
          header-align="center"
          align="center"
          label="CreateTime"
        >
        </el-table-column>
        <el-table-column
          fixed="right"
          header-align="center"
          align="center"
          width="150"
          label="操作"
        >
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="approve(scope.row)"
              >Approve</el-button
            >
            <el-button type="text" size="small" @click="reject(scope.row)"
              >Reject</el-button
            >
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
      labelForm: {
        labelId: 0,
        labelContent: ""
      },
      rules: {},
      tempData: {},
      docContent: "",
      labelList: [],
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      input: "",
      dataForm: {
        content: ""
      }
    };
  },

  computed: {
    userId: {
      get() {
        return this.$store.state.user.id;
      },
      set(val) {
        this.$store.commit("user/updateId", val);
      }
    }
  },

  watch: {},

  methods: {
    approve(val) {
      this.$http({
        url: this.$http.adornUrl("/annotator/doc/approve"),
        method: "post",
        params: this.$http.adornParams({
          annotateRecordId: val.recordId,
          userId: this.userId
        })
      }).then(data => {
        console.log("1111233", data);
        if (data.status == 200) {
          this.$message({
            message: "annotate has been approved",
            type: "success",
            duration: 1000
          });
        } else {
          this.$message({
            message: "approve fail",
            type: "warning",
            duration: 1000
          });
        }
        this.getDataList();
      });
    },
    reject(val) {
      this.$http({
        url: this.$http.adornUrl("/annotator/doc/reject"),
        method: "post",
        params: this.$http.adornParams({
          annotateRecordId: val.recordId,
          userId: this.userId
        })
      }).then(data => {
        console.log("1111233", data);
        if (data.status == 200) {
          this.$message({
            message: "annotate has been rejected",
            type: "success",
            duration: 1000
          });
        } else {
          this.$message({
            message: "reject fail",
            type: "warning",
            duration: 1000
          });
        }
        this.getDataList();
      });
    },
    batchApprove() {},
    changeSelectVal(val) {
      console.log("-----changeval---------", val);
      let obj = {};
      obj = this.labelList.find(item => {
        return item.labelContent === val;
      });
      console.log("--111---111-----", obj);
      this.labelForm.labelId = obj.labelId;
    },
    deleteHandle(id) {
      var ids = id
        ? [id]
        : this.dataListSelections.map(item => {
            return item.docId;
          });
      this.$confirm(
        `确定对[id=${ids.join(",")}]进行[${id ? "删除" : "批量删除"}]操作?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/annotator/doc/deleteBatch"),
            method: "post",
            data: this.$http.adornData(ids, false)
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.getDataList();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        })
        .catch(() => {});
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/annotator/annotatorrecord/approvalList"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          content: this.dataForm.content
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list;
          this.totalPage = data.page.totalCount;
          console.log("00datakist00", this.dataList);
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val;
    },
    getUserInfo() {
      this.$http({
        url: this.$http.adornUrl("/sys/user/info"),
        method: "get",
        params: this.$http.adornParams()
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.userId = data.user.userId;
          console.log("userId=", this.userId);
        }
      });
    },
    current_change: function(currentPage) {
      this.currentPage = currentPage;
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
  activated() {
    console.log("sssssssssssssssssssssssssssssssss");
    this.getDataList();
    this.getUserInfo();
  }
};
</script>
<style scoped></style>

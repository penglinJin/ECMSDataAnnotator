<template>
  <div>
    <el-dialog
      title="提示"
      :visible.sync="annotationVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form
        :model="labelForm"
        :rules="rules"
        ref="labelForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <!-- <el-form-item label="id" prop="labelIds">
          <el-select
            v-model="labelForm.labels"
            placeholder=""
            multiple
            disabled
          ></el-select>
        </el-form-item> -->
        <el-form-item label="label" prop="labelContents">
          <el-select
            v-model="labelForm.labelContents"
            placeholder="Please Select"
            @change="changeSelectVal"
            multiple
          >
            <el-option
              v-for="item in labelList"
              :key="item.labelId"
              :label="item.labelContent"
              :value="item.labelContent"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="" prop="docId">
          <el-input v-model="docContent" disabled></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('labelForm')"
            >Confirm</el-button
          >
          <el-button @click="resetForm('labelForm')">Reset</el-button>
          <el-button @click="annotationVisible = false">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <div>
      <el-row>
        <upload v-model="fileUrl"></upload>

        <el-button type="primary" @click.native="testMethod">Test</el-button>
        <el-button type="primary">Export Dataset</el-button>
        <el-button
          v-if="isAuth('annotator:doc:deleteBatch')"
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
          width="800"
          label="Text"
        >
        </el-table-column>
        <el-table-column
          prop="docState"
          header-align="center"
          align="center"
          width="100"
          label="docState"
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
            <el-button type="text" size="small" @click="annotate(scope.row)"
              >Annotate</el-button
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
        labelContents: []
      },
      newLabels: [],
      oldLabels: [],
      labelIds: [],
      labelContents: [],
      rules: {},
      tempData: {},
      docContent: "",
      annotationVisible: false,
      labelList: [],
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      fileUrl: "",
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

  watch: {
    fileUrl: function(val, oldVal) {
      if (val != null) {
        this.processDataSet(val);
      }
    }
  },

  methods: {
    submitForm(formName) {
      console.log("dataForm", this.labelForm);
      console.log("1111labelContents11111", this.labelContents);
      console.log("1111labelIds11111", this.labelForm.labelIds);
      var labelIds=this.labelForm.labelIds;
      this.annotationVisible = false;
      this.$http({
        url: this.$http.adornUrl("/annotator/srcdoc/annotate"),
        method: "post",
        params: this.$http.adornParams({
          userId: this.userId,
          docId: this.tempData.docId
        }),
        data: this.$http.adornData(labelIds,false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "annotate success",
            type: "success",
            duration: 1000
          });
          console.log(data);
        }
        this.annotationVisible = false;
        this.getDataList();
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleClose() {
      this.annotationVisible = false;
      console.log("about to close");
    },
    changeSelectVal(val) {
      if (val != null) {
        console.log("about to change");
        var arr = new Array();
        for (var i = 0; i < val.length; i++) {
          let savedLabel = val[i];
          console.log("savedLabel", savedLabel);
          let obj = {};
          for (var j = 0; j < this.labelList.length; j++) {
            if (savedLabel == this.labelList[j].labelContent) {
              obj = this.labelList[j];
            }
          }
          if (obj != null) {
            arr.push(obj.labelId);
          }
        }
        this.labelForm.labelIds = arr;
        this.labelContents = val;
        console.log("---labelContents---", this.labelForm.labelContents);
        console.log("---labelIds---", this.labelForm.labelIds);
      }
    },
    asyncLabel() {},
    annotate(val) {
      this.$http({
        url: this.$http.adornUrl("/annotator/labelinfo/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          content: ""
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.labelList = data.page.list;
        }
        console.log("---------labelList---------------", this.labelList);
        console.log("--------text----------", val);
        this.docContent = val.docContent;
        this.tempData = val;
        this.annotationVisible = true;
      });
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
        url: this.$http.adornUrl("/annotator/doc/preList"),
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
          console.log("user",data.user);
          console.log("userId=", this.userId);
        }
      });
    },
    processDataSet(val) {
      console.log("processing userId", this.userId);
      this.$http({
        url: this.$http.adornUrl("/annotator/srcdoc/process"),
        method: "post",
        params: this.$http.adornParams({
          filePath: val,
          userId: this.userId
        })
      }).then(res => {
        this.getDataList();
        console.log("---", res);
      });
      console.log("new file path:", val);
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

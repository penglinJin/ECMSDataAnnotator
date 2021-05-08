<template>
  <div>
    <el-dialog
      title="Upload Data"
      :visible.sync="uploadVisible"
      width="30%"
      :before-close="handleClose2"
    >
      <h1>Select a file format</h1>
      <el-radio v-model="radio" label="1">Plain Text</el-radio>
      <el-radio v-model="radio" label="2">JSON</el-radio>
      <br />
      <span class="span1">{{ dataFormat }}</span>
      <upload v-model="fileUrl" v-if="true"></upload>
    </el-dialog>

    <el-dialog
      title="Annotate"
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
        <el-form-item label="oldLabels" prop="oldLabels">
          <el-input v-model="labelForm.oldLabels" disabled></el-input>
        </el-form-item>
        <el-form-item label="label" prop="labelContents">
          <el-select
            v-model="labelForm.labelContents"
            placeholder="Please Select"
            @change="changeSelectVal"
            @keyup="test12"
            multiple
          >
            <el-option
              v-for="item in labelList"
              :style="{color: item.textColor}"
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
        <el-button type="primary" @click="test1()">Import Dataset</el-button>

        <el-button type="primary" @click="exportDataset()"
          >Export Dataset</el-button
        >
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
          width="600"
          label="Text"
        >
        </el-table-column>
        <el-table-column label="img" align="center" height="10px" prop="img">
          <template slot-scope="scope">
            <el-popover placement="right" title="" trigger="hover">
              <img :src="scope.row.img" />
              <img
                slot="reference"
                :src="scope.row.img"
                :alt="scope.row.img"
                style="max-height: 180;max-width: 320px"
              />
            </el-popover>
          </template>
        </el-table-column>

        <el-table-column
          prop="labels"
          header-align="center"
          align="center"
          width="100"
          label="Labels"
        >
        </el-table-column>
        <el-table-column
          prop="nlpLabel"
          header-align="center"
          align="center"
          width="100"
          label="NlpLabel"
        >
        </el-table-column>
        <el-table-column
          prop="docState"
          header-align="center"
          align="center"
          width="100"
          label="state"
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
          label="Operation"
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
      dataForm: {
        id: 0,
        userName: "",
        password: "",
        comfirmPassword: "",
        salt: "",
        email: "",
        mobile: "",
        roleIdList: [],
        status: 1
      },
      labelForm: {
        oldLabels: "",
        labelContents: []
      },
      newLabels: [],
      oldLabels: [],
      labelIds: [],
      labelContents: [],
      rules: {},
      tempData: {},
      docContent: "",
      uploadVisible: false,
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
      radio: "1",
      dataFormat:
        "EU rejects German call to boycott British lamb.\n Peter Blackburn \n President Obama",
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
        this.uploadVisible = false;
      }
    },
    radio: function(val, oldVal) {
      if (val != null) {
        if (val == 1) {
          this.dataFormat =
            "EU rejects German call to boycott British lamb.\n Peter Blackburn \n President Obama";
        } else if (val == 2) {
          this.dataFormat =
            '{"text": "Terrible customer service.", "labels": ["negative"]} \n {"text": "Really great transaction.", "labels": ["positive"]} \n {"text": "Great price.", "labels": ["positive"]}';
        }
      }
    }
  },

  methods: {
    test12(e){
      console.log('eeeeeeeeeeeeeeee', e)
      
    }
    ,
    download(data) {
      if (!data) {
        return;
      }
      let BLOB = new Blob([data]);
      let url = window.URL.createObjectURL(BLOB);
      let link = document.createElement("a");
      link.style.display = "none";
      link.href = url;

      link.setAttribute("download", "exportData.json");
      
      document.body.appendChild(link);
      link.click();
    },
    exportDataset() {
      this.$http({
        url: this.$http.adornUrl("/annotator/srcdoc/downloadFile"),
        method: "get",
        params: this.$http.adornParams()
      }).then(({ data }) => {
        console.log(data);
        this.download(data)
      });
    },
    getRole() {
      this.$http({
        url: this.$http.adornUrl(`/sys/user/info/${this.userId}`),
        method: "get",
        params: this.$http.adornParams()
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataForm.userName = data.user.username;
          this.dataForm.salt = data.user.salt;
          this.dataForm.email = data.user.email;
          this.dataForm.mobile = data.user.mobile;
          this.dataForm.roleIdList = data.user.roleIdList;
          this.dataForm.status = data.user.status;
        }
        console.log("daatForm", this.dataForm);
      });
    },
    submitForm(formName) {
      console.log("dataForm", this.labelForm);
      // console.log("1111labelContents11111", this.labelContents);
      // console.log("1111labelIds11111", this.labelForm.labelIds);
      var labelIds = this.labelForm.labelIds;
      this.annotationVisible = false;
      this.$http({
        url: this.$http.adornUrl("/annotator/srcdoc/annotate"),
        method: "post",
        params: this.$http.adornParams({
          userId: this.userId,
          docId: this.tempData.docId,
          htmlContent: ""
        }),
        data: this.$http.adornData(labelIds, false)
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
        this.resetForm(formName);
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleClose() {
      this.annotationVisible = false;
      console.log("about to close");
    },
    handleClose2() {
      this.uploadVisible = false;
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
        url: this.$http.adornUrl("/annotator/labelinfo/oldLabels"),
        method: "get",
        params: this.$http.adornParams({
          docId: val.docId
        })
      }).then(({ data }) => {
        if (data.code == 0) {
          this.oldLabels = data.oldLabels;
          console.log("---oldLabels---", this.oldLabels);
        }
      }),
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
            console.log("1233333333333",this.labelList);
          }
          this.docContent = val.docContent;
          this.tempData = val;

          if (this.oldLabels != null) {
            let contents = [];
            for (var i = 0; i < this.oldLabels.length; i++) {
              for (var j = 0; j < this.labelList.length; j++) {
                if (this.oldLabels[i] == this.labelList[j].labelId) {
                  console.log("1111111", this.labelList[j].labelId);
                  contents.push(this.labelList[j].labelContent);
                }
              }
            }
            console.log("--contents--", contents);
            var s = "";
            for (var m = 0; m < contents.length; m++) {
              s = s + contents[m] + " ";
            }
            this.labelForm.oldLabels = s;
          }
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
          console.log("user", data.user);
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
    },
    test1() {
      this.uploadVisible = true;
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
    this.getUserInfo();
    this.getRole();
    this.getDataList();
  }
};
</script>
<style scoped>
.span1 {
  display: inline-block;
  width: 80%;
  margin: 30px 0px;
  background-color: #000;
  color: #fff;
  padding: 10px;
  white-space: pre-wrap;
}
</style>

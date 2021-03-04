<template>
  <div>
    <div>
      <el-row>
        <el-button type="primary" @click="addVisible = true"
          >Create Label</el-button
        >
        <el-dialog
          title="提示"
          :visible.sync="addVisible"
          width="30%"
          :before-close="handleClose"
        >
          <el-form
            :model="addForm"
            :rules="rules"
            ref="addForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item label="labelName" prop="labelContent">
              <el-input v-model="addForm.labelContent"></el-input>
            </el-form-item>
            <el-form-item label="color" prop="textColor">
              <el-input v-model="addForm.textColor"></el-input>
              <el-color-picker v-model="addForm.textColor"></el-color-picker>
            </el-form-item>
            <el-form-item label="shortKey" prop="prefixKey">
              <el-dropdown @command="handleCommand">
                <el-input v-model="addForm.prefixKey"></el-input>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="0">0</el-dropdown-item>
                  <el-dropdown-item command="1">1</el-dropdown-item>
                  <el-dropdown-item command="2">2</el-dropdown-item>
                  <el-dropdown-item command="3">3</el-dropdown-item>
                  <el-dropdown-item command="4">4</el-dropdown-item>
                  <el-dropdown-item command="5">5</el-dropdown-item>
                  <el-dropdown-item command="6">6</el-dropdown-item>
                  <el-dropdown-item command="7">7</el-dropdown-item>
                  <el-dropdown-item command="8">8</el-dropdown-item>
                  <el-dropdown-item command="9">9</el-dropdown-item>
                  <!-- <el-dropdown-item command="a">a</el-dropdown-item>
                  <el-dropdown-item command="b">b</el-dropdown-item>
                  <el-dropdown-item command="c">c</el-dropdown-item>
                  <el-dropdown-item command="d">d</el-dropdown-item>
                  <el-dropdown-item command="e">e</el-dropdown-item>
                  <el-dropdown-item command="f">f</el-dropdown-item>
                  <el-dropdown-item command="g">g</el-dropdown-item>
                  <el-dropdown-item command="h">h</el-dropdown-item>
                  <el-dropdown-item command="i">i</el-dropdown-item>
                  <el-dropdown-item command="j">j</el-dropdown-item>
                  <el-dropdown-item command="k">k</el-dropdown-item>
                  <el-dropdown-item command="l">l</el-dropdown-item>
                  <el-dropdown-item command="m">m</el-dropdown-item>
                  <el-dropdown-item command="n">n</el-dropdown-item>
                  <el-dropdown-item command="o">o</el-dropdown-item>
                  <el-dropdown-item command="p">p</el-dropdown-item>
                  <el-dropdown-item command="q">q</el-dropdown-item>
                  <el-dropdown-item command="r">r</el-dropdown-item>
                  <el-dropdown-item command="s">s</el-dropdown-item>
                  <el-dropdown-item command="t">t</el-dropdown-item>
                  <el-dropdown-item command="u">u</el-dropdown-item>
                  <el-dropdown-item command="v">v</el-dropdown-item>
                  <el-dropdown-item command="w">w</el-dropdown-item>
                  <el-dropdown-item command="x">x</el-dropdown-item>
                  <el-dropdown-item command="y">y</el-dropdown-item>
                  <el-dropdown-item command="z">z</el-dropdown-item> -->
                </el-dropdown-menu>
              </el-dropdown>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('addForm')"
                >Confirm</el-button
              >
              <el-button @click="resetForm('addForm')">Reset</el-button>
              <el-button @click="addVisible = false">Cancel</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>

        <el-button
          v-if="isAuth('annotator:labelinfo:deleteBatch')"
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
          prop="labelId"
          header-align="center"
          align="center"
          width="100"
          label="LabelId"
        >
        </el-table-column>
        <el-table-column
          prop="labelContent"
          header-align="center"
          align="center"
          width="200"
          label="Name"
        >
        </el-table-column>
        <el-table-column
          prop="prefixKey"
          header-align="center"
          align="center"
          width="200"
          label="shortKey"
        >
        </el-table-column>
        <el-table-column
          prop="textColor"
          header-align="center"
          align="center"
          width="200"
          label="color"
        >
        </el-table-column>
        <el-table-column
          fixed="right"
          prop="createTime"
          header-align="center"
          align="center"
          label="CreateTime"
        >
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
      addForm: {
        labelContent: "",
        textColor: "",
        prefixKey: ""
      },
      rules: {
        labelContent: [
          {
            required: true,
            message: "Please enter label name",
            trigger: "blur"
          },
          { min: 1, max: 20, message: "1 to 20 chars", trigger: "blur" }
        ],
        textColor: {
          required: true,
          message: "Please select color",
          trigger: "change"
        },
        prefixKey: [
          {
            required: true,
            message: "Please select a short key",
            trigger: "change"
          },
          { min: 1, max: 1, message: "1 char only", trigger: "blur" }
        ]
      },
      addVisible: false,
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
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
    addLabel() {
      if (this.addForm != null) {
        this.$http({
          url: this.$http.adornUrl("/annotator/labelinfo/save"),
          method: "post",
          data: this.$http.adornData({
            labelContent: this.addForm.labelContent,
            createUserId: this.userId,
            textColor: this.addForm.textColor,
            prefixKey: this.addForm.prefixKey,
            backgroundColor: this.addForm.textColor
          })
        });
      } else {
        return false;
      }
    },
    handleCommand(command) {
      this.addForm.prefixKey = command;
      this.$message(
        "click on item " + command + " input=" + this.addForm.prefixKey
      );
    },
    submitForm(formName) {
      this.getUserInfo();
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
          this.addLabel();
          alert("success");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 删除
    deleteHandle(id) {
      var ids = id
        ? [id]
        : this.dataListSelections.map(item => {
            return item.labelId;
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
            url: this.$http.adornUrl("/annotator/labelinfo/deleteBatch"),
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
        url: this.$http.adornUrl("/annotator/labelinfo/list"),
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
          console.log("userId=", this.userId);
        }
      });
    },
    processDataSet(val) {
      this.getUserInfo();
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
  }
};
</script>
<style scoped></style>

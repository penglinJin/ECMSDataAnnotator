<template>
  <div class="mod-demo-echarts">
    <el-alert title="Hint:" type="warning" :closable="false"> </el-alert>

    <el-row :gutter="20">
      <el-col :span="24">
        <el-card>
          <div id="J_chartPieBox" class="chart-box"></div>
        </el-card>
      </el-col>
      <el-col :span="24">
        <el-card>
          <div id="J_chartPieBox2" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'
import echarts from "echarts";
export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},
  data() {
    //这里存放数据
    return {
      a: "",
      chartLine: null,
      chartBar: null,
      chartPie: null,
      chartScatter: null,
      chartPie2: null,
      option: {
        backgroundColor: "#2c343c",
        title: {
          text: "Contribuution Pie",
          left: "center",
          top: 20,
          textStyle: {
            color: "#ccc"
          }
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
            colorLightness: [0, 1]
          }
        },
        series: [
          {
            name: "Contribution Source", //访问来源
            type: "pie",
            radius: "55%",
            center: ["50%", "50%"],
            data: [
              { value: 1, name: "Jin" },
              { value: 2, name: "Cao" },
              { value: 5, name: "Ni" },
              { value: 4, name: "ABC" },
              { value: 1, name: "QWE" }
            ].sort(function(a, b) {
              return a.value - b.value;
            }),
            roseType: "radius",
            label: {
              normal: {
                textStyle: {
                  color: "rgba(255, 255, 255, 0.3)"
                }
              }
            },
            labelLine: {
              normal: {
                lineStyle: {
                  color: "rgba(255, 255, 255, 0.3)"
                },
                smooth: 0.2,
                length: 10,
                length2: 20
              }
            },
            itemStyle: {
              normal: {
                color: "#c23531",
                shadowBlur: 200,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            },
            animationType: "scale",
            animationEasing: "elasticOut",
            animationDelay: function(idx) {
              return Math.random() * 200;
            }
          }
        ]
      },
      option2: {
        title: {
          text: "Annotation Pie",
          subtext: "",
          left: "center"
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          left: "center",
          top: "bottom",
          data: [
            // "rose1",
            // "rose2",
            // "rose3",
            // "rose4",
            // "rose5",
            // "rose6",
            // "rose7",
            // "rose8"
          ]
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: "Square Mode",
            type: "pie",
            radius: [20, 140],
            center: ["50%", "50%"],
            roseType: "area",
            itemStyle: {
              borderRadius: 5
            },
            data: [
              { value: 30, name: "rose 1" },
              { value: 28, name: "rose 2" },
              { value: 26, name: "rose 3" },
              { value: 24, name: "rose 4" },
              { value: 22, name: "rose 5" },
              { value: 20, name: "rose 6" },
              { value: 18, name: "rose 7" },
              { value: 16, name: "rose 8" }
            ]
          }
        ]
      }
    };
  },
  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},

  activated() {
    // 由于给echart添加了resize事件, 在组件激活时需要重新resize绘画一次, 否则出现空白bug
    if (this.chartLine) {
      this.chartLine.resize();
    }
    if (this.chartBar) {
      this.chartBar.resize();
    }
    if (this.chartPie) {
      this.chartPie.resize();
    }
    if (this.chartScatter) {
      this.chartScatter.resize();
    }
    if (this.chartPie2) {
      this.chartPie2.resize();
    }
  },

  //方法集合
  methods: {
    // 饼状图
    initChartPie() {
      this.chartPie = echarts.init(document.getElementById("J_chartPieBox"));
      this.chartPie.setOption(this.option);
      window.addEventListener("resize", () => {
        this.chartPie.resize();
      });
    },
    //2号饼状图
    initChartPie2() {
      this.chartPie2 = echarts.init(document.getElementById("J_chartPieBox2"));
      this.chartPie2.setOption(this.option2);
      window.addEventListener("resize", () => {
        this.chartPie2.resize();
      });
    },

    //获取注解完成情况
    getCompletionSituation() {
      this.$http({
        url: this.$http.adornUrl("/annotator/annotatorrecord/completeSituation"),
        method: "get"
      }).then(({ data }) => {
        console.log(data, "data-------------");
        this.option2.series[0].data = data.completeSituation;
        console.log("option2------", this.option2);
        this.initChartPie2();
        // this.dataListLoading = false;
      });
    },
    //获取数据列表
    getStatics() {
      this.$http({
        url: this.$http.adornUrl("/annotator/annotatorrecord/numStatics"),
        method: "get"
      }).then(({ data }) => {
        console.log(data, "data-------------");
        // if (data && data.code === 0) {
        //   this.option.series.data.name = data.name;
        //   this.option.series.data.value = data.value;
        // } else {
        //   this.dataList = [];
        //   this.totalPage = 0;
        // }
        this.option.series[0].data = data.numStatics;
        console.log("option------", this.option);
        this.initChartPie();
        // this.dataListLoading = false;
      });
    }
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    this.getStatics();
    this.getCompletionSituation();
  },

  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {
    this.initChartPie();
  },
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {} //生命周期 - 销毁完成
};
</script>

<style>
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.chart-box {
  min-height: 400px;
}
</style>

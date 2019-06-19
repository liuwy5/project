$(function () {
    var index = {
        init: function () {
            var productNo1=$("#type1").val();
            var productNo2=$("#type2").val();
            var productNo3=$("#type3").val();
            this.dailyApplyData("line-chart1", productNo1, "#6AD6D6");
            this.dailyApplyData("line-chart2", productNo2, "#6464C1");
            this.dailyApplyData("line-chart3", productNo3, "#52AFF5");
            this.dailyLoanData();
        },

        //每日借据规模
        dailyApplyData: function (param1, param2, param3) {
            var self = this;
            $.dataAjax("/api/statistics/productDailyLoanAmount", {productNo: param2}, function(result){
                showFun("loanAmount", result.data.amount, param1);
                showFun("scale", result.data.loanScale, param1);
                var data = result.data.statisticsList;
                var lineData = [];
                if (data) {
                    for (var i in data) {
                        var objData = {x: data[i].daily, y: data[i].amount};
                        lineData.push(objData);
                    }
                }
                self.renderLine(param1, lineData, param3);
            });
        },

        //每日借据申请额度综合
        dailyLoanData: function () {
            var productNo = $("#barChart").val();
            var self = this;
            $.dataAjax("/api/statistics/dailyApply",  {productNo: productNo}, function(result){
                var data = result.data;
                var barData = [];
                if (data) {
                    for (var i in data) {
                        var objData = {x: data[i].daily, y: data[i].amount};
                        barData.push(objData);
                    }
                }
                self.renderBar("bar-chart", barData);
            });
        },

        //折线图
        renderLine: function (domId, data, styleColor) {
            var date = [];
            var money = [];
            var $func = constant.data_tables.renderFunc;
            for(var i in data){
                date.push(data[i].x);
                money.push($.toTenThousand(data[i].y));
            }

            echarts.init(document.getElementById(domId), 'echarts-macarons').setOption({
                tooltip: {
                    trigger: 'axis',
                    formatter: function(data){
                        var date = data[0].name;
                        var amount = data[0].value || 0;
                        return  date + "<br/>额度(元)：" + $func.toMoneyForm(amount * 10000);
                    }
                },
                grid: {
                    top: "8%",
                    bottom: "10%",
                    right: "5%",
                    left: "6%",
                    y: "50%"
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap : false,
                        data: date
                    }
                ],
                yAxis: [
                    {
                        name : '万元',
                        type: 'value',
                        axisLabel: {
                            show: true,
                            inside: true
                        },
                        z: 10,
                        splitLine: {
                            show: false
                        }
                    }
                ],
                series: [
                    {
                        name: '额度(元)',
                        type: 'line',
                        smooth: false,
                        data: money,
                        areaStyle: {normal: {}}
                    }
                ],
                color: [styleColor]
            });
        },

        //柱状图
        renderBar: function (domId, data) {
            var date = [];
            var money = [];
            var $func = constant.data_tables.renderFunc;
            for(var i in data){
                date.push(data[i].x);
                money.push($.toTenThousand(data[i].y));
            }

            echarts.init(document.getElementById(domId), 'echarts-macarons').setOption({
                tooltip: {
                    trigger: 'axis',
                    formatter: function(data){
                        var date = data[0].name;
                        var amount = data[0].value || 0;
                        return  date + "<br/>额度(元)：" + $func.toMoneyForm(amount * 10000);
                    }
                },
                calculable: true,
                grid: {
                    top: "8%",
                    bottom: "10%",
                    right: "5%"
                },
                xAxis: [
                    {
                        type: 'category',
                        data: date
                    }
                ],
                yAxis: [
                    {
                        name : '万元',
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '额度(元)',
                        type: 'bar',
                        data: money
                    }
                ]
            });
        }
    };


    var showFun=function(className, showData, showPosition){
        var tableCons = constant.data_tables;
        if(!!showData) {
            showData = tableCons.renderFunc.toMoneyForm(showData);
            showData = showData.toString();
            if(showPosition == "line-chart1") {
                $("."+className)[0].innerHTML=showData;
            }else if(showPosition == "line-chart2"){
                $("."+className)[1].innerHTML=showData;
            }else{
                $("."+className)[2].innerHTML=showData;
            }
        }else{
            showData ='0';
            if(showPosition == "line-chart1") {
                $("."+className)[0].innerHTML=showData;
            }else if(showPosition == "line-chart2"){
                $("."+className)[1].innerHTML=showData;
            }else{
                $("."+className)[2].innerHTML=showData;
            }
        }

    }


    //初始化和图表切换下拉选择框
    $(function(){

        index.init();
        window.onresize = function () {
            index.init();
        }

       $(".scriptType").change(function (event){
           var param1 = $(event.target).attr("id");
           var param2 = $("#"+param1).val();
           var param3 = "";

           if(param1 == "type1") {

               param1 = "line-chart1";
               param3 = "#6AD6D6"

           }else if(param1 == "type2"){

               param1 = "line-chart2";
               param3 = "#6464C1"

           }else{

               param1 = "line-chart3";
               param3 = "#52AFF5"
           }

           index.dailyApplyData(param1, param2, param3);   //param1:图绘制的位置 param2:下拉选择框选中的值 param3:绘制的颜色
         }
       );

       $("#barChart").change(function () {

           index.dailyLoanData();
       })
    })
});
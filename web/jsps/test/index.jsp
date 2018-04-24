<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片轮播 (左右切换)</title>
<style type="text/css">
  body,div,ul,li,a,img{margin: 0;padding: 0;}
  ul,li{list-style: none;}
  a{text-decoration: none;}
  #wrapper{position: relative;margin: 30px auto;width: 400px;height: 200px;}
  #banner{position:relative;width: 400px;height: 200px;overflow: hidden;}
  .imgList{position:relative;width:2000px;height:200px;z-index: 10;overflow: hidden;}
  .imgList li{float:left;display: inline;}
  #prev,
  #next{position: absolute;top:80px;z-index: 20;cursor: pointer;opacity: 0.2;filter:alpha(opacity=20);}
  #prev{left: 10px;}
  #next{right: 10px;}
  #prev:hover,
  #next:hover{opacity: 0.5;filter:alpha(opacity=50);}
  .bg{position: absolute;bottom: 0;width: 400px;height: 40px;z-index:20;opacity: 0.4;filter:alpha(opacity=40);background: black;}
  .infoList{position: absolute;left: 10px;bottom: 10px;z-index: 30;}
  .infoList li{display: none;}
  .infoList .infoOn{display: inline;color: white;}
  .indexList{position: absolute;right: 10px;bottom: 5px;z-index: 30;}
  .indexList li{float: left;margin-right: 5px;padding: 2px 4px;border: 2px solid black;background: grey;cursor: pointer;}
  .indexList .indexOn{background: red;font-weight: bold;color: white;}
</style>
<script type="text/javascript" src="../../jquery/jquery-1.5.1.js"></script>

</head>
<body>
  <div id="wrapper"><!-- 最外层部分 -->
    <div id="banner"><!-- 轮播部分 -->
      <ul class="imgList"><!-- 图片部分 -->
      <li><a href="#"><img src="./img/1.jpg" width="400px" height="200px" alt="puss in boots1"></a></li>
      <li><a href="#"><img src="./img/2.jpg" width="400px" height="200px" alt="puss in boots2"></a></li>
      <li><a href="#"><img src="./img/3.jpg" width="400px" height="200px" alt="puss in boots3"></a></li>
      <!-- <li><a href="#"><img src="./img/4.jpg" width="400px" height="200px" alt="puss in boots4"></a></li>
      <li><a href="#"><img src="./img/test5.jpg" width="400px" height="200px" alt="puss in boots5"></a></li> -->
      </ul>
      <img src="./img/left.png" width="20px" height="40px" id="prev">
      <img src="./img/right.png" width="20px" height="40px" id="next">
      <div class="bg"></div> <!-- 图片底部背景层部分-->
      <ul class="infoList"><!-- 图片左下角文字信息部分 -->
        <li class="infoOn">puss in boots1</li>
        <li>puss in boots2</li>
        <li>puss in boots3</li>
        <li>puss in boots4</li>
        <li>puss in boots5</li>
      </ul>
      <ul class="indexList"><!-- 图片右下角序号部分 -->
        <li class="indexOn">1</li>
        <li>2</li>
        <li>3</li>        
      </ul>
    </div>
  </div> 
 
  <script type="text/javascript">
  var curIndex = 0, //当前index
      imgLen = $(".imgList li").length; //图片总数
     // 定时器自动变换2.5秒每次
  var autoChange = setInterval(function(){ 
    if(curIndex < imgLen-1){ 
      curIndex ++; 
    }else{ 
      curIndex = 0;
    }
    //调用变换处理函数
    changeTo(curIndex); 
  },2500);
   //左箭头滑入滑出事件处理
  $("#prev").hover(function(){ 
    //滑入清除定时器
    clearInterval(autoChange);
  },function(){ 
    //滑出则重置定时器
    autoChangeAgain();
  });
  //左箭头点击处理
  $("#prev").click(function(){ 
    //根据curIndex进行上一个图片处理
    curIndex = (curIndex > 0) ? (--curIndex) : (imgLen - 1);
    changeTo(curIndex);
  });
  //右箭头滑入滑出事件处理
  $("#next").hover(function(){ 
    //滑入清除定时器
    clearInterval(autoChange);
  },function(){ 
    //滑出则重置定时器
    autoChangeAgain();
  });
  //右箭头点击处理
  $("#next").click(function(){ 
    curIndex = (curIndex < imgLen - 1) ? (++curIndex) : 0;
    changeTo(curIndex);
  });
  //对右下角按钮index进行事件绑定处理等
  $(".indexList").find("li").each(function(item){ 
    $(this).hover(function(){ 
      clearInterval(autoChange);
      changeTo(item);
      curIndex = item;
    },function(){ 
      autoChangeAgain();
    });
  });
  //清除定时器时候的重置定时器--封装
  function autoChangeAgain(){ 
      autoChange = setInterval(function(){ 
      if(curIndex < imgLen-1){ 
        curIndex ++;
      }else{ 
        curIndex = 0;
      }
    //调用变换处理函数
      changeTo(curIndex); 
    },2500);
    }
  function changeTo(num){ 
    var goLeft = num * 400;
    $(".imgList").animate({left: "-" + goLeft + "px"},500);
    $(".infoList").find("li").removeClass("infoOn").eq(num).addClass("infoOn");
    $(".indexList").find("li").removeClass("indexOn").eq(num).addClass("indexOn");
  }
  </script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.hqep.model.userModel" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
userModel model = (userModel)session.getAttribute("userlog");
if(model==null)
response.sendRedirect( "/hqep/view/login.html"); 
%>

<!DOCTYPE HTML>
<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>html5问卷调查表</title>
		<link rel="stylesheet" type="text/css" href="../css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="../css/demo.css" />
		<link rel="stylesheet" type="text/css" href="../css/component.css" />
		<link rel="stylesheet" type="text/css" href="../css/cs-select.css" />
		<link rel="stylesheet" type="text/css" href="../css/cs-skin-boxes.css" />
		<script src="../js/modernizr.custom.js"></script>
	</head>
	<body>
<div class="container">

    <div class="fs-form-wrap" id="fs-form-wrap">
        <div class="fs-title">
            <h1>问卷调查表</h1>
        </div>
        <form id="myform" class="fs-form fs-form-full" autocomplete="off" action="/hqep/ans/in" method="post">
        <input type="hidden" name="userid" value="<%=model.getId()%>"/>
            <ol class="fs-fields">
                <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" for="q1">1?</label>
                     <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q1a" name="q1" type="radio" value="A"/><label for="q1a"
                                                                                                class="radio-conversion">1:1</label></span>
                        <span><input id="q1b" name="q1" type="radio" value="B"/><label for="q1b" 
                                                                                            class="radio-social">1:2</label></span>
                        <span><input id="q1c" name="q1" type="radio" value="C"/><label for="q1c"
                                                                                            class="radio-mobile">1:3</label></span>
                     <span><input id="q1d" name="q1" type="radio" value="D"/><label for="q1d"
                                                                                            class="radio-mobile">1:4</label></span>                                                                       
                    </div>
                </li>
               <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" for="q2">2?</label>
                     <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q2a" name="q2" type="radio" value="A"/><label for="q2a"
                                                                                                class="radio-conversion">2:1</label></span>
                        <span><input id="q2b" name="q2" type="radio" value="B"/><label for="q2b"
                                                                                            class="radio-social">2:2</label></span>
                        <span><input id="q2c" name="q2" type="radio" value="C"/><label for="q2c"
                                                                                            class="radio-mobile">2:3</label></span>
                       <span><input id="q2d" name="q2" type="radio" value="D"/><label for="q2d"
                                                                                            class="radio-mobile">2:4</label></span>
                    </div>
                </li>
                <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" for="q3"
                           data-info="This will help us know what kind of service you need">3?</label>
                    <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q3a" name="q3" type="radio"  value="A"/><label for="q3a"
                                                                                                class="radio-conversion">3:1</label></span>
                        <span><input id="q3b" name="q3" type="radio"  value="B"/><label for="q3b"
                                                                                            class="radio-social">3:2</label></span>
                        <span><input id="q3c" name="q3" type="radio"  value="C"/><label for="q3c"
                                                                                            class="radio-mobile">3:3</label></span>
                        <span><input id="q3d" name="q3" type="radio"  value="D"/><label for="q3d"
                                                                                            class="radio-mobile">3:4</label></span>
                    </div>
                </li>
                <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" for="q4"
                           data-info="This will help us know what kind of service you need">4?</label>
                    <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q4a" name="q4" type="radio"  value="A"/><label for="q4a"
                                                                                                class="radio-conversion">4:1</label></span>
                        <span><input id="q4b" name="q4" type="radio"  value="B"/><label for="q4b"
                                                                                            class="radio-social">4:2</label></span>
                        <span><input id="q4c" name="q4" type="radio"  value="C"/><label for="q4c"
                                                                                            class="radio-mobile">4:3</label></span>
                        <span><input id="q4d" name="q4" type="radio"  value="D"/><label for="q4d"
                                                                                            class="radio-mobile">4:4</label></span>
                    </div>
                </li>
                <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" for="q5"
                           data-info="This will help us know what kind of service you need">5?</label>
                    <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q5a"  name="q5" type="radio"  value="A"/><label for="q5a"
                                                                                                class="radio-conversion">5:1</label></span>
                        <span><input id="q5b" name="q5" type="radio"  value="B"/><label for="q5b"
                                                                                            class="radio-social">5:2</label></span>
                        <span><input id="q5c" name="q5" type="radio"  value="C"/><label for="q5c"
                                                                                            class="radio-mobile">5:3</label></span>
                        <span><input id="q5d" name="q5" type="radio"  value="D"/><label for="q5d"
                                                                                            class="radio-mobile">5:4</label></span>
                    </div>
                </li>
                  <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" for="q5"
                           data-info="This will help us know what kind of service you need">6?</label>
                    <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q6a" name="q6" type="radio"  value="A"/><label for="q6a"
                                                                                                class="radio-conversion">6:1</label></span>
                        <span><input id="q6b" name="q6" type="radio"  value="B"/><label for="q6b"
                                                                                            class="radio-social">6:2</label></span>
                        <span><input id="q6c" name="q6" type="radio"  value="C"/><label for="q6c"
                                                                                            class="radio-mobile">6:3</label></span>
                        <span><input id="q6d" name="q6" type="radio"  value="D"/><label for="q6d"
                                                                                            class="radio-mobile">6:4</label></span>
                    </div>
                </li>
                 <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" 
                           data-info="This will help us know what kind of service you need">7?</label>
                    <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q7a" name="q7" type="radio"  value="A"/><label for="q7a"
                                                                                                class="radio-conversion">7:1</label></span>
                        <span><input id="q7b" name="q7" type="radio"  value="B"/><label for="q7b"
                                                                                            class="radio-social">7:2</label></span>
                        <span><input id="q7c" name="q7" type="radio"  value="C"/><label for="q7c"
                                                                                            class="radio-mobile">7:3</label></span>
                        <span><input id="q7d" name="q7" type="radio"  value="D"/><label for="q7d"
                                                                                            class="radio-mobile">7:4</label></span>
                    </div>
                </li>
                <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" 
                           data-info="This will help us know what kind of service you need">8?</label>
                    <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q8a" name="q8" type="radio"  value="A"/><label for="q8a"
                                                                                                class="radio-conversion">8:1</label></span>
                        <span><input id="q8b" name="q8" type="radio"  value="B"/><label for="q8b"
                                                                                            class="radio-social">8:2</label></span>
                        <span><input id="q8c" name="q8" type="radio"  value="C"/><label for="q8c"
                                                                                            class="radio-mobile">8:3</label></span>
                        <span><input id="q8d" name="q8" type="radio"  value="D"/><label for="q8d"
                                                                                            class="radio-mobile">8:4</label></span>
                    </div>
                </li>
                <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" 
                           data-info="This will help us know what kind of service you need">9?</label>
                    <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q9a" name="q9" type="radio"  value="A"/><label for="q9a"
                                                                                                class="radio-conversion">9:1</label></span>
                        <span><input id="q9b" name="q9" type="radio"  value="B"/><label for="q9b"
                                                                                            class="radio-social">9:2</label></span>
                        <span><input id="q9c" name="q9" type="radio"  value="C"/><label for="q9c"
                                                                                            class="radio-mobile">9:3</label></span>
                        <span><input id="q9d" name="q9" type="radio"  value="D"/><label for="q9d"
                                                                                            class="radio-mobile">9:4</label></span>
                    </div>
                </li>
                <li data-input-trigger>
                    <label class="fs-field-label fs-anim-upper" 
                           data-info="This will help us know what kind of service you need">10?</label>
                    <div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower">
                        <span><input id="q10a" name="q10" type="radio"  value="A"/><label for="q10a"
                                                                                                class="radio-conversion">10:1</label></span>
                        <span><input id="q10b" name="q10" type="radio"  value="B"/><label for="q10b"
                                                                                            class="radio-social">10:2</label></span>
                        <span><input id="q10c" name="q10" type="radio"  value="C"/><label for="q10c"
                                                                                            class="radio-mobile">10:3</label></span>
                        <span><input id="q10d" name="q10" type="radio"  value="D"/><label for="q10d"
                                                                                            class="radio-mobile">10:4</label></span>
                    </div>
                </li>
                <li>
                    <label class="fs-field-label fs-anim-upper" for="q4">描述你如何评价你的新网站</label>
                    <textarea class="fs-anim-lower" id="q11" name="q11" placeholder="Describe here"></textarea>
                </li>
                <li>
                    <label class="fs-field-label fs-anim-upper" for="q12">你认为你的网站值多少钱?</label>
                    <input class="fs-mark fs-anim-lower" id="q12" name="q12" type="text" placeholder="1000" step="100"
                           min="100"/>
                </li>
                <li>
                    <label class="fs-field-label fs-anim-upper" for="q6"
                           data-info="We won't send you spam, we promise...">你的家庭住址是?</label>
                    <input class="fs-anim-lower" id="q13" name="q13" type="text" placeholder="dean@road.us" required/>
                </li>
            </ol><!-- /fs-fields -->
            <button class="fs-submit" type="submit">提交问卷</button>
        </form><!-- /fs-form -->
    </div><!-- /fs-form-wrap -->

</div><!-- /container -->
<script src="../js/classie.js"></script>
<script src="../js/selectFx.js"></script>
<script src="../js/fullscreenForm.js"></script>
<script>
    (function () {
        var formWrap = document.getElementById('fs-form-wrap');

        [].slice.call(document.querySelectorAll('select.cs-select')).forEach(function (el) {
            new SelectFx(el, {
                stickyPlaceholder: false,
                onChange: function (val) {
                    document.querySelector('span.cs-placeholder').style.backgroundColor = val;
                }
            });
        });

        new FForm(formWrap, {
            onReview: function () {
                classie.add(document.body, 'overview'); // for demo purposes only
            }
        });
    })();
</script>
</body>
</html>

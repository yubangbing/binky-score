<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>获取华东理工成绩单</title>
    <link type="text/css" rel="stylesheet" href="./css/login.css"/>
    <script type="text/javascript" src="./js/jquery-1.6.4.js"></script>
</head>
<body bgcolor="#619bd8" leftmargin="0" topmargin="0" scroll="no">
<div align="center">
    <center>
        <table cellspacing="0" cellpadding="0" width="0" border="0">
            <tbody>
            <tr>
                <td valign="bottom" height="80"></td>
            </tr>
            <tr>
                <td>
                    <table class="OfficeLogin" height="200" cellspacing="0" cellpadding="0" width="100%" bgcolor="gainsboro" border="0">
                        <tbody>
                        <tr>
                            <td>
                                <table height="150" cellspacing="0" cellpadding="0" width="100%" border="0">
                                    <tbody>
                                    <tr>
                                        <td width="5" height="5"></td>
                                        <td height="5"></td>
                                        <td width="5" height="5"></td>
                                    </tr>
                                    <tr>
                                        <td width="5" height="5"></td>
                                        <td width="100"><img src="./images/login_win.png"></td>
                                        <td width="5" height="5"></td>
                                    </tr>
                                    <tr>
                                        <td width="5" height="5"></td>
                                        <td height="5"></td>
                                        <td width="5" height="5"></td>
                                    </tr>
                                    </tbody></table>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <form id="formlogin" method="post" onsubmit="return false;">

                                    <table height="120" cellspacing="0" cellpadding="0" border="0">
                                        <tbody>
                                        <tr>
                                            <td height="17" width="155">&nbsp;</td>
                                            <td height="17"></td>
                                        </tr>
                                        <tr>
                                            <td align="right" width="155" height="30">用户名(学号)：</td>
                                            <td>
                                                <input name="username" type="text" id="loginname" class="text">

                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" width="155" height="30">密&nbsp;&nbsp;码：</td>
                                            <td>
                                                <input name="password" type="password" id="nloginpwd" class="text">

                                            </td>
                                        </tr>

                                        <tr>
                                            <td align="right" colspan="2" height="18">
                                                <table cellspacing="0" cellpadding="0" width="100%" border="0">
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="4">
																					<span id="errorSpan" class="error">
																						<br>

																					</span>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td width="100%" align="center"><input type="button" id="loginsubmit"  value="登录(Login)"></td>

                                                    </tr>
                                                    </tbody></table>
                                            </td>
                                        </tr>
                                        </tbody></table>
                                </form>
                            </td>
                        </tr>
                        </tbody></table>
                </td>
            </tr>
            </tbody></table>
    </center>
</div>
<div id="waring" align="center" style="color: brown;font-size: 16px"></div>
<script type="text/javascript">
    var LOGIN = {
        checkInput:function() {
            if ($("#loginname").val() == "") {
                alert("学号不能为空");
                $("#loginname").focus();
                return false;
            }
            if ($("#loginname").val().length != 9) {
                alert("学号不正确");
                $("#loginname").focus();
                return false;
            }
            if ($("#nloginpwd").val() == "") {
                alert("密码不能为空");
                $("#nloginpwd").focus();
                return false;
            }
            return true;
        },
        doLogin:function() {
            $.post("${pageContext.request.contextPath}/user/getscore", $("#formlogin").serialize(),function(data){
                if(data =="1"){
                    alert("请使用校园网");
                    return;
                }
                if(data =="2"){
                    alert("用户名或密码错误");
                    $("#waring").html("");
                    return;
                }
                if(data.length>1){
                    location.href = "${pageContext.request.contextPath}/user/redirect?soup="+data;
                }
            });
        },
        login:function() {
            if (this.checkInput()) {
                $("#waring").html("呃。。会有点儿慢，等几秒钟。。。。。");
                this.doLogin();
            }
        }

    };
    $(function(){
        $("#loginsubmit").click(function(){
            LOGIN.login();
        });
    });
</script>

</body>


</html>
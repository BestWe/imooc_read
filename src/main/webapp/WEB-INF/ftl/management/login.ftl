<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
    <style type="text/css">
        .container {
            width: 420px;
            height: 320px;
            min-height: 320px;
            max-height: 320px;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            margin: auto;
            padding: 20px;
            z-index: 130;
            border-radius: 8px;
            background-color: #fff;
            font-size: 16px;
        }

        .layui-input {
            border-radius: 5px;
            margin-left: -50px;
            width: 350px;;
            height: 40px;
            font-size: 15px;
            text-align: center  ;
            font-weight: 700;
        }

        .layui-form-item {
            margin-left: -20px;
        }

        #logoid {
            margin-top: -16px;
            padding-left: 150px;
            padding-bottom: 15px;
        }

        .layui-btn {
            margin-left: -50px;
            border-radius: 5px;
            width: 350px;
            height: 40px;
            font-size: 15px;
        }

        .verity {
            width: 120px;
        }

        .font-set {
            font-size: 13px;
            text-decoration: none;
            margin-left: 120px;
        }

        a:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>
<!--表单内容-->
<form class="layui-form" action="/management/user/login">
    <div class="container">
        <div class="layui-form-item">
            <div class="layui-input-block">
               <h1 style="text-align: center;margin-left: -100px">慕课书评系统</h1>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input class="layui-input layui-form-danger" lay-verify="required" type="text" name="username" placeholder="请输入用户名">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input class="layui-input layui-form-danger" lay-verify="required" type="text" name="password" placeholder="请输入密码">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="login" id="login" type="submit"  >登陆
                </button>
            </div>
        </div>
    </div>
</form>


<script src="/resources/layui/layui.js"></script>
<script>
    layui.use(['element', 'form'], function () {
        var element = layui.element, form = layui.form, layer = layui.layer;
        form.render();
    })
</script>
</body>
</html>
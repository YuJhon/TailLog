<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Enbrands2.0 Web SSH</title>
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.js"></script>
    <script src="/static/sockjs.min.js"></script>
</head>
<body>

<div id="log-container" style="height: 800px; overflow-y: scroll; background: #0e0e0e; color: #04f434; padding: 20px;">
    <div>
        </br>Enbrands 2.0 WEB SSH</br>
        </br>Version 1.0</br>
        </br>大哥无聊</br>

        <button id="openLog">开启日志</button></div><br>
    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        var websocket = null;
        if (window['WebSocket']) {
            websocket = new WebSocket("ws://" + window.location.host + '/log');
        }
        else {
            websocket = new new SockJS('/websocket/socketjs');
        }

        websocket.onopen = function (event) {
            console.log('open', event);
        };

        websocket.onmessage = function (event) {
            // 接收服务端的实时日志并添加到HTML页面中
            $("#log-container div").append(event.data);
            // 滚动条滚动到最低部
            $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
        };

        /** 事件触发 **/
        $("#openLog").bind('click',asnyReq);
    });

    /** 异步触发事件**/
    function asnyReq() {
        $.ajax({
            type: "GET",
            data: {},
            url: "/debug/socketDemo.do?TTL=" + Math.random(),
            datatype: "json",
            success: function (result) {
                console.log(result);
                if(undefined!=result&&result!=null)
                {
                    if("0"==result.retCode){
                        $("#openLog").hide();
                    }
                }
            }
        });
    }
</script>
</body>
</html>
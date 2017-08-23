<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- <%@include file="/WEB-INF/jsp/common/head.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
     <!-- 留言管理start -->
<div id="myTabs">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><h4>给管理员留言</h4></a></li>
    
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="message">
    	<div id="messageForm">
    		<textarea class="form-control" id="messageContent" name="messageContent" type="text" style="width:680px;" placeholder="请输入留言内容"></textarea><br>
    		<input class="form-control" id="inputCode" name="inputCode" type="text" style="width:80px;" placeholder="请输入验证码"/>
    		<span id="checkCode"  onclick="createCode()"></span>
    		<button class="btn btn-primary addMessage" onclick="validateCode()">提交</button>
    		<span class="ret" style="color:red"></span>
    	</div>
    	
    	<div id="messagetitle"><h4>我的留言:</h4></div>
		<c:forEach items="${messageList }" var="message">
		<div class="panel panel-success" style="box-shadow: 0px 0px 4px #ccc;border: solid 1px #CCC;margin-bottom:15px; padding:10px;" id="${message.id}"> 
			<div class="panel-body"><h4>${message.createdBy}&nbsp;留言:</h4> ${message.messageContent} <span class="pull-right">${message.createTime }</span></div>
			<c:forEach items="${replyList}" var="reply">
			<c:if test="${reply.messageId ==message.id }">
				<div class="panel-body" style="border-top: solid 1px #CCC;margin:10px 0px 0px 0px;padding:10px 0px 0px 20px;"><h4>${reply.createdBy}&nbsp;回复:</h4> ${reply.replyContent} <span class="pull-right">${reply.createTime }</span></div> 
			</c:if>
			</c:forEach>
		</div>
	</c:forEach>

	</div>
  </div>

</div>
     <!-- 留言管理end -->
 
 
<%-- <%@include file="/WEB-INF/jsp/common/foot.jsp"%> --%>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>

 
  <script type="text/javascript">
$('#myTabs a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
});

   var code;
   $(function(){
   	createCode();
   });
   function createCode()
        {
            code = "";
            var codeLength = 4; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
            for(var i = 0; i < codeLength; i++)
            {
                var charNum = Math.floor(Math.random() * 52);
                code += codeChars[charNum];
            }
            if(checkCode)
            {
                $("#checkCode").html(code);
            }
        }
        function validateCode()
        {
            var inputCode=document.getElementById("inputCode").value;
            var inputContent=document.getElementById("messageContent").value;
            if(inputContent.length <= 0)
            {
                $(".ret").html("请输入留言内容!");
            }
            else if(inputCode.length <= 0)
            {
                $(".ret").html("请输入验证码！");
            }
            else if(inputCode.toUpperCase() != code.toUpperCase())
            {
                $(".ret").html("验证码输入有误！");
                createCode();
            }
            else
            {
                addMessage();
            }
        }
</script>   

<script src="${pageContext.request.contextPath}/statics/localjs/information/message.js"></script>

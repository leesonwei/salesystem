/* $.post("referQuery",{userid:$("#userid").val()},function(result){
	 debugger;
	 $('.tree li:has(ul)')
 });*/

$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
  $('.tree li.parent_li > span').on('click', function (e) {
    var children = $(this).parent('li.parent_li').find(' > ul > li');
    if (children.is(":visible")){
      children.hide('fast');
      $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
    } else {
      children.show('fast');
      $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
    }
    e.stopPropagation();
  });
    
function viewUser(id){
	var path=$("#path").val();
	var viewInfo=$(".viewInfo");
	$.ajax({
		type:"POST",
		url:path+'/infoquery/viewInfo',
		data:{id:id},
		datatype:"json",
		success:function(result){			
			var user=eval('('+result+')');
			var row='';
			viewInfo.html('');
			row+="<p>申请日期:"+user.createTime+"</p>";
			row+="<p>用户名:"+user.loginCode+"</p>";
			row+="<p>姓名:"+user.userName+"</p>";
			row+="<p>证件号码:"+user.idCard+"</p>";
			row+="<p>收货国家（地区）:"+user.country+"</p>";
			row+="<p>联系电话:"+user.mobile+"</p>";
			row+="<p>移动电话:"+user.mobile+"</p>";
			row+="<p>电子邮件:"+user.email+"</p>";
			row+="<p>收货地址:"+user.userAddress+"</p>";
			row+="<p>邮政编码:"+user.postCode+"</p>";
			row+="<p>推荐人用户名:"+user.referCode+"</p>";
			viewInfo.append(row);
		},
		error:function(data){
			alert("error");
		}
	});
}  
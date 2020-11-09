<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<html>

	<head>
		<title>木地酒庄后台管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${pageContext.request.contextPath}/static/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/static/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/static/assets/css/main-min.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div class="header">
			
			<div class="dl-title">
				<!--<img src="/chinapost/Public/${pageContext.request.contextPath}/static/assets/img/top.png">-->
			</div>

			<div class="dl-log">欢迎您，<span class="dl-log-user">root</span>
				<a href="http://127.0.0.1:5500/" title="退出系统" class="dl-log-quit">[退出]</a>
			</div>
		</div>
		<div class="content">
			<div class="dl-main-nav">
				<div class="dl-inform">
					<div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div>
				</div>
				<ul id="J_Nav" class="nav-list ks-clear">
					<li class="nav-item dl-selected">
						<div class="nav-item-inner nav-home">系统管理</div>
					</li>
<%--					<li class="nav-item dl-selected">--%>
<%--						<div class="nav-item-inner nav-order">业务管理</div>--%>
<%--					</li>--%>
<%--					<li class="nav-item dl-selected">--%>
<%--						<div class="nav-item-inner nav-order">用户管理</div>--%>
<%--					</li>--%>
				</ul>
			</div>
			<ul id="J_NavContent" class="dl-tab-conten">

			</ul>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/js/bui-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/js/common/main-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/js/config-min.js"></script>
		<script>
			BUI.use('common/main', function() {
				var config = [{
					id: '1',
					menu: [{
						text: '系统管理',
						items: [{
							id: '1',
							text: '地区管理',
							href: 'admin/Area/index'
						}, {
							id: '2',
							text: '新闻管理',
							href: 'admin/News/index'
						}, {
							id: '3',
							text: '酒的详细信息',
							href: 'admin/Product/index'
						}, {
							id: '4',
							text: '酒类管理',
							href: 'admin/Type/index'
						}, {
							id: '5',
							text: '酒的库存管理',
							href: 'admin/ProductYear/index'
						}]
					}]
				}
				// , {
				// 	id: '7',
				// 	homePage: '9',
				// 	menu: [{
				// 		text: '业务管理',
				// 		items: [{
				// 			id: '9',
				// 			text: '查询业务',
				// 			href: 'Node/index.html'
				// 		},{
				// 			id: '10',
				// 			text: '新增业务',
				// 			href: 'Node/index.html'
				// 		}
				// 		]
				// 	}]
				// }
				];
				new PageUtil.MainPage({
					modulesConfig: config
				});
			});
		</script>
		<div style="text-align:center;">
			<p>
				<a href="http://www.mycodes.net/" target="_blank">zh</a>
			</p>
		</div>
	</body>
</html>
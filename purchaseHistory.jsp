<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品購入履歴</title>
<link rel="stylesheet" type ="text/css" href="css/cartAndPurchaseHistory.css">
<link rel="stylesheet" type ="text/css" href="css/error.css">
<link rel="stylesheet" type ="text/css" href="css/orion.css">
<link rel="stylesheet" type="text/css" href="./css/header.css">
</head>
<body>

  <jsp:include page="header.jsp"/>
  <div class="main-container" id="contents">

	<div class="page-title">
	  <h1>商品購入履歴一覧画面</h1>
	</div>

  <s:if test="historyList != null && historyList.size()>0">
   <table class="cart-phistory-table">
	<thead>
    <tr>
      <th>商品名</th>
      <th>ふりがな</th>
      <th>商品画像</th>
      <th>発売会社名</th>
      <th>発売年月日</th>
      <th>値段</th>
      <th>個数</th>
      <th>合計金額</th>
      <th>宛先名前</th>
      <th>宛先住所</th>
    </tr>
    </thead>

	<tbody>
    <s:iterator value="historyList">
    <tr>
      <td><s:property value="productName"/></td>
      <td><s:property value="nameKana"/></td>
      <td><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' width=50px height="50px"/></td>
      <td><s:property value="releaseCompany"/></td>
      <td><s:property value="releaseDate"/></td>
      <td><s:property value="price"/></td>
      <td><s:property value="productCount"/></td>
      <td><s:property value="totalPrice"/></td>
      <td><s:property value="familyName"/><span>　</span><s:property value="firstName"/></td>
      <td><s:property value="userAddress"/></td>
    </tr>
    </s:iterator>
    </tbody>
   </table>

  <s:form action="DeletePurchaseHistoryAction">
  	<div class="submit_btn_box">
    <s:submit class="btn-field submit_btn" value="履歴削除"/>
    </div>
  </s:form>
 </s:if>

 <s:else>
    <div class="error">商品購入履歴情報がありません。</div>
  </s:else>
	</div>
</body>
</html>
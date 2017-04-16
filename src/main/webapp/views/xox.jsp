<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XOX GAME</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h4 align="center">XOX GAME</h4>
        <div class="table-responsive">
            <table align="center" id="game-table" border="1">
                <tr id="1">
                    <td class="game-table-td" id="1x1" row="1" column="1"></td>
                    <td class="game-table-td" id="1x2" row="1" column="2"></td>
                    <td id="1x3" class="game-table-td" row="1" column="3"></td>
                </tr>
                <tr id="2">
                    <td id="2x1" class="game-table-td" row="2" column="1"></td>
                    <td id="2x2" class="game-table-td" row="2" column="2"></td>
                    <td id="2x3" class="game-table-td" row="2" column="3"></td>
                </tr>
                <tr id="3">
                    <td id="3x1" class="game-table-td" row="3" column="1"></td>
                    <td id="3x2" class="game-table-td" row="2" column="2"></td>
                    <td id="3x3" class="game-table-td" row="3" column="3"></td>
                </tr>
            </table>
        </div>
        <br>
        <mytext>YOU ---> <strong>X</strong></mytext><br>
        <mytext>COMPUTER ---> <strong>O</strong></mytext><br><br>
        <div id="game-steps">

        </div>
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/xox.game.js"/>"></script>
</html>
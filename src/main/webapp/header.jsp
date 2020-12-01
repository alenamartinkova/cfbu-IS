<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<header>
    <div class="header">
        <div>
            <h1><a href="index.jsp">Project VIS</a></h1>
        </div>

        <div>
            <nav>
                <ul class="list">
                    <li class="list-item">
                        <a href="#">TEAMS</a>
                    </li>
                    <li class="list-item">
                        <a href="players-all.jsp">PLAYERS</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>

<style>
    * {
        font-family: Open-Sans, sans-serif;
    }

    main {
        min-height: 620px;
    }

    body {
        margin: 0;
    }

    header {
        width: 100%;
        border-bottom: 1px solid darkred;
        position: fixed;
        top: 0;
        background: white;
    }

    .header {
        display: flex;
        justify-content: space-between;
        max-width: 1400px;
        margin: 0 auto;
    }

    .header h1 {
        margin: 0;
        padding: 20px;
    }

    .btn {
        background-color: #000;
        border: none;
        color: white;
        padding: 15px 25px;
        border-radius: 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 10px 0 30px 0;
        cursor: pointer;
    }

    .list {
        width: 100%;
        margin: 20px 0 0 0;
    }

    .checkbox-list {
        padding-left: 0;
    }

    .list-item {
        list-style: none;
        position: relative;
        display: inline-block;
        padding: 10px;
    }

    main {
        margin-top: 150px;
    }

    .main-index h1 {
        color: darkred;
    }

    .main-index a {
        font-size: 24px;
        padding: 20px;
    }

    a {
        color: black;
        text-decoration: none;
    }

    a:hover {
        color: darkred;
    }

    li {
        list-style: none;
    }

    .row p {
        display: inline-block;
    }

    table, th, td {
        margin: 0 auto;
        border: 1px solid darkred;
    }

    th, td {
        padding: 3px 10px;
    }

    .gray-bg {
        background: #e6e6e6;
    }

    .detail-wrapper {
        max-width: 800px;
    }

    .row {
        display: flex;
        justify-content: space-between;
    }

</style>
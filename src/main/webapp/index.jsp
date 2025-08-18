<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome - Pahana Edu Billing System</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Reset some defaults */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        /* Navbar */
        .navbar {
            background-color: rgba(0,0,0,0.6);
            padding: 15px 0;
            color: #fff;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            box-shadow: 0 2px 5px rgba(0,0,0,0.3);
        }

        /* Hero container */
        .container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            color: #fff;
            padding: 50px 20px;
        }

        .container h2 {
            font-size: 48px;
            margin-bottom: 20px;
            text-shadow: 1px 1px 5px rgba(0,0,0,0.4);
        }

        .container p {
            font-size: 20px;
            margin-bottom: 40px;
            text-shadow: 1px 1px 3px rgba(0,0,0,0.3);
        }

        /* Styled button */
        .btn-primary {
            padding: 15px 35px;
            font-size: 18px;
            font-weight: bold;
            color: #fff;
            background: linear-gradient(45deg, #ff416c, #ff4b2b);
            border: none;
            border-radius: 50px;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        }

        .btn-primary:hover {
            transform: scale(1.05);
            box-shadow: 0 6px 20px rgba(0,0,0,0.3);
        }

        /* Responsive */
        @media(max-width: 768px){
            .container h2 {
                font-size: 36px;
            }
            .container p {
                font-size: 16px;
            }
            .btn-primary {
                font-size: 16px;
                padding: 12px 25px;
            }
        }
    </style>
</head>
<body>
<nav class="navbar">
    Pahana Edu Billing System
</nav>
<div class="container">
    <h2>Welcome to Pahana Edu Billing System</h2>
    <p>Manage your bookshop billing with ease and efficiency.</p>
    <a href="login.jsp" class="btn-primary">Login to Get Started</a>
</div>
</body>
</html>
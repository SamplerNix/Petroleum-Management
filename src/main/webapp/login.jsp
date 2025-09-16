<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Login Page</title>
  <style>
    * {
      box-sizing: border-box;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    body {
      margin: 0;
      padding: 0;
      background: #f0f2f5;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .login-container {
      background: white;
      padding: 40px 30px;
      border-radius: 10px;
      box-shadow: 0 0 15px rgba(0,0,0,0.1);
      width: 100%;
      max-width: 400px;
    }

    .login-container h2 {
      margin-bottom: 20px;
      text-align: center;
      color: #333;
    }

    .login-container label {
      display: block;
      margin-bottom: 8px;
      color: #555;
      font-weight: 600;
    }

    .login-container input[type="text"],
    .login-container input[type="password"] {
      width: 100%;
      padding: 10px 15px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      transition: border 0.3s;
    }

    .login-container input[type="text"]:focus,
    .login-container input[type="password"]:focus {
      border-color: #007bff;
      outline: none;
    }

    .login-container button {
      width: 100%;
      padding: 10px 15px;
      background: #007bff;
      border: none;
      color: white;
      font-size: 16px;
      border-radius: 5px;
      cursor: pointer;
      transition: background 0.3s;
    }

    .login-container button:hover {
      background: #0056b3;
    }

    .login-container .footer {
      margin-top: 15px;
      text-align: center;
      font-size: 14px;
      color: #888;
    }
  </style>
</head>
<body>

  <div class="login-container">
    <h2>Login</h2>
    <form action="your_backend_login_script.php" method="POST">
      <label for="username">Username</label>
      <input type="text" id="username" name="username" required />

      <label for="password">Password</label>
      <input type="password" id="password" name="password" required />

      <button type="submit">Login</button>
    </form>
    <div class="footer">
      &copy; 2025 Your Company
    </div>
  </div>

</body>
</html>

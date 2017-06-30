<?php
    $con =  mysqli_connect("127.0.0.1", "root", "root", "login");

    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $name, $username, $password, $age);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["name"] = $name;
        $response["username"] = $username;
        $response["password"] = $password;
	$response["age"] = $age;
    }

    echo json_encode($response);
?>
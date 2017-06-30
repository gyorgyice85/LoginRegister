<?php
    $con = mysqli_connect("127.0.0.1", "root", "root", "login");

    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, password, age) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $name, $username, $password, $age);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>
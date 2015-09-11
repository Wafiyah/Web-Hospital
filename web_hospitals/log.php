<?php

//create table users (userid varchar2(10), password varchar2(20), constraint pk_users primary key (userid));
//insert into users values('kharis', 'pass123');
error_reporting(E_ALL^E_NOTICE);
$nis=$_POST['user'];
$password=$_POST['password'];

if(empty($nis) or empty($password)){
    //echo "Empty";
	include 'index.php';}
else
{
    $db = "(DESCRIPTION =
        (ADDRESS = (PROTOCOL = TCP)(HOST = patronus.ad-ins.com)(PORT = 1521))
        (CONNECT_DATA =
          (SERVER = DEDICATED)
          (SERVICE_NAME = XE)
        )
      )" ;
    $connect = oci_connect("system", "ebha", "localhost");
    $query = "SELECT * from system.Login_table WHERE user_id='".$nis."' and password='".$password."'";
    $result = oci_parse($connect, $query);
    oci_execute($result);
    $tmpcount = oci_fetch($result);
    if ($tmpcount!=0) {
			
			INCLUDE 'after_login.php';}
		
        //echo "Login Success      ^_^   ^_^  ^_^ ";}
    else
    
	{
				
		include '.php';
				//echo "Login Failed";
    }
}
?>
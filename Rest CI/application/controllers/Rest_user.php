<?php

require APPPATH .'/libraries/REST_Controller.php';

class User extends REST_Controller {

  var $API="";

  function __construct(){

    parent::__construct();
    $this->API="http://192.168.43.213:81/android_lowker/";
  }

	function login_get($username,$password){

        $get_user = $this->db->query("SELECT * FROM user where username='$username' and password='$password'")->result();
          
        if($get_user)
          $this->response(
           array(
               "status" => "success",
               "result" => $get_user
           )
       );
    }
    // function login_post(){
    // 	 $data_user = array(
    //        'username'     => $this->post('username'),
    //        'password'   => $this->post('password'),
    //        );

    //    $user = json_decode($this->API.'User/login',$data_user, array(CURLOPT_BUFFERSIZE => 10));

    // 	 //cek  validasi
    // 	 if (empty($data_user['username']) || empty($data_user['password'])) {
    // 	 	$this->response(
    // 	 			array(
    // 	 				"status"=> "failed",
    // 	 				"message" => "semua harus diisi"
    // 	 			)
    // 	 	);
    // 	 	# code...
    // 	 } else {
    // 	 	$insert = $this->db->insert('user', $data_user);

    // 	 	if ($insert) {
    // 	 		$this->response(
    // 	 			array(
    // 	 					"status"=>"success",
    // 	 					"result"=> array($data_user),
    // 	 					"message"=>$insert
    // 	 			)
    // 	 		);
    // 	 		# code...
    // 	 	}
    // 	 	# code...
    // 	 }
    	 
          
    // }
}
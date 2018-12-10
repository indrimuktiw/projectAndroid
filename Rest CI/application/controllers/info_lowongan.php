<?php
require ('application/libraries/REST_Controller.php');

class info_lowongan extends REST_Controller {

   // show data info_lowongan
   function user_get() {
       $get_transaksi = $this->db->query("SELECT info.id_info_lowongan, info.id_perusahaan, info.id_kategori, info.nama_pekerjaan, info.syarat FROM perusahaan, info_lowongan info, kategori Where info.id_perusahaan=perusahaan.id_perusahaan AND info.id_kategori=kategori.id_kategori")->result();
     
       $this->response(array("status"=>"success","result" => $get_transaksi));
   }

   // insert info_lowongan
   function user_post() {
       $data_info_lowongan = array(
           'id_info_lowongan'  => $this->post('id_info_lowongan'),
           'id_perusahaan'     => $this->post('id_perusahaan'),
           'id_kategori'       => $this->post('id_kategori'),
           'nama_pekerjaan'    => $this->post('nama_pekerjaan'),
           'syarat'            => $this->post('syarat')
  );
      
       if  (empty($data_info_lowongan['id_info_lowongan'])){
            $this->response(array('status'=>'fail',"message"=>"id_info_lowongan kosong"));
       }
       else {
           $getId = $this->db->query("Select id_info_lowongan from info_lowongan where id_info_lowongan='".$data_info_lowongan['id_info_lowongan']."'")->result();
          
           //jika id_info_lowongan tidak ada dalam database maka eksekusi insert
           if (empty($getId)){
                    if (empty($data_info_lowongan['id_perusahaan'])){
                       $this->response(array('status'=>'fail',"message"=>"id_perusahaan kosong"));
                    }
                    else if(empty($data_info_lowongan['syarat'])){
                       $this->response(array('status'=>'fail',"message"=>"syarat kosong"));
                    }else if(empty($data_info_lowongan['id_kategori'])){
                       $this->response(array('status'=>'fail',"message"=>"id_kategori kosong"));
                    }else if(empty($data_info_lowongan['nama_pekerjaan'])){
                       $this->response(array('status'=>'fail',"message"=>"nama_pekerjaan kosong"));
                    }
                    else{
                       //jika masuk pada else atau kondisi ini maka dipastikan seluruh input telah di set
                       //jika akan melakukan pembelian id_perusahaan dan id_kategori harus dipastikan ada
                       $getIdPerusahaan = $this->db->query("Select id_perusahaan from perusahaan Where id_perusahaan='".$data_info_lowongan['id_perusahaan']."'")->result();

                       $getIdKategori = $this->db->query("Select id_kategori from kategori Where id_kategori='".$data_info_lowongan['id_kategori']."'")->result();
                       $message="";
                       if (empty($getIdPerusahaan)) $message.="id_perusahaan tidak ada/salah ";

                          if (empty($getIdKategori)) {
                           if (empty($message)) {
                               $message.="id_kategori tidak ada/salah";
                           }
                           else {
                               $message.="dan id_kategori tidak ada/salah";
                           }
                       }
                       if (empty($message)){
                           $insert= $this->db->insert('info_lowongan',$data_info_lowongan);
                           if ($insert){
                               $this->response(array('status'=>'success','result' => $data_info_lowongan,"message"=>$insert));   
                           }
                          
                       }else{
                           $this->response(array('status'=>'fail',"message"=>$message));   
                       }
                      
                    }
           }else{
               $this->response(array('status'=>'fail',"message"=>"id_info sudah ada"));
           }  
       }
   }

   // update data info_lowongan
   function user_put() {
       $data_info_lowongan = array(
                   'id_info_lowongan'    => $this->put('id_info_lowongan'),
                   'id_perusahaan'      => $this->put('id_perusahaan'),
                   'id_kategori'    => $this->put('id_kategori'),
                   'nama_pekerjaan'     => $this->put('nama_pekerjaan'),
                   'syarat'        => $this->put('syarat')
                   );
       if  (empty($data_info_lowongan['id_info_lowongan'])){

$this->response(array('status'=>'fail',"message"=>"id_info_lowongan kosong"));
       }else{
           $getId = $this->db->query("Select id_info_lowongan from info_lowongan where id_info_lowongan='".$data_info_lowongan['id_info_lowongan']."'")->result();
           //jika id_info_lowongan harus ada dalam database
           if (empty($getId)){
             $this->response(array('status'=>'fail',"message"=>"id_info_lowongan tidak ada/salah")); 
           }else{
               //jika masuk disini maka dipastikan id_info_lowongan ada dalam database
                if (empty($data_info_lowongan['id_perusahaan'])){
                   $this->response(array('status'=>'fail',"message"=>"id_perusahaan kosong"));
                }
                else if(empty($data_info_lowongan['syarat'])){
                   $this->response(array('status'=>'fail',"message"=>"syarat kosong"));
                }else if(empty($data_info_lowongan['id_kategori'])){
                   $this->response(array('status'=>'fail',"message"=>"id_kategori kosong"));
                }else if(empty($data_info_lowongan['nama_pekerjaan'])){
                       $this->response(array('status'=>'fail',"message"=>"nama_pekerjaan kosong"));
                } 
                else{
                   //jika masuk pada else atau kondisi ini maka dipastikan seluruh input telah di set
                   //jika akan melakukan edit pembelian id_perusahaan dan id_kategori harus dipastikan ada
                   $getIdPerusahaan = $this->db->query("Select id_perusahaan from perusahaan Where id_perusahaan='".$data_info_lowongan['id_perusahaan']."'")->result();

                       $getIdKategori = $this->db->query("Select id_kategori from kategori Where id_kategori='".$data_info_lowongan['id_kategori']."'")->result();
                   $message="";
                   if (empty($getIdPerusahaan)) $message.="id_perusahaan tidak ada/salah ";
                   if (empty($getIdKategori)) {

    if (empty($message)) {
                           $message.="id_kategori tidak ada/salah";
                       }
                       else {
                           $message.="dan id_kategori tidak ada/salah";
                       }
                   }
                   if (empty($message)){
                       $this->db->where('id_info_lowongan',$data_info_lowongan['id_info_lowongan']);
                       $update= $this->db->update('info_lowongan',$data_info_lowongan);
                       if ($update){
                           $this->response(array('status'=>'success','result' => $data_info_lowongan,"message"=>$update));
                       }
                      
                   }else{
                       $this->response(array('status'=>'fail',"message"=>$message));   
                   }
                }
           }

       }
   }

   // delete info_lowongan
   function user_delete() {
       $id_info_lowongan = $this->delete('id_info_lowongan');
       if (empty($id_info_lowongan)){
           $this->response(array('status' => 'fail', "message"=>"id_info_lowongan harus diisi"));
       } else {
           $this->db->where('id_info_lowongan', $id_info_lowongan);
           $delete = $this->db->delete('info_lowongan');  
           if ($this->db->affected_rows()) {
               $this->response(array('status' => 'success','message' =>"Berhasil delete dengan id_info_lowongan = ".$id_info_lowongan));
           } else {

   $this->response(array('status' => 'fail', 'message' =>"id_info_lowongan tidak dalam database"));
           }
       }
   }
}  

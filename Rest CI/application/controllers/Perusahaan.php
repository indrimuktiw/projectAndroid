<?php

// use Restserver\Libraries\REST_Controller;
// defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require('application/libraries/REST_Controller.php');
require APPPATH . 'libraries/Format.php';

class Perusahaan extends REST_Controller {

    // Konfigurasi letak folder untuk upload image
    private $folder_upload = 'uploads/';

    function all_get(){
        $get_perusahaan = $this->db->query("
            SELECT
                id_perusahaan,
                nama_perusahaan,
                alamat_perusahaan,
                no_telp_perusahaan,
                photo_url
 FROM perusahaan")->result();
       $this->response(
           array(
               "status" => "success",
               "result" => $get_perusahaan
           )
       );
    }

    function all_post() {

        $action  = $this->post('action');
        $data_perusahaan = array(
                        'id_perusahaan' => $this->post('id_perusahaan'),
                        'nama_perusahaan'       => $this->post('nama_perusahaan'),
                        'alamat_perusahaan'     => $this->post('alamat_perusahaan'),
                        'no_telp_perusahaan'      => $this->post('no_telp_perusahaan'),
                        'photo_url'   => $this->post('photo_url')
                    );

        switch ($action) {
            case 'insert':
                $this->insertPerusahaan($data_perusahaan);
                break;
            
            case 'update':
                $this->updatePerusahaan($data_perusahaan);
                break;
            
            case 'delete':
                $this->deletePerusahaan($data_perusahaan);
                break;
            
            default:
                $this->response(
                    array(
                        "status"  =>"failed",
                        "message" => "action harus diisi"
                    )
                );
                break;
        }
    }
function insertPerusahaan($data_perusahaan){

        // Cek validasi
        if (empty($data_perusahaan['nama_perusahaan']) || empty($data_perusahaan['alamat_perusahaan']) || empty($data_perusahaan['no_telp_perusahaan'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "Nama / alamat / telp harus diisi"
                )
            );
        } else {

            $data_perusahaan['photo_url'] = $this->uploadPhoto();

            $do_insert = $this->db->insert('perusahaan', $data_perusahaan);
           
            if ($do_insert){
                $this->response(
                    array(
                        "status" => "success",
                        "result" => array($data_perusahaan),
                        "message" => $do_insert
                    )
                );
            }
        }
    }

    function updatePerusahaan($data_perusahaan){

        // Cek validasi
        if (empty($data_perusahaan['nama_perusahaan']) || empty($data_perusahaan['alamat_perusahaan']) || empty($data_perusahaan['no_telp_perusahaan'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "Nama / alamat / telp harus diisi"
                )
            );
        } else {
            // Cek apakah ada di database
            $get_perusahaan_baseID = $this->db->query("
                SELECT 1
 FROM perusahaan
                WHERE id_perusahaan =  {$data_perusahaan['id_perusahaan']}")->num_rows();

            if($get_perusahaan_baseID === 0){
                // Jika tidak ada
                $this->response(
                    array(
                        "status"  => "failed",
                        "message" => "ID Perusahaan tidak ditemukan"
                    )
                );
            } else {
                // Jika ada
                $data_perusahaan['photo_url'] = $this->uploadPhoto();

                if ($data_perusahaan['photo_url']){
                    // Jika upload foto berhasil, eksekusi update
                    $update = $this->db->query("
                        UPDATE perusahaan SET
                            nama_perusahaan = '{$data_perusahaan['nama_perusahaan']}',
                            alamat_perusahaan = '{$data_perusahaan['alamat_perusahaan']}',
                            no_telp_perusahaan = '{$data_perusahaan['no_telp_perusahaan']}',
                            photo_url = '{$data_perusahaan['photo_url']}'
                        WHERE id_perusahaan = '{$data_perusahaan['id_perusahaan']}'");

                } else {
                    // Jika foto kosong atau upload foto tidak berhasil, eksekusi update
                    $update = $this->db->query("
                        UPDATE perusahaan
                        SET
                            nama_perusahaan    = '{$data_perusahaan['nama_perusahaan']}',
                            alamat_perusahaan  = '{$data_perusahaan['alamat_perusahaan']}',
                            no_telp_perusahaan    = '{$data_perusahaan['no_telp_perusahaan']}'
                        WHERE id_perusahaan = {$data_perusahaan['id_perusahaan']}"
                    );
                }
               
                if ($update){
                    $this->response(
                        array(
                            "status"    => "success",
                            "result"    => array($data_perusahaan),
                            "message"   => $update
  )
                    );
                }
            }   
        }
    }

    function deletePerusahaan($data_perusahaan){

        if (empty($data_perusahaan['id_perusahaan'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "ID Perusahaan harus diisi"
                )
            );
        } else {
            // Cek apakah ada di database
            $get_perusahaan_baseID =$this->db->query("
                SELECT 1
                FROM perusahaan
                WHERE id_perusahaan = {$data_perusahaan['id_perusahaan']}")->num_rows();

            if($get_perusahaan_baseID > 0){
                
                $get_photo_url =$this->db->query("
                SELECT photo_url
                FROM perusahaan
                WHERE id_perusahaan = {$data_perusahaan['id_perusahaan']}")->result();
            
                if(!empty($get_photo_url)){

                    // Dapatkan nama file
                    $photo_nama_file = basename($get_photo_url[0]->photo_url);
                    // Dapatkan letak file di folder upload
                    $photo_lokasi_file = realpath(FCPATH . $this->folder_upload . $photo_nama_file);
                    
                    // Jika file ada, hapus
                    if(file_exists($photo_lokasi_file)) {
                        // Hapus file
                        unlink($photo_lokasi_file);
                    }
$this->db->query("
                        DELETE FROM perusahaan
                        WHERE id_perusahaan = {$data_perusahaan['id_perusahaan']}");
                    $this->response(
                        array(
                            "status" => "success",
                            "message" => "Data ID = " .$data_perusahaan['id_perusahaan']. " berhasil dihapus"
                        )
                    );
                }
            
            } else {
                $this->response(
                    array(
                        "status" => "failed",
                        "message" => "ID Perusahaan tidak ditemukan"
                    )
                );
            }
        }
    }

    function uploadPhoto() {

        // Apakah user upload gambar?
        if ( isset($_FILES['photo_url']) && $_FILES['photo_url']['size'] > 0 ){

            // Foto disimpan di android-api/uploads
            $config['upload_path'] = realpath(FCPATH . $this->folder_upload);
            $config['allowed_types'] = 'jpg|png|jpeg';

            // Load library upload & helper
            $this->load->library('upload', $config);
            $this->load->helper('url');

            // Apakah file berhasil diupload?
            if ( $this->upload->do_upload('photo_url')) {

               // Berhasil, simpan nama file-nya
               // URL image yang disimpan adalah http://localhost/android-api/uploads/namafile
               $img_data = $this->upload->data();
               $post_image = base_url(). $this->folder_upload .$img_data['file_name'];

            } else {

                // Upload gagal, beri nama image dengan errornya
                // Ini bodoh, tapi efektif
                $post_image = $this->upload->display_errors();
                
            }
        } else {
            // Tidak ada file yang di-upload, kosongkan nama image-nya
            $post_image = '';
        }

        return $post_image;
    }
}

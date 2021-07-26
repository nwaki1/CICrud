<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class MahasiswaApi extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		$this->load->model('Mahasiswa');
		$this->load->library('form_validation');
	}

	function index()
	{
		$data = $this->Mahasiswa->fetch_all();
		echo json_encode($data->result_array());
	}

	function insert()
	{
		$this->form_validation->set_rules('NoInduk', 'No Induk', 'required');
		$this->form_validation->set_rules('Nama', 'Nama', 'required');
		$this->form_validation->set_rules('TahunMasuk', 'Tahun Masuk', 'required');
		if($this->form_validation->run())
		{
			$data = array(
				'NoInduk'	=>	$this->input->post('NoInduk'),
				'Nama'		=>	$this->input->post('Nama'),
				'TahunMasuk'		=>	$this->input->post('TahunMasuk')
			);

			$this->Mahasiswa->insert_api($data);

			$array = array(
				'success'		=>	true
			);
		}
		else
		{
			$array = array(
				'error'					=>	true,
				'NoInduk_error'		=>	form_error('NoInduk'),
				'Nama_error'		=>	form_error('Nama'),
				'TahunMasuk_error'		=>	form_error('TahunMasuk')
			);
		}
		echo json_encode($array);
	}
	
	function fetch_single()
	{
		if($this->input->post('id'))
		{
			$data = $this->Mahasiswa->fetch_single_user($this->input->post('id'));

			foreach($data as $row)
			{
				$output['NoInduk'] = $row['NoInduk'];
				$output['Nama'] = $row['Nama'];
				$output['TahunMasuk'] = $row['TahunMasuk'];
			}
			echo json_encode($output);
		}
	}

	function update()
	{
		$this->form_validation->set_rules('NoInduk', 'No Induk', 'required');
		$this->form_validation->set_rules('Nama', 'Nama', 'required');
		$this->form_validation->set_rules('TahunMasuk', 'Tahun Masuk', 'required');
		if($this->form_validation->run())
		{	
			$data = array(
				'NoInduk'	=>	$this->input->post('NoInduk'),
				'Nama'		=>	$this->input->post('Nama'),
				'TahunMasuk'		=>	$this->input->post('TahunMasuk')
			);

			$this->Mahasiswa->update_api($this->input->post('id'), $data);

			$array = array(
				'success'		=>	true
			);
		}
		else
		{
			$array = array(
				'error'				=>	true,
				'NoInduk_error'		=>	form_error('NoInduk'),
				'Nama_error'		=>	form_error('Nama'),
				'TahunMasuk_error'  =>	form_error('TahunMasuk')
			);
		}
		echo json_encode($array);
	}

	function delete()
	{
		if($this->input->post('id'))
		{
			if($this->Mahasiswa->delete_single_user($this->input->post('id')))
			{
				$array = array(

					'success'	=>	true
				);
			}
			else
			{
				$array = array(
					'error'		=>	true
				);
			}
			echo json_encode($array);
		}
	}

}


?>
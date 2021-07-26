<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class MataKuliahApi extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		$this->load->model('MataKuliah');
		$this->load->library('form_validation');
	}

	function index()
	{
		$data = $this->MataKuliah->fetch_all();
		echo json_encode($data->result_array());
	}

	function insert()
	{
		$this->form_validation->set_rules('KodeMatkul', 'No Induk', 'required');
		$this->form_validation->set_rules('Nama', 'Nama', 'required');
		if($this->form_validation->run())
		{
			$data = array(
				'KodeMatkul'	=>	$this->input->post('KodeMatkul'),
				'Nama'		=>	$this->input->post('Nama')
			);

			$this->MataKuliah->insert_api($data);

			$array = array(
				'success'		=>	true
			);
		}
		else
		{
			$array = array(
				'error'					=>	true,
				'KodeMatkul_error'		=>	form_error('KodeMatkul'),
				'Nama_error'		=>	form_error('Nama')
			);
		}
		echo json_encode($array);
	}
	
	function fetch_single()
	{
		if($this->input->post('id'))
		{
			$data = $this->MataKuliah->fetch_single_user($this->input->post('id'));

			foreach($data as $row)
			{
				$output['KodeMatkul'] = $row['KodeMatkul'];
				$output['Nama'] = $row['Nama'];
			}
			echo json_encode($output);
		}
	}

	function update()
	{
		$this->form_validation->set_rules('KodeMatkul', 'No Induk', 'required');
		$this->form_validation->set_rules('Nama', 'Nama', 'required');
		if($this->form_validation->run())
		{	
			$data = array(
				'KodeMatkul'	=>	$this->input->post('KodeMatkul'),
				'Nama'		=>	$this->input->post('Nama')
			);

			$this->MataKuliah->update_api($this->input->post('id'), $data);

			$array = array(
				'success'		=>	true
			);
		}
		else
		{
			$array = array(
				'error'				=>	true,
				'KodeMatkul_error'		=>	form_error('KodeMatkul'),
				'Nama_error'		=>	form_error('Nama')
			);
		}
		echo json_encode($array);
	}

	function delete()
	{
		if($this->input->post('id'))
		{
			if($this->MataKuliah->delete_single_user($this->input->post('id')))
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
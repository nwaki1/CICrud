<?php
class Mahasiswa extends CI_Model
{
	function fetch_all()
	{
		$this->db->order_by('Id', 'DESC');
		return $this->db->get('mahasiswa');
	}

	function insert_api($data)
	{
		$this->db->insert('mahasiswa', $data);
	}

	function fetch_single_user($user_id)
	{
		$this->db->where('Id', $user_id);
		$query = $this->db->get('mahasiswa');
		return $query->result_array();
	}

	function update_api($user_id, $data)
	{
		$this->db->where('Id', $user_id);
		$this->db->update('mahasiswa', $data);
	}

	function delete_single_user($user_id)
	{
		$this->db->where('Id', $user_id);
		$this->db->delete('mahasiswa');
		if($this->db->affected_rows() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

?>
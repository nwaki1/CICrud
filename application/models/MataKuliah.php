<?php
class MataKuliah extends CI_Model
{
	function fetch_all()
	{
		$this->db->order_by('Id', 'DESC');
		return $this->db->get('mata_kuliah');
	}

	function insert_api($data)
	{
		$this->db->insert('mata_kuliah', $data);
	}

	function fetch_single_user($user_id)
	{
		$this->db->where('Id', $user_id);
		$query = $this->db->get('mata_kuliah');
		return $query->result_array();
	}

	function update_api($user_id, $data)
	{
		$this->db->where('Id', $user_id);
		$this->db->update('mata_kuliah', $data);
	}

	function delete_single_user($user_id)
	{
		$this->db->where('Id', $user_id);
		$this->db->delete('mata_kuliah');
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
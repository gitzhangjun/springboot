package com.pa.jk.map.dao;

import java.util.Map;
import com.pa.jk.map.bean.Hospital;

public interface HospitalDao {

	boolean save(Hospital hospital);

	boolean update(Hospital hospital);

	Hospital loadByHospitalId(long hospitalId);

	Map<Long, Hospital> loadAll();

}

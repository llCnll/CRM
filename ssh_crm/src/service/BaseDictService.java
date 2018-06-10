package service;

import java.util.List;

import domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> getListByTypeCode(String dict_type_code);
	
}

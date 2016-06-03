package com.web.type;

import java.io.Serializable;

public class Type  implements Serializable { 

	
	public int  id ;
	public String  name , xuehao , jiaoshi , addtime , flag  ;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getXuehao() {
		return xuehao;
	}
	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}
	public String getJiaoshi() {
		return jiaoshi;
	}
	public void setJiaoshi(String jiaoshi) {
		this.jiaoshi = jiaoshi;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}  


	
	 
	
	
	
	
	  
}

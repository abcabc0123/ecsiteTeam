package com.internousdev.orion.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.orion.dao.PurchaseHistoryInfoDAO;
import com.internousdev.orion.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware {

	private Map<String,Object>session;
	private PurchaseHistoryInfoDAO purchaseHistoryDAO = new PurchaseHistoryInfoDAO();
	private List<PurchaseHistoryInfoDTO> historyList = new ArrayList<PurchaseHistoryInfoDTO>();

	public String execute(){
//セッションタイムアウト
		if(session.isEmpty()){
			return "sessionTimeout";
		}

		historyList = purchaseHistoryDAO.getHistoryInfo(session.get("loginUserId").toString());
		return SUCCESS;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

	public List<PurchaseHistoryInfoDTO> getHistoryList(){
		return historyList;
	}

	public void setHistoryList(List<PurchaseHistoryInfoDTO> historyList){
		this.historyList = historyList;
	}
}

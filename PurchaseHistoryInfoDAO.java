package com.internousdev.orion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.orion.dto.PurchaseHistoryInfoDTO;
import com.internousdev.orion.util.DBConnector;

public class PurchaseHistoryInfoDAO {

//購入履歴を取得
	public List<PurchaseHistoryInfoDTO> getHistoryInfo(String userId){

		DBConnector db = new DBConnector();
	    Connection con = db.getConnection();
		List<PurchaseHistoryInfoDTO> historyDTO = new ArrayList<PurchaseHistoryInfoDTO>();
		String sql = "SELECT"
				+ " pi.product_name, pi.product_name_kana, pi.image_file_path, pi.image_file_name, pi.release_date, pi.release_company,"
				+ " phi.product_count, phi.price, phi.product_count * phi.price as totalPrice," //個数×金額を取得。名前は "totalPrice"
				+ " di.family_name, di.first_name, di.user_address"
				+ " FROM purchase_history_info  phi LEFT JOIN product_info pi"
				+ " ON phi.product_id = pi.product_id"
				+ " LEFT JOIN destination_info di ON phi.destination_id = di.id"
				+ " WHERE  phi.user_id=? ORDER BY phi.regist_date DESC ";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){

              // 取得した情報をListに格納
				PurchaseHistoryInfoDTO dto = new PurchaseHistoryInfoDTO();
				dto.setProductName(rs.getString("product_name"));
				dto.setNameKana(rs.getString("product_name_kana"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setProductCount(rs.getInt("product_count"));
				dto.setPrice(rs.getInt("price"));
				dto.setTotalPrice(rs.getInt("totalPrice"));
				dto.setFamilyName(rs.getString("family_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setUserAddress(rs.getString("user_address"));
				historyDTO.add(dto);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		}
		return historyDTO;
	}

//購入履歴を削除
	public int deleteHistory(String userId){

		DBConnector db = new DBConnector();
	    Connection con = db.getConnection();
		int result = 0;
		String sql = "delete from purchase_history_info where user_id=?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			result = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
			con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}

//購入履歴を登録
	public int regist(String userId, int productId, int productCount, int destinationId,int price){

		DBConnector db = new DBConnector();
	    Connection con = db.getConnection();
		int result = 0;
		String sql = "insert into purchase_history_info(user_id,product_id,product_count,destination_id,price,regist_date,update_date) values(?,?,?,?,?,now(),now())";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			ps.setInt(4, destinationId);
			ps.setInt(5, price);
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}
}

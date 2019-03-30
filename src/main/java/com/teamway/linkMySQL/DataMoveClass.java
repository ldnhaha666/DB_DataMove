package com.teamway.linkMySQL;

import com.shenzhen.teamway.DataBaseAInfo;
import com.sun.javaws.Main;
import org.apache.ibatis.io.Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

public class DataMoveClass {

	/**
	 * DataBaseAInfo类对应表camerainfo
	 * DataBaseBInfo类对应表cms_res_camera_info
	 * getDataA方法getDataA(dataIP："源数据库IP地址"，userName：“源数据库用户名”，passWord：“源数据库密码”)
	 * getDataB方法getDataB(LinkedList<DataBaseAInfo>:"从源数据库获取的数据List链表"，dataIP："目标数据库IP地址"，userName：“目标数据库用户名”，passWord：“目标数据库密码”)
	 */

	public LinkedList<DataBaseAInfo> getDataA() throws SQLException, ClassNotFoundException, IOException {

		//String url = "jdbc:mysql://"+dataIP+":3306/teamway_a?useUnicode=true&characterEncoding=utf8";

		InputStream logicInfo = this.getClass().getResourceAsStream("/DataBaseLogicInfo.properties");
		Properties properties = new Properties();
		properties.load(logicInfo);
		String url = properties.getProperty("sourceURL");
		String userName = properties.getProperty("sourceUserName");
		String passWord = properties.getProperty("sourcePassword");


		Class.forName(properties.getProperty("driver")); //classLoader,加载对应驱动
		Connection conn = DriverManager.getConnection(url, userName, passWord);
		String sql = "select IP,Port,UserName,Passwd,CamSeq from camerainfo";
		PreparedStatement pstmt = conn.prepareStatement(sql);


		LinkedList<DataBaseAInfo> DataBaseAList = new LinkedList<DataBaseAInfo>();

		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection Error");
		}
		try {
			while (rs.next()) {
				DataBaseAInfo DataA = new DataBaseAInfo();
				DataA.setIP(rs.getString(1));
				DataA.setPort(rs.getShort(2));
				DataA.setUserName(rs.getString(3));
				DataA.setPasswd(rs.getString(4));
				DataA.setCamSeq(rs.getInt(5));
				DataBaseAList.addLast(DataA);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("GetData Error");
		} finally {
			conn.close();
		}

		System.out.println("CopyData success");

		return DataBaseAList;
	}


	public void setDataB(LinkedList<DataBaseAInfo> DataList) throws SQLException, ClassNotFoundException, IOException {

		InputStream logicInfo = this.getClass().getResourceAsStream("/DataBaseLogicInfo.properties");
		Properties properties = new Properties();
		properties.load(logicInfo);
		String url = properties.getProperty("targetURL");
		String userName = properties.getProperty("targetUserName");
		String passWord = properties.getProperty("targetPassword");

		Class.forName(properties.getProperty("driver"));
		Connection conn = DriverManager.getConnection(url, userName, passWord);

		PreparedStatement pstmt;

		//String sql = "update cms_res_camera_info set(IP,Port,UserName,Password) = (?,?,?,?) Where ipc_channel = (?)";
		String sql = "update cms_res_camera_info set IP=(?),Port=(?),UserName=(?),Password=(?) Where ipc_channel = (?)";

		int temp = DataList.size();
		System.out.println("查询到的数据 的大小:" + temp);

		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		try {
			for (int i = 0; i < temp; i++) {
				pstmt.setString(1, DataList.getFirst().getIP());
				pstmt.setInt(2, DataList.getFirst().getPort());
				pstmt.setString(3, DataList.getFirst().getUserName());
				pstmt.setString(4, DataList.getFirst().getPasswd());
				pstmt.setInt(5, DataList.getFirst().getCamSeq());
				int i1 = pstmt.executeUpdate();
				System.out.println("更新成功了的条数为：" + i1);
				DataList.poll();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("INSERT Error");
		}
		System.out.println("INSERT Success");
		pstmt.close();
		conn.close();

	}
}

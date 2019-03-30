package com.teamway.test;

import com.shenzhen.teamway.DataBaseAInfo;
import com.teamway.linkMySQL.DataMoveClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class TestMain {

    public static void main(String args[]) throws IOException {

        DataMoveClass test_1 = new DataMoveClass();
        //LinkedList<DataBaseAInfo> prl = new LinkedList<DataBaseAInfo>();

        try {
	        LinkedList<DataBaseAInfo> dataAs = test_1.getDataA();
	        for (DataBaseAInfo dataA : dataAs) {
		        System.out.println("查询到数据为:"+dataA.toString());
	        }
	        test_1.setDataB(dataAs);
            //prl = test_1.getDataA();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

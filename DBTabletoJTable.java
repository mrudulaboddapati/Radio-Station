package com.playlist.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DBTabletoJTable extends BaseDao {

	public void showdatainTable(DefaultTableModel tableModel, String query) {
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);

			ResultSetMetaData metaData = rs.getMetaData();
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(metaData.getColumnName(i));
			}
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= columnCount; i++) {
//					if (i == 1)
//						continue;

					vector.add(rs.getObject(i));
				}
				data.add(vector);
			}

			tableModel.setDataVector(data, columnNames);
			rs.close();
			statement.close();

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

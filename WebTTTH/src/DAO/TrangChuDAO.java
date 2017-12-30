package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.course;
import Bean.CourseInfo;
import Bean.LichKhaiGiang;
import DBConnection.DBConnection;

public class TrangChuDAO {
	
public static List<course> LoadKhoaHoc(Connection conn){
		
		List<course> list= new ArrayList<course>();
		
		String sql= "SELECT * from course";
		
		try {
			PreparedStatement ptmt= conn.prepareStatement(sql);
			
			ResultSet rs= ptmt.executeQuery();
			
			while(rs.next()){
				
				String coursename=rs.getString("coursename");
				String courseimage=rs.getString("courseimage");
				int course_id=rs.getInt("course_id");
				course course= new course();
				course.setCourse_id(course_id);
				course.setCoursename(coursename);
				course.setCourseimage(courseimage);
				
				
				list.add(course);
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return list;
	}

	public course getCourse(int course_id) {
		Connection con = DBConnection.CreateConnection();
		String sql = "select * from course where course_id='" + course_id + "'";
		course course = new course();
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String coursename=rs.getString("coursename");
				
				course = new course(coursename);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}
	public CourseInfo getCourseInfo(int course_id) {
		Connection con = DBConnection.CreateConnection();
		String sql = "select * from course where course_id='" + course_id + "'";
		CourseInfo course = new CourseInfo();
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			
				String info=rs.getString("info");
				
				course = new CourseInfo(info);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}
	public LichKhaiGiang getClass(int course_id) {
		Connection con = DBConnection.CreateConnection();
		String sql = "select * from class where course_id='" + course_id + "'";
		LichKhaiGiang lkg = new LichKhaiGiang();
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int class_id=rs.getInt("class_id");
				String classname=rs.getString("classname");
				String timestudy=rs.getString("timestudy");
				String startday=rs.getString("startday");
				String location=rs.getString("location");
				lkg = new LichKhaiGiang(class_id,classname,timestudy,startday,location);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lkg;
	}
	public static List<LichKhaiGiang> LoadClass(int course_id){
		
		List<LichKhaiGiang> list= new ArrayList<LichKhaiGiang>();
		
		Connection con = DBConnection.CreateConnection();
		String sql = "select * from class where course_id='" + course_id + "'";

		
		try {
			PreparedStatement ptmt= con.prepareStatement(sql);
			
			ResultSet rs= ptmt.executeQuery();
			
			while(rs.next()){
				int class_id=rs.getInt("class_id");
				String classname=rs.getString("classname");
				String timestudy=rs.getString("timestudy");
				String startday=rs.getString("startday");
				String location=rs.getString("location");
				
				LichKhaiGiang lkg= new LichKhaiGiang();
				
				lkg.setClass_id(class_id);
				lkg.setClassname(classname);
				lkg.setTimestudy(timestudy);
				lkg.setStartday(startday);
				lkg.setLocation(location);
				
				
				
				list.add(lkg);
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return list;
	}

}

package laptrinhjavaweb.dao.impl;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import laptrinhjavaweb.dao.ICourseDAO;
import laptrinhjavaweb.mapper.CourseMapper;
import laptrinhjavaweb.model.CourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAO implements ICourseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CourseModel> findAll() {
        String sql = "SELECT course.*, categorycourse.name FROM course join categorycourse on course.category_id = categorycourse.id";

        return jdbcTemplate.query(sql, new CourseMapper());
    }

//    @Override
//    public List<CourseModel> findAll() {
//        List<CourseModel> results = new ArrayList<>();
//        StringBuilder sql = new StringBuilder("SELECT * FROM course");
//
//        Connection con = getConnection();
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//
//        if (con != null) {
//            try {
//                statement = con.prepareStatement(sql.toString());
//
//                rs = statement.executeQuery();
//                while (rs.next()) {
//                    //Đẩy dl vào đối tượng NewModel
//                    CourseModel courses = new CourseModel();
//                    courses.setId(rs.getLong("id"));
//                    courses.setFullname(rs.getString("fullname"));
//                    courses.setShortname(rs.getString("shortname"));
//                    courses.setSummary(rs.getString("summary"));
//                    courses.setStartDate(rs.getTimestamp("startdate"));
//                    courses.setEndDate(rs.getTimestamp("enddate"));
//
//                    results.add(courses);
//                }
//                return results;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    @Override
    public Long insert(CourseModel courseModel) {
        String sql = "INSERT INTO course (category_id, fullname, shortname, summary, created_date, modified_date, created_by, modified_by, lms_course_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, courseModel.getCategoryCourseId());
                ps.setString(2, courseModel.getFullName());
                ps.setString(3, courseModel.getShortName());
                ps.setString(4, courseModel.getSummary());
                ps.setTimestamp(5, courseModel.getCreatedDate());
                ps.setTimestamp(6, courseModel.getModifiedDate());
                ps.setString(7, courseModel.getCreatedBy());
                ps.setString(8, courseModel.getModifiedBy());
                ps.setLong(9, courseModel.getLmsCourseId());
                return ps;
            }
        }, keyHolder);

        // Lấy ID vừa được tạo ra từ KeyHolder
        // keyHolder.getKey() có thể trả về null nếu không có key nào được sinh ra
        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().longValue();
        }
        return null;
    }

//    @Override
//    public Long insert(CourseModel courseModel) {
//        Long id=null;
//        String sql="insert into course (fullname, shortname, summary, startdate, enddate) values(?, ?, ?, ?, ?)";
//        Connection con = getConnection();
//        PreparedStatement statement = null;
//        ResultSet rs=null;
//
//        try {
//            con.setAutoCommit(false);
//
//            //Gán tham số vào PreparedStatement để insert
//            statement=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, courseModel.getFullname());
//            statement.setString(2, courseModel.getShortname());
//            statement.setString(3, courseModel.getSummary());
//            statement.setTimestamp(4, courseModel.getStartDate());
//            statement.setTimestamp(5, courseModel.getEndDate());
//
//            statement.executeUpdate();
//            rs=statement.getGeneratedKeys();
//            if(rs.next()) {
//                id=rs.getLong(1);
//            }
//            con.commit();
//            return id;
//        }catch(SQLException e) {
//            if(con!=null) {
//                try {
//                    con.rollback();
//                }catch(SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//            return null;
//        }
//    }

    @Override
    public void update(CourseModel updateCourse) {
        String sql = "UPDATE course SET category_id = ?, fullname = ?, shortname = ?, summary = ?, created_date = ?, modified_date = ?, created_by = ?, modified_by = ?, lms_course_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, updateCourse.getCategoryCourseId(), updateCourse.getFullName(), updateCourse.getShortName(),
                updateCourse.getSummary(), updateCourse.getCreatedDate(), updateCourse.getModifiedDate(), updateCourse.getCreatedBy(),
                updateCourse.getModifiedBy(), updateCourse.getLmsCourseId(), updateCourse.getId());
    }

//    @Override
//    public void update(CourseModel updateCourse) {
//        String sql = "UPDATE course SET fullname = ?, shortname = ?, summary = ?, startdate = ?, enddate = ? WHERE id = ?";
//        Connection con = getConnection();
//        PreparedStatement statement = null;
//
//        try {
//            con.setAutoCommit(false);
//            statement = con.prepareStatement(sql);
//            statement.setString(1, updateCourse.getFullname());
//            statement.setString(2, updateCourse.getShortname());
//            statement.setString(3, updateCourse.getSummary());
//            statement.setTimestamp(4, updateCourse.getStartDate());
//            statement.setTimestamp(5, updateCourse.getEndDate());
//            statement.setLong(6, updateCourse.getId()); // ID để xác định bản ghi cần cập nhật
//
//            statement.executeUpdate();
//            con.commit();
//        } catch(SQLException e) {
//            if(con!=null) {
//                try {
//                    con.rollback();
//                }catch(SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//
//    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM course WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


//    public void delete(long id) {
//        String sql = "DELETE FROM course WHERE id = ?";
//        Connection con = getConnection();
//        PreparedStatement statement = null;
//
//        try {
//            con.setAutoCommit(false);
//            statement = con.prepareStatement(sql);
//            statement.setLong(1, id); // Truyền id để xóa bản ghi tương ứng
//
//            statement.executeUpdate();
//            con.commit();
//        }catch(SQLException e) {
//            if(con!=null) {
//                try {
//                    con.rollback();
//                }catch(SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public CourseModel findOne(Long id) {
        String sql = "SELECT course.*, categorycourse.* FROM course join categorycourse on course.category_id = categorycourse.id WHERE course.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CourseMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

//    @Override
//    public CourseModel findOne(Long id) {
//        String sql = "SELECT * FROM course WHERE id = ?";
//        Connection con = getConnection();
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//
//        try {
//            statement = con.prepareStatement(sql);
//            statement.setLong(1, id);
//            rs = statement.executeQuery();
//
//            if (rs.next()) {
//                CourseModel courses = new CourseModel();
//                courses.setId(rs.getLong("id"));
//                courses.setFullname(rs.getString("fullname"));
//                courses.setShortname(rs.getString("shortname"));
//                courses.setSummary(rs.getString("summary"));
//                courses.setStartDate(rs.getTimestamp("startdate"));
//                courses.setEndDate(rs.getTimestamp("enddate"));
////                news.setShortDescription(rs.getString("shortdescription"));
////                news.setCreatedDate(rs.getTimestamp("createddate"));
////                news.setCreatedBy(rs.getString("createdby"));
//                return courses;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}

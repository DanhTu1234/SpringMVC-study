package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.LOVersionModel;
import laptrinhjavaweb.model.LearningObjectModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LearningObjectMapper implements RowMapper<LearningObjectModel> {

    @Override
    public LearningObjectModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        LearningObjectModel learningObjectModel = new LearningObjectModel();
        learningObjectModel.setId(rs.getInt("id"));
        learningObjectModel.setTitle(rs.getString("title"));
        learningObjectModel.setDescription(rs.getString("description"));
        learningObjectModel.setCreatedAt(rs.getTimestamp("created_at"));
        learningObjectModel.setUpdatedAt(rs.getTimestamp("updated_at"));
        try{
            LOVersionModel version = new LOVersionModel();
            version.setVersionNumber(rs.getString("version_number"));
            learningObjectModel.setLoVersion(version);
        }catch (Exception e){
            e.printStackTrace();
        }
        return learningObjectModel;
    }
}

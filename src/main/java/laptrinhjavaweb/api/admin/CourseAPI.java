package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.client.SyncService;
import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICourseService;
import laptrinhjavaweb.service.impl.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "courseAPIOfAdmin")
public class CourseAPI {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private SyncService syncService;

    // API lấy bài viết theo id (GET)
    @GetMapping("/api/course/{id}")
    public CourseModel getCourseById(@PathVariable Long id) {
        return courseService.findOne(id);
    }

    @GetMapping("/api/course")
    public List<CourseModel> getAllCourse() {
        return courseService.findAll();
    }

//    @PostMapping("/api/course")
//    public CourseModel createCourse(@RequestBody CourseModel courseModel) {
//        return courseService.insert(courseModel);
//    }

//    @PostMapping("/api/course")
//    public CourseModel createCourse(@RequestBody CourseModel courseModel){
//        CourseModel saved = courseService.insert(courseModel);
//        syncService.pushToWebB(saved);
//        return saved;
//    }

    @PostMapping("/api/course")
    public ResponseEntity<CourseModel> createCourse(@RequestBody CourseModel courseModel) {

        // Gọi sang LMS và nhận về kết quả
        CourseModel responseFromLMS = syncService.pushLMS(courseModel);

        // Kiểm tra kết quả trả về
        if (responseFromLMS != null) {
            // Nếu thành công, lấy ID từ LMS và gán vào đối tượng của LCMS
            courseModel.setLmsCourseId(responseFromLMS.getId());
        } else {
            // Nếu thất bại trả về lỗi
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Lưu vào CSDL của LCMS
        CourseModel saveLCMS = courseService.insert(courseModel);

        // Trả về đối tượng đã lưu và status hiển thị trong postman
        return new ResponseEntity<>(saveLCMS, HttpStatus.OK);
    }

    @PutMapping("/api/course/{id}")
    public ResponseEntity<CourseModel> updateCourse(@PathVariable Long id, @RequestBody CourseModel courseModel) {
        // Gán ID từ URL vào courseModel
        courseModel.setId(id);

        CourseModel oldCourse = courseService.findOne(id);
        // Gán lms_course_id vào đối tượng mới để gửi sang LMS
        courseModel.setLmsCourseId(oldCourse.getLmsCourseId());

        syncService.updateLMS(courseModel);

        // Khi LMS cập nhật thành công, mới cập nhật CSDL của LCMS
        CourseModel updatedCourse = courseService.update(courseModel);

        // Trả về đối tượng đã cập nhật thành công
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/api/course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {

        CourseModel oldCourse = courseService.findOne(id);

        syncService.deleteLMS(oldCourse);

        // Khi LMS cập nhật thành công, mới cập nhật CSDL của LCMS
        courseService.delete(id);
        return ResponseEntity.ok("Course deleted succes!");
    }


//    @PostMapping("/api/course")
//    public ResponseEntity<CourseModel> createCourse(@RequestBody CourseModel courseModel){
//        // Bước 1: Gọi Moodle API để tạo khóa học bên đó trước
//        Long moodleId = syncService.pushToMoodle(courseModel);
//
//        // Bước 2: Kiểm tra kết quả từ Moodle
//        if (moodleId != null) {
//            // Nếu thành công, gán Moodle ID vào model của bạn
//            courseModel.setMoodleCourseId(moodleId);
//        } else {
//            // Nếu thất bại, trả về lỗi cho client
//            System.err.println("Không thể đồng bộ khóa học với Moodle. Hủy thao tác.");
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        // Bước 3: Chỉ khi Moodle tạo thành công, mới lưu khóa học vào CSDL của LCMS
//        CourseModel savedCourse = courseService.insert(courseModel);
//
//        // Trả về đối tượng đã lưu thành công
//        return new ResponseEntity<>(savedCourse, HttpStatus.OK);
//    }


//    @PutMapping("/api/course")
//    public CourseModel updateCourse(@RequestBody CourseModel updateCourse){
//        return courseService.update(updateCourse);
//    }
//
//    @DeleteMapping("/api/course/{id}")
//    public void deleteCourse(@PathVariable long id){
//        courseService.delete(id);
//    }

}

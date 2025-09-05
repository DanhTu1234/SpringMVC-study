package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "classCourseAPIOfAdmin")
public class CourseAPI {
    @Autowired
    private ICourseService courseService;

    @PostMapping("/api/course")
    public ResponseEntity<CourseModel> createCourse(@RequestBody CourseModel courseModel) {
        CourseModel saveCourse = courseService.createSyncCourse(courseModel);

        return new ResponseEntity<>(saveCourse, HttpStatus.CREATED);
    }

    @PutMapping("/api/course/{id}")
    public ResponseEntity<CourseModel> updateCourse(@RequestBody CourseModel courseModel, @PathVariable Long id) {
        // Gán ID từ URL vào class_offeringModel
        courseModel.setId(id);

        // Gọi một phương thức duy nhất từ service để xử lý toàn bộ logic
        CourseModel updatedCourse = courseService.updateSyncCourse(courseModel);

        // Trả về đối tượng đã cập nhật thành công
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/api/course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteSyncCourse(id);
        return ResponseEntity.ok().build();
    }

}

package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.model.CourseSectionModel;
import laptrinhjavaweb.service.impl.CourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "classCourseSectionAPIOfAdmin")
public class CourseSectionAPI {
    @Autowired
    private CourseSectionService courseSectionService;

    @PostMapping("/api/section")
    public ResponseEntity<CourseSectionModel> createCourseSection(@RequestBody CourseSectionModel courseSectionModel) {
        CourseSectionModel saveCourseSection = courseSectionService.createSyncCourseSection(courseSectionModel);

        return new ResponseEntity<>(saveCourseSection, HttpStatus.CREATED);
    }

    @PutMapping("/api/section/{id}")
    public ResponseEntity<CourseSectionModel> updateCourseSection(@RequestBody CourseSectionModel courseSectionModel, @PathVariable Long id) {
        courseSectionModel.setId(id);

        CourseSectionModel updateCourseSection = courseSectionService.updateSyncCourseSection(courseSectionModel);

        return new ResponseEntity<>(updateCourseSection, HttpStatus.OK);
    }

    @DeleteMapping("/api/section/{id}")
    public ResponseEntity<String> deleteCourseSection(@PathVariable Long id) {
        courseSectionService.deleteSyncCourseSection(id);
        return ResponseEntity.ok().build();
    }
}

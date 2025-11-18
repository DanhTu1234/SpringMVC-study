package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.service.IUserService;
import laptrinhjavaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "classUserAPIOfAdmin")
@CrossOrigin("http://127.0.0.1:5500")
public class UserAPI{
    @Autowired
    private IUserService userService;

    @PostMapping("/api/sync/users")
    public ResponseEntity<String> syncAllUsers() {
        userService.syncAllUserFromMoodle();
        return ResponseEntity.ok("Đồng bộ tất cả user thành công!");
    }
}

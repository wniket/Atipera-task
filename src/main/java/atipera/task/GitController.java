package atipera.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repositories")
public class GitController {
    private final GitService gitService;
    public GitController (GitService gitService){
        this.gitService=gitService;
    }
    @GetMapping("/{username}")
    public ResponseEntity<?> getRep(@PathVariable String username) {
        try {
            return ResponseEntity.ok(
                    gitService.getUsers(username)
            );
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body(Map.of(
                            "status", 404,
                            "message", "User not found"
                    ));
        }
    }
}

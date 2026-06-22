package atipera.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitService {
    private final GitClient gitClient;
    public GitService(GitClient gitClient)
    {
        this.gitClient=gitClient;
    }
    public List<DataRepository> getUsers(String username){
        List<DataRepository> repos = gitClient.getRepository(username);
        if (repos == null) {
            throw new RuntimeException("User not found");
        }
        return gitClient.getRepository(username)
                 .stream()
                 .filter(n->!n.fork())
                 .toList();
    }
    public List<RepositoryDto> getBranch(String username){
        return gitClient.getRepository(username)
                .stream()
                .filter(r->!r.fork())
                .map(r->{
                    List<BranchDto> branchDtos = gitClient.getBranch(
                            r.owner().login(),r.name()
                    ).stream()
                            .map(br->new BranchDto(br.name(),
                                    br.commit().sha()
                            )).toList();
                    return new RepositoryDto(r.name(),r.owner().login(),branchDtos);
                })
                .toList();
    }
}

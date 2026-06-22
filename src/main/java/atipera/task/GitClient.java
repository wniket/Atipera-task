package atipera.task;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class GitClient {
    private final RestClient restClient = RestClient.create();

    public List<DataRepository> getRepository(String username){
        return restClient.get()
                .uri("https://api.github.com/users/" + username + "/repos")
                .retrieve()
                .body(new ParameterizedTypeReference<List<DataRepository>>() {
                });
    }
    public List<DataBranch> getBranch(String owner, String repo){
        return restClient.get()
                .uri("https://api.github.com/repos/" + owner + "/" + repo + "/branches")
                .retrieve()
                .body(new ParameterizedTypeReference<List<DataBranch>>() {});
    }

}
record DataRepository(String name, Owner owner , boolean fork){}
record Owner(String login){}
record DataBranch(String name,Commit commit){}
record Commit(String sha){}
record BranchDto(String name, String lastCommitSha) {}
record RepositoryDto(String repositoryName, String ownerLogin, List<BranchDto> branches){}
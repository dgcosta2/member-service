package c322.edu.iu.finalproject.memberservice.controller;

import c322.edu.iu.finalproject.memberservice.model.Member;
import c322.edu.iu.finalproject.memberservice.model.Title;
import c322.edu.iu.finalproject.memberservice.repository.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.*;
import java.util.Optional;

@RestController
@RequestMapping
public class MemberController {
    private MemberRepository repository;


    private final WebClient title;

    public MemberController(MemberRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.title = webClientBuilder.baseUrl("http://localhost:8070").build();
    }

    private List<Title> reservedTitles(Member member, Title title) {
        List<Title> reservedTitles = new ArrayList<>();
        reservedTitles.add(title);

        return reservedTitles;
    }

    @GetMapping
    public List<Member> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public int create(@Valid @RequestBody Member member) {
        Member newMember = repository.save(member);
        return newMember.getId();
    }
    @GetMapping("/{id}")
    public Member findById(@PathVariable int id) {
        Optional<Member> member = repository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new NoSuchElementException("Member not found with ID " + id);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Member member, @PathVariable int id) {
        member.setId(id);
        Optional<Member> m = repository.findById(id);
        if (m.isPresent()) {
            repository.save(member);
        }
        else throw new IllegalStateException("Member is not in the database.");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Member member = new Member();
        member.setId(id);
        repository.delete(member);
    }

}

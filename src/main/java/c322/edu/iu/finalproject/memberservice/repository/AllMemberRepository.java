package c322.edu.iu.finalproject.memberservice.repository;

import c322.edu.iu.finalproject.memberservice.model.Member;
import java.util.*;

public class AllMemberRepository {
    private List<Member> members = new ArrayList<>();

    public List<Member> findAll() {
        return members;
    }

    public int create(Member member) {
        int id = members.size() + 1;
        member.setId(id);
        members.add(member);
        return id;
    }

    public void update(Member member, int id) {
        Member x = getMemberById(id);
        if (x != null) {
            x.setName(member.getName());
            x.setEmail(member.getEmail());
        } else {
            throw new IllegalStateException("Member id is not valid.");
        }
    }

    public void delete(int id) {
        Member x = members.get(id);
        if (x != null) {
            members.remove(x);
        } else {
            throw new IllegalStateException("This member id is not valid.");
        }
    }

    public Member getMemberById(int id) {
        return members.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }
}

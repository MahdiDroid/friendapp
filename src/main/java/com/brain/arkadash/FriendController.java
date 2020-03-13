package com.brain.arkadash;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FriendController {

    private  FriendRepository friendRepository;

    @Autowired
    public FriendController(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @GetMapping("/friend")
    public Iterable<Friend> getMyFriend(){
        return friendRepository.findAll();
    }

    @PostMapping("/friend")
    public Friend createFriend(@RequestBody Friend friend){
        return friendRepository.save(friend);
    }
    @DeleteMapping("/friend/{id}")
    public void createFriend(@PathVariable Integer id){

         friendRepository.deleteById(id);
    }

    @PutMapping("/friend/{id}")
    public Optional<Friend> updateFriend(@RequestBody Friend newFriend ,@PathVariable Integer id){
        return friendRepository.findById(id)
                .map(f ->{
                    f.setFirstName(newFriend.getFirstName());
                    f.setLastName(newFriend.getLastName());
                    return friendRepository.save(f);
                        }
                    );

    }

}

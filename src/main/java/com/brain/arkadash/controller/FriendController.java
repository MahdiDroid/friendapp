package com.brain.arkadash.controller;



import com.brain.arkadash.repositories.AddressRepository;
import com.brain.arkadash.repositories.FriendRepository;
import com.brain.arkadash.viewmodels.FriendViewModel;
import com.brain.arkadash.domain.Address;
import com.brain.arkadash.domain.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FriendController {

    private FriendRepository friendRepository;
    private AddressRepository addressRepository;

    @Autowired
    public FriendController(FriendRepository friendRepository, AddressRepository addressRepository) {
        this.friendRepository = friendRepository;
        this.addressRepository = addressRepository;
    }


    @GetMapping("/friend")
    
    public Iterable<Friend> getFriends(){
        return friendRepository.findAll();
    }
    @GetMapping("/friend/{id}")
    public Optional<Friend> getOneFriend(@PathVariable Integer id ){
        return friendRepository.findById(id);
    }

    @PostMapping("/friend")
    public Friend createFriend(@Valid  @RequestBody FriendViewModel friendViewModel){

       Optional<Address> address =  addressRepository.findById(friendViewModel.getAddressId());
       Friend newFriend = new Friend();
        newFriend.setFirstName(friendViewModel.getFirstName());
        newFriend.setLastName(friendViewModel.getLastName());
        newFriend.setAddress(address.get());
        return friendRepository.save(newFriend);

    }
    @DeleteMapping("/friend/{id}")
    public void deleteFriend(@PathVariable Integer id){

         friendRepository.deleteById(id);
    }

    @PutMapping("/friend/{id}")

    public Optional<Friend> updateFriend(@RequestBody Friend newFriend ,@PathVariable Integer id){
        return friendRepository.findById(id)
                .map(f ->{
                    f.setFirstName(newFriend.getFirstName());
                    f.setLastName(newFriend.getLastName());
                    f.setAddress( newFriend.getAddress());
                    return friendRepository.save(f);
                        }
                    );

    }

}

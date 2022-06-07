package com.example.demo.security;


import com.example.demo.basicModels.player.Player;
import com.example.demo.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerDetailsService implements UserDetailsService {

    @Autowired
    PlayerRepo playerRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User.UserBuilder builder = null;

        Optional<Player> playerToFind = playerRepo.findByUsername(username);

        if (playerToFind.isPresent()) {
            Player foundPlayer = playerToFind.get();

            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(foundPlayer.getPassword());
            builder.roles(foundPlayer.getRole());

        } else {
            throw new UsernameNotFoundException("Player not found");
        }
        return builder.build();
    }
}

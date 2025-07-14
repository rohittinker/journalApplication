package com.rohitEnterprises.firstSuccessfullAPI.repository;

import com.rohitEnterprises.firstSuccessfullAPI.entity.User;
import com.rohitEnterprises.firstSuccessfullAPI.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserByUsername() {
        // Arrange
        User mockUser = User.builder()
                .userName("ram")
                .password("rinky")
                .roles(new ArrayList<>())
                .build();

        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        // Act
        UserDetails user = userDetailsService.loadUserByUsername("ram");

        // Assert
        assertNotNull(user);
        assertEquals("ram", user.getUsername());
        assertEquals("rinky", user.getPassword());
    }
}
